import java.util.Arrays;

public class FloydWarshall {

	public static void main(String[] args) {
		final int n = 4;
		int[][] data = { { 0, 2, -2 }, { 2, 3, 2 }, { 3, 1, -1 }, { 1, 0, 4 },
				{ 1, 2, 3 } };

		int[][] dist = new int[n][n];
		int[][] next = new int[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(dist[i], Short.MAX_VALUE);

		for (int i = 0; i < data.length; i++) {
			dist[data[i][0]][data[i][1]] = data[i][2];
			next[data[i][0]][data[i][1]] = data[i][1];
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				if (k == i)
					continue;
				for (int j = 0; j < n; j++) {
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
