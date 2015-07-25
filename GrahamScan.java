import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class GrahamScan {

	private static int turn(Point p, Point q, Point r) {
		int result = (r.x - q.x) * (p.y - q.y) - (r.y - q.y) * (p.x - q.x);
		if (result < 0)
			return -1;
		if (result > 0)
			return 1;
		return 0;
	}

	private static boolean ccw(Point p, Point q, Point r) {
		return turn(p, q, r) > 0;
	}

	private static int area(Point a, Point b, Point c) {
		return a.x * b.y - a.y * b.x + b.x * c.y - b.y * c.x + c.x * a.y - c.y
				* a.x;
	}

	private static int dist(Point a, Point b) {
		int dx = a.x - b.x, dy = a.y - b.y;
		return dx * dx + dy * dy;
	}

	private static ArrayList<Point> convexHull(ArrayList<Point> polygon) {
		if (polygon.size() < 3)
			return null;

		int i, P0 = 0, N = polygon.size();
		for (i = 1; i < N; i++)
			if (polygon.get(i).y < polygon.get(P0).y
					|| (polygon.get(i).y == polygon.get(P0).y && polygon.get(i).x > polygon
							.get(P0).x))
				P0 = i;

		Point temp = polygon.get(0);
		polygon.set(0, polygon.get(P0));
		polygon.set(P0, temp);

		Point pivot = polygon.get(0);
		Collections.sort(polygon, new Comparator<Point>() {
			public int compare(Point a, Point b) {
				if (area(pivot, a, b) == 0)
					return dist(pivot, a) - dist(pivot, b);
				int d1x = a.x - pivot.x, d1y = a.y - pivot.y;
				int d2x = b.x - pivot.x, d2y = b.y - pivot.y;
				return (Math.atan2((double) d1y, (double) d1x) - Math.atan2(
						(double) d2y, (double) d2x)) < 0 ? -1 : 1;
			}
		});

		Stack<Point> stack = new Stack<>();
		Point previous = new Point();
		Point current = new Point();
		stack.push(polygon.get(N - 1));
		stack.push(polygon.get(0));

		i = 1;
		while (i < N) {
			current = stack.pop();
			previous = stack.peek();
			stack.push(current);
			if (ccw(previous, current, polygon.get(i))) {
				stack.push(polygon.get(i));
				i++;
			} else
				stack.pop();
		}

		ArrayList<Point> convexHull = new ArrayList<>();
		while (!stack.empty()) {
			convexHull.add(stack.pop());
		}
		convexHull.remove(convexHull.size() - 1);

		return convexHull;
	}

	public static void main(String[] args) {
		int[][] data = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 },
				{ 0, 1 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 1, 2 }, { 2, 2 } };

		ArrayList<Point> point = new ArrayList<>();
		for (int i = 0; i < data.length; i++)
			point.add(new Point(data[i][0], data[i][1]));

		System.out.println(convexHull(point));
	}

}
