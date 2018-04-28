
public class NeighborSort {
	//variable to keep track of the number of data comparisons
	static int comp = 0;
	
	private static void incr() {
		comp++;
	}
	
	public void resetComp() {
		comp = 0;
	}
	
	/**
	 * Array sorting algorithm. Creates a sub_array with the difference of the current and next element (curr - next).
	 * Iterates through the sub_array. Finds groups of positive numbers and reverses that section of the original array.
	 * Determines if the array is sorted and recursively calls neighborSort on the new array.
	 * @param array
	 * @return
	 */
	public int[] neighborSort(int[] array) {
		int[] sub_array = new int[array.length - 1];
		
		//create sub_array by subtracting the next element from the current element
		for (int i = 0; i < array.length - 1; i++) {
			sub_array[i] = array[i] - array[i+1];
		}
		
		int x = 0; 
		int start = 0;
		boolean sorted = true;
		//Finds the groups of positive numbers and will reverse that part of the array
		while (x < sub_array.length) {
			start = x;
			while (sub_array[x] > 0) {				
				incr();								//data comparison takes place here
				if (x == sub_array.length-1) {
					x++;
					break;
				}
				else {
					x++;
				}
			}
			incr();									//accounts for the comparison that results in false
			//don't need to do x+1 because failing the while statement added the neccessary one
			if (start != x) {
				array = reverse(array, start, x);
				sorted = false;
			}
			x++;
		}
		
		if (sorted) {
			return array;
		}
		else {
			//System.out.println("Intermediate array: ");
			//printArray(array);
			return neighborSort(array);
		}
	}
	
	/**
	 * Reverses the elements in the array that are in between indexes start and end. Returns the 
	 * entire array with the specified section reversed.
	 * @param array
	 * @param start
	 * @param end
	 * @return
	 */
	private static int[] reverse(int[] array, int start, int end) {
		int temp = 0;
		while (start < end) {
			temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			start++;
			end--;
		}
		return array;
	}
	
	/**
	 * Prints the parameter array
	 * @param array
	 */
	public void printArray(int[] array) {
		int x = 0;
		System.out.print("[");
		System.out.print(array[x]);
		x++;
		while (x < array.length) {
			System.out.print(", " + array[x]);
			x++;
		}
		System.out.println("]");
	}
}