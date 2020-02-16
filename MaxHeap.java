public class MaxHeap {
	int array[];
	int count;
	int capacity;
	public MaxHeap(int capacity) {
		this.capacity =capacity;
		this.array = new int[capacity];
		this.count = 0;
	}
	public MaxHeap(int arr[]) {
		int size = arr.length;
		this.capacity = size;
		this.array = new int[size];
		this.count = size;
		for(int i=0;i<size;i++) {
			this.array[i]=arr[i];
		}
		for(int i=(size-1)/2;i>=0;i--) {
			precolateDown( i);
		}
	}
	/*
	 * Get the parent node of ith-node in the heap
	 */
	private int parent( int i) {
		if(i<=0 || i >= count) {
			return -1;
		}
		return (i-1)/2;
	}
	/*
	 * Get the Left node of ith-node in the heap
	 */
	private  int getLeft(int i) {
		int left = 2*i+1;
		return left >= count ?  -1 :left;
	}
	/*
	 * Get the Right node of ith-node in the heap
	 */
	private int getRight(int i) {
		int right = 2*i+2;
		return right >= count ?  -1 :right;
	}
	public int getMax() {
		return count ==0 ?  -1 :array[0];
	}
	public void precolateDown(int i) {
		int left = getLeft( i);
		int right = getRight( i);
		if( (left != -1 && array[left] > array[i])
			|| (right != -1 && array[right]> array[i]) ) {
			if(left == -1 && right == -1) {
				/*
				 * Nothing needs to be done since root node does't have child nodes
				 */
			} else if(left != -1 && right == -1) {
				/*
				 * verify with root node to validate max-heap property
				 */
				if(array[left] > array[i]) {
					swap(array,left,i);
				}
			} else if(left == -1 && right != -1) {
				/*
				 * This condition will never occur since complete binary tree
				 *  first fill in left child then it fills to right child 
				 */
			} else {
				if(array[left] > array[right]) {
					swap(array,left,i);
					precolateDown( left);
				} else {
					swap(array,right,i);
					precolateDown(right);
				}
			}
		}
	}
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	/*
	 * Copy first element to the result
	 * Copy last element to the fist element
	 * Remove the last element from the array
	 * call precoalte down with root index
	 */
	public int delete(MaxHeap heap) {
		if(count <= 0) {
			return -1;
		}
		int max = array[0];
		array[0]= array[count-1];
		count--;
		precolateDown(0);
		/*
		 * Return the first element
		 */
		return max;
	}
	/*
	 * Increase the heap size if there is no vacant space
	 * Keep the new element at the end of the heap
	 * Heapify the element from bottom to the top
	 */
	public void insert(int data) {
		if(count == capacity) {
			resize();
		}
		count++;
		int i = count-1;
		while(i >0 && data > array[ (i-1)/2]) {
			array[i] = array[(i-1)/2];
			i = (i-1)/2;
		}
		
		array[i] = data;
	}
	private void resize() {
		int arr[] = new int[capacity *2];
		capacity = capacity*2;
		for(int i=0;i<array.length;i++) {
			arr[i] = array[i];
		}
		array = arr;
	}
}
