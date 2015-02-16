import java.util.Arrays;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		int[] num = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		int[] prev = new int[num.length];
		int[] next = new int[num.length + 1];

		int length = 0;
		for (int i = 0; i < num.length; i++) {
			// binary search for the largest positive mid <= L such that
			// num[next[mid]] < num[i]
			int low = 1, high = length;
			while (low <= high) {
				int mid = (low + high) >> 1;
				if (num[next[mid]] < num[i])
					low = mid + 1;
				else
					high = mid - 1;
			}

			// After searching, low is 1 greater than the length of the longest
			// prefix of num[i]
			// The predecessor of num[i] is the last index of the subsequence of
			// length low - 1
			prev[i] = next[low - 1];
			next[low] = i;

			// If we found a subsequence longer than any we've found yet, update
			// length
			length = Math.max(length, low);
		}

		// Reconstruct the longest increasing subsequence
		int[] ans = new int[length];
		int index = next[length];
		for (int i = length - 1; i >= 0; i--) {
			ans[i] = num[index];
			index = prev[index];
		}

		System.out.println("num: " + Arrays.toString(num));
		System.out.println("prev: " + Arrays.toString(prev));
		System.out.println("next: " + Arrays.toString(next));
		System.out.println("ans: " + Arrays.toString(ans));
	}

}
