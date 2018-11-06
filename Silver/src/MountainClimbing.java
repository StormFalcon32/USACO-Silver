import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MountainClimbing {
	
	static int n;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\MountainClimbing\\10.in"));
		BufferedReader in = new BufferedReader(new FileReader("climb.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("climb.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		Cow[] cows = new Cow[n];
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			cows[i] = new Cow(Integer.parseInt(ln.nextToken()), Integer.parseInt(ln.nextToken()));
		}
//		Sort the cows
		Arrays.sort(cows);
		System.out.println(calcTime(cows));
		out.close();
		in.close();
	}
	
	static int calcTime(Cow[] cows) {
		int currTimeUp = cows[0].up;
		int currTimeDown = Math.max(0, currTimeUp) + cows[0].down;
//		Fill downtimes
		for (int i = 1; i < n; i++) {
//			Down time is the maximum of either the previous down time or the current up time, added to the current cow's down
			currTimeUp += cows[i].up;
			currTimeDown = Math.max(currTimeDown, currTimeUp) + cows[i].down;
		}
//		Return last down time (down guaranteed to take longer than up)
		return currTimeDown;
	}
	
	static class Cow implements Comparable<Cow> {
		int up;
		int down;
		
		public Cow(int a, int b) {
			up = a;
			down = b;
		}

		public int compareTo(Cow other) {
//			Greedy algorithm, want to sort cows starting from up < down, smallest up starting first
//			After the up < down, you want to sort the rest with biggest down starting first
			if (this.up < this.down) {
				if (other.up < other.down) {
					return Integer.compare(this.up, other.up);
				} else {
					return -1;
				}
			} else {
				if (other.up < other.down) {
					return 1;
				} else {
					return Integer.compare(other.down, this.down);
				}
			}
		}
	}
}