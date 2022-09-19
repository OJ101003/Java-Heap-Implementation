package heapPrograms;

public class heap<T extends Comparable<? super T>> {
	private T[] heap; // Array of heap entries; ignore heap[0]
	private int lastIndex; // Index of last entry and number of entries
	private static final int DEFAULT_CAPACITY = 25;
	private int numSwaps = 0;

	public heap() {
		this(DEFAULT_CAPACITY);
	}

	// This constructor is used for the optimal method of creating and sorting a heap.
	// Time complexity of this should only be O(n)
	public heap(T[] entry) { // The input is a generic array 
		this(entry.length); // Calls constructor for this heap with the initial capacity of the array length

		// This for loop copies all the contents of entry into the heap array.
		for (int index = 0; index < entry.length - 1; index++) {
			heap[index + 1] = entry[index + 1]; // index + 1 since we don't use the first space in the array
			lastIndex++;
		}
		
		// This for loop calls the reheap method for every non leaf heap entry
		for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--) {
			reheap(rootIndex);
		}
	}
	
	public heap(int initialCapacity) {
		if (initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;

		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
	}

	// This method is the non optimal method of creating a heap
	// The method works by adding each entry sequentially and then upheaping each entry
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addSequential(T newEntry) { // Gets 1 thing as entry
		int newIndex = lastIndex + 1; // This is the location of the new entry. It's + 1 since lastIndex will be taken by something
		int parentIndex = newIndex / 2; // The parent leaf of a node

	// Checks if parentIndex > 0 and if newEntry > heap[parentIndex]
		while ((parentIndex > 0) && ((Comparable) newEntry).compareTo(heap[parentIndex]) > 0) { 
	// If while loop requirements are met, then the new entry and parent leaf are swapped, and the while loop continues until the requirements aren't satisfied
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
			numSwaps++; // Counts the number of swaps that occurred in this method of creating a heap
		}

		heap[newIndex] = newEntry; // Moves the new entry to it's index location
		lastIndex++;
		
	}
	
	// Removes the max heap and reheaps the array
	public T removeMax() {
		T root = null;

		if (!isEmpty()) {
			root = heap[1];
			heap[1] = heap[lastIndex];
			lastIndex--;
			reheap(1);
		}

		return root;
	}

	public T getMax() {
		T root = null;
		if (!isEmpty())
			root = heap[1];
		return root;
	}

	public boolean isEmpty() {
		return lastIndex < 1;
	}

	public int getSize() {
		return lastIndex;
	}

	public void clear() {
		while (lastIndex > -1) {
			heap[lastIndex] = null;
			lastIndex--;
		}
		lastIndex = 0;
	}

	// Returns the whole heap
	public T getHeap(int i){
		return heap[i+1];
	}
	
	// Returns the number of swaps that occurred
	public int getNumSwaps() {
		return numSwaps;
	}
	
	// Reheap method makes sure that the array is a valid heap
	private void reheap(int rootIndex)
	{
	   boolean done = false;
	   T orphan = heap[rootIndex];
	   int leftChildIndex = 2 * rootIndex;

	   while (!done && (leftChildIndex <= lastIndex) )
	   {
	      int largerChildIndex = leftChildIndex; 
	      int rightChildIndex = leftChildIndex + 1;
	      if ( (rightChildIndex <= lastIndex) &&
	            heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
	      {
	         largerChildIndex = rightChildIndex;
	      } 
	      if (orphan.compareTo(heap[largerChildIndex]) < 0)
	      {
	         heap[rootIndex] = heap[largerChildIndex];
	         rootIndex = largerChildIndex;
	         leftChildIndex = 2 * rootIndex;
	      }
	      else
	         done = true;
	      numSwaps++; // Increments the number of swaps that occur 
	   } 
	   heap[rootIndex] = orphan;
	}
}
