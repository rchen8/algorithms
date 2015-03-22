import java.util.Arrays;

public class Dijkstra {

	private static void dijkstra(int[][] graph, int source) {
		int[] dist = new int[graph.length];
		int[] prev = new int[graph.length];
		boolean[] visited = new boolean[graph.length];

		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(prev, -1);

		int current = source;
		dist[current] = 0;
		while (current != -1) {
			visited[current] = true;
			for (int i = 0; i < graph.length; i++) {
				if (graph[current][i] != 0) {
					int cost = dist[current] + graph[current][i];
					if (cost < dist[i]) {
						dist[i] = cost;
						prev[i] = current;
					}
				}
			}

			int next = -1, min = Integer.MAX_VALUE;
			for (int i = 0; i < graph.length; i++) {
				if (!visited[i] && dist[i] < min) {
					next = i;
					min = dist[i];
				}
			}

			current = next;
		}

		System.out.println(Arrays.toString(dist));
	}

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

		dijkstra(graph, 0);
	}

}
