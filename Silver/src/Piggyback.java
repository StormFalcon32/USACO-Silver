import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Piggyback {
	static int bEnergy;
	static int eEnergy;
	static int pEnergy;
	static int t = 0;

	public static void main(String[] args) throws IOException {
//		 BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\Piggyback\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("piggyback.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piggyback.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		bEnergy = Integer.parseInt(ln.nextToken());
		eEnergy = Integer.parseInt(ln.nextToken());
		pEnergy = Integer.parseInt(ln.nextToken());
		int n = Integer.parseInt(ln.nextToken());
		int m = Integer.parseInt(ln.nextToken());
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] fields = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			fields[i] = new LinkedList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			ln = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(ln.nextToken()) - 1;
			int b = Integer.parseInt(ln.nextToken()) - 1;
			fields[a].add(b);
			fields[b].add(a);
		}
//		Calculate distances
		int[] bessieDistToFields = calcDistance(fields, 0, n);
		int[] elsieDistToFields = calcDistance(fields, 1, n);
		int[] fieldsDistToEnd = calcDistance(fields, n - 1, n);
		int leastEnergy = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			// Calculate the distance from field 1 to the meeting spot
			int movesForBessie = bessieDistToFields[i];
			int movesForElsie = elsieDistToFields[i];
			int movesTogether = fieldsDistToEnd[i];
			if (movesForBessie == -1 || movesForElsie == -1 || movesTogether == -1) {
				continue;
			}
			leastEnergy = Math.min(movesForBessie * bEnergy + movesForElsie * eEnergy + movesTogether * pEnergy, leastEnergy);
		}
		out.println(leastEnergy);
		out.close();
		in.close();
	}

	static int[] calcDistance(LinkedList<Integer>[] edges, int start, int n) {
//		Keep track of distances
		int[] distance = new int[n];
		Arrays.fill(distance, -1);
//		BFS Queue
		LinkedList<Integer> queue = new LinkedList<Integer>();
		distance[start] = 0;

		queue.add(start);
		while (!queue.isEmpty()) {
			int curr = queue.poll();

			for (int target : edges[curr]) {
//				Update distance at i
				if (distance[target] == -1) {
					queue.add(target);
					distance[target] = distance[curr] + 1;
				}
			}
		}
		return distance;
	}
}
