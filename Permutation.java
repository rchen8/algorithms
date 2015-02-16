public class Permutation {

	private static void combo(String s, int m) {
		combo(s, "", 0, s.length() - m);
	}

	private static void combo(String s, String prefix, int m, int n) {
		if (n == s.length())
			System.out.println(prefix);
		else
			for (int i = m; i <= n; i++)
				combo(s, prefix + s.charAt(i), i + 1, n + 1);
	}

	private static void permute(String s) {
		permute("", s);
	}

	private static void permute(String prefix, String suffix) {
		if (suffix.length() == 0)
			System.out.println(prefix);
		else
			for (int i = 0; i < suffix.length(); i++)
				permute(prefix + suffix.charAt(i), suffix.substring(0, i)
						+ suffix.substring(i + 1));
	}

	public static void main(String[] args) {
		permute("ABCD");
		combo("ABCDE", 3);
	}

}
