import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NumberTheory {

	private static ArrayList<Integer> prime;

	private static void sieve(int n) {
		boolean[] sieve = new boolean[n + 1];
		Arrays.fill(sieve, true);
		sieve[0] = false;
		sieve[1] = false;

		prime = new ArrayList<>();
		for (int i = 2; i * i <= n; i++) {
			if (sieve[i]) {
				prime.add(i);
				for (int j = i * 2; j <= n; j += i)
					sieve[j] = false;
			}
		}
	}

	private static boolean isPrime(int n) {
		if (n < 2)
			return false;
		if (n == 2 || n == 3)
			return true;
		if ((n & 1) == 0 || n % 3 == 0)
			return false;
		for (int i = 6; i * i <= n; i += 6)
			if (n % (i - 1) == 0 || n % (i + 1) == 0)
				return false;
		return true;
	}

	private static HashMap<Integer, Integer> primeFactors(int n) {
		HashMap<Integer, Integer> factors = new HashMap<>();

		int index = 0, pf = prime.get(index);
		while (n != 1 && pf * pf <= n) {
			int count = 0;
			while (n % pf == 0) {
				n /= pf;
				count++;
			}

			if (count != 0)
				factors.put(pf, count);
			pf = prime.get(++index);
		}

		if (n != 1)
			factors.put(n, 1);
		return factors;
	}

	private static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	private static int lcm(int a, int b) {
		return a * (b / gcd(a, b));
	}

	private static int totient(int n) {
		HashMap<Integer, Integer> factors = primeFactors(n);
		for (int i : factors.keySet())
			n -= n / i;
		return n;
	}

	public static void main(String[] args) {
		sieve(1_000_000);

		System.out.println(totient(7654321));
	}

}
