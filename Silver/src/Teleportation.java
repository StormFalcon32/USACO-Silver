import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Teleportation {

	static int n;
	static TreeMap<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		// BufferedReader in = new BufferedReader(new
		// FileReader("D:\\bench\\eclipse\\Workspace\\Silver\\Teleportation\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("teleport.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		long t = 0;
		map = new TreeMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(ln.nextToken());
			int b = Integer.parseInt(ln.nextToken());
			t += Math.abs(a - b);
			if (Math.abs(a - b) > Math.abs(a)) {
				update(b, 2);
				if ((a < 0 && a >= b) || (a >= 0 && a < b)) {
					update(2 * a, -1);
					update(2 * (b - a), -1);
				} else {
					update(0, -1);
					update(2 * b, -1);
				}
			}
		}
		long lowestCost = t;
		long count = 0;
		long adjust = Long.MIN_VALUE;
		for (Integer y : map.keySet()) {
			int change = map.get(y);
			t += count * (y - adjust);
			adjust = y;
			count += change;
			lowestCost = Math.min(lowestCost, t);
		}
		out.println(lowestCost);
		out.close();
		in.close();
	}

	static void update(int key, int change) {
		int val = 0;
		if (map.containsKey(key)) {
			val = map.get(key);
		}
		val += change;
		map.put(key, val);
	}
}