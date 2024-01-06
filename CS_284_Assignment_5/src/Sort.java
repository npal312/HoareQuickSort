import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sort {

	private static class Interval{
		/** data fields */
		private int lower;
		private int upper;
		
		/** Creates an Interval instance with lower and upper bounds */
		public Interval(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
		
		/** Returns the lower bound of the Interval instance */
		public int getLower() {
			return this.lower;
		}
		
		/** Returns the upper bound of the Interval instance */
		public int getUpper() {
			return this.upper;
		}
		
		/** Returns if the bounds of two Interval instances are equal to each other */
		public boolean equals(Object o) {
			return (((Interval)o).getUpper() == upper && ((Interval)o).getLower() == lower);
		}
		
		/** Creates a hashCode of the Interval instance using the lower and upper bounds */
		public int hashCode() {
			return lower * lower + upper;
		}
		
		/** Creates a String representation of the Interval */
		public String toString() {
			return "Lower = " + lower + ", Upper = " + upper;
		}
	}
	
	
	/** Sorts the given array using Lamport's iterative variant of Hoare's quicksort
	 * @param array The array to be sorted
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		if (array.length == 0) { //no elements in array
			return;
		}
		
		Set<Interval> bounds = new HashSet<>();
		bounds.add(new Interval(0, (array.length-1)));
		
		while(bounds.isEmpty() == false) {
			List<Interval> boundList = new ArrayList<Interval>(bounds);
			Interval ival = boundList.get(0);
			
			bubbleSort(array, ival);
			
			if (array.length == 3) { //if only 3 elements in the array
				System.out.println(toString(array));
				return;
			}
			
			int up = ival.getLower();
			int down = 0;
			if (ival.getUpper() - ival.getLower() <= 2) {
				down = (ival.getLower() + ival.getUpper()) / 2;
			}
			else {
				down = ival.getUpper();
			}
			
			T placeholder = array[ival.getLower()];
			array[ival.getLower()] = array[(ival.getLower() + ival.getUpper()) / 2];
			array[(ival.getLower() + ival.getUpper()) / 2] = placeholder;
			
			
			if (ival.getUpper() - ival.getLower() > 2) {
				down = quickSort(array, ival, up, down); //to bring down from quick sort into main function
			}
			
			placeholder = array[ival.getLower()];
			array[ival.getLower()] = array[down];
			array[down] = placeholder;
			
			
			bounds.remove(ival);
			
			if (ival.getUpper() - down > 1) {
				bounds.add(new Interval(down + 1, ival.getUpper()));
			}
			if (down - ival.getLower() > 1) {
				bounds.add(new Interval(ival.getLower(), down - 1));
			}
		}
		
		System.out.println(toString(array));
	}
	
	
	/** Performs two iterations of bubble sort on the given array using the given Interval's bounds
	 * @param array The array to be sorted
	 * @param ival The Interval where the first, middle, and last elements are gotten from
	 */
	public static <T extends Comparable<T>> void bubbleSort(T[] array, Interval ival) {
		int f = ival.getLower();
		int m = (ival.getLower() + ival.getUpper()) / 2;
		int l = ival.getUpper();
		
		for (int i = 0; i < 2; i++) { //two iterations of bubble sort
			if (array[f].compareTo(array[m]) > 0) {
				T placeholder = array[m];
				array[m] = array[f];
				array[f] = placeholder;
			}
			if (array[m].compareTo(array[l]) > 0) {
				T placeholder = array[l];
				array[l] = array[m];
				array[m] = placeholder;
			}	
		}
	}
	
	
	/** Performs quick sorting on a given array using the given Interval's bounds
	 * @param array The array to be sorted
	 * @param ival The Interval where the first and last elements are gotten from
	 * @param up The up pointer used for quick sorting
	 * @param down The down pointer used for quick sorting
	 * @return down (The updated down pointer)
	 */
	public static <T extends Comparable<T>> int quickSort(T[] array, Interval ival, int up, int down) {
		int f = ival.getLower();
		int l = ival.getUpper();
		
		while (up < down) {
			for (int i = f; i <= l; i++) {
				if (array[i].compareTo(array[f]) > 0) {
					up = i;
					break;
				}
				if (i == l) {
					up = l;
				}
			}
			
			for (int i = l; i >= f; i--) {
				if (array[i].compareTo(array[f]) < 0) {
					down = i;
					break;
				}
				if (i == f) {
					down = f;
				}
			}
			
			if (up < down && array[up].compareTo(array[down]) > 0) {
				T placeholder = array[up];
				array[up] = array[down];
				array[down] = placeholder;
			}
		}
		return down;
	}
	
	
	/** Returns a String representation of the array */
	public static <T extends Comparable<T>> String toString(T[] array) {
		StringBuilder sb = new StringBuilder();
		
		if (array.length == 0) {
			return "";
		}
		
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				sb.append(array[i]);
			}
			else {
				sb.append(array[i] + " ");	
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
	}
	
}
