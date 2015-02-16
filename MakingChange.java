public class MakingChange {

	private static void makeChange(int[] coins, int n) {
		int[] best = new int[n + 1];
		int[] used = new int[n + 1];
		used[0] = 1;

		for (int i = 1; i <= n; i++) {
			int minCoins = i;
			int newCoin = 1;

			for (int j = 0; j < coins.length; j++) {
				if (coins[j] > i)
					continue;
				else if (best[i - coins[j]] + 1 < minCoins) {
					minCoins = best[i - coins[j]] + 1;
					newCoin = coins[j];
				}
			}

			best[i] = minCoins;
			used[i] = newCoin;
		}
	}

	public static void main(String[] args) {
		makeChange(new int[] { 1, 3, 4 }, 6);
	}

}
