package gcj1A;
import java.util.Arrays;

/* The Heap is basically a wrapper around an array that maintains 
 * The property that any node is less than (has higher priority)
 * than its children.
 * 
 */
public class Heap {
	private int[] data;
	private int length;
	
	public Heap(int[] data) {
		this.data = data;
		this.length = data.length;
		this.InitializeHeap();
	}
	private void InitializeHeap() {
		for (int i = 0; i < this.length; i++) {
			Heapify(this.data, 0, this.length);
		}
	}
	private void Heapify(int[] data, int start, int end) {
		// Left Child is 2 * i
		// Right Child is 2 * i + 1 
		// Ensure that data[i] <= data[2 * i]
		// Ensure that data[i] <= data[2 * i + 1]
		
		for (int i = start; i < end; i++) {
			int lci = 2 * i + 1;
			int rci = 2 * i + 2;
			int endIndex = i;
			if (rci < data.length && data[rci] <= data[lci] && data[rci] < data[i]) {
				swap(data, rci, i);
				endIndex = rci;
			} else if(lci < data.length && data[lci] < data[i]) {
				swap(data, lci, i);
				endIndex = lci;
			}
			Heapify(data, i, endIndex);
		}
		
	}
	private int pop() {
		int result = this.data[0];
		swap(data, 0, this.length);
		int[] newData = new int[this.length - 1];
		for (int i = 0; i < newData.length; i++) {
			newData[i] = data[i];
		}
//		heapify()
		return result;
	}
	private void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
	/*
	 * @overrides(non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer line = new StringBuffer();
		int maxDepth = maxNodes(this.length);  // max depth = log(n + 1) 
		int maxLeaves = (int)Math.pow(2, maxDepth - 1);// 2 ^ height - 1;
		int pyramidWidth = maxLeaves * 2 + maxLeaves - 1 - 2;
		int currentDepth = 1;
		int i = 0;
		while (currentDepth < maxDepth) {
			int nodesPerLine = (int)Math.pow(2, currentDepth - 1);
			int nodesPrinted = 0;
			int spacesBefore = (int)((pyramidWidth - (2 * nodesPerLine)) / (nodesPerLine + 1));
			
			while (nodesPrinted < nodesPerLine) {
				for(int j = 0; j < spacesBefore; j++) {
					line.append(" ");
				}
				if (this.data[i] < 10) {
					line.append(" ");
				}
				line.append(this.data[i]);
				i++;
				nodesPrinted++;
			}
			currentDepth++;
			line.append("\n");
		}
		return line.toString();
	}
	private int maxNodes(int length) {
		return (int)Math.ceil(Math.log(length + 1) / Math.log(2));
	}
	
	/* pick every path from top to bottom 
	 * and see if the heap condition holds
	 */
	private boolean test() {
		for (int i = this.length - 1; i >= 0; i--) {
			int j = i;
			while(j >= 0) {
				int parentIndex = -1;
				if (j % 2 == 0) {
					parentIndex = (int)((j - 2) / 2);
				} else {
					parentIndex = (int)((j - 1) / 2);
				}
				
				if (parentIndex < 0) {
					break;
				}
				
				if (this.data[j] < this.data[parentIndex]) {
					return false;
				}
				j = parentIndex;
			}
		}
		return true;
	}
	private boolean testTheTest() {
		int numberOfSwaps = 5;
		Heap testHeap = new Heap(this.data);
		for (int j = 0; j < numberOfSwaps; j++) {
			swap(testHeap.data, testHeap.randInd(), testHeap.randInd());
		}
		return testHeap.test();
	}
	public int randInd() {
		return (int)(Math.random() * data.length);
	}
	/*
	 * Heap sort returns the given aray sorted
	 * and uses the Heap Data Structure to do it
	 */
	public void HeapSort(int[] data) {
		Heap h = new Heap(data);
		int[] result = new int[data.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = h.pop();
		}
	}
	public static void main(String args[]) {
		int[] test1 = new int[20];
		for (int i = 0; i < test1.length; i++) {
			test1[i] = (int)(Math.random() * 100);
		}
		System.out.println(Arrays.toString(test1));
		
		Heap h1 = new Heap(test1);
		System.out.println(h1);
		
		
		if (h1.test()) {
			System.out.println("The Heap is correct");
		}
		if (!h1.testTheTest()) {
			System.out.println("Tests are Functioning");
		}
	}
}
