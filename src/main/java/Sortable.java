package gcj1A;
import java.util.Arrays;

public class Sortable {
	int[] data;
	static int tLength = 10;
	static int range = 1000;
	static int[] randData = Sortable.randArray();
	
	public Sortable(int[] data) {
		this.data = data;
	}
	
	/*
	 * O(n^2)
	 * Every element makes a comparison to every
	 * other element in the worst case
	 * The worst case occurs when the list is in reverse sorted order
	 */
	public void insertionSort() {
		if (!this.isValid()) {
			return;
		}
		for (int i = 1; i < this.data.length; i++)  {
			int tmp = this.data[i];
			int j = i;
			while (j >= 1 && this.data[j-1] > tmp) {
				this.data[j] = this.data[j-1];
				j--;
			}
			this.data[j] = tmp;
		}
		
	}
	/*
	 * improvement on insertion sort
	 * 
	 * Sort every h-th element
	 * Then decrease h and sort ever h-th elem again
	 * Do this until h = 1 which is exactly insertion sort
	 * 
	 * More efficient since we end up running insertion 
	 * sort on an almost sorted array which it can do 
	 * in almost linear time
	 * 
	 * O(n log n) -ish
	 * 
	 * Depends on the sequence best is not known
	 * However hi = (3^i - 1) / 2 is O(n log n) (empirically)
	 * 
	 */
	public void shellSort() {
		int shells = 3;
		while (shells > 0) {
			shells--;

			// Decrease h until it hits 1
			int h;
			if (shells == 0) {
				h = 1;
			} else {
				h = (int)(Math.pow(3, shells) - 1);
			}
			
			// Step 1 Grab every then number starting at 0
			// Then at 1, ... , then at h-1
			for(int i = h; i < this.data.length; i++) {
				int j = i;
				int tmp = this.data[j];
				while (j >= h && this.data[j - h] > tmp) {
					this.data[j] = this.data[j-h];
					j = j - h;
				}
				this.data[j] = tmp;
			}
			
		}
	}
	/*
	 * Finds the minimum in the unsorted section and places
	 * it at the max of the sorted section.
	 */
	public void selectionSort() {
		for (int i = 0; i < this.data.length - 1; i++) {
			int min = i;
			for (int j = i; j < this.data.length; j++) {
				min = this.data[j] < this.data[min] ? j : min;
			}
			int tmp = this.data[i];
			this.data[i] = this.data[min];
			this.data[min] = tmp;
		}
	}
	/*
	 * Quick sort selects a value as the "pivot"
	 * Everything <= than it goes to the left
	 * Everything > than it goes to the right
	 * Then the left section and right section
	 * are recursively sorted
	 * 
	 * Worst performance when pivot is either the 
	 * largest or smallest element in the list
	 * 
	 * Improved quickSort picks the median
	 * from three random values and avoids worst case scenario
	 */
	public void quickSortR(int start, int end) {
		// An array of length 0 or 1 is already sorted
		// Also Return if start is greater than end
		if (end - start < 1) {
			return;
		} else if (end - start == 1) {
			// If the list has two elements just switch them if needed
			if (this.data[end] < this.data[start]) {
				swap(end, start);
			}
			return;
		}
		int pivot = this.data[end];
		int i = start;
		int j = end - 1;
		while (i < j) {
			while (i <= end - 1 && this.data[i] <= pivot) {
				i++;
			}
			while (j >= start && this.data[j] > pivot) {
				j--;
			}
			
			if (i < j && this.data[j] <= pivot && this.data[i] > pivot) {
				swap(i, j);
			}
		}

		swap(i, end);
		quickSortR(start, i - 1);
		quickSortR(i + 1, end);
	}
	public void quickSort() {
		quickSortR(0, this.data.length - 1);
	}
	/*
	 * O(n log n)
	 * Split the list
	 * Run merge sort on the chunk
	 * Start with a pointer at the beginning of each
	 * chunk.  Whichever pointer is pointing at the 
	 * smallest chunk gets copied back into teh original
	 * and that chunk's pointer advances.  Repeat the
	 * proceudre until all the values in teh chunk have been 
	 * placed back into the original array
	 */
	public void mergeSort() {
		mergeSortR(this.data);
	}
	public void mergeSortR(int[] data) {
		if (data.length < 2) {
			return;
		}
		int left = data.length / 2;
		int[] result = new int[data.length];
		Sortable l = new Sortable(Arrays.copyOfRange(data, 0, left));
		Sortable r = new Sortable(Arrays.copyOfRange(data, left, data.length));
		if (l.data.length > 1) {
			l.mergeSort();			
		}
		if (r.data.length > 1) {
			r.mergeSort();			
		}
		int lInd = 0;
		int rInd = 0;
		int resultIndex = 0;
		while (lInd < l.data.length || rInd < r.data.length) {
			if (lInd < l.data.length && 
					(rInd >= r.data.length || l.data[lInd] <= r.data[rInd])) {
				result[resultIndex] = l.data[lInd];
				resultIndex++;
				lInd++;
			} else if (rInd < r.data.length) {
				result[resultIndex] = r.data[rInd];
				resultIndex++;
				rInd++;
			}
		}
		this.data = result;
	}

