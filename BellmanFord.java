import java.util.Arrays;

public class BellmanFord {

	public static void main(String[] args) {
		int vertices = 6;
		int[][] edges = { { 0, 1, 7 }, { 0, 2, 9 }, { 0, 5, 14 }, { 1, 2, 10 },
				{ 1, 3, 15 }, { 2, 3, 11 }, { 2, 5, 2 }, { 3, 4, 6 },
				{ 4, 5, 9 } };

		int[] dist = new int[vertices];
		int[] prev = new int[vertices];

		int source = 0, target = 4;
		// initialize graph
		for (int i = 0; i < vertices; i++) {
			dist[i] = i == source ? 0 : 1 << 30;
			prev[i] = -1;
		}

		// relax edges repeatedly
		for (int i = 0; i < vertices; i++) {
			for (int[] edge : edges) {
				int u = edge[0], v = edge[1], w = edge[2];
				if (dist[u] + w < dist[v]) {
					dist[v] = dist[u] + w;
					prev[v] = u;
				}
				if (dist[v] + w < dist[u]) {
					dist[u] = dist[v] + w;
					prev[u] = v;
				}
			}
		}

		// check for negative weight cycles
		for (int[] edge : edges) {
			int u = edge[0], v = edge[1], w = edge[2];
			if (dist[u] + w < dist[v] || dist[v] + w < dist[u])
				throw new RuntimeException(
						"graph contains a negative-weight cycle");
		}

		System.out.println(Arrays.toString(dist));

		// path reconstruction
		System.out.println(target);
		for (; prev[target] != -1; target = prev[target])
			System.out.println(prev[target]);
	}

}
