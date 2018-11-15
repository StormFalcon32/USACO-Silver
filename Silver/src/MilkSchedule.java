import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MilkSchedule {
	
	static int numCows;
	static int numConstraints;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\bjchen\\git\\USACO-Silver\\Silver\\MilkSchedule\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("msched.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msched.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numCows = Integer.parseInt(ln.nextToken());
		numConstraints = Integer.parseInt(ln.nextToken());
		for (int i = 0; i < numCows; i++) {
			
		}
		out.close();
		in.close();
	}
}