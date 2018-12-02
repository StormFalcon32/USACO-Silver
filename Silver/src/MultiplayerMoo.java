import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class MultiplayerMoo {

	static int[] dR = { 1, 0, -1, 0 };
	static int[] dC = { 0, 1, 0, -1 };

	static int curr = 0;
	static int n;
	static int[][] grid;
	static boolean[][] visited;
	static HashMap<String, Region> nodeMap = new HashMap<String, Region>();

	public static void main(String[] args) throws IOException {
		// BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\bench\\git\\USACO-Silver\\Silver\\MultiplayerMoo\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("multimoo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		// HashMap of cells, key is the number, value is the number
		HashMap<Integer, Integer> cells = new HashMap<Integer, Integer>();
		grid = new int[n][n];
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				int x = Integer.parseInt(ln.nextToken());
				if (cells.containsKey(x)) {
					cells.put(x, cells.get(x) + 1);
				} else {
					cells.put(x, 1);
				}
				grid[i][j] = x;
			}
		}

		visited = new boolean[n][n];
		int biggestSingle = 0;
		ArrayList<Region> regions = new ArrayList<Region>();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (!visited[r][c]) {
					visited[r][c] = true;
					Region rg = new Region(grid[r][c], curr);
					floodFill(r, c, grid[r][c], rg);
					biggestSingle = Math.max(biggestSingle, curr);
					rg.area = curr;
					regions.add(rg);
					curr = 0;
				}
			}
		}

		for (Region rg : regions) {
			for (String s : rg.cells) {
				String[] ind = s.split(" ");
				int r = Integer.valueOf(ind[0]);
				int c = Integer.valueOf(ind[1]);
				for (int i = 0; i < 4; i++) {
					int newR = r + dR[i];
					int newC = c + dC[i];
					if (inBounds(newR, newC) && grid[r][c] != grid[newR][newC]) {
						connect(rg, nodeMap.get((newR) + " " + (newC)));
					}
				}
			}
		}
		int biggestTeam = 0;
		Collections.sort(regions);
		for (int i = 0; i < regions.size(); i++) {
			Region x = regions.get(i);
			for (Region y : x.connected) {
				if (biggestTeam >= cells.get(x.num)+ cells.get(y.num)) {
					continue;
				}
				int currTeam = 0;
				ArrayList<Region> q = new ArrayList<Region>();
				HashSet<Region> visitedTeams = new HashSet<Region>();
				q.add(x);
				visitedTeams.add(x);
				while (!q.isEmpty()) {
					Region rg = q.remove(0);
					currTeam += rg.area;
					for (Region o : rg.connected) {
						if (!visitedTeams.contains(o) && (o.num == x.num || o.num == y.num)) {
							visitedTeams.add(o);
							q.add(o);
						}
					}
				}
				biggestTeam = Math.max(biggestTeam, currTeam);
			}
		}
		out.print(biggestSingle + "\n" + biggestTeam + "\n");
		out.close();
		in.close();
	}

	static boolean inBounds(int r, int c) {
		if (r < n && r >= 0 && c < n && c >= 0) {
			return true;
		}
		return false;
	}

	public static void floodFill(int r, int c, int num, Region rg) {
		if (grid[r][c] == num) {
			nodeMap.put(r + " " + c, rg);
			rg.cells.add(r + " " + c);
			visited[r][c] = true;
			curr++;
			for (int i = 0; i < 4; i++) {
				if (inBounds(r + dR[i], c + dC[i]) && !visited[r + dR[i]][c + dC[i]]) {
					floodFill(r + dR[i], c + dC[i], num, rg);
				}
			}
		}
	}

	public static void connect(Region a, Region b) {
		a.connected.add(b);
		b.connected.add(a);
	}

	static class Region implements Comparable<Region> {
		int num;
		int area;
		// Cells in this region
		HashSet<String> cells = new HashSet<String>();
		// Adjacent regions
		HashSet<Region> connected = new HashSet<Region>();

		public Region(int a, int b) {
			num = a;
			area = b;
		}

		@Override
		public int compareTo(Region other) {
			return Integer.compare(other.area, this.area);
		}
	}
}