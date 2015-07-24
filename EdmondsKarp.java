import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class EdmondsKarp {

	private static HashMap<Integer, Integer> p;
	private static int f, s, t;

	private static HashMap<Integer, ArrayList<Integer>> AdjList;
	private static int[][] AdjMat;

	private static void augmentPath(int v, int minEdge) {
		if (v == s) { // managed to get back to source
			f = minEdge; // minEdge of the path
			return;
		} else if (p.containsKey(v)) { // augment if there is a path
			// we need AdjMat for fast lookup here
			augmentPath(p.get(v), Math.min(minEdge, AdjMat[p.get(v)][v]));
			AdjMat[p.get(v)][v] -= f; // forward edges -> decrease
			AdjMat[v][p.get(v)] += f; // backward edges -> increase
		}
	}

	private static int edmondsKarp() {
		int max_flow = 0;
		while (true) { // this will be run max O(VE) times
			f = 0;

			// O(E) BFS and record path p
			LinkedList<Integer> q = new LinkedList<>();
			HashMap<Integer, Integer> dist = new HashMap<>();
			q.add(s);
			dist.put(s, 0); // start from source
			while (!q.isEmpty()) {
				int u = q.remove(); // queue: layer by layer!
				if (u == t) // modification 1: reach sink t, stop BFS
					break;

				if (AdjList.containsKey(u)) {
					for (int v : AdjList.get(u)) { // for each neighbors of u
						// modification 2: also check AdjMat as edges may
						// disappear
						if (AdjMat[u][v] > 0 && !dist.containsKey(v)) {
							dist.put(v, dist.get(u) + 1); // then v is reachable
															// from u
							q.add(v); // enqueue v for next steps
							p.put(v, u); // modification 3: parent of v->first
											// is u
						}
					}
				}
			}

			augmentPath(t, Integer.MAX_VALUE); // path augmentation in O(V)
			if (f == 0) // seems that we cannot pass any more flow
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
