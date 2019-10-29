import java.io.*;
import java.util.*;

class MyHeap {

   ArrayList<Integer> heap;
   int heapCap = 50;

   public int getHeapCap() {
   	  return heapCap;
   }

   public int getHeapSize() {
   	  return heap.size() - 1;
   }

   public boolean isFull() {
      if (getHeapCap() == getHeapSize()) {
      	 return true;
      }
      else {
      	 return false;
      }
   }

   public boolean isEmpty() {
   	  if (getHeapSize() == 0) {
   	  	 return true;
   	  }
   	  else {
   	  	 return false;
   	  }
   }

   // creates heap of default size
   public void MyHeap() {
   	  // heap starts at index 1
      heap = new ArrayList<Integer>(heapCap + 1);
      heap.add(0);
   }

   // creates heap of specified size
   public void MyHeap(int capacity) {
   	  heapCap = capacity;
   	  // heap starts at index 1
      heap = new ArrayList<Integer>(heapCap + 1);
      heap.add(0);
   }

   // must call myHeap before this method
   public boolean buildHeap(int[] array) {
      // check if array fits inside of heap
      if (array.length > heapCap + 1) {
      	 // array doesn't fit inside of heap
      	 return false;
      }
      // initialize array structure with keys in order given
      for (int x = 0; x < array.length; x++) {
      	 heap.add(array[x]);
      }
      // loop from last node with children down to root
      for (int i = (heap.size() / 2); i > 0; i--) {
      	 driftDown(i);
      }
      return true;
   }

   public boolean insert(int item) {
      if (isFull() == false) {
         if (isEmpty() == true) {
         	heap.set(1, item);
         	return true;
         }
         else {
         	// add new item to the end of the heap
         	heap.add(item);
         	// drift up
         	driftUp(getHeapSize());
         	return true;
         }
      }
      else {
      	 return false;
      }
   }

   public int deleteMin() {
   	  int minHeap;
          if (getHeapSize() == 1) {
            return heap.get(1);
          }
   	  if (isEmpty() == false) {
   	  	 // save top of heap
   	  	 minHeap = heap.get(1);
   	  	 // move back of list to first position
   	  	 heap.set(1, heap.get(getHeapSize()));
   	  	 heap.remove(getHeapSize());
   	  	 // drift new head down
   	  	 driftDown(1);
   	  	 return minHeap;
   	  }
   	  else {
   	  	 return 0;
   	  }
   }

   public void driftDown(int index) {
   	  int pos = index;
   	  int tmp = heap.get(pos);
   	  int child;
   	  while (pos * 2 <= getHeapSize()) {		// pos is parent
   	  	 child = pos * 2;
   	  	 // get min child
   	  	 if (child != getHeapSize() && heap.get(child + 1) < heap.get(child)) {
   	  	 	child++;
   	  	 }
   	  	 if (heap.get(child) < tmp) {			// heap[child] is smaller
   	  	 	heap.set(pos, heap.get(child));		// move it up
   	  	 	pos = child;						// drift down pos
   	  	 }
   	  	 else {
   	  	 	break;
   	  	 }
   	  }
   	  heap.set(pos, tmp);
   }

   public void driftUp(int index) {
   	  int pos = index;
   	  int tmp;
   	  while ((pos / 2) > 0) {					// drift up until root
   	  	 // drift up until parent is smaller
   	  	 if (heap.get(pos) < heap.get(pos / 2)) {
   	  	 	tmp = heap.get(pos / 2);
   	  	 	heap.set(pos / 2, heap.get(pos));
   	  	 	heap.set(pos, tmp);
   	  	 }
   	  	 pos = pos / 2;
   	  }
   }

   public static int[] heapSortDecreasing(int[] inputList) {
          MyHeap theheap = new MyHeap();
   	  theheap.MyHeap(inputList.length);						// create heap to store input list
   	  theheap.buildHeap(inputList);							// build heap out of input list
   	  int[] output = new int[inputList.length];		// create storage for output
   	  int i = inputList.length - 1;
   	  while (i >= 0) {
   	  	 // remove top of heap and add to output in reverse order to create decreasing
   	  	 int cut = theheap.deleteMin();
                 output[i] = cut;
                 i--;
   	  }
      return output;
   }
}
