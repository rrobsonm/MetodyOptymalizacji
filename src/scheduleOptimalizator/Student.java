package scheduleOptimalizator;

import java.util.Arrays;
import java.util.List;

public class Student {
	
	private int id;
	private int[] requiredClasses;
	private int[] travelTime;         //24 ints. Each corresponds to travel time in minutes. travelTime[1] gets travel time from 1:00 to 1:59
	private int objective;
	private int penalty;
	
	public int getTravelTime(int timeSlotNumber){
		return travelTime[TimeProvider.timeSlotNumberToHour(timeSlotNumber)];
	}
	
	public void updateObjectiveValue(int[][] slots){
		
		

		quicksort(0, slots.length-1, slots);
		
		/*
		java.util.Arrays.sort(slots, new java.util.Comparator<int[]>() {
		    public int compare(int[] a, int[] b) {
		        return Integer.compare(a[0], b[0]);
		    }
		});
		*/

	}
		
	private void quicksort( final int low, final int high, int[][] slots) {
	    int i = low, j = high;
	    // Get the pivot element from the middle of the list
	    int pivot = slots[low + (high-low)/2][0];

	    // Divide into two lists
	    while (i <= j) {
	      // If the current value from the left list is smaller then the pivot
	      // element then get the next element from the left list
	      while (slots[i][0] < pivot) {
	        ++i;
	      }
	      // If the current value from the right list is larger then the pivot
	      // element then get the next element from the right list
	      while (slots[j][0] > pivot) {
	        --j;
	      }

	      // If we have found a values in the left list which is larger then
	      // the pivot element and if we have found a value in the right list
	      // which is smaller then the pivot element then we exchange the
	      // values.
	      // As we are done we can increase i and j
	      if (i <= j) {  	  
	    	int temp[] = slots[i];
	    	slots[i] = slots[j];
	    	slots[j] = temp;  
	        ++i;
	        --j;
	      }
	    }
	    // Recursion
	    if (low < j)
	      quicksort(low, j, slots);
	    if (i < high)
	      quicksort(i, high, slots);
	}
	
	
	
}
