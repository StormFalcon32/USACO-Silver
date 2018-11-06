import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Homework {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\Homework\\10.in"));
		BufferedReader in = new BufferedReader(new FileReader("homework.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		ln = new StringTokenizer(in.readLine());
		int[] scores = new int[n];
		for (int i = 0; i < n; i++) {
			scores[i] = Integer.parseInt(ln.nextToken());
		}
		double[][] kVals = new double[n][3];
		double[] score = score(scores, scores.length - 3, 0, 0);
		int maxInd = scores.length - 3;
		double max = score[0];
		int small = (int) score[1];
		double lastScore = score[0];
		double[] temp1 = {max, score[1], maxInd};
		kVals[kVals.length - 1] = temp1;
		for (int i = n - 4; i >= 0; i--) {
			score = score(scores, i, lastScore, small);
			if (score[0] > max) {
				max = score[0];
				maxInd = i;
				double[] temp = {max, score[1], maxInd};
				kVals[i] = temp;
			} else if (score[0] == max) {
				max = score[0];
				maxInd = i;
				double[] temp = {max, score[1], maxInd};
				kVals[i] = temp;
			}
			small = (int) score[1];
			lastScore = score[0];
		}
		for (int i = 0; i < kVals.length; i++) {
			if (kVals[i][0] == max) {
				//System.out.println((int) (kVals[i][2] + 1));
				out.println((int) (kVals[i][2] + 1));
			}
		}
		out.close();
		in.close();
	}
	
	public static double[] score(int[] a, int justEat, double lastScore, int lastSmall) {
		double sum = 0;
		if (lastScore == 0 && lastSmall == 0) {
			int[] b = a.clone();
			int[] eat = new int[justEat + 1];
			for (int i = 0; i < eat.length; i++) {
				eat[i] = b[i];
				b[i] = 0;
			}
			Arrays.sort(b);
			for (int i = 0; i < eat.length; i++) {
				b[i] = eat[i];
			}
			for (int i = justEat + 2; i < b.length; i++) {
				sum += b[i];
			}
			double[] temp = {sum / (b.length - 2 - justEat), b[eat.length], justEat};
			return temp;
		} else {
			if (a[justEat + 1] < lastSmall) {
				sum = lastScore * (a.length - 3 - justEat) + lastSmall;
				double[] temp = {sum / (a.length - 2 - justEat), a[justEat + 1], justEat};
				return temp;
			} else {
				sum = lastScore * (a.length - 3 - justEat) + a[justEat + 1];
				double[] temp = {sum / (a.length - 2 - justEat), lastSmall, justEat};
				return temp;
			}
		}
		
	}
}