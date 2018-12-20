import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Convention {
	
	static int numCows;
	static int cowsPerBus;
	static int numBusses;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("convention.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numCows = Integer.parseInt(ln.nextToken());
		numBusses = Integer.parseInt(ln.nextToken());
		cowsPerBus = Integer.parseInt(ln.nextToken());
		int[] arrivalTimes = new int[numCows];
		
		ln = new StringTokenizer(in.readLine());
		for (int i = 0; i < numCows; i++) {
			arrivalTimes[i] = Integer.parseInt(ln.nextToken());
		}
		Arrays.sort(arrivalTimes);
		int worstPossible = arrivalTimes[numCows - 1] - arrivalTimes[0];
		out.println(binarySearch(0, worstPossible, arrivalTimes));
		out.close();
		in.close();
	}
	
	static int binarySearch(int l, int h, int[] arrivals) {
		int m = (l + h) / 2;
//		Given a time, it's easy to check if it is possible
//		Using this property, we binary search the times
		int currCow = 0;
		int bussesUsed = 0;
		boolean possible = true;
		while (currCow < numCows) {
			int start = arrivals[currCow];
			bussesUsed++;
			for (int i = 1; i < cowsPerBus && currCow + 1< numCows && arrivals[currCow + 1] - start <= m; i++) {
				currCow++;
			}
			int end = arrivals[currCow];
			currCow++;
			if (end - start > m || bussesUsed > numBusses) {
				possible = false;
				break;
			}
		}
		if (l == h) {
			if (possible) {
				return l;
			}
			return h + 1;
		}
		if (possible) {
//			Need to decrease time difference allowed
			return binarySearch(l, Math.max(0, m - 1), arrivals);
		}
//		Need to increase time allowed
		return binarySearch(Math.min(m + 1, h), h, arrivals);
	}
}