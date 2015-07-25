public class SegmentTree {

	private static final int RANGE_SUM = 0;
	private static final int RANGE_MIN = 1;
	private static final int RANGE_MAX = 2;

	private static int[] array;
	private static int[] segmentTree;

	private static void init(int n) {
		int length = 2 << (int) Math.floor(Math.log(n) / Math.log(2) + 1);
		segmentTree = new int[length];
	}

	private static void build(int type, int node, int low, int high) {
		if (low == high)
			segmentTree[node] = type == RANGE_SUM ? array[low] : low;
		else {
			int lIndex = 2 * node, rIndex = 2 * node + 1;
			int mid = low + high >> 1;
			build(type, lIndex, low, mid);
			build(type, rIndex, mid + 1, high);

			int lContent = segmentTree[lIndex], rContent = segmentTree[rIndex];
			if (type == RANGE_SUM) {
				segmentTree[node] = lContent + rContent;
			} else {
				int lValue = array[lContent], rValue = array[rContent];
				if (type == RANGE_MIN)
					segmentTree[node] = lValue <= rValue ? lContent : rContent;
				else
					segmentTree[node] = lValue >= rValue ? lContent : rContent;
			}
		}
	}

	private static int query(int type, int node, int low, int high, int i, int j) {
		if (i > high || j < low)
			return -1;
		if (low >= i && high <= j)
			return segmentTree[node];

		int mid = low + high >> 1;
		int p1 = query(type, 2 * node, low, mid, i, j);
		int p2 = query(type, 2 * node + 1, mid + 1, high, i, j);

		if (p1 == -1)
			return p2;
		if (p2 == -1)
			return p1;

		if (type == RANGE_SUM)
			return p1 + p2;
		else if (type == RANGE_MIN)
			return array[p1] <= array[p2] ? p1 : p2;
		else
			return array[p1] >= array[p2] ? p1 : p2;
	}

	private static void update(int type, int node, int low, int high, int index) {
		if (low == high) {
			segmentTree[node] = array[index];
			return;
		} else {
			int lIndex = node * 2, rIndex = node * 2 + 1;
			int mid = low + high >> 1;
			if (index <= mid)
				update(type, node * 2, low, mid, index);
			else
				update(type, node * 2 + 1, mid + 1, high, index);

			int lContent = segmentTree[lIndex], rContent = segmentTree[rIndex];
			if (type == RANGE_SUM) {
				segmentTree[node] = lContent + rContent;
			} else {
				int lValue = array[lContent], rValue = array[rContent];
				if (type == RANGE_MIN)
					segmentTree[node] = lValue <= rValue ? lContent : rContent;
				else
					segmentTree[node] = lValue >= rValue ? lContent : rContent;
			}
		}
	}

	public static void main(String[] args) {
		array = new int[] { 10, 2, 47, 3, 7, 9, 1, 98, 21, 37 };
		init(array.length);

		build(RANGE_SUM, 1, 0, array.length - 1);
		System.out.println(query(RANGE_SUM, 1, 0, array.length - 1, 1, 3));

		array[1] = 69;
		update(RANGE_SUM, 1, 0, array.length - 1, 1);
		System.out.println(query(RANGE_SUM, 1, 0, array.length - 1, 1, 3));
	}

}
