import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;

public class QuickHull {

	private static final int n = 11;

	private static long cross(Point a, Point b, Point c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}

	private static Point[] convexHull(Point[] pt) {
		if (pt.length <= 1)
			return pt;

		int k = 0;
		Point[] hull = new Point[2 * n];

		Arrays.sort(pt, new Comparator<Point>() {
			public int compare(Point a, Point b) {
				return a.x == b.x ? a.y - b.y : a.x - b.x;
			}
		});

		// build lower hull
		for (int i = 0; i < n; i++) {
			while (k >= 2 && cross(hull[k - 2], hull[k - 1], pt[i]) <= 0)
				k--;
			hull[k++] = pt[i];
		}

		// build upper hull
		for (int i = n - 2, t = k + 1; i >= 0; i--) {
			while (k >= t && cross(hull[k - 2], hull[k - 1], pt[i]) <= 0)
				k--;
			hull[k++] = pt[i];
		}

		// remove non-hull vertices after k; remove k - 1 which is a duplicate
		if (k > 1)
			hull = Arrays.copyOfRange(hull, 0, k - 1);

		return hull;
	}

	public static void main(String[] args) {
		int[][] data = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 },
				{ 0, 1 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 1, 2 }, { 2, 2 } };

		Point[] pt = new Point[n];
		for (int i = 0; i < pt.length; i++)
			pt[i] = new Point(data[i][0], data[i][1]);

		Point[] hull = convexHull(pt);
		System.out.println(Arrays.toString(hull));
	}
}
