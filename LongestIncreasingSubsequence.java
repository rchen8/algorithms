import java.util.Arrays;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		int[] x = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
		int[] p = new int[x.length];
		int[] m = new int[x.length + 1];

		int length = 0;
		for (int i = 0; i < x.length; i++) {
			// binary search for the largest positive mid <= length such that
			// x[i] > x[m[mid]]
			int low = 1, high = length;
			while (low <= high) {
				int mid = low + high >> 1;
				if (x[i] > x[m[mid]])
					low = mid + 1;
				else
					high = mid - 1;
			}

			// after searching, low is 1 greater than the length of the longest
			// prefix of x[i]
			// the predecessor of x[i] is the last index of the subsequence of
			// length low - 1
			p[i] = m[low - 1];
			m[low] = i;

			// if we found a subsequence longer than any we've found yet, update
			// length
			length = Math.max(length, low);
		}

		// reconstruct the longest increasing subsequence
		// if there are multiple subsequences, print the values that come later
		// in the array x
		int[] s = new int[length];
		int k = m[length];
		for (int i = length - 1; i >= 0; i--) {
			s[i] = x[k];
			k = p[k];
		}

		System.out.println(length);
		System.out.println(Arrays.toString(s));
	}

}
