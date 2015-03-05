import java.util.Arrays;

public class FloydWarshall {

	public static void main(String[] args) {
		int vertices = 4;
		int[][] edges = { { 0, 2, -2 }, { 2, 3, 2 }, { 3, 1, -1 }, { 1, 0, 4 },
				{ 1, 2, 3 } };

		int[][] dist = new int[vertices][vertices];
		int[][] next = new int[vertices][vertices];
		for (int i = 0; i < vertices; i++)
			Arrays.fill(dist[i], 1 << 30);

		for (int i = 0; i < edges.length; i++) {
			dist[edges[i][0]][edges[i][1]] = edges[i][2];
			next[edges[i][0]][edges[i][1]] = edges[i][1];
		}

		for (int k = 0; k < vertices; k++) {
			for (int i = 0; i < vertices; i++) {
				if (k == i)
					continue;
				for (int j = 0; j < vertices; j++) {
					if (k == j || i == j)
						continue;
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						next[i][j] = next[i][k];
					}
				}
			}
		}

		// path reconstruction
		int u = 3, v = 2; // u = start, v = end
		for (; u != v; u = next[u][v])
			System.out.println(u);
		System.out.println(u);
	}

}
