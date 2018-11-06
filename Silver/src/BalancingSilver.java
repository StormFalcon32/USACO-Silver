import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BalancingSilver {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\PLACEHOLD\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("balancing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
//		Read in values and sort
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(ln.nextToken());
			int b = Integer.parseInt(ln.nextToken());
			Point temp = new Point(a, b);
			points[i] = temp;
		}
		Arrays.sort(points);
		ArrayList<Point> top = new ArrayList<Point>();
		ArrayList<Point> bottom = new ArrayList<Point>();
		int m = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int lineY = points[i].y + 1;
			top = new ArrayList<Point>();
			bottom = new ArrayList<Point>();
//			Iterate through every possible Y line
			for (int j = 0; j < n; j++) {
//				Split points into top and bottom based on each Y line
				int currY = points[j].y;
				if (currY < lineY) {
					bottom.add(points[j]);
				} else {
					top.add(points[j]);
				}
			}
//			Iterate through every possible X line for each Y line
			int prevLineX = -1;
			for (int j = 0; j < n; j++) {
				int lineX = points[j].x + 1;
				if (lineX != prevLineX) {
					prevLineX = lineX;
					int topL = 0;
					int topR = 0;
					int botL = 0;
					int botR = 0;
	//				Split top into left and right
					int topIndex = 0;
					while (topIndex < top.size() && top.get(topIndex).x < lineX) {
						topL++;
						topIndex++;
					}
					topR = top.size() - topL;
	//				Split bottom into left and right
					int bottomIndex = 0;
					while (bottomIndex < bottom.size() && bottom.get(bottomIndex).x < lineX) {
						botL++;
						bottomIndex++;
					}
					botR = bottom.size() - botL;
	//				If the biggest region is smaller than the previous smallest max region set new smallest max region
					int biggest = Math.max(topL, Math.max(topR, Math.max(botL, botR)));
					if (biggest < m) {
						m = biggest;
					}
				}
			}
		}
		out.println(m);
		out.close();
		in.close();
	}
	
	public static class Point implements Comparable<Point>{
		int x;
		int y;
		
		public Point(int a, int b) {
			x = a;
			y = b;
		}

		public int compareTo(Point other) {
			return Integer.compare(this.x, other.x);
		}
	}
}