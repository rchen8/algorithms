import java.util.Arrays;

public class SubsetSum {

	private static boolean subsetSum(int[] set, int sum) {
		// The value of subset[i][j] will be true if there is a subset of
		// set[0..j-1] with sum equal to i
		boolean[][] subset = new boolean[sum + 1][set.length + 1];

		// If sum is 0, then answer is true
		Arrays.fill(subset[0], true);

		// If sum is not 0 and set is empty, then answer is false
		for (int i = 1; i <= sum; i++)
			subset[i][0] = false;

		// Fill the subset table in botton up manner
		for (int i = 1; i <= sum; i++) {
			for (int j = 1; j <= set.length; j++) {
				subset[i][j] = subset[i][j - 1];
				if (i >= set[j - 1])
					subset[i][j] = subset[i][j]
							|| subset[i - set[j - 1]][j - 1];
			}
		}

		return subset[sum][set.length];
	}

	public static void main(String[] args) {
		System.out.println(subsetSum(new int[] { 15, 9, 30, 21, 19, 3, 12, 6,
				25, 27 }, 50));
		System.out.println(subsetSum(new int[] { 12, 34, 8, 42, 22, 5 }, 100));
	}

}
