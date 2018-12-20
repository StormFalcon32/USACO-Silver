import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Convention2 {
	
	static int numCows;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("convention2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numCows = Integer.parseInt(ln.nextToken());
		TreeSet<Cow> cows = new TreeSet<Cow>();
		for (int i = 0; i < numCows; i++) {
			ln = new StringTokenizer(in.readLine());
			int arrive = Integer.parseInt(ln.nextToken());
			int eat = Integer.parseInt(ln.nextToken());
			cows.add(new Cow(arrive, eat, i));
		}
		
		Cow first = cows.pollFirst();
		int currTime = first.arrival;
		first.startEat = first.arrival;
		Cow prev = first;
		Cow[] finished = new Cow[numCows];
		finished[0] = first;
		TreeSet<Cow> line = new TreeSet<Cow>(new SortByRank());
		for (int i = 1; i < numCows; i++) {
			int finishedEating = currTime + prev.duration;
			while (!cows.isEmpty() && cows.first().arrival <= finishedEating) {
				line.add(cows.pollFirst());
			}
			Cow curr = (line.isEmpty()) ? cows.pollFirst() : line.pollFirst();
			currTime = Math.max(curr.arrival, finishedEating);
			curr.startEat = Math.max(curr.arrival, currTime);
			finished[i] = curr;
			prev = curr;
		}
		int worstTime = 0;
		for (int i = 0; i < numCows; i++) {
			worstTime = Math.max(worstTime, finished[i].startEat - finished[i].arrival);
		}
		out.println(worstTime);
		out.close();
		in.close();
	}
	
	static class Cow implements Comparable<Cow> {
		int arrival, duration, seniority, startEat;
		
		public Cow(int a, int b, int c) {
			arrival = a;
			duration = b;
			seniority = c;
		}

		@Override
		public int compareTo(Cow other) {
			if (this.arrival == other.arrival) {
				return Integer.compare(this.seniority, other.seniority);
			}
			return Integer.compare(this.arrival, other.arrival);
		}
	}
	
	static class SortByRank implements Comparator<Cow> {
		
		@Override
		public int compare(Cow a, Cow b) {
			return Integer.compare(a.seniority, b.seniority);
		}
	}
}