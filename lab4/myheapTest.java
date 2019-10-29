import java.io.*;
import java.util.*;

class myheapTest {
	
	public static void main(String[] args) {
		int i;
		MyHeap myheap = new MyHeap();
		myheap.MyHeap();
		int[] test = {3, 1, 4, 6, 8, 2, 13};
		// test build heap
		myheap.buildHeap(test);
		for (i = 1; i < myheap.heap.size(); i++) {
			System.out.print((myheap.heap).get(i) + ", ");
		}
		System.out.println("");
                // test get heapSize
                System.out.println(myheap.getHeapSize());
		// test insert
		myheap.insert(5);
		for (i = 1; i < myheap.heap.size(); i++) {
			System.out.print((myheap.heap).get(i) + ", ");
		}
		System.out.println("");
		// test delete min
		myheap.deleteMin();
		for (i = 1; i < myheap.heap.size(); i++) {
			System.out.print((myheap.heap).get(i) + ", ");
		}
		System.out.println("");

        // test heap Sort decreasing
		int[] sorttest = {5, 2, 7, 12, 6, 1, 9, 3};
		int[] sorted = myheap.heapSortDecreasing(sorttest);
        for (i = 0; i < sorted.length; i++) {
        	System.out.print(sorted[i] + ", ");
        }
        System.out.println("");
	}
}
