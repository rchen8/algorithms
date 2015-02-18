public class Knapsack {

	private static void knapsackUnbounded(int[] value, int[] weight, int n) {
		int[] best = new int[n + 1];
		int[] used = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			int max = 0, item = -1;
			for (int j = 0; j < value.length; j++) {
				if (weight[j] > i)
					continue;
				if (best[i - weight[j]] + value[j] > max) {
					max = best[i - weight[j]] + value[j];
					item = j;
				}
			}
			best[i] = max;
			used[i] = item;
		}

		System.out.println(best[n]);
		for (int i = n; i > 0; i -= weight[used[i]])
			System.out.print(used[i] + " ");
		System.out.println();
	}

	private static void knapsack01(int[] value, int[] weight, int n) {
		int[][] best = new int[weight.length + 1][n + 1];
		int[] used = new int[n + 1];

		for (int i = 1; i <= weight.length; i++) {
			for (int j = 1; j <= n; j++) {
				if (weight[i - 1] <= j) {
					if (best[i - 1][j - weight[i - 1]] + value[i - 1] > best[i - 1][j]) {
						best[i][j] = best[i - 1][j - weight[i - 1]]
								+ value[i - 1];
						used[j] = i;
					} else
						best[i][j] = best[i - 1][j];
				} else
					best[i][j] = best[i - 1][j];
			}
		}

		System.out.println(best[weight.length][n]);
		for (int i = n; i > 0; i -= weight[used[i] - 1]) {
			while (i > 0 && best[weight.length][i] == best[weight.length][i - 1])
				i--;
			System.out.print(used[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		knapsackUnbounded(new int[] { 4, 2, 10, 1, 2 }, new int[] { 12, 1, 4,
				1, 2 }, 15);
		System.out.println();
		knapsack01(new int[] { 4, 2, 10, 1, 2 }, new int[] { 12, 1, 4, 1, 2 },
				15);
	}

}
