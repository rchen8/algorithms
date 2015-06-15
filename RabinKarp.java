public class RabinKarp {

	private static final int BASE = 101;
	private static final long MOD = 1_000_000_007;

	private static int hash(String pattern) {
		long hashCode = 0;
		for (int i = 0; i < pattern.length(); i++)
			hashCode += (pattern.charAt(i) * Math.round(Math.pow(BASE,
					pattern.length() - i)))
					% MOD;
		return (int) hashCode;
	}

	private static int rabinKarp(String s, String pattern) {
		int hpattern = hash(pattern);
		int hs = hash(s.substring(0, pattern.length()));
		for (int i = 0; i < s.length() - pattern.length(); i++) {
			if (hs == hpattern)
				if (s.substring(i, i + pattern.length()).equals(pattern))
					return i;
			hs = hash(s.substring(i + 1, i + 1 + pattern.length()));
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(rabinKarp("ABC ABCDAB ABCDABCDABDE", "ABCDABD"));
	}

}
