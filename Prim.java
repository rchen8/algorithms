import java.util.Arrays;

public class Prim {

	public static void main(String[] args) {
		final int n = 4;
		int[][] matrix = { { 0, 4, 9, 21 }, { 4, 0, 8, 17 }, { 9, 8, 0, 16 },
				{ 21, 17, 16, 0 } };

		int[] best = new int[n];
		boolean[] visited = new boolean[n];
		Arrays.fill(best, -1);
		Arrays.fill(visited, false);

		int vertex = 0; // start from vertex 0
		int length = 0; // initial length of MST is 0
		for (int i = 0; i < n - 1; i++) {
			visited[vertex] = true;

			// expand the vertex and update edges in the queue
			for (int j = 0; j < n; j++)
				if (!visited[j] && matrix[vertex][j] != 0)
					if (best[j] == -1 || matrix[vertex][j] < matrix[best[j]][j])
						best[j] = vertex;

			// choose the unused edge with minimum length in the queue
			vertex = -1;
			for (int j = 0; j < n; j++)
				if (!visited[j] && best[j] != -1)
					if (vertex == -1
							|| matrix[best[j]][j] < matrix[best[vertex]][vertex])
						vertex = j;

			// update total cost of MST
			length += matrix[best[vertex]][vertex];
		}

		System.out.println(length);
	}

}
