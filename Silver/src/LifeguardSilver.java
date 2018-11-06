import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class LifeguardSilver {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\LifeguardSilver\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		State[] cows = new State[n * 2];
		int total = 0;
//		Initialize array of states, state has position and group (start and end have same group id)
		for (int i = 0; i < n * 2; i += 2) {
			ln = new StringTokenizer(in.readLine());
			int s = Integer.parseInt(ln.nextToken());
			int e = Integer.parseInt(ln.nextToken());
			State temp1 = new State(s, i / 2);
			State temp2 = new State(e, i / 2);
			cows[i] = temp1;
			cows[i + 1] = temp2;
		}
//		Sort array of states
		Arrays.sort(cows);
		int lastPos = 0;
		TreeSet<Integer> ts = new TreeSet<Integer>();
//		Stores amount of time exclusively covered by each lifeguard
		int[] singleCovered = new int[n];
		for (State curr : cows) {
			if (ts.size() == 1) {
//				A lifeguard's shift has been started but not ended, there's no overlap
//				The remaining element in the TreeSet is the one who's shift is not being overlapped
//				So the gap between it and the last position is only singly covered by it
				singleCovered[ts.first()] += curr.pos - lastPos;
			}
			if (!ts.isEmpty()) {
//				Calculate total by adding the space in between every state
				total += curr.pos - lastPos;
			}
			if (ts.contains(curr.group)) {
//				This is the end of a lifeguard's shift, it's no longer relevant so remove from tree
				ts.remove(curr.group);
			} else {
//				Otherwise, a lifeguard is starting a shift so add to tree
				ts.add(curr.group);
			}
			lastPos = curr.pos;
		}
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, total - singleCovered[i]);
		}
		out.println(max);
		out.close();
		in.close();
	}
	
	static class State implements Comparable<State> {
		int pos, group;
		public State(int a, int i) {
			this.pos = a;
			this.group = i;
		}
		
		public int compareTo(State other) {
			return Integer.compare(this.pos, other.pos);
		}
	}
}
