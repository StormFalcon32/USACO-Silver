import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Cruise {
	
	static int numPorts;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\bench\\git\\USACO-Silver\\Silver\\Cruise\\2.in"));
//		BufferedReader in = new BufferedReader(new FileReader("cruise.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cruise.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numPorts = Integer.parseInt(ln.nextToken());
		int numDirs = Integer.parseInt(ln.nextToken());
		int numTimes = Integer.parseInt(ln.nextToken());
		Port[] ports = new Port[numPorts];
		Port[] orderedPorts = new Port[numPorts];
		for (int i = 0; i < numPorts; i++) {
			ports[i] = new Port(i);
		}
		for (int i = 0; i < numPorts; i++) {
			ln = new StringTokenizer(in.readLine());
			ports[i].left = ports[Integer.parseInt(ln.nextToken()) - 1];
			ports[i].right = ports[Integer.parseInt(ln.nextToken()) - 1];
		}
		orderedPorts[0] = ports[0];
		for (int i = 0; i < numPorts - 1; i++) {
			orderedPorts[i + 1] = ports[i].left;
		}
		ln = new StringTokenizer(in.readLine());
		Port curr = ports[0];
		for (int i = 0; i < numDirs; i++) {
			String dir = ln.nextToken();
			if (dir.equals("L")) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		int moves = curr.num;
		System.out.println(orderedPorts[moves * numTimes % numPorts].num + 1);
		out.close();
		in.close();
	}
	
	static class Port {
		int num;
		Port left;
		Port right;
		
		public Port(int a) {
			num = a;
		}
	}
}