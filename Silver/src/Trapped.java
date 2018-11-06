import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Trapped {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader("trapped.in"));
		//BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\Trapped\\2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("trapped.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		Haybale[] bales = new Haybale[n];
		int area = 0;
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			Haybale temp = new Haybale(Integer.parseInt(ln.nextToken()), Integer.parseInt(ln.nextToken()));
			bales[i] = temp;
		}
		Arrays.sort(bales);
		int b = 1;
		while (b < bales.length) {
			Haybale[] temp = new Haybale[n];
			for (int i = 0; i < n; i++) {
				temp[i] = bales[i];
			}
			int calc = calc(b, temp, b, b - 1);
			if (calc == 1) {
				area += bales[b].pos - bales[b - 1].pos;
			}
			b++;
		}
		//System.out.println(area);
		out.println(area);
		in.close();
		out.close();		
	}
	
	public static int calc(int cowPos, Haybale[] locations, int high, int low) {
		if (high != locations.length && low != -1) {
			int lowPos = locations[low].pos;
			int highPos = locations[high].pos;
			int lowSize = locations[low].size;
			int highSize = locations[high].size;
			int dist = highPos - lowPos;
			if (dist > highSize) {
				high++;
			} else if (dist > lowSize) {
				low--;
			} else {
				return 1;
			}
			return calc(cowPos, locations, high, low);
		} else {
			return 0;
		}
	}
	
	public static class Haybale implements Comparable<Haybale> {
		int pos;
		int size;
		
		public Haybale(int a, int b) {
			size = a;
			pos = b;
		}

		public int compareTo(Haybale other) {
			return Integer.compare(this.pos, other.pos);
		}
	}
}
