import java.util.Arrays;

public class NumberTheory {

	public static void main(String[] args) {
		final int n = 1_000_000;

		// Sieve of Eratosthenes
		boolean[] prime = new boolean[n + 1];
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;

		// Euler's totient function
		int[] totient = new int[n + 1];
		for (int i = 0; i <= n; i++)
			totient[i] = i;

		for (int i = 2; i <= n; i++) {
			if (prime[i]) {
				for (int j = i; j <= n; j += i) {
					prime[j] = false;
					totient[j] -= totient[j] / i;
				}
				prime[i] = true;
			}
		}
	}

}
