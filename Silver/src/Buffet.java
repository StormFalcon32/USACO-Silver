import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Buffet {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\Buffet\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("buffet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buffet.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		for (int i = 0; i < n; i++) {
			
		}
		out.close();
		in.close();
	}
}