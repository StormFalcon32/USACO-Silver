import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumToSeven {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\SumToSeven\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("div7.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int[] cows = new int[n];
		for (int i = 0; i < n; i++) {
			cows[i] = Integer.parseInt(in.readLine());
		}
		int[] fAppearance = new int[7];
		Arrays.fill(fAppearance, n);
		int[] lAppearance = new int[7];
		int[] cf = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			cf[i] = (cf[i - 1] + cows[i - 1]) % 7;
			fAppearance[cf[i]] = Math.min(fAppearance[cf[i]], i);
			lAppearance[cf[i]] = Math.max(lAppearance[cf[i]], i);
		}
		int max = 0;
		for (int i = 0; i < 7; i++) {
			max = Math.max(max, lAppearance[i] - fAppearance[i]);
		}
		out.println(max);
		out.close();
		in.close();
	}
}
