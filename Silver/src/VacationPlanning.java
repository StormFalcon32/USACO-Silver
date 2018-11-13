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

	public static void main(String[] args) throws IOException {
		// BufferedReader in = new BufferedReader(new
		// FileReader("C:\\Users\\bench\\git\\USACO-Silver\\Silver\\VacationPlanning\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("vacation.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("vacation.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numFarms = Integer.parseInt(ln.nextToken());
		numFlights = Integer.parseInt(ln.nextToken());
		numHubs = Integer.parseInt(ln.nextToken());
		numTrips = Integer.parseInt(ln.nextToken());
		Vertex[] adjList = new Vertex[numFarms];
		for (int i = 0; i < numFlights; i++) {
			ln = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(ln.nextToken());
			int dest = Integer.parseInt(ln.nextToken());
			int price = Integer.parseInt(ln.nextToken());
			if (adjList[start] == null) {
				// Nothing there
				adjList[start] = new Vertex(false);
				adjList[start].adjVerts.add(new Edge(dest, price));
			} else {
				// Already something, only need to add new dest
				adjList[start].adjVerts.add(new Edge(dest, price));
			}
		}
		for (int i = 0; i < numHubs; i++) {
			if (adjList[i] == null) {
				// Nothing there
				adjList[i] = new Vertex(true);
			} else {
				// Already something, only need to update
				adjList[i].hub = true;
			}
		}
		for (int i = 0; i < numTrips; i++) {
			ln = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(ln.nextToken());
			int dest = Integer.parseInt(ln.nextToken());
			System.out.println(start + " " + dest);
		}
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

		public Vertex(boolean a) {
			hub = a;
		}
	}
}