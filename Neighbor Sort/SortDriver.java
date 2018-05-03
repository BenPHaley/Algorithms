import java.util.ArrayList;
import java.util.Collections;

/**
 * This is the driver for the original NeighborSort algorithm.
 * @author bhale
 *
 */
public class SortDriver {

	public static void main(String[] args) {
		NeighborSort runner = new NeighborSort();
		
		ArrayList<Integer> comparisons = new ArrayList<Integer>();
		System.out.println("Beginning large test.");
		int n = 10;
		double average = 0.0;
		while (n < 1001) {
			int list[] = new int[n];
			for (int i = 0; i < n; i++) {
				list[i] = i;
			}
			for (int i = 0; i < 1000; i++) {
				shuffle(list);
				runner.neighborSort(list);
				average += runner.comp;
				runner.resetComp();
			}
			average /= 1000.0;
			System.out.println("n = " + n + "; Average comparisons = " + average);
			average = 0.0;
			comparisons.clear();
			n*=10;
		}
		System.out.println("End of large test.");
		
		System.out.println("About to run on truthTableArrays()");
		truthTableArrays();
	}
	
	/**
	 * Cycles through all n^n combinations of arrays and runs NeighborSort on each one.
	 * Finds the maximum, minimum and average number of data comparisons taken. 
	 * 
	 * Started with n = 4 and now have n = 8
	 */
	public static void truthTableArrays() {
		NeighborSort runner = new NeighborSort();
		int max = -1;
		int min = 10000;
		int arraySize = 8;
		double average = 0.0;
		for (int first = 0; first < arraySize; first++) {
			for (int second = 0; second < arraySize; second++) {
				for (int third = 0; third < arraySize; third++) {
					for (int fourth = 0; fourth < arraySize; fourth++) {
						for (int fifth = 0; fifth < arraySize; fifth++) {
							for (int sixth = 0; sixth < arraySize; sixth++) {
								for (int seventh = 0; seventh < arraySize; seventh++) {
									for (int eighth = 0; eighth < arraySize; eighth++) {
										int array[] = {first, second, third, fourth, fifth, sixth, seventh, eighth};
										//runner.printArray(array);
										runner.neighborSort(array);
										//System.out.println("Comparisons for above : " + runner.comp);
										max = max < runner.comp ? runner.comp : max;
										min = min > runner.comp ? runner.comp : min;
										average+=runner.comp;
										//System.out.println();
										runner.resetComp();
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println("Worst comparisons: " + max);
		System.out.println("Average comparisons: " + average/(64.0*64.0*64.0*64.0));
		System.out.println("Best comparisons: " + min);
	}
	
	/**
	 * Shuffles the array by copying all the elements to an arraylist and calling 
	 * Collections.shuffle(). Then the elements are copied back to the original array and
	 * returned. 
	 * @param array
	 * @return
	 */
	public static int[] shuffle(int[] array) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int x : array) {
			list.add(x);
		}
		Collections.shuffle(list);
		int i = 0;
		for (int x : list) {
			array[i] = x;
			i++;
		}
		return array;
	}

}
