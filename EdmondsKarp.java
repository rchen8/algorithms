import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarp {

	private static int vertices = 7;
	private static int[][] capacity, residual;
	private static int[] parent;
	private static HashMap<Integer, ArrayList<Integer>> neighbors;

	private static int edmondsKarp(int source, int sink) {
		int maxFlow = 0; // initial flow is 0
		residual = new int[vertices][vertices]; // residual capacity from u to v

		while (true) {
			int flow = bfs(source, sink);
			if (flow == 0)
				break;

			maxFlow += flow;
			int v = sink;

			// backtrack search and write flow
			while (v != source) {
				int u = parent[v];
				residual[u][v] += flow;
				residual[v][u] -= flow;
				v = u;
			}
		}

		return maxFlow;
	}

	private static int bfs(int source, int sink) {
		parent = new int[vertices];
		Arrays.fill(parent, -1);

		parent[source] = -2; // make sure source is not rediscovered
		int[] flow = new int[vertices]; // capacity of found path to node
		flow[source] = Integer.MAX_VALUE;

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(source);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v : neighbors.get(u)) {
				// if there is available capacity and v is not seen before in
				// search
				if (capacity[u][v] - residual[u][v] > 0 && parent[v] == -1) {
					parent[v] = u;
					flow[v] = Math
							.min(flow[u], capacity[u][v] - residual[u][v]);

					if (v != sink)
						queue.offer(v);
					else
						return flow[sink];
				}
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		int[][] data = { { 0, 1, 3 }, { 0, 3, 3 }, { 1, 2, 4 }, { 2, 0, 3 },
				{ 2, 3, 1 }, { 2, 4, 2 }, { 3, 4, 2 }, { 3, 5, 6 },
				{ 4, 1, 1 }, { 4, 6, 1 }, { 5, 6, 9 } };

		capacity = new int[vertices][vertices];
		neighbors = new HashMap<>();
		for (int i = 0; i < data.length; i++) {
			capacity[data[i][0]][data[i][1]] = data[i][2];

			ArrayList<Integer> list = neighbors.get(data[i][0]);
			if (list == null)
				list = new ArrayList<>();
			list.add(data[i][1]);
			neighbors.put(data[i][0], list);
		}

		int source = 0;
		int sink = vertices - 1;

		System.out.println(edmondsKarp(source, sink));
		for (int[] i : residual)
			System.out.println(Arrays.toString(i));
	}

}
