import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.HashSet;

public class BovineShuffle {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\BovineShuffle\\7.in"));
		BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		Shuffle[] shuffles = new Shuffle[n];
//		Every position always has one out, but could have multiple ins
		for (int i = 0; i < n; i++) {
			shuffles[i] = new Shuffle();
		}
		ln = new StringTokenizer(in.readLine());
//		I is current position, and input number is target position
		for (int i = 0; i < n; i++) {
			int target = Integer.parseInt(ln.nextToken()) - 1;
			shuffles[i].target = target;
		}
		HashSet<Integer> traversed = new HashSet<Integer>();
		HashSet<Integer> looped = new HashSet<Integer>();
		int safeSpots = 0;
		for (int i = 0; i < n; i++) {
			HashSet<Integer> temp = new HashSet<Integer>();
			if (looped.contains(i)) {
				continue;
			}
			traversed.clear();
			int target = shuffles[i].target;
			int prev = i;
			boolean success = false;
			int loopSize = 0;
//			Separate handling if position loops with itself
			if (target == prev) {
				success = true;
				safeSpots++;
			}
//			If you ever go in a loop, you are good
//			If you ever see the same thing twice you aren't
			while (prev != target && !traversed.contains(target) && !looped.contains(target)) {
				loopSize++;
				temp.add(prev);
				if (target == i) {
					success = true;
					safeSpots += loopSize;
					break;
				}
				prev = target;
				traversed.add(prev);
				target = shuffles[target].target;
			}
			if (success) {
				looped.addAll(temp);
			} else {
				looped.add(i);
			}
		}
		out.println(safeSpots);
		out.close();
		in.close();
	}
	
	static class Shuffle {
		int target;
		
		public Shuffle() {
			
		}
	}
}