import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class FieldReduction {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\FieldReduction\\6.in"));
		BufferedReader in = new BufferedReader(new FileReader("reduce.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		Link[] xVals = new Link[n];
		Link[] yVals = new Link[n];
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(ln.nextToken());
			int b = Integer.parseInt(ln.nextToken());
			xVals[i] = new Link(a);
			yVals[i] = new Link(b);
			xVals[i].other = yVals[i];
			yVals[i].other = xVals[i];
		}
		Arrays.sort(xVals);
		Arrays.sort(yVals);
		LinkedList<Link> xSm = new LinkedList<Link>();
		for (int i = 0; i < 4; i++) {
			xSm.add(xVals[i]);
		}
		LinkedList<Link> xLg = new LinkedList<Link>();
		for (int i = 1; i < 5; i++) {
			xLg.add(xVals[n - i]);
		}
		LinkedList<Link> ySm = new LinkedList<Link>();
		for (int i = 0; i < 4; i++) {
			ySm.add(yVals[i]);
		}
		LinkedList<Link> yLg = new LinkedList<Link>();
		for (int i = 1; i < 5; i++) {
			yLg.add(yVals[n - i]);
		}
		Collections.sort(xSm);
		Collections.sort(xLg);
		Collections.sort(ySm);
		Collections.sort(yLg);
		int bestArea = (xVals[n - 1].val - xVals[0].val) * (yVals[n - 1].val - yVals[0].val);
		
//		The value of x1 signifies how many points are taken off the small end of x, and so on for the other 4
		for (int x1 = 0; x1 < 4; x1++) {
			for (int x2 = 0; x2 < 4 - x1; x2++) {
				for (int y1 = 0; y1 < 4 - x2 - x1; y1++) {
					for (int y2 = 0; y2 < 4 - y1 - x2 - x1; y2++) {
						HashSet<Link> used = new HashSet<Link>();
						HashSet<Link> temp = new HashSet<Link>();
						for (int i = 0; i < x1 && !xSm.isEmpty(); i++) {
							Link o = xSm.pollFirst().other;
							if (used.contains(o.other)) {
								temp.add(o.other);
							}
							used.add(o);
							if (ySm.contains(o)) {
								ySm.remove(o);							
							}
							if (yLg.contains(o)) {
								yLg.remove(o);
							}
						}
						xSm.addAll(temp);
						temp = new HashSet<Link>();
						for (int i = 0; i < x2 && !xLg.isEmpty(); i++) {
							Link o = xLg.pollLast().other;
							if (used.contains(o.other)) {
								temp.add(o.other);
							}
							used.add(o);
							if (ySm.contains(o)) {
								ySm.remove(o);							
							}
							if (yLg.contains(o)) {
								yLg.remove(o);
							}
						}
						xLg.addAll(temp);
						temp = new HashSet<Link>();
						for (int i = 0; i < y1 && !ySm.isEmpty(); i++) {
							Link o = ySm.pollFirst().other;
							if (used.contains(o.other)) {
								temp.add(o.other);
							}
							used.add(o);
							if (xLg.contains(o)) {
								xLg.remove(o);
							}
							if (xSm.contains(o)) {
								xSm.remove(o);							
							}
						}
						ySm.addAll(temp);
						temp = new HashSet<Link>();
						for (int i = 0; i < y2 && !yLg.isEmpty(); i++) {
							Link o = yLg.pollLast().other;
							if (used.contains(o.other)) {
								temp.add(o.other);
							}
							used.add(o);
							if (xLg.contains(o)) {
								xLg.remove(o);
							}
							if (xSm.contains(o)) {
								xSm.remove(o);
							}
						}
						yLg.addAll(temp);
						int bigX = xVals[n - 5].val;
						if (!xLg.isEmpty()) {
							bigX = xLg.getLast().val;
						}
						int smallX = xVals[4].val;
						if (!xSm.isEmpty()) {
							smallX = xSm.getFirst().val;
						}
						int bigY = yVals[n - 5].val;
						if (!yLg.isEmpty()) {
							bigY = yLg.getLast().val;
						}
						int smallY = yVals[4].val;
						if (!ySm.isEmpty()) {
							smallY = ySm.getFirst().val;
						}
						int xLength = Math.abs(bigX - smallX);
						int yLength = Math.abs(bigY - smallY);
						bestArea = Math.min(bestArea, xLength * yLength);
						xSm = new LinkedList<Link>();
						for (int i = 0; i < 4; i++) {
							xSm.add(xVals[i]);
						}
						xLg = new LinkedList<Link>();
						for (int i = 1; i < 5; i++) {
							xLg.add(xVals[n - i]);
						}
						ySm = new LinkedList<Link>();
						for (int i = 0; i < 4; i++) {
							ySm.add(yVals[i]);
						}
						yLg = new LinkedList<Link>();
						for (int i = 1; i < 5; i++) {
							yLg.add(yVals[n - i]);
						}
						Collections.sort(xSm);
						Collections.sort(xLg);
						Collections.sort(ySm);
						Collections.sort(yLg);
					}
				}
			}
		}
		out.println(bestArea);
		out.close();
		in.close();
	}
	
	
	static class Link implements Comparable<Link> {
		int val;
		Link other;
		
		public Link(int a) {
			val = a;
		}

		@Override
		public int compareTo(Link o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.val, o.val);
		}
	}
}
