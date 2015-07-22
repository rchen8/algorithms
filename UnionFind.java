public class UnionFind {

	private static int[] set;

	private static void union(int i, int j) {
		set[find(i)] = find(j);
	}

	private static int find(int i) {
		return set[i] == i ? i : (set[i] = find(set[i]));
	}

	private static boolean sameSet(int i, int j) {
		return find(i) == find(j);
	}

	public static void main(String[] args) {
		final int n = 10;

		set = new int[n];
		for (int i = 0; i < n; i++)
			set[i] = i;

		int[][] pairs = { { 1, 2 }, { 3, 1 }, { 3, 5 }, { 4, 6 }, { 5, 4 },
				{ 4, 3 }, { 5, 2 }, { 2, 1 }, { 7, 10 }, { 9, 10 }, { 8, 9 } };
		for (int i = 0; i < pairs.length; i++) {
			int a = pairs[i][0] - 1;
			int b = pairs[i][1] - 1;

			union(set[a], set[b]);
		}

		for (int i = 0; i < n; i++)
			System.out.print(find(i) + " ");
		System.out.println();
	}

}
