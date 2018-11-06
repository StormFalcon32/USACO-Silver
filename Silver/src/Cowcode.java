import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Cowcode {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\Cowcode\\1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
		StringTokenizer ln1 = new StringTokenizer(in.readLine());
		String initial = ln1.nextToken();
		long n = Long.parseLong(ln1.nextToken());
		
		System.out.println(recv(initial, n));
		out.close();
		in.close();
	}
	
	public static char recv(String s, long index) {
		if (index < s.length()) {
			return s.charAt((int) index);
		}
		long len = s.length();
		while (len * 2 < index) {
			len *= 2;
		}
		if (len == index) {
			return recv(s, index - 1);
		}
		return recv(s, index - len - 1);
	}

}
