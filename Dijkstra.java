import java.util.Arrays;

public class Dijkstra {

	public static void main(String[] args) {
		final int n = 6;
		int[][] edge = { { 1, 2, 7 }, { 1, 3, 9 }, { 1, 6, 14 }, { 2, 3, 10 },
				{ 2, 4, 15 }, { 3, 4, 11 }, { 3, 6, 2 }, { 4, 5, 6 },
				{ 5, 6, 9 } };

		int[][] graph = new int[n][n];
		for (int i = 0; i < edge.length; i++) {
			graph[edge[i][0] - 1][edge[i][1] - 1] = edge[i][2];
			graph[edge[i][1] - 1][edge[i][0] - 1] = edge[i][2];
		}

		int[] dist = new int[n];
		int[] prev = new int[n];
		boolean[] visited = new boolean[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(prev, -1);

		int current = 0;
		int target = 4;
		dist[current] = 0;

		while (!visited[target] && current != -1) {
			int adj = -1, min = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (graph[current][i] == 0 || visited[i])
					continue;

				if (dist[current] + graph[current][i] < dist[i]) {
					dist[i] = dist[current] + graph[current][i];
					prev[i] = current;
				}
				if (dist[i] < min) {
					adj = i;
					min = dist[i];
				}
			}

			visited[current] = true;
			current = adj;
		}

		System.out.println(Arrays.toString(dist));

		// path reconstruction
		System.out.println(target);
		for (; prev[target] != -1; target = prev[target])
			System.out.println(prev[target]);
	}

}
