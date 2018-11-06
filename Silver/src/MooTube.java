import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MooTube {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\PLACEHOLD\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int q = Integer.parseInt(ln.nextToken());
		Relevance[] nodes = new Relevance[n];
		boolean[] used = new boolean[n];
		for (int i = 0; i < n - 1; i++) {
			ln = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(ln.nextToken()) - 1;
			int b = Integer.parseInt(ln.nextToken()) - 1;
			int c = Integer.parseInt(ln.nextToken());
			if (nodes[a] == null) {
				nodes[a] = new Relevance();	
			}
			if (nodes[b] == null) {
				nodes[b] = new Relevance();
			}
			nodes[a].links.add(new Link(b, c));
			nodes[b].links.add(new Link(a, c));
		}
		int[] ret = new int[q];
		for (int i = 0; i < q; i++) {
			used = new boolean[n];
			ln = new StringTokenizer(in.readLine());
//			Go through every request
			int k = Integer.parseInt(ln.nextToken());
			int v = Integer.parseInt(ln.nextToken()) - 1;
			Relevance startVid = nodes[v];
			used[v] = true;
			PriorityQueue<Link> queue = new PriorityQueue<Link>();
			for (int j = 0; j < startVid.links.size(); j++) {
				Link temp = startVid.links.get(j);
				if (temp.kVal >= k) {
					used[temp.vid] = true;
					queue.add(temp);
					ret[i]++;
				}
			}
			while (!queue.isEmpty()) {
				Link curr = queue.poll();
				Relevance currVid = nodes[curr.vid];
				for (int j = 0; j < currVid.links.size(); j++) {
					Link temp = currVid.links.get(j);
					if (temp.kVal >= k && !used[temp.vid]) {
						used[temp.vid] = true;
						queue.add(temp);
						ret[i]++;
					}
				}
			}
		}
		for (int i = 0; i < q; i++) {
			out.println(ret[i]);
		}
		out.close();
		in.close();
	}
	
	static class Relevance {
		LinkedList<Link> links = new LinkedList<Link>();
		
		public Relevance() {

		}
	}
	
	static class Link implements Comparable<Link> {
		int vid;
		int kVal;
		
		public Link(int a, int b) {
			vid = a;
			kVal = b;
		}

		@Override
		public int compareTo(Link other) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
