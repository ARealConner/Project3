class HeapImpl<T extends Comparable<? super T>> implements Heap<T> {
	private static final int INITIAL_CAPACITY = 128;
	private final int THE_MULTIPLIER_USED_TO_FIND_THE_STORAGE_INDEX = 2;
	private final int LEFT_CHILD_INDEXER = 1;
	private final int RIGHT_CHILD_INDEXER = 2;
	private T[] _storage;
	private int _numElements;


	@SuppressWarnings("unchecked")
	public HeapImpl () {
		_storage = (T[]) new Comparable[INITIAL_CAPACITY];
		_numElements = 0;
	}

	@SuppressWarnings("unchecked")
	public void add (T data) {
		// TODO: implement me
		if (_numElements == _storage.length) {
			expandStorage();
		}
		_storage[_numElements] = data;
		_numElements++;
		if (_storage[(_numElements-1)/THE_MULTIPLIER_USED_TO_FIND_THE_STORAGE_INDEX].compareTo(_storage[_numElements-1]) < 0) {
			bubbleUp(_numElements-1);
		}
	}

	private void expandStorage() {
		T[] _newStorage = (T[]) new Comparable[_storage.length* THE_MULTIPLIER_USED_TO_FIND_THE_STORAGE_INDEX];
		for (int i = 0; i < _storage.length; i++) {
			_newStorage[i] = _storage[i];
		}
		_storage = _newStorage;
	}

	public T removeFirst () {
		// TODO: implement me
		if (_numElements == 0) {
			return null;
		} else {
			T temp = _storage[0];
			_storage[0] = _storage[_numElements - 1];
			_numElements--;
			trickleDown(0);
			return temp;
		}
	}

	void bubbleUp(int index) {
		if (index == 0) {
			return;
		}
		int parentIndex = (index - 1) / THE_MULTIPLIER_USED_TO_FIND_THE_STORAGE_INDEX;
		if (_storage[index].compareTo(_storage[parentIndex]) > 0) {
			T temp = _storage[index];
			_storage[index] = _storage[parentIndex];
			_storage[parentIndex] = temp;
			bubbleUp(parentIndex);
		}
	}

	void trickleDown (int index) {
		int _leftChild = index* THE_MULTIPLIER_USED_TO_FIND_THE_STORAGE_INDEX +LEFT_CHILD_INDEXER;
		// index of left child since y'all hate magic numbers and took marks off our project 1
		int _rightChild = index* THE_MULTIPLIER_USED_TO_FIND_THE_STORAGE_INDEX +RIGHT_CHILD_INDEXER;
		// index of right child since y'all hate magic numbers and took marks off our project 1
		/*       0
		       /   \
		      1     2
		     / \   / \
		    3   4 5   6
		    1 = (0*2)+1, 2 = (0*2)+1, 3 = (1*2)+1, 4 = (1*2)+2, ...,
		    n_left = (n_parent*2)+1, n_right = (n_parent*2)+2
		 */
 		int _largeChild; // index of the larger child
		if (index < _storage.length || _storage[index] == null || _storage[_leftChild] == null || _storage[_rightChild] == null) {
			return;
		}
		if (_storage[_leftChild].compareTo(_storage[_rightChild]) > 0) {
			_largeChild = _leftChild;
		} else {
			_largeChild = _rightChild; // if the two children are equal, switching which child doesn't matter
		}
		while (_storage[index].compareTo(_storage[_largeChild]) < 0) {
			T temp = _storage[index];
			_storage[index] = _storage[_largeChild];
			_storage[_largeChild] = temp;
			trickleDown(_largeChild);
		}
	}

	public int size () {
		return _numElements;
	}
}
