import java.io.*;
import java.util.*;

class inversionTest {

	public static void main(String[] args) {

		Inversions invert = new Inversions();

		int[] test1 = {6, 4, 3, 1};
		int[] test2 = {2, 3, 8, 6, 1};
		int[] test3 = {2, 4, 1, 3, 5};

		System.out.println("Correct=6	test1: " + invert.invCounter(test1));	// 6
		System.out.println("Correct=5	test2: " + invert.invCounter(test2));	// 5
		System.out.println("Correct=3	test3: " + invert.invCounter(test3));	// 3
	}
}
