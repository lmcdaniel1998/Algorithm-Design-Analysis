import java.io.*;
import java.util.*;

class dijTester {

	public static void main(String[] args) {
		Dijkstra shortPath = new Dijkstra();
         	String file = "dij_test_3.txt";
         	int[][] answer = shortPath.findShortPaths(file);
		System.out.println("[");
		for (int[] x : answer) {
			System.out.print(" [");
			for (int y : x) {
				System.out.print(y + ", ");
			}
			System.out.print("\b\b],");
			System.out.println();
		}
		System.out.print("\b\b");
		System.out.println("]");
	}
}
