import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class VacationPlanning {

	static int numFarms;
	static int numFlights;
	static int numHubs;
	static int numTrips;
	static int INF = 1000000000;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\bench\\git\\USACO-Silver\\Silver\\VacationPlanning\\5.in"));
		BufferedReader in = new BufferedReader(new FileReader("vacation.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("vacation.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numFarms = Integer.parseInt(ln.nextToken());
		numFlights = Integer.parseInt(ln.nextToken());
		numHubs = Integer.parseInt(ln.nextToken());
		numTrips = Integer.parseInt(ln.nextToken());
		// Array costs[i][j] = min price from i to j
		int[][] costs = new int[numFarms][numFarms];
		for (int i = 0; i < numFarms; ++i) {
			for (int j = 0; j < numFarms; ++j) {
				costs[i][j] = INF;
			}
		}
		for (int i = 0; i < numFarms; i++) {
			costs[i][i] = 0;
		}
		for (int i = 0; i < numFlights; i++) {
			ln = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(ln.nextToken()) - 1;
			int dest = Integer.parseInt(ln.nextToken()) - 1;
			int price = Integer.parseInt(ln.nextToken());
			costs[start][dest] = price;
		}
		// Floyd Warshall
		for (int k = 0; k < numFarms; k++) {
			for (int i = 0; i < numFarms; i++) {
				for (int j = 0; j < numFarms; j++) {
					if (i == j || j == k || i == k) {
						continue;
					}
					costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
				}
			}
		}
		int numValid = 0;
		long sum = 0;
		for (int i = 0; i < numTrips; i++) {
			ln = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(ln.nextToken()) - 1;
			int dest = Integer.parseInt(ln.nextToken()) - 1;
			long cost = INF;
			for (int j = 0; j < numHubs; j++) {
				cost = Math.min(cost, costs[start][j] + costs[j][dest]);
			}
			if (cost != INF) {
				numValid++;
				sum += cost;
			}
		}
		out.println(numValid + "\n" + sum);
		out.close();
		in.close();
	}
	
	

	static class Edge {
		int dest;
		int price;

		public Edge(int a, int b) {
			dest = a;
			price = b;
		}
	}

	static class Vertex {
		boolean hub;
		LinkedList<Edge> adjVerts;
		boolean used = false;

		public Vertex(boolean a) {
			hub = a;
		}
	}
}