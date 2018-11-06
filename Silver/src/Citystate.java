import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Citystate {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader("citystate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
		int n = Integer.parseInt(in.readLine());
		int[][] arr = new int[n][4];
		for (int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			String str = line.nextToken();
			for (int j = 0; j < 2; j++) {
				char ch = str.charAt(j);
				arr[i][j] = ch - 'A' + 1;
			}
			str = line.nextToken();
			for (int j = 0; j < 2; j++) {
				char ch = str.charAt(j);
				arr[i][j + 2] = ch - 'A' + 1;
			}
		}
		out.println(calc(arr, out));
		in.close();
		out.close();
	}

	public static int calc(int[][] a, PrintWriter out) {
		int[] map = new int[475255];
		int dup = 0;
		for (int i = 0; i < a.length; i++) {
			int c = 17576 * a[i][0] + 676 * a[i][1] + 26 * a[i][2] + a[i][3];
			if (a[i][0] == a[i][2] && a[i][1] == a[i][3]) {
				dup++;
				map[c] = 1;
			} else {
				map[c]++;
			}
		}
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			int c = 17576 * a[i][2] + 676 * a[i][3] + 26 * a[i][0] + a[i][1];
			if (map[c] > 0) {
				if (map[c] > 0) {
					sum += map[c];
				}
			}
		}
		return (sum - dup) / 2;
	}
}