	public void heapSort() {
		this.initializeHeap();
		for (int i = 0; i < this.data.length - 1; i++) {
			swap(i, this.data.length - 1);
			this.Heapify(this.data.length - 1, i + 1);
		}
	}
	/*
	 * Heap with min all the way on the right
	 * min's Index is at this.data.length - 1
	 * LC = (this.data.length - 1 - parentIndex) * 2 - 1
	 * RC = (this.data.length - 1 - parentIndex) * 2 - 2
	 */
	public void Heapify(int start, int end) {
		for (int i = start; i >= end; i--) {
			int elemCount = this.data.length - 1;
			int lIndex = 2 * i - elemCount - 1;
			int rIndex = lIndex - 1;
			if (rIndex >= end && this.data[lIndex] > this.data[rIndex] && this.data[rIndex] < this.data[i]) {
				swap(i, rIndex);
				Heapify(rIndex, end);
			} else if (lIndex >= end && this.data[lIndex] < this.data[i]) {
				swap(i, lIndex);
				Heapify(lIndex, end);
			}
			
		}
	}
	public void initializeHeap() {
		for (int i = 0; i < this.data.length; i++) {
			Heapify(i, 0);
		}
	}
	public int findMedian() {
		int mid = (int)Math.ceil((double)(this.data.length) / (double)(2)) - 1;
		return findKthTerm(mid, 5);
	}
	/*
	 * Find the Median of small blocks of legnth idk 5
	 * Then find the median of the medians
	 * Then split all the values that are <= and > into two tables
	 * If there are exactly k values <= then the median is the value used to make the split
	 * If size(left) < k values find the k - (size(left) + 1)th element in the right section
	 * In the above its size(left) + 1. The plus one for the median value
	 * 
	 */
	public int findKthTerm(int k, int blockSize) {
//		int blockSize = (int)Math.floor((double)values.length / (double)2);
//		int[] medians = new int[]
		for (int i = 0; i < this.data.length - blockSize; i+=blockSize) {
			this.quickSortR(i, i + blockSize - 1);
		}
		this.quickSortR(this.data.length - blockSize, this.data.length - 1);
		return 0;
	}
	public void swap(int i, int j) {
		int tmp = this.data[i];
		this.data[i] = this.data[j];
		this.data[j] = tmp;
	}
	public static boolean test() {
		boolean result = true;
		result = result && Sortable.testInsertionSort();
		result = result && Sortable.testShellSort();
		result = result && Sortable.testSelectionSort();
		result = result && Sortable.testHeapSort();
		result = result && Sortable.testQuickSort();
		result = result && Sortable.testMergeSort();
		result = result && Sortable.testFindMedian();

		return result;
	}
	public static boolean testInsertionSort() {
		Sortable test = new Sortable(Arrays.copyOf(Sortable.randData, Sortable.randData.length));
		test.insertionSort();
		boolean sorted = test.isSorted();
		boolean correctSize = Sortable.tLength == test.data.length;
		System.out.println(test);
		System.out.println("InsertionSort sorted?: " + sorted);
		System.out.println("InsertionSort sized?: " + correctSize);

		return sorted && correctSize;
	}
	public static boolean testShellSort() {
		Sortable test = new Sortable(Arrays.copyOf(Sortable.randData, Sortable.randData.length));
		test.shellSort();
		boolean sorted = test.isSorted();
		boolean correctSize = Sortable.tLength == test.data.length;
		System.out.println(test);
		System.out.println("ShellSort sorted?: " + sorted);
		System.out.println("ShellSort size?: " + correctSize);

		return sorted && correctSize;
	}
	public static boolean testSelectionSort() {
		Sortable test = new Sortable(Arrays.copyOf(Sortable.randData, Sortable.randData.length));
		test.selectionSort();
		boolean sorted = test.isSorted();
		boolean correctSize = Sortable.tLength == test.data.length;
		System.out.println(test);
		System.out.println("Selection sorted?: " + sorted);
		System.out.println("Selection size?: " + correctSize);

		return sorted && correctSize;
	}
	public static boolean testHeapSort() {
		Sortable test = new Sortable(Arrays.copyOf(Sortable.randData, Sortable.randData.length));
		test.heapSort();
		boolean sorted = test.isSorted();
		boolean correctSize = Sortable.tLength == test.data.length;
		System.out.println(test);
		System.out.println("HeapSort sorted?: " + sorted);
		System.out.println("HeapSort size?: " + correctSize);

		return sorted && correctSize;
	}
	public static boolean testQuickSort() {
		Sortable test = new Sortable(Arrays.copyOf(Sortable.randData, Sortable.randData.length));
		test.quickSort();
		boolean sorted = test.isSorted();
		boolean correctSize = Sortable.tLength == test.data.length;
		System.out.println(test);
		System.out.println("QuickSort sorted?: " + sorted);
		System.out.println("QuickSort size?: " + correctSize);

		return sorted && correctSize;
	}
	public static boolean testMergeSort() {
		Sortable test = new Sortable(Arrays.copyOf(Sortable.randData, Sortable.randData.length));
		test.mergeSort();
		boolean sorted = test.isSorted();
		boolean correctSize = Sortable.tLength == test.data.length;
		System.out.println(test);
		System.out.println("MergeSort sorted?: " + sorted);
		System.out.println("MergeSort size?: " + correctSize);

		return sorted && correctSize;
	}
	public static boolean testFindMedian() {
		Sortable test = new Sortable(Arrays.copyOf(Sortable.randData, Sortable.randData.length));
		test.findMedian();
		boolean found = test.isKthTerm();
		boolean correctSize = Sortable.tLength == test.data.length;
		System.out.println(test);
		System.out.println("MergeSort sorted?: " + sorted);

		return sorted && correctSize;
	}
	public boolean isValid() {
		if (this.data.length <= 1) {
			return false;
		}
		return true;
	}
	public static int[] randArray() {
		int[] testData = new int[Sortable.tLength];
		for(int i = 0; i < testData.length; i++) {
			testData[i] = (int)(Math.random() * Sortable.range);
		}
		return testData;
	}
	public boolean isSorted() {
		for(int i = 0; i < this.data.length - 1; i++) {
			if (this.data[i] > this.data[i+1]) {
				return false;
			}
		}
		
		return true;
	}
	public boolean isKthTerm() {
		this.insertionSort();
		int mid = (int)this.data.length / 2.0
	}
	public String toString() {
		return Arrays.toString(this.data);
	}
	public static void main(String args[]) {
		System.out.println("All Working: " + Sortable.test());
	}
}
