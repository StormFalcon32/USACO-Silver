import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MooyoMooyo {
	
	static int ROWS;
	static int COLS = 10;
	static int numRegions = 0;
	static int regionSize;
	static int[] dirR = {0, 0, 1, -1};
	static int[] dirC = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("mooyomooyo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		ROWS = Integer.parseInt(ln.nextToken());
		regionSize = Integer.parseInt(ln.nextToken());
		int[][] grid = new int[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			String str = in.readLine();
			for (int c = 0; c < COLS; c++) {
				grid[r][c] = Integer.parseInt(str.substring(c, c + 1));
			}
		}
		while (true) {
			numRegions = 0;
//			Delete regions
			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					if (grid[r][c] != 0) {
						Vertex root = new Vertex(r, c);
						grid = floodFill(grid, root);
					}
				}
			}
			if (numRegions == 0) {
//				No regions left, stop
				break;
			}
//			Make things fall
			grid = drop(grid);
		}
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				out.print(grid[r][c]);
			}
			out.println();
		}
		out.close();
		in.close();
	}
	
	static boolean inBounds(int r, int c) {
		if (r < ROWS && r >= 0 && c < COLS && c >= 0) {
			return true;
		}
		return false;
	}
	
	static int[][] drop(int[][] grid) {
		for (int c = 0; c < COLS; c++) {
			boolean done = false;
			for (int r = ROWS - 1; r >= 0 && !done; r--) {
				if (grid[r][c] == 0) {
					done = true;
					for (int r2 = r; r2 > 0; r2--) {
						if (grid[r2][c] != 0 || grid[r2 - 1][c] != 0) {
							done = false;
						}
						grid[r2][c] = grid[r2 - 1][c];
					}
					grid[0][c] = 0;
					r++;
				}
			}
		}
		return grid;
	}
	
	static int[][] floodFill(int[][] grid, Vertex root) {
		LinkedList<Vertex> q = new LinkedList<Vertex>();
		LinkedList<Vertex> clear = new LinkedList<Vertex>();
		q.add(root);
		int numCells = 0;
		boolean[][] visited = new boolean[ROWS][COLS];
		visited[root.r][root.c] = true; 
		while (!q.isEmpty()) {
			numCells++;
			Vertex curr = q.poll();
			clear.add(curr);
			for (int i = 0; i < 4; i++) {
				int newR = curr.r + dirR[i];
				int newC = curr.c + dirC[i];
				if (inBounds(newR, newC) && grid[newR][newC] == grid[root.r][root.c] && !visited[newR][newC]) {
					q.add(new Vertex(newR, newC));
					visited[newR][newC] = true; 
				}
			}
		}
		if (numCells >= regionSize) {
			numRegions++;
			while (!clear.isEmpty()) {
				Vertex curr = clear.poll();
				grid[curr.r][curr.c] = 0; 
			}
		}
		return grid;
	}
	
	static class Vertex {
		int r;
		int c;
		
		public Vertex(int a, int b) {
			r = a;
			c = b;
		}
	}
}