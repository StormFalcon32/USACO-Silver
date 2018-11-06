import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class LightsOn {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader("lightson.in"));
		//BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\eclipse-workspace\\USACO\\LightsOn\\6.in")); //Initialization
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int m = Integer.parseInt(ln.nextToken());
		@SuppressWarnings("unchecked")
		LinkedList<Room>[][] links = new LinkedList[n][n];
		boolean[][] lightMap = new boolean[n][n];
		boolean[][] farm = new boolean[n][n];
		for (int i = 0; i < m; i++) {
			ln = new StringTokenizer(in.readLine());
			Room temp1 = new Room(Integer.parseInt(ln.nextToken()) - 1, Integer.parseInt(ln.nextToken()) - 1);
			Room temp2 = new Room(Integer.parseInt(ln.nextToken()) - 1, Integer.parseInt(ln.nextToken()) - 1);
			int x1 = temp1.x;
			int y1 = temp1.y;		
			if (links[x1][y1] != null) {
				links[x1][y1].add(temp2);
			} else {
				links[x1][y1] = new LinkedList<Room>();
				links[x1][y1].add(temp1);
				links[x1][y1].add(temp2);
			}
		}
		int ret = 1;
		int prevRet = ret;
		LinkedList<Room> queue = new LinkedList<Room>(); //Initialize the queue
		queue.add(links[0][0].get(0)); //Add 1, 1
		lightMap[0][0] = true;
		while (links[0][0].size() > 1) { //Turn on all lights from 1, 1
			Room temp = links[0][0].get(1);
			int x = temp.x;
			int y = temp.y;
			if (lightMap[x][y] == false) {
				ret++;
			}
			links[0][0].remove(1);
			lightMap[x][y] = true;
			//System.out.println(x + " " + y);
			farm[x][y] = true;
			if (links[x][y] == null) {
				links[x][y] = new LinkedList<Room>();
				links[x][y].add(temp);
			}
		}
		int[] dX = {-1, 1, 0, 0};
		int[] dY = {0, 0, -1, 1};
		Result r = floodFill(lightMap, farm, links, queue, ret, n, dX, dY, out); //FIRST CALL
		ret = r.lR;
		while (ret > prevRet) { //REPEAT FLOODFILL
			prevRet = ret;
			lightMap = r.lMap;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (lightMap[i][j] == true) {
						farm[i][j] = true;
					}
				}
			}
			queue.add(links[0][0].get(0));
			r = floodFill(lightMap, farm, links, queue, ret, n, dX, dY, out);
			ret = r.lR;
		}
		//System.out.println(ret);
		out.println(ret);
		in.close();
		out.close();
	}
	
	public static Result floodFill(boolean[][] lM, boolean[][] fM, LinkedList<Room>[][] l, LinkedList<Room> q, int litRooms, int gS, int[] dX, int[] dY, PrintWriter o) {
		while (!q.isEmpty()) {
			Room temp = q.removeFirst();
			int x = temp.x;
			int y = temp.y;
			fM[x][y] = false;
			for (int i = 0; i < 4; i++) {
				int nX = x + dX[i];
				int nY = y + dY[i];
				if (nX > -1 && nX < gS && nY > -1 && nY < gS) {
					if (fM[nX][nY] == true) {
						fM[nX][nY] = false;
						while (l[nX][nY] != null && l[nX][nY].size() > 1) {
							Room temp2 = l[nX][nY].get(1);
							int x1 = temp2.x;
							int y1 = temp2.y;
							//System.out.println(lM[0][1]);
							if (lM[x1][y1] == false) {
								litRooms++;
								fM[x1][y1] = true;
								lM[x1][y1] = true;
								//System.out.println(x1 + " " + y1);
							}
							l[nX][nY].remove(1);
							if (l[x1][y1] == null) {
								l[x1][y1] = new LinkedList<Room>();
								l[x1][y1].add(temp2);
							}
						}
						q.add(l[nX][nY].get(0));
					}
				}
			}
		}
		Result temp = new Result(litRooms, lM, fM);
		return temp;
	}
	public static class Result {
		int lR;
		boolean[][] lMap;
		boolean[][] fMap;
		
		public Result(int a, boolean[][] b, boolean[][] c) {
			lR = a;
			lMap = b;
			fMap = c;
		}
	}
	public static class Room {
		int x;
		int y;

		public Room(int a, int b) {
			x = a;
			y = b;
		}
	}
}