import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MooCast {
	
	static Cow[][] grid = new Cow[25000][25000];
	static int reached = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\MooCast\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		boolean[][] visited = new boolean[25000][25000];
		Cow[] cowList = new Cow[n];
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(ln.nextToken());
			int y = Integer.parseInt(ln.nextToken());
			int p = Integer.parseInt(ln.nextToken());
			Cow temp = new Cow(p, x, y);
			cowList[i] = temp;
			grid[x][y] = temp;
		}
		for (int i = 0; i < n; i++) {
			Cow rangeCheck = grid[cowList[i].x][cowList[i].y];
			for (int j = 0; j < n; j++) {
				Cow candidate = grid[cowList[j].x][cowList[j].y];
				if (Math.sqrt(Math.pow((rangeCheck.x - candidate.x), 2) + Math.pow((rangeCheck.y - candidate.y), 2)) <= rangeCheck.range) {
					rangeCheck.reachable.add(candidate);
				}
			}
		}
		int maxReach = 0;
		for (int i = 0; i < n; i++) {
			reached = 0;
			visited = new boolean[25000][25000];
			DFS(cowList[i], visited);
			if (reached > maxReach) {
				maxReach = reached;
			}
		}
		System.out.println(maxReach);
		//out.println(maxReach);
		out.close();
		in.close();
	}
	
	public static void DFS(Cow c, boolean[][] v) {
		if (v[c.x][c.y] == true) {
			return;
		}
		v[c.x][c.y] = true;
		reached++;
		for (int i = 0; i < c.reachable.size(); i++) {
			DFS(grid[c.reachable.get(i).x][c.reachable.get(i).y], v);
		}
	}
	
	public static class Cow {
		ArrayList<Cow> reachable = new ArrayList<Cow>();
		int range;
		int x;
		int y;
		
		public Cow(int r, int a, int b) {
			this.range = r;
			x = a;
			y = b;
		}
	}
}

