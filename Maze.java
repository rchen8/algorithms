import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Maze {

	private static char[][] maze;
	private static final int[][] dir = { { -1, 0, 0, 1 }, { 0, -1, 1, 0 } };

	private static boolean isValid(int x, int y) {
		return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
	}

	private static void backTrack(HashMap<Point, Point> visit, Point pt) {
		while (pt != null) {
			if (maze[pt.x][pt.y] == ' ') // TODO
				maze[pt.x][pt.y] = 'X'; // TODO
			pt = visit.get(pt);
		}
	}

	private static void floodFill(Point start, Point end) {
		LinkedList<Point> bfs = new LinkedList<>();
		HashMap<Point, Point> visit = new HashMap<>();

		bfs.add(start);
		visit.put(start, null);
		A: while (!bfs.isEmpty()) {
			Point pt = bfs.poll();

			for (int i = 0; i < dir[0].length; i++) {
				int x = pt.x + dir[0][i];
				int y = pt.y + dir[1][i];
				Point adj = new Point(x, y);

				if (end.equals(adj)) {
					visit.put(end, pt);
					break A;
				}

				if (isValid(x, y) && maze[x][y] == ' ' // TODO
						&& !visit.containsKey(adj)) {
					bfs.add(adj);
					visit.put(adj, pt);
				}
			}
		}

		backTrack(visit, end);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(".txt"));

		int n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			maze = new char[10][]; // TODO

			Point start = null, end = null;
			for (int j = 0; j < maze.length; j++) {
				maze[j] = scan.next().toCharArray();
				for (int k = 0; k < maze[j].length; k++) {
					if (maze[j][k] == 'S') // TODO
						start = new Point(j, k);
					else if (maze[j][k] == 'E') // TODO
						end = new Point(j, k);
				}
			}

			floodFill(start, end);

			for (char[] c : maze)
				System.out.println(new String(c));
			System.out.println();
		}
		scan.close();
	}

}
