import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BreedCounting {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\PLACEHOLD\\1.in"));
//		Read in inputs
		BufferedReader in = new BufferedReader(new FileReader("bcount.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int q = Integer.parseInt(ln.nextToken());
		int b1 = 0;
		int b2 = 0;
		int b3 = 0;
		int[][] breeds = new int[n][3];
//		Breeds[i][n] = amount of breed n between position 1 and i in the line
		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(in.readLine());
			if (temp == 1) {
				b1++;
			} else if (temp == 2) {
				b2++;
			} else {
				b3++;
			}
			breeds[i][0] = b1;
			breeds[i][1] = b2;
			breeds[i][2] = b3;
		}
//		Read in each query, answer is breeds[b] - breeds[a]
		for (int i = 0; i < q; i++) {
			int[] breedCount = new int[3];
			ln = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(ln.nextToken()) - 1;
			int b = Integer.parseInt(ln.nextToken()) - 1;
//			Subtract
			if (a == 0) {
				for (int j = 0; j < 3; j++) {
					breedCount[j] = breeds[b][j];
				}
			} else {
				for (int j = 0; j < 3; j++) {
					int big = breeds[b][j];
					int small = breeds[a - 1][j];
					breedCount[j] = big - small;
				}
			}
			out.println(breedCount[0] + " " + breedCount[1] + " " + breedCount[2]);
		}
		out.close();
		in.close();
	}
}