import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FordFulkerson {

	private static int vertices = 7;
	private static int[][] capacity;
	private static HashMap<Integer, HashSet<Integer>> neighbors;

	private static int fordFulkerson(int source, int sink) {
		if (source == sink)
			return Integer.MAX_VALUE;

		int[] prev = new int[vertices];
		int[] flow = new int[vertices];
		boolean[] visited = new boolean[vertices];

		int totalFlow = 0;
		while (true) {
			// find path with highest capacity from source to sink
			// uses a modified Djikstra's algorithm
			Arrays.fill(prev, -1);
			Arrays.fill(flow, 0);
			Arrays.fill(visited, false);
			flow[source] = Integer.MAX_VALUE;

			int maxLoc = -1;
			while (true) {
				int maxFlow = 0;
				maxLoc = -1;

				// find the unvisited node with the highest capacity to it
				for (int i = 0; i < vertices; i++) {
					if (flow[i] > maxFlow && !visited[i]) {
						maxFlow = flow[i];
						maxLoc = i;
					}
				}

				if (maxLoc == -1 || maxLoc == sink)
					break;
				visited[maxLoc] = true;

				// update its neighbors
				for (int i : neighbors.get(maxLoc)) {
					if (flow[i] < Math.min(maxFlow, capacity[maxLoc][i])) {
						prev[i] = maxLoc;
						flow[i] = Math.min(maxFlow, capacity[maxLoc][i]);
					}
				}

			}

			if (maxLoc == -1) // no path
				break;
			int pathCapacity = flow[sink];
			totalFlow += pathCapacity;

			// add that flow to the network, update capacity appropriately
			int current = sink;
			while (current != source) {
				int next = prev[current];
				capacity[next][current] -= pathCapacity; // forward arc
				capacity[current][next] += pathCapacity; // reverse arc

				if (capacity[next][current] == 0)
					neighbors.get(next).remove(current);

				HashSet<Integer> set = neighbors.get(current);
				if (set == null)
					set = new HashSet<>();
				set.add(next);
				neighbors.put(current, set);

				current = next;
			}
		}

		return totalFlow;
	}

	public static void main(String[] args) {
		int[][] data = { { 1, 3, 5 }, { 3, 2, 2 }, { 3, 6, 6 }, { 4, 1, 5 },
				{ 4, 6, 7 }, { 5, 1, 9 }, { 5, 4, 4 }, { 6, 2, 8 } };

		capacity = new int[vertices][vertices];
		neighbors = new HashMap<>();
		for (int i = 0; i < data.length; i++) {
			capacity[data[i][0]][data[i][1]] = data[i][2];

			HashSet<Integer> set = neighbors.get(data[i][0]);
			if (set == null)
				set = new HashSet<>();
			set.add(data[i][1]);
			neighbors.put(data[i][0], set);
		}

		int source = 5;
		int sink = 2;

		System.out.println(fordFulkerson(source, sink));
		for (int[] i : capacity)
			System.out.println(Arrays.toString(i));
	}

}
