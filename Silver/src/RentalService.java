import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class RentalService {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader("rental.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int m = Integer.parseInt(ln.nextToken());
		int r = Integer.parseInt(ln.nextToken());
		Integer[] milkOutputs = new Integer[n];
		MilkRequest[] milkBuyers = new MilkRequest[m];
		Integer[] rents = new Integer[r];
		int totalOutput = 0;
		for (int i = 0; i < n; i++) {
			milkOutputs[i] = Integer.parseInt(in.readLine());
			totalOutput += milkOutputs[i];
		}
		Arrays.sort(milkOutputs, Collections.reverseOrder());
		int totalGallons = 0;
		for (int i = 0; i < m; i++) {
			ln = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(ln.nextToken());
			int b = Integer.parseInt(ln.nextToken());
			milkBuyers[i] = new MilkRequest(a, b);
			totalGallons += a;
		}
		Arrays.sort(milkBuyers);
		for (int i = 0; i < r; i++) {
			rents[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(rents, Collections.reverseOrder());
		long sum = 0;
//		int rentedCows = n;
//		int lastCow = 0;
		if (totalGallons >= totalOutput) {
			int gallonsLeft = totalOutput;
			for (int i = 0; i < m; i++) {
				if (gallonsLeft < milkBuyers[i].gallons) {
					sum += milkBuyers[i].price * gallonsLeft;
					break;
				} else {
					sum += milkBuyers[i].product;
					gallonsLeft -= milkBuyers[i].gallons;
				}
			}
		} else {
			int gallonsLeft = totalGallons;
			for (int i = 0; i < n; i++) {
				gallonsLeft -= milkOutputs[i];
				if (gallonsLeft <= 0) {
//					lastCow = i;
					break;
				}
			}
			for (int i = 0; i < m; i++) {
				sum += milkBuyers[i].product;
			}
		}
		for (int i = 0; i < n; i++) {
			if (true) {
				
			}
		}
		System.out.println(sum);
		in.close();
		out.close();		
	}

	static class MilkRequest implements Comparable<MilkRequest> {
		int gallons;
		int price;
		int product;
		
		public MilkRequest(int a, int b) {
			gallons = a;
			price = b;
			product = price * gallons;
		}
		
		public int compareTo(MilkRequest other) {
			if (product == other.product) {
				return Integer.compare(other.gallons, this.gallons);
			}
			return Integer.compare(other.product, product);
		}
	}
}
