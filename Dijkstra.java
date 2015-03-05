import java.util.Arrays;

public class Dijkstra {

	public static void main(String[] args) {
		int vertices = 6;
		int[][] edges = { { 0, 1, 7 }, { 0, 2, 9 }, { 0, 5, 14 }, { 1, 2, 10 },
				{ 1, 3, 15 }, { 2, 3, 11 }, { 2, 5, 2 }, { 3, 4, 6 },
				{ 4, 5, 9 } };

		int[][] graph = new int[vertices][vertices];
		for (int i = 0; i < edges.length; i++) {
			graph[edges[i][0]][edges[i][1]] = edges[i][2];
			graph[edges[i][1]][edges[i][0]] = edges[i][2];
		}

		int[] dist = new int[vertices];
		int[] prev = new int[vertices];
		boolean[] visited = new boolean[vertices];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(prev, -1);

		int current = 0;
		int target = 4;
		dist[current] = 0;

		while (!visited[target] && current != -1) {
			int adj = -1, min = Integer.MAX_VALUE;
			for (int i = 0; i < vertices; i++) {
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
