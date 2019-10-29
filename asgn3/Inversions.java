import java.util.*;
import java.io.*;

class Inversions {

	public static int invCounter(int[] arr) {
		int l = 0;
		int r = arr.length - 1;
		return mergeSort(arr, l, r);
	}

	public static int merge(int[] array, int left, int middle, int right) {
		int i = 0;		// position in leftArray
		int j = 0;		// position in rightArray
		int k = left;		// position in array
		int numSwap = 0;

		// create separate arrays for left and right
		int[] leftArray = Arrays.copyOfRange(array, left, middle + 1);
		int[] rightArray = Arrays.copyOfRange(array, middle + 1, right + 1);

		// i scans through left and j scans through right
		// Conquer step
		while (i < leftArray.length && j < rightArray.length) {
			// if leftArray[i] <= rightArray[j] then left array is in proper position
			if (leftArray[i] <= rightArray[j]) {
				array[k] = leftArray[i];
				k++;				// increment place in array
				i++;				// increment place in leftArray
			}
			// if leftArray[i] > rightArray[j] then a swap must occur
			else {
				array[k] = rightArray[j];
				k++;				// increment place in array
				j++;				// increment place in rightArray
				// calculated by how many other items swapped number crossed in left half
				numSwap += (middle + 1) - (left + i);
			}
		}
		// add the rest of the ints to array
		for (int a = i; a < leftArray.length; a++) {
			array[k] = leftArray[a];
			k++;					// increment place in array
		}
		for (int b = j; b < rightArray.length; b++) {
			array[k] = rightArray[b];
			k++;					// increment place in array
		}
		return numSwap;
	}

	public static int mergeSort(int[] array, int left, int right) {
		int InvCount = 0;
		int middle;
		if (left < right) {
			// find middle of array
			middle = (left + right) / 2;
			// recursivly mergeSort the left and right half
			// Divide step
			InvCount += mergeSort(array, left, middle);
			InvCount += mergeSort(array, middle + 1, right);

			// merge arrays
			// Combine step
			InvCount += merge(array, left, middle, right);
		}
		return InvCount;
	}
}
