import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MaxCross {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\MaxCross\\8.in"));
		BufferedReader in = new BufferedReader(new FileReader("maxcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int k = Integer.parseInt(ln.nextToken());
		int b = Integer.parseInt(ln.nextToken());
		boolean[] crosswalks = new boolean[n];
//		false is fixed, true is broken
		for (int i = 0; i < b; i++) {
			int temp = Integer.parseInt(in.readLine());
			crosswalks[temp - 1] = true;
		}
//		Initial block
		int broken = 0;
		for (int i = 0; i < k; i++) {
			if (crosswalks[i]) {
				broken++;
			}
		}
		int minimum = broken;
		for (int i = k; i < n; i++) {
			if (crosswalks[i]) {
				broken++;
			}
			if (crosswalks[i - k]) {
				broken--;
			}
			minimum = Math.min(minimum, broken);
		}
		out.println(minimum);
		out.close();
		in.close();
	}
}
