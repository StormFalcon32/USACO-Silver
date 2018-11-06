import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CrossCountrySki {

	static int rows;
	static int cols;
	static int t = 0;
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\CrossCountrySki\\9.in"));
		BufferedReader in = new BufferedReader(new FileReader("ccski.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ccski.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		rows = Integer.parseInt(ln.nextToken());
		cols = Integer.parseInt(ln.nextToken());
		int numWaypoints = 0;
//		Grid of vertices where vertives[r][c] is the height
		Vertex[][] vertices = new Vertex[rows][cols];
		for (int r = 0; r < rows; r++) {
			ln = new StringTokenizer(in.readLine());
			for (int c = 0; c < cols; c++) {
				t++;
				vertices[r][c] = new Vertex(r, c, Long.parseLong(ln.nextToken()));
			}
		}
//		Read in waypoints and set them
		for (int r = 0; r < rows; r++) {
			ln = new StringTokenizer(in.readLine());
			for (int c = 0; c < cols; c++) {
				if (Integer.parseInt(ln.nextToken()) == 1) {
					t++;
					vertices[r][c].isWaypoint = true;
					numWaypoints++;
				}
			}
		}
//		Generate starting edges
		PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
		boolean[][] used = new boolean[rows][cols];
		long minD = 0;
		used[0][0] = true;
		int numEdgesIncluded = 0;
		edges.addAll(makeEdges(vertices[0][0], vertices));
//		Prim's
		while (numEdgesIncluded < rows * cols - 1) {
			Edge curr = edges.poll();
			Vertex v1 = curr.v1;
			Vertex v2 = curr.v2;
			if (used[v1.row][v1.col] && used[v2.row][v2.col]) {
				continue;
			}
			if (v1.isWaypoint) {
				numWaypoints--;
				v1.isWaypoint = false;
				minD = Math.max(minD, curr.maxWeightAlongPath);
			} else if (v2.isWaypoint) {
				numWaypoints--;
				v2.isWaypoint = false;
				minD = Math.max(minD, curr.maxWeightAlongPath);
			}
			if (numWaypoints == 0) {
				break;
			}
			Vertex newVertex = v1;
			if (used[v1.row][v1.col]) {
				newVertex = v2;
			}
			used[newVertex.row][newVertex.col] = true;
			numEdgesIncluded++;
			TreeSet<Edge> listToAdd = makeEdges(vertices[newVertex.row][newVertex.col], vertices);
			t += 10;
			while (!listToAdd.isEmpty()) {
				t++;
				Edge toAdd = listToAdd.pollFirst();
				toAdd.maxWeightAlongPath = Math.max(toAdd.maxWeightAlongPath, curr.maxWeightAlongPath);
				edges.add(toAdd);
			}
		}
		System.out.println(t);
		System.out.println(System.currentTimeMillis() - startTime);
		out.println(minD);
		out.close();
		in.close();
	}
	
	static TreeSet<Edge> makeEdges(Vertex source, Vertex[][] verts) {
		TreeSet<Edge> newEdges = new TreeSet<Edge>();
		int dirR[] = {1, -1, 0, 0};
		int dirC[] = {0, 0, 1, -1};
		int sourceR = source.row;
		int sourceC = source.col;
		for (int i = 0; i < 4; i++) {
			int newR = sourceR + dirR[i];
			int newC = sourceC + dirC[i];
			if (newR >= 0 && newR < rows && newC >= 0 && newC < cols) {
				t++;
				newEdges.add(new Edge(source, verts[newR][newC]));
			}
		}
		return newEdges;
	}
	
	static class Edge implements Comparable<Edge> {
		Vertex v1;
		Vertex v2;
		long weight;
		long maxWeightAlongPath;
		
		public Edge(Vertex a, Vertex b) {
			v1 = a;
			v2 = b;
			
			weight = Math.abs(v1.height - v2.height);
			maxWeightAlongPath = weight;
		}
		
		public int compareTo(Edge other) {
			if (this.weight == other.weight) {
				return 1;
			}
			return Long.compare(this.weight, other.weight);
		}
	}
	
	static class Vertex {
		int row;
		int col;
		long height;
		boolean isWaypoint = false;
		
		public Vertex(int a, int b, long c) {
			row = a;
			col = b;
			height = c;
		}
	}
}