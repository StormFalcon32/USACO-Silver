import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Marathon {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\PLACEHOLD\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("marathon.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int k = Integer.parseInt(ln.nextToken());
		Checkpoint[] checkpoints = new Checkpoint[n];
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(ln.nextToken());
			int b = Integer.parseInt(ln.nextToken());
			checkpoints[i] = new Checkpoint(a, b);
		}
//		Calculate total distance
		int totalDistance = 0;
		for (int i = 1; i < n; i++) {
			Checkpoint curr = checkpoints[i];
			Checkpoint prev = checkpoints[i - 1];
			totalDistance += Math.abs(curr.x - prev.x) + Math.abs(curr.y - prev.y);
		}
		int bestDistance = totalDistance;
//		Try removing every checkpoint other than starting and finishing
//		Keep track of how much less distance would need to be traveled by removing each individual point
		Potential[] potentials = new Potential[n];
		for (int i = 1; i < n - 1; i++) {
			int potentialDistance = totalDistance;
			Checkpoint prev = checkpoints[i - 1];
			Checkpoint toRemove = checkpoints[i];
			Checkpoint next = checkpoints[i + 1];
			potentialDistance -= Math.abs(toRemove.x - prev.x) + Math.abs(toRemove.y - prev.y); 
			potentialDistance -= Math.abs(toRemove.x - next.x) + Math.abs(toRemove.y - next.y);
			potentialDistance += Math.abs(prev.x - next.x)+ Math.abs(prev.y - next.y);
			potentials[i] = new Potential(potentialDistance, i);
		}
		for (int i = n - 2; i >= n - k; i--) {
			if (i == n - 2) {
				bestDistance -= potentials[i].pot;
			}
		}
		System.out.println(bestDistance);
		out.close();
		in.close();
	}
	
	static class Checkpoint {
		int x;
		int y;
		
		public Checkpoint(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	static class Potential implements Comparable<Potential> {
		int pot, index;
		public Potential(int a, int b) {
			pot = a;
			index = b;
		}
		
		public int compareTo(Potential other) {
			return Integer.compare(this.pot, other.pot);
		}
	}
}
