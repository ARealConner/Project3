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
			T[] _newStorage = (T[]) new Comparable[_storage.length* THE_MULTIPLIER_USED_TO_FIND_THE_STORAGE_INDEX];
			System.arraycopy(_storage, 0, _newStorage, 0, _storage.length);
			_storage = _newStorage;
		}
		_storage[_numElements] = data;
		_numElements++;
		trickleDown(0);
	}

	public T removeFirst () {
		// TODO: implement me
		return null;
	}

	public int size () {
		return _numElements;
	}
}
