import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class PairedUp {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\PLACEHOLD\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("pairup.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		TreeSet<Cow> cows = new TreeSet<Cow>();
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			cows.add(new Cow(Integer.parseInt(ln.nextToken()), Integer.parseInt(ln.nextToken())));
		}
		int worstCase = 0;
		while (!cows.isEmpty()) {
			Cow high = cows.last();
			Cow low = cows.first();
			if (high.numCows > low.numCows) {
				high.numCows -= low.numCows;
				cows.remove(low);
			} else if (high.numCows < low.numCows) {
				low.numCows -= high.numCows;
				cows.remove(high);
			} else {
				cows.remove(high);
				cows.remove(low);
			}
			worstCase = Math.max(worstCase, high.time + low.time);
		}
		out.println(worstCase);
		out.close();
		in.close();
	}
	
	static class Cow implements Comparable<Cow> {
		int numCows;
		int time;
		
		public Cow(int a, int b) {
			numCows = a;
			time = b;
		}

		@Override
		public int compareTo(Cow other) {
			// TODO Auto-generated method stub
			return Integer.compare(this.time, other.time);
		}
	}
}
