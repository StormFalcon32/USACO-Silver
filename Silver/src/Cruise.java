import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Cruise {
	
	static int numPorts;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\bench\\git\\USACO-Silver\\Silver\\Cruise\\10.in"));
		BufferedReader in = new BufferedReader(new FileReader("cruise.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cruise.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numPorts = Integer.parseInt(ln.nextToken());
		int numDirs = Integer.parseInt(ln.nextToken());
		int numTimes = Integer.parseInt(ln.nextToken());
		Port[] ports = new Port[numPorts];
//		Dp[i] = the port number you will end up in after applying the directions i times
		int[] dp = new int[numPorts + 1];
		for (int i = 0; i < numPorts; i++) {
			ports[i] = new Port(i);
		}
		for (int i = 0; i < numPorts; i++) {
			ln = new StringTokenizer(in.readLine());
			ports[i].left = ports[Integer.parseInt(ln.nextToken()) - 1];
			ports[i].right = ports[Integer.parseInt(ln.nextToken()) - 1];
		}
		ln = new StringTokenizer(in.readLine());
		Port curr = ports[0];
		String[] dirs = new String[numDirs];
		for (int i = 0; i < numDirs; i++) {
			dirs[i] = ln.nextToken();
		}
		boolean repeated = false;
		HashSet<Integer> traversed = new HashSet<Integer>();
		traversed.add(0);
		int times = 0;
		int loopSize = 0;
		while(!repeated) {
			times++;
			Port prev = curr;
			int next = applyDirections(dirs, curr);
			curr = ports[next];
			if (times == numTimes) {
				out.println(curr.num + 1);
				out.close();
				in.close();
				return;
			}
			dp[times] = curr.num;
			if (traversed.contains(curr.num)) {
				repeated = true;
				loopSize = prev.timesDirApplied - curr.timesDirApplied + 1;
			} else {
				traversed.add(curr.num);
				curr.timesDirApplied = times;
			}
		}
		int timesDirAppliedBeforeLoop = curr.timesDirApplied;
		int timesInLoop = numTimes - timesDirAppliedBeforeLoop;
		int finalIndex = (timesInLoop % loopSize) + timesDirAppliedBeforeLoop;
		out.println(dp[finalIndex] + 1);
		out.close();
		in.close();
	}
	
	static int applyDirections(String[] dirs, Port curr) {
		for (int i = 0; i < dirs.length; i++) {
			if (dirs[i].equals("L")) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		return curr.num;
	}
	
	static class Port {
		int num;
		Port left;
		Port right;
		int timesDirApplied;
		
		public Port(int a) {
			num = a;
		}
	}
}