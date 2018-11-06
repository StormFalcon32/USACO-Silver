import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MeasurementSilver {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Silver\\MeasurementSilver\\2.in"));
		BufferedReader in = new BufferedReader(new FileReader("measurement.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int g = Integer.parseInt(ln.nextToken());
		Measure[] measurements = new Measure[n];
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(ln.nextToken());
			int b = Integer.parseInt(ln.nextToken());
			int c = Integer.parseInt(ln.nextToken());
			measurements[i] = new Measure(a, b, c);
		}
		Arrays.sort(measurements);
		int best = g;
		int changes = 0;
		ArrayList<Cow> productions = new ArrayList<Cow>();
		for (int i = 0; i < n; i++) {
			int change = measurements[i].change;
			int cow = measurements[i].cow;
			boolean changed = false;
			if (productions.isEmpty()) {
				int newProd = g + change;
				if (newProd > g) {
					productions.add(new Cow(cow, newProd, true));
					best = newProd;
				} else {
					productions.add(new Cow(cow, newProd, false));
				}
				changes++;
			} else {
				boolean found = false;
				for (int j = 0; j < productions.size(); j++) {
					Cow temp = productions.get(j);
					best = Math.max(temp.production, best);
					if (temp.ID == cow) {
						found = true;
						temp.production += change;
						if (temp.production > best) {
							best = temp.production;
							for (int k = 0; k < productions.size(); k++) {
								Cow temp1 = productions.get(k);
								if (temp1.best && temp1.production < best) {
									changed = true;
									temp1.best = false;
								}
							}
						} else if (temp.production == best) {
							if (!temp.best) {
								changed = true;
								temp.best = true;
							}
						} else {
							if (temp.best) {
								changed = true;
								temp.best = false;
							}
						}
					}
				}
				if (!found) {
					int newProd = g + change;
					if (newProd >= productions.get(0).production) {
						productions.add(new Cow(cow, newProd, true));
						changed = true;
					} else {
						productions.add(new Cow(cow, newProd, false));
					}
				}
				if (changed) {
					changes++;
				}
			}
		}
		out.println(changes);
		out.close();
		in.close();
	}
	
	static class Measure implements Comparable<Measure> {
		int day;
		int cow;
		int change;
		
		public Measure(int a, int b, int c) {
			day = a;
			cow = b;
			change = c;
		}

		public int compareTo(Measure other) {
			// TODO Auto-generated method stub
			return Integer.compare(this.day, other.day);
		}
	}
	
	static class Cow {
		int ID;
		int production;
		boolean best;
		
		public Cow(int a, int b, boolean c) {
			ID = a;
			production = b;
			best = c;
		}
	}
}
