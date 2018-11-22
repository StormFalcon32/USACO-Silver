import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Fuel {

	static int numStations;
	static int capacity;

	public static void main(String[] args) throws IOException {
		// BufferedReader in = new BufferedReader(new
		// FileReader("C:\\Users\\bench\\git\\USACO-Silver\\Silver\\Fuel\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("fuel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fuel.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numStations = Integer.parseInt(ln.nextToken());
		capacity = Integer.parseInt(ln.nextToken());
		int gas = Integer.parseInt(ln.nextToken());
		int totalDistance = Integer.parseInt(ln.nextToken());
		Station[] stations = new Station[numStations];
		int[] s = new int[numStations];
		int[] nextCheapest = new int[numStations];
		for (int i = 0; i < numStations; i++) {
			ln = new StringTokenizer(in.readLine());
			int dist = Integer.parseInt(ln.nextToken());
			int price = Integer.parseInt(ln.nextToken());
			stations[i] = new Station(dist, price);
		}
		Arrays.sort(stations);
		int length = 0;
		for (int i = numStations - 1; i >= 0; i--) {
			while (length > 0 && stations[s[length - 1]].price >= stations[i].price) {
				length--;
			}
			if (length == 0) {
				nextCheapest[i] = -1;
			} else {
				nextCheapest[i] = s[length - 1];
			}
			s[length] = i;
			length++;
		}
		gas -= stations[0].dist;
		long cost = 0;
		for (int i = 0; i < numStations; i++) {
			int gasNeeded = Math.min(capacity, (nextCheapest[i] == -1 ? totalDistance : stations[nextCheapest[i]].dist) - stations[i].dist);
			if (gasNeeded > gas) {
				cost += (gasNeeded - gas) * stations[i].price;
				gas = gasNeeded;
			}
			gas -= (i == numStations - 1 ? totalDistance : stations[i + 1].dist) - stations[i].dist;
			if (gas < 0) {
				out.println(-1);
				out.close();
				in.close();
				return;
			}
		}
		out.println(cost);
		out.close();
		in.close();
	}

	static class Station implements Comparable<Station> {
		int dist;
		int price;

		public Station(int a, int b) {
			dist = a;
			price = b;
		}

		@Override
		public int compareTo(Station other) {
			return Integer.compare(this.dist, other.dist);
		}

	}
}