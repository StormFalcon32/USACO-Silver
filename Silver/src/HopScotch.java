import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HopScotch {

	static int ROWS;
	static int COLS;

	public static void main(String[] args) throws IOException {
		// BufferedReader in = new BufferedReader(new
		// FileReader("D:\\bench\\eclipse\\Workspace\\Silver\\HopScotch\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("hopscotch.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		ROWS = Integer.parseInt(ln.nextToken());
		COLS = Integer.parseInt(ln.nextToken());
		int[][] grid = new int[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			ln = new StringTokenizer(in.readLine());
			for (int c = 0; c < COLS; c++) {
				grid[r][c] = Integer.parseInt(ln.nextToken());
			}
		}
		int[][] numWays = new int[ROWS][COLS];
		numWays[0][0] = 1;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				for (int targetR = r + 1; targetR < ROWS; targetR++) {
					for (int targetC = c + 1; targetC < COLS; targetC++) {
						if (grid[r][c] != grid[targetR][targetC]) {
							numWays[targetR][targetC] += numWays[r][c];
							numWays[targetR][targetC] %= 1000000007;
						}
					}
				}
			}
		}
		out.println(numWays[ROWS - 1][COLS - 1]);
		out.close();
		in.close();
	}

	public static boolean canJump(int[][] board, int currRow, int currCol, int row, int col) {
		if (row >= 0 && row < COLS && col >= 0 && col < COLS && board[row][col] != board[currRow][currCol]
				&& row > currRow && col > currCol) {
			return true;
		}
		return false;
	}

	static class Point {
		int r;
		int c;

		public Point(int a, int b) {
			r = a;
			c = b;
		}
	}
}