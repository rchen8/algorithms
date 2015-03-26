import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Tarjan {

	private static int[] index, lowlink;
	private static boolean[] onStack;
	private static HashMap<Integer, ArrayList<Integer>> graph;

	private static int depth;
	private static Stack<Integer> s;

	private static void tarjan() {
		depth = 0;
		s = new Stack<>();
		for (int v : graph.keySet())
			if (index[v] == -1)
				strongconnect(v);
	}

	private static void strongconnect(int v) {
		// Set the depth index for v to the smallest unused index
		index[v] = lowlink[v] = depth++;
		s.push(v);
		onStack[v] = true;

		// Consider successors of v
		for (int w : graph.get(v)) {
			if (index[w] == -1) {
				// Successor w has not yet been visited; recurse on it
				strongconnect(w);
				lowlink[v] = Math.min(lowlink[v], lowlink[w]);
			} else if (onStack[w]) {
				// Successor w is in stack s and hence in the current SCC
				lowlink[v] = Math.min(lowlink[v], index[w]);
			}
		}

		// If v is a root node, pop the stack and generate an SCC
		if (lowlink[v] == index[v]) {
			HashSet<Integer> scc = new HashSet<>();
			for (int w = -1; w != v;) {
				w = s.pop();
				onStack[w] = false;
				scc.add(w);
			}
			System.out.println(scc);
		}
	}

	public static void main(String[] args) {
		int[][] data = { { 1 }, { 2 }, { 0 }, { 1, 2, 4 }, { 3, 5 }, { 2, 6 },
				{ 5 }, { 4, 6, 7 } };

		graph = new HashMap<>();
		for (int i = 0; i < data.length; i++) {
			graph.put(i, new ArrayList<Integer>());
			for (int j : data[i])
				graph.get(i).add(j);
		}

		index = new int[data.length];
		lowlink = new int[data.length];
		onStack = new boolean[data.length];

		Arrays.fill(index, -1);
		tarjan();
	}

}
