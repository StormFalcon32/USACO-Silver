import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Superbull {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\Superbull\\2.in"));
		BufferedReader in = new BufferedReader(new FileReader("superbull.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("superbull.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int[] teams = new int[n];
		PriorityQueue<Match> pq = new PriorityQueue<Match>();
		for (int i = 0; i < n; i++) {
			teams[i] = Integer.parseInt(in.readLine());
			pq.add(new Match(0, i, teams[0] ^ teams[i]));
		}
		long bestScore = 0;
		boolean[] used = new boolean[n];
		used[0] = true;
		int matchesInMst = 0;
		while (matchesInMst < n - 1) {
			Match curr = pq.poll();
			if (used[curr.v1] && used[curr.v2]) {
				continue;
			}
			int newVertex = 0;
			if (used[curr.v1]) {
				// Vertex 1 in MST, vertex 2 isn't, add vertex 2
				newVertex = curr.v2;
			} else {
				// Vice Versa
				newVertex = curr.v1;
			}
			matchesInMst++;
			used[newVertex] = true;
			bestScore += curr.score;
			for (int i = 0; i < n; i++) {
				if (i != newVertex) {
					pq.add(new Match(newVertex, i, teams[newVertex] ^ teams[i]));
				}
			}
		}
		out.println(bestScore);
		out.close();
		in.close();
	}

	static class Match implements Comparable<Match> {
		int v1;
		int v2;
		long score;

		public Match(int a, int b, long weight) {
			v1 = a;
			v2 = b;
			score = weight;
		}

		public int compareTo(Match other) {
			return Long.compare(other.score, this.score);
		}
	}
}
