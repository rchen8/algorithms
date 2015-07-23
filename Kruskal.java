import java.util.PriorityQueue;

public class Kruskal {

	private static int[] set;

	private static void union(int i, int j) {
		set[find(i)] = find(j);
	}

	private static int find(int i) {
		return set[i] == i ? i : (set[i] = find(set[i]));
	}

	private static boolean sameSet(int i, int j) {
		return find(i) == find(j);
	}

	public static void main(String[] args) {
		final int n = 4;
		int[][] edge = { { 0, 1, 4 }, { 0, 2, 9 }, { 0, 3, 21 }, { 1, 2, 8 },
				{ 1, 3, 17 }, { 2, 3, 16 } };

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int[] i : edge)
			pq.add(new Edge(i[0], i[1], i[2]));

		set = new int[n];
		for (int i = 0; i < n; i++)
			set[i] = i;

		int cost = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.remove();
			if (!sameSet(e.a, e.b)) {
				cost += e.weight;
				union(e.a, e.b);
			}
		}

		System.out.println(cost);
	}

	private static class Edge implements Comparable<Edge> {
		private int a, b, weight;

		private Edge(int a, int b, int weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}
	}

}
