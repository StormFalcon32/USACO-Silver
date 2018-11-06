import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PailsSilver {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\PLACEHOLD\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("pails.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(ln.nextToken());
		int y = Integer.parseInt(ln.nextToken());
		int k = Integer.parseInt(ln.nextToken());
		int m = Integer.parseInt(ln.nextToken());
		if (x > y) {
			int temp = x;
			x = y;
			y = temp;
		}
		System.out.println(k + m);
		out.close();
		in.close();
	}
}
