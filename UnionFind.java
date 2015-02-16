import java.util.Arrays;

public class UnionFind {

	private static Node[] set;

	private static void union(Node x, Node y) {
		Node x_root = find(x);
		Node y_root = find(y);
		if (x_root == y_root)
			return;

		// x and y are not already in same set. Merge them.
		if (x_root.rank < y_root.rank)
			x_root.parent = y_root;
		else if (x_root.rank > y_root.rank)
			y_root.parent = x_root;
		else {
			y_root.parent = x_root;
			x_root.rank++;
		}
	}

	private static Node find(Node x) {
		if (x.parent != x)
			x.parent = find(x.parent);
		return x.parent;
	}

	public static void main(String[] args) {
		final int n = 10;

		set = new Node[n];
		for (int i = 0; i < n; i++)
			set[i] = new Node(i);

		int[][] pairs = { { 1, 2 }, { 3, 1 }, { 3, 5 }, { 4, 6 }, { 5, 4 },
				{ 4, 3 }, { 5, 2 }, { 2, 1 }, { 7, 10 }, { 9, 10 }, { 8, 9 } };
		for (int i = 0; i < pairs.length; i++) {
			int a = pairs[i][0] - 1;
			int b = pairs[i][1] - 1;

			union(set[a], set[b]);
		}
		
		System.out.println(Arrays.toString(set));
	}

	private static class Node {
		private Node parent;
		private int rank, index;

		private Node(int index) {
			rank = 0;
			this.index = index;
			parent = this;
		}

		public String toString() {
			Node n = this;
			while (n.index != n.parent.index)
				n = n.parent;
			return "" + n.index;
		}
	}

}
