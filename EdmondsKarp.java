import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class EdmondsKarp {

	private static HashMap<Integer, Integer> path;
	private static int flow, source, sink;

	private static HashMap<Integer, ArrayList<Integer>> adjList;
	private static int[][] adjMat;

	private static void augmentPath(int v, int minEdge) {
		if (v == source) {
			flow = minEdge;
			return;
		} else if (path.containsKey(v)) {
			augmentPath(path.get(v), Math.min(minEdge, adjMat[path.get(v)][v]));
			adjMat[path.get(v)][v] -= flow;
			adjMat[v][path.get(v)] += flow;
		}
	}

	private static int edmondsKarp() {
		int maxFlow = 0;
		while (true) {
			flow = 0;

			LinkedList<Integer> bfs = new LinkedList<>();
			HashMap<Integer, Integer> dist = new HashMap<>();
			bfs.add(source);
			dist.put(source, 0);
			while (!bfs.isEmpty()) {
				int u = bfs.remove();
				if (u == sink)
					break;

				if (adjList.containsKey(u)) {
					for (int v : adjList.get(u)) {
						if (adjMat[u][v] > 0 && !dist.containsKey(v)) {
							dist.put(v, dist.get(u) + 1);

							bfs.add(v);
							path.put(v, u);
						}
					}
				}
			}

			augmentPath(sink, Integer.MAX_VALUE);
			if (flow == 0)
				break;
			maxFlow += flow;
		}

		return maxFlow;
	}

	public static void main(String[] args) {
		final int n = 8;
		int[][] data = { { 1, 3, 5 }, { 3, 2, 2 }, { 3, 6, 6 }, { 4, 1, 5 },
				{ 4, 6, 7 }, { 5, 1, 9 }, { 5, 4, 4 }, { 6, 2, 8 } };

		adjList = new HashMap<>();
		adjMat = new int[n + 1][n + 1];
		for (int i = 0; i < data.length; i++) {
			ArrayList<Integer> list = adjList.get(data[i][0]);
			if (list == null)
				list = new ArrayList<>();
			list.add(data[i][1]);
			adjList.put(data[i][0], list);

			adjMat[data[i][0]][data[i][1]] = data[i][2];
		}

		source = 5;
		sink = 2;
		path = new HashMap<>();
		System.out.println(edmondsKarp());
	}

}
