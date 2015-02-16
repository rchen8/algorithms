import java.util.HashSet;

public class Prim {

	public static void main(String[] args) {
		final int n = 4;
		int[][] matrix = { { 0, 4, 9, 21 }, { 4, 0, 8, 17 }, { 9, 8, 0, 16 },
				{ 21, 17, 16, 0 } };

		HashSet<Integer> visited = new HashSet<>();
		HashSet<Integer> unvisited = new HashSet<>();

		visited.add(0);
		for (int i = 1; i < n; i++)
			unvisited.add(i);

		int length = 0;
		while (visited.size() < n) {
			int vertex = -1, min = Integer.MAX_VALUE;
			for (int i : visited) {
				for (int j : unvisited) {
					if (matrix[i][j] < min) {
						vertex = j;
						min = matrix[i][j];
					}
				}
			}

			visited.add(vertex);
			unvisited.remove(vertex);
			length += min;
		}

		System.out.println(length);
	}

}
