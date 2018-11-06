import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Haybales {
	public static void main(String[] args) throws IOException {
		// 10/10 Correct
		BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
		StringTokenizer ln1 = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		int n = Integer.parseInt(ln1.nextToken());
		int q = Integer.parseInt(ln1.nextToken());
		StringTokenizer ln2 = new StringTokenizer(in.readLine());
		int[] haybales = new int[n];
		for (int i = 0; i < n; i++) {
			haybales[i] = Integer.parseInt(ln2.nextToken());;
		}
		Arrays.sort(haybales);
		for (int i = 0; i < q; i++) {
			StringTokenizer ln = new StringTokenizer(in.readLine());
			int small = Integer.parseInt(ln.nextToken());
			int big = Integer.parseInt(ln.nextToken());
			small = Math.min(small, big);
			big = Math.max(small, big);
			if (small > haybales[haybales.length - 1] || big < haybales[0]) {
				// System.out.println(0);
				out.println(0);
			} else if (big > haybales[haybales.length - 1] && small < haybales[0]) {
				// System.out.println(haybales.length);
				out.println(haybales.length);
			} else if (big > haybales[haybales.length - 1] && small > haybales[0]) {
				int b = haybales.length - 1;
				int a = bsearch(0, b, small, haybales, false);
				// System.out.println(b - a + 1);
				out.println(b - a + 1);
			} else if (big < haybales[haybales.length - 1] && small < haybales[0]) {
				int b = bsearch(0, haybales.length - 1, big, haybales, true);
				int a = 0;
				// System.out.println(b - a + 1);
				out.println(b - a + 1);
			}
			else {
				int b = bsearch(0, haybales.length - 1, big, haybales, true);
				int a = bsearch(0, b + 1, small, haybales, false);
				if (b < a) {
					// System.out.println(0);
					out.println(0);
				} else {
					// System.out.println(b - a + 1);
					out.println(b - a + 1);
				}
			}
		}
		out.close();
		in.close();
	}
	public static int bsearch(int l, int h, int i, int arr[], boolean t) {
		int high = h;
		int low = l;
		int search = i;
		int mid = (high + low) / 2;
		if (arr[mid] == search) {
			return mid;
		} else if (arr[low] == search) {
			return low;
		} else if (arr[high] == search) {
			return high;
		} else if (Math.abs(high - low) == 1) {
			if (t) {
				return low;
			} else {
				return high;
			}
		} else if (arr[mid] > search) {
			high = mid;
		} else if (arr[mid] < search) {
			low = mid;
		}
		return bsearch(low, high, search, arr, t);
	}
}