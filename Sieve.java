import java.util.Arrays;

public class Sieve {

	public static void main(String[] args) {
		final int n = 1_000_000;

		// initially assume all integers are prime
		boolean[] prime = new boolean[n + 1];
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;

		// mark non-primes <= n using Sieve of Eratosthenes
		for (int i = 2; i * i <= n; i++) {
			// if i is prime, then mark multiples of i as nonprime
			// suffices to consider mutiples i, i+1, ..., n/i
			if (prime[i])
				for (int j = i; i * j <= n; j++)
					prime[i * j] = false;
		}
	}

}
