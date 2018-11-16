import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MilkSchedule {

	static int numCows;
	static int numConstraints;
	static LinkedList<Integer>[] adjList;
	static int[] times;
	static long[] finishTimes;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\bench\\git\\USACO-Silver\\Silver\\MilkSchedule\\2.in"));
		BufferedReader in = new BufferedReader(new FileReader("msched.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msched.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numCows = Integer.parseInt(ln.nextToken());
		numConstraints = Integer.parseInt(ln.nextToken());
		times = new int[numCows];
		finishTimes = new long[numCows];
		Arrays.fill(finishTimes, -1);
		adjList = new LinkedList[numCows];
		for (int i = 0; i < numCows; i++) {
			times[i] = Integer.parseInt(in.readLine());
		}
		for (int i = 0; i < numCows; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
		for (int i = 0; i < numConstraints; i++) {
			ln = new StringTokenizer(in.readLine());
			// A has to come before B
			int a = Integer.parseInt(ln.nextToken()) - 1;
			int b = Integer.parseInt(ln.nextToken()) - 1;
			adjList[b].add(a);
		}
		long totalTime = 0;
		for (int i = 0; i < numCows; i++) {
			totalTime = Math.max(totalTime, getFinishTime(i));
		}
		out.println(totalTime);
		out.close();
		in.close();
	}
	
	static long getFinishTime(int i) {
	    if (finishTimes[i] == -1) {
	        long start = 0;
	        for (int j = 0; j < adjList[i].size(); j++) {
	            start = Math.max(start, getFinishTime(adjList[i].get(j)));
	        }
	        finishTimes[i] = start + times[i];
	    }
	    return finishTimes[i];
	}
}