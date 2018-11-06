import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Cowdance {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 10/10 Correct
		BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int max = Integer.parseInt(ln.nextToken());
		int[] dancers = new int[n];
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			dancers[i] = Integer.parseInt(ln.nextToken());
		}
		calc(n, 1, max, dancers, out);
		out.close();
		in.close();
	}
	
	public static void calc(int h, int l, int t, int[] d, PrintWriter p) {
		if ((l + 1) == h) {
			System.out.println(l);
			p.println(l);
			return;
		} else if ((l + 2) == h) {
			if (simDance(l + 1, t, d)) {
				if (simDance(l, t, d)) {
					System.out.println(l);
					p.println(l);
					return;
				} else {
					System.out.println(l + 1);
					p.println(l + 1);
					return;
				}
			} else {
				System.out.println(h + 1);
				p.println(h + 1);
				return;
			}
		}
		int m = (h + l) / 2;
		if (simDance(m, t, d)) {
			calc(m + 1, l, t, d, p);
		} else {
			calc(h, m + 1, t, d, p);
		}
	}
	
	public static boolean simDance(int k, int t, int[] d) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < k; i++) {
			pq.add(d[i]);
		}
		for (int i = k; i < d.length; i++) {
			pq.add(pq.poll() + d[i]);
		}
		for (int i = 0; i < k - 1; i++) {
			pq.poll();
		}
		int last = pq.poll();
		if (last <= t) {
			return true;
		}
		return false;
	}
}