public class KnuthMorrisPratt {

	private static int[] kmp_table(char[] w) {
		int pos = 2; // the current position we are computing in t
		int cnd = 0; // the zero-based index in W of the next character of the
						// current candidate substring

		int[] t = new int[w.length];
		t[0] = -1;
		t[1] = 0;
		while (pos < w.length) {
			if (w[pos - 1] == w[cnd]) { // the substring continues
				cnd++;
				t[pos] = cnd;
				pos++;
			} else if (cnd > 0) // it doesn't, but we can fall back
				cnd = t[cnd];
			else { // we have run out of candidates. Note cnd = 0
				t[pos] = 0;
				pos++;
			}
		}

		return t;
	}

	private static int kmp_search(char[] s, char[] w) {
		int m = 0; // the beginning of the current match in s
		int i = 0; // the position of the current character in w

		int[] t = kmp_table(s);
		while (m + i < s.length) {
			if (w[i] == s[m + i]) {
				if (i == w.length - 1)
					return m;
				i++;
			} else {
				if (t[i] > -1) {
					m += i - t[i];
					i = t[i];
				} else {
					i = 0;
					m++;
				}
			}
		}

		// if we reach here, we have searched all of s unsuccessfully
		return -1;
	}

	public static void main(String[] args) {
		char[] s = "whatthemomooofun".toCharArray();
		char[] t = "moo".toCharArray();

		System.out.println(kmp_search(s, t));
	}

}
