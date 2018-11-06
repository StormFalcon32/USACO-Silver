import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HoofPaperScissors {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\PLACEHOLD\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		String[] farmer = new String[n];
		int hTotal = 0;
		int pTotal = 0;
		int sTotal = 0;
		for (int i = 0; i < n; i++) {
			farmer[i] = in.readLine();
			if (farmer[i].equals("H")) {
				pTotal++;
			} else if (farmer[i].equals("P")) {
				sTotal++;
			} else {
				hTotal++;
			}
		}
		Sum hSum = new Sum(0, hTotal);
		Sum pSum = new Sum(0, pTotal);
		Sum sSum = new Sum(0, sTotal);
//		Calculate sums
		int max = 0;
		for (int i = 0; i < n - 1; i++) {
			if (farmer[i].equals("H")) {
				pSum.front += 1;
				pSum.back -= 1;
			} else if (farmer[i].equals("P")) {
				sSum.front += 1;
				sSum.back -= 1;
			} else {
				hSum.front += 1;
				hSum.back -= 1;
			}
			max = Math.max(max, (Math.max(hSum.front, Math.max(pSum.front, sSum.front)) + Math.max(hSum.back, Math.max(pSum.back, sSum.back))));
		}
		max = Math.max(max, Math.max(hTotal, Math.max(pTotal, sTotal)));
		out.println(max);
		out.close();
		in.close();
	}
	
	static class Sum {
		int front, back;
		
		public Sum(int a, int b) {
			this.front = a;
			this.back = b;
		}
	}
}
