import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class OutOfSorts {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader("sort.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		int n = Integer.parseInt(in.readLine());
		int[] arr = new int[n];
		int[][] dupArr = new int[2][n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(in.readLine());
			dupArr[0][i] = arr[i];
		}
		int lastDiff = 0;
		Arrays.sort(dupArr[0]);
		for (int i = 0; i < n; i++) {
			int curr = arr[i];
			int supposedIndex = bsearch(0, n - 1, curr, dupArr);
			if (i - supposedIndex > 0) {
				dupArr[1][supposedIndex] = 1;
				if (i - supposedIndex > lastDiff) {
					lastDiff = i - supposedIndex;
				}
			}
		}
		out.println(lastDiff + 1);
		in.close();
		out.close();		
	}
	
	static int bsearch(int l, int h, int val, int[][] list) {
		int mid = (h + l) / 2;
		int midVal = list[0][mid];
		if (midVal == val) {
			int min = list[0].length;
			int max = list[0].length;
			int posIndex = mid;
			int negIndex = mid;
			while (negIndex > -1 && list[0][negIndex] == val) {
				if (list[1][negIndex] == 0) {
					min = negIndex;
				}
				negIndex--;
			}
			if (min == list[0].length) {
				while (posIndex < list[0].length && list[0][posIndex] == val) {
					if (list[1][posIndex] == 0) {
						max = posIndex;
					}
					posIndex++;
				}
			}
			return Math.min(min, max);
		}
		if (val < midVal) {
			return bsearch(l, mid - 1, val, list);
		} else {
			return bsearch(mid + 1, h, val, list);
		}
	}
}
