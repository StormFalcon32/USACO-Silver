import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SlowDown {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\SlowDown\\3.in"));
		BufferedReader in = new BufferedReader(new FileReader("slowdown.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("slowdown.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		PriorityQueue<Integer> dEvents = new PriorityQueue<Integer>();
		PriorityQueue<Integer> tEvents = new PriorityQueue<Integer>();
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			String type = ln.nextToken();
			int location = Integer.parseInt(ln.nextToken());
			if (type.equals("D")) {
				dEvents.add(location);
			} else {
				tEvents.add(location);
			}
		}
		double time = 0.00;
		double distance = 0.00;
		double sDenominator = 1.00;
		double lastTime = 0.00;
		double lastDistance = 0.00;
//		Loop until travel 1km
		while (distance < 1000) {
//			Look at the closest events in each pq
			int currTEvent = -1;
			int currDEvent = -1;
			if (!tEvents.isEmpty()) {
				currTEvent = tEvents.peek();
			}
			if (!dEvents.isEmpty()) {
				currDEvent = dEvents.peek();
			}
//			Compare them
			if (currTEvent != -1 && currDEvent != -1) {
//				There are still both types of events
				if (currTEvent - lastTime < (currDEvent - lastDistance) * sDenominator) {
//					Time event happens first
					time = currTEvent;
					distance += (time - lastTime) / sDenominator;
					tEvents.poll();
				} else {
					distance = currDEvent;
					time += (distance - lastDistance) * sDenominator;
					dEvents.poll();
				}
			} else if (currTEvent != -1) {
//				No more distance events, only time events
				time = currTEvent;
				distance += (time - lastTime) / sDenominator;
				tEvents.poll();
			} else if (currDEvent != -1) {
//				No more time events, only distance events
				distance = currDEvent;
				time += (distance - lastDistance) * sDenominator;
				dEvents.poll();
			} else {
//				No more events
				distance = 1000;
				time += (distance - lastDistance) * sDenominator;
			}
			lastTime = time;
			lastDistance = distance;
			sDenominator++;
		}
		out.println(Math.round(time));
		out.close();
		in.close();
	}
}