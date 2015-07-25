import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class EdmondsKarp {

	private static HashMap<Integer, Integer> p;
	private static int f, s, t;

	private static HashMap<Integer, ArrayList<Integer>> AdjList;
	private static int[][] AdjMat;

	private static void augmentPath(int v, int minEdge) {
		if (v == s) {
			f = minEdge;
			return;
		} else if (p.containsKey(v)) {
			augmentPath(p.get(v), Math.min(minEdge, AdjMat[p.get(v)][v]));
			AdjMat[p.get(v)][v] -= f;
			AdjMat[v][p.get(v)] += f;
		}
	}

	private static int edmondsKarp() {
		int max_flow = 0;
		while (true) {
			f = 0;

			LinkedList<Integer> q = new LinkedList<>();
			HashMap<Integer, Integer> dist = new HashMap<>();
			q.add(s);
			dist.put(s, 0);
			while (!q.isEmpty()) {
				int u = q.remove();
				if (u == t)
					break;

				if (AdjList.containsKey(u)) {
					for (int v : AdjList.get(u)) {
						if (AdjMat[u][v] > 0 && !dist.containsKey(v)) {
							dist.put(v, dist.get(u) + 1);
															
							q.add(v);
							p.put(v, u);
											
						}
					}
				}
			}

			augmentPath(t, Integer.MAX_VALUE);
			if (f == 0)
				break;
			max_flow += f;
		}

		return max_flow;
	}

	public static void main(String[] args) {
		final int n = 8;
		int[][] data = { { 1, 3, 5 }, { 3, 2, 2 }, { 3, 6, 6 }, { 4, 1, 5 },
				{ 4, 6, 7 }, { 5, 1, 9 }, { 5, 4, 4 }, { 6, 2, 8 } };

		AdjList = new HashMap<>();
		AdjMat = new int[n + 1][n + 1];
		for (int i = 0; i < data.length; i++) {
			ArrayList<Integer> adj = AdjList.get(data[i][0]);
			if (adj == null)
				adj = new ArrayList<>();
			adj.add(data[i][1]);
			AdjList.put(data[i][0], adj);

			AdjMat[data[i][0]][data[i][1]] = data[i][2];
		}

		s = 5;
		t = 2;
		p = new HashMap<>();
		System.out.println(edmondsKarp());
	}

}
