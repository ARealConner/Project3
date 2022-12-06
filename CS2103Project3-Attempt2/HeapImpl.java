/**
 * A data structure that implements a maximum heap. The heap's internal storage
 * should be based on a standard Java array.
 */
class HeapImpl<T extends Comparable<? super T>> implements Heap<T> {
	private static final int INITIAL_CAPACITY = 128;
	private T[] _storage;
	private int _numElements;

	@SuppressWarnings("unchecked")
	public HeapImpl () {
		_storage = (T[]) new Comparable[INITIAL_CAPACITY];
		_numElements = 0;
	}

	/**
	 * Adds the specified item to the heap.
	 * This operation must run in logarithmic time <em>O</em>O(log <em>n</em>), where <em>n</em>
	 * is the number of items currently stored in the heap.
	 * @param data the item to add
	 */
	public void add (T data) {
		// TODO: implement me
		// check if we need to resize
		if (_numElements == _storage.length) {
			expandStorage();
		}
		// add the data to the end of the array
		_storage[_numElements] = data;
		// increment the number of elements
		_numElements++;
		// bubble up the new element
		bubbleUp(_numElements - 1);
	}

	@SuppressWarnings("unchecked")
	private void expandStorage() {
		T[] newStorage = (T[]) new Comparable[_storage.length * 2];
		System.arraycopy(_storage, 0, newStorage, 0, _storage.length);
		_storage = newStorage;
	}

	private void swap(int index1, int index2) {
		T temp = _storage[index1];
		_storage[index1] = _storage[index2];
		_storage[index2] = temp;
	}

	private int getLeftChildIndex(int index) {
		return index * 2 + 1;
	}

	private int getRightChildIndex(int index) {
		return index * 2 + 2;
	}

	private int getParentIndex(int index) {
		return (index-1) / 2;
	}

	// bubbleUP
	private void bubbleUp(int index) {
		if (index == 0) {
			return;
		}
		int parentIndex = getParentIndex(index);
		if (_storage[index].compareTo(_storage[parentIndex]) > 0) {
			swap(index, parentIndex);
			bubbleUp(parentIndex);
		}
	}


	/**
	 * Removes and returns the currently "largest" item from the heap (which is always at the top).
	 * This operation must run in constant time.
	 * @return the top of the heap
	 */
	public T removeFirst () {
		// TODO: implement me
		T removed = _storage[0];
		_storage[0] = _storage[_numElements - 1];
		_storage[_numElements - 1] = null;
		_numElements--;
		trickleDown(0);
		return removed;
	}

	// trickleDown
	private void trickleDown(int index) {
		int leftChildIndex = getLeftChildIndex(index);
		int rightChildIndex = getRightChildIndex(index);
		int largestIndex = index;
		if (leftChildIndex < _numElements && _storage[leftChildIndex].compareTo(_storage[largestIndex]) > 0) {
			largestIndex = leftChildIndex;
		}
		if (rightChildIndex < _numElements && _storage[rightChildIndex].compareTo(_storage[largestIndex]) > 0) {
			largestIndex = rightChildIndex;
		}
		if (largestIndex != index) {
			swap(index, largestIndex);
			trickleDown(largestIndex);
		}
	}

	/**
	 * Returns the number of items currently stored in the heap.
	 * This operation must run in constant time.
	 * @return the number of items currently stored in the heap.
	 */
	public int size () {
		return _numElements;
	}
}
