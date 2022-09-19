package heapPrograms;

import java.io.*;
import java.util.*;

public class testHeap {
	private static int lineCounter; // Used to see how many lines are in the data.txt file

	@SuppressWarnings("unchecked")
	public static <T> void main(String[] args) throws IOException {
		File dataFile = new File("data.txt"); // Opens the data.txt file under dataFile
		Scanner dataScanner = new Scanner(dataFile); // Opens scanner on dataFile
		lineCounter = 0; // Initializes lineCounter as 0
		// Counts how many lines there are in the dataFile
		while (dataScanner.hasNextLine()) {
			dataScanner.nextLine();
			lineCounter++;
		}
		dataScanner = new Scanner(dataFile); // Reinitializes the scanner so it starts from the top again.
		Integer[] numbers = new Integer[lineCounter + 1]; // Creates new Integer array to store numbers in, first space not used
		for (int i = 0; i < lineCounter; i++) {
			numbers[i + 1] = dataScanner.nextInt(); // Copies integers from dataScanner to numbers array
		}
		PrintWriter pw = new PrintWriter("DataOutput.txt"); // Creates new file called DataOutput.txt and should overwrite previous files
		testHeap mainHeap = new testHeap(); // Creates new testHeap instance to call methods from
		pw.print("=====================================================================\n"); // Formatting
		mainHeap.sequentialAdd(numbers, pw); // Calls the sequentialAdd method in this class and passes through the numbers array and printwriter
		mainHeap.optimalMethod(numbers, pw); // Calls the optimalAdd method in this class and passes through the numbers array and printwriter
		pw.print("\n====================================================================="); // Formatting
		pw.close(); // Closes printwriter
	}

	// This method handles the sequential add of the content of numbers and also prints out the data in proper format
	public void sequentialAdd(Integer[] array, PrintWriter pw) {
		heap<Integer> sequential = new heap<>(lineCounter); // New heap is created for sequential method with the size of lineCounter
		for (int i = 0; i < lineCounter; i++) {
			sequential.addSequential(array[i + 1]); // Calls the addSequential method in heaps.java and passes through array[i + 1]
		}
		// The rest of the method is printing out formatting and numbers from print methods
		pw.print("Heap built using sequential insertions: ");
		this.heapBuilt(sequential, pw); // Gets the first 10 integers of the heap
		pw.print("\nNumber of swaps in the heap creation: ");
		pw.print(sequential.getNumSwaps()); // Gets number of swaps from sequential heap
		pw.print("\nHeap after 10 removals: ");
		this.heapRemoval(sequential, pw); // GEts the first 10 integers of the heap after removal
	}

	// This method handles the optimal method of creating a heap and prints out the data in proper format
	public <T> void optimalMethod(Integer[] numbers, PrintWriter pw) { // Parameters are the Integer[] array and printwriter
		heap<Integer> optimal = new heap<>(numbers); // Creates new optimal heap and passes through the numbers array
		// Same as the sequentialAdd method
		pw.print("\n\nHeap built using optimal insertions: ");
		this.heapBuilt(optimal, pw);
		pw.print("\nNumber of swaps in the heap creation: ");
		pw.print(optimal.getNumSwaps());
		pw.print("\nHeap after 10 removals: ");
		this.heapRemoval(optimal, pw);
	}

	// This method prints out the first 10 integers from the heap passed through
	public <T> void heapBuilt(heap<Integer> heap, PrintWriter pw) {
		for (int i = 0; i < 10; i++) {
			int nextInt = heap.getHeap(i);
			if (i < 9) { // If statement prevents there from being just 1 comma at the end 
				pw.print(nextInt);
				pw.print(",");
			} else {
				pw.print(nextInt + ",...");
			}
		}
	}

	// This method calls the removeMax method, in the heap passed through, 10 times, and then prints out the numbers in proper formatting
	public <T> void heapRemoval(heap<Integer> heap, PrintWriter pw) { // Parameters are the Integer[] array and printwriter
		for (int i = 0; i < 10; i++) {
			heap.removeMax();
		}
		for (int i = 0; i < 10; i++) {
			int nextInt = heap.getHeap(i);
			if (i < 9) { // If statement prevents there from being just 1 comma at the end 
				pw.print(nextInt);
				pw.print(",");
			} else {
				pw.print(nextInt + ",...");
			}
		}
	}
}
