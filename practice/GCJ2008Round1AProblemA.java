import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.TreeMap;

/**
 * Minimum Scalar Product
 * 
 * NOTE: Good design practices are mostly ignored in programming contests.
 * 
 * @author DavidJennings
 *
 */
public class GCJ2008Round1AProblemA {
	private BufferedReader in;
	private PrintStream out;
	
	public static void main(String args[]) {
		try {
			(new GCJ2008Round1AProblemA()).goGo();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public GCJ2008Round1AProblemA() {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = System.out;
	}
	
	public void goGo() throws Exception {
		int T = Integer.parseInt(in.readLine()); // test cases
		
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(in.readLine());
			long[] x = new long[n];
			String[] xIn = in.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				x[j] = Long.parseLong(xIn[j]);
			}
			xIn = null; // clear
			long[] y = new long[n];
			String[] yIn = in.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				y[j] = Long.parseLong(yIn[j]);
			}
			yIn = null; // clear
			
			long m = minSP(x, y);
			out.println("Case #" + (i + 1) + ": " + m);
		}
		
		in.close();
	}
	
	private long minSP(long[] x, long[] y) throws Exception {
		// trees ensure order
		TreeMap<Long, Integer> xTree = new TreeMap<Long, Integer>();
		loadTreeFromArray(xTree, x);
		TreeMap<Long, Integer> yTree = new TreeMap<Long, Integer>();
		loadTreeFromArray(yTree, y);
		
		long total = 0;
		
		while (!xTree.isEmpty()) {
			Long xCur = xTree.firstKey();
			removeOrDecrement(xTree, xCur);
			Long yCur = yTree.lastKey();
			removeOrDecrement(yTree, yCur);
			total += xCur * yCur;
		}
		
		if (!yTree.isEmpty()) { // should be empty, check for possible bug
			throw new Exception("Y tree is not empty");
		}
		
		return total;
	}
	
	private void loadTreeFromArray(TreeMap<Long, Integer> tree, long[] array) {
		for (int i = 0; i < array.length; i++) {
			if (tree.containsKey(array[i])) {
				Integer inc = tree.get(array[i]) + 1;
				tree.put(array[i], inc);
			} else {
				tree.put(array[i], 1);
			}
		}
	}
	
	private void removeOrDecrement(TreeMap<Long, Integer> tree, Long key) {
		Integer count = tree.get(key);
		if (count <= 1) {
			tree.remove(key);
		} else {
			tree.put(key, count - 1);
		}
	}
}
