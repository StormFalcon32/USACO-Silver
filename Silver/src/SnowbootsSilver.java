import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SnowbootsSilver {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\Snowboots\\3.in"));
		BufferedReader in = new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int b = Integer.parseInt(ln.nextToken());
		ln = new StringTokenizer(in.readLine());
		int[] tiles = new int[n];
		for (int i = 0; i < n; i++) {
			tiles[i] = Integer.parseInt(ln.nextToken());
		}
		Boot[] boots = new Boot[b];
		for (int i = 0; i < b; i++) {
			ln = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(ln.nextToken());
			int y = Integer.parseInt(ln.nextToken());
			boots[i] = new Boot(y, x);
		}
//		Queue of possible states
		LinkedList<State> q = new LinkedList<State>();
		for (int i = 0; i < b; i++) {
			q.offer(new State(0, i));
		}
		boolean[][] possibleStates = new boolean[n][b];
		while (!q.isEmpty()) {
			State curr = q.poll();
			int bootInd = curr.numBoot;
			int pos = curr.pos;
			Boot currBoot = boots[bootInd];
			int depth = currBoot.depth;
			int step = currBoot.step;
			if (pos + step > n - 1) {
				step = n - 1 - pos;
			}
//			How far can this boot go from the current position
			while (step > -1) {
				int potDepth = tiles[pos + step];
				if (potDepth <= depth && !possibleStates[pos + step][bootInd]) {
					possibleStates[pos + step][bootInd] = true;
					q.offer(new State(pos + step, bootInd));
				}
				step--;
			}
//			What other boots can I put on from here
			for (int i = bootInd + 1; i < b; i++) {
				if (boots[i].depth >= tiles[pos] && !possibleStates[pos][i]) {
					q.offer(new State(pos, i));
				}
			}
		}
		for (int i = 0; i < b; i++) {
			if (possibleStates[n - 1][i]) {
				out.println(i);
				break;
			}
		}
		out.close();
		in.close();
	}
	
	static class Boot {
		int step;
		int depth;
		
		public Boot(int a, int b) {
			step = a;
			depth = b;
		}
	}
	
	static class State {
		int pos;
		int numBoot;
		
		public State(int a, int b) {
			pos = a;
			numBoot = b;
		}
	}
}
