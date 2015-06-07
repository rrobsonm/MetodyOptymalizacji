package scheduleOptimalizator;

import java.util.Arrays;
import java.util.List;

public class Student {
	
	private int id;
	public int[] requiredClasses;
	private int[] travelTime;         //24 ints. Each corresponds to travel time in minutes. travelTime[1] gets travel time from 1:00 to 1:59
	public int clashes;
	public int busyTime;
	public int absent;
	private final static int TRAVEL_TIME_TABLE_LENGTH = 24; // (24 % TRAVEL_TIME_TABLE_LENGTH) MUST BE 0
	
	Student(int id, int[] travelTime, int[] requiredClasses){
		if(travelTime.length != TRAVEL_TIME_TABLE_LENGTH) throw new IllegalArgumentException("student id:"+id+" should contain "+TRAVEL_TIME_TABLE_LENGTH+" integers representing travel times");
		this.id = id;
		this.requiredClasses = requiredClasses;
		this.travelTime = travelTime;
	}
	public int getId() {
		return id;
	}
	public int getTravelTime(int timeSlotNumber){
		int tmp = 24/TRAVEL_TIME_TABLE_LENGTH;
		return travelTime[TimeProvider.timeSlotNumberToHour(timeSlotNumber)/tmp];
	}
	
	public void updateValues(int[][] slots, int[] classes){//slots = {{poczatek1,koniec1}{poczatek2,koniec2}....}, classes = numery typow zajec na ktore jestem wpisany
		
		quicksort(0, slots.length-1, slots);
		
		//Policzenie kolizji zajêæ POCZATEK
		int cashesCounter = 0;
		
		for(int i =0; i < slots.length-1; ++i ){
			int difference = slots[i+1][0] - slots[i][1];
			if( difference <= 0){
				++cashesCounter;
				
				for(int j=i+2; (j < slots.length) && (slots[j][0] - slots[i][1] <= 0); ++j){
					++cashesCounter;
				}
			}
		}//Policzenie kolizji zajêæ KONIEC
		clashes = cashesCounter;
		
		//Policzenie nieobecnoœci zajêæ POCZATEK
		int absentCounter = 0;
				
		outerloop:
		for(int i =0; i < requiredClasses.length-1; ++i ){
			for (int j = 0; j < classes.length; j++) {
				if(requiredClasses[i] == classes[j]){
				continue outerloop;
				}
			}
			absentCounter++;
		}//Policzenie nieobecnoœci zajêæ KONIEC
		absent = absentCounter;
		
		if(slots.length>2){
			//Policzenie czasu na zajeciach POCZATEK (mo¿e byæ problem z jednymi zajêciami, nie myœla³em o skrajnych przypoadkach dobrze narazie)
			//pierwsze zajecia liczymy recznie bo maja jeden dojazd
			int busyCounter = getTravelTime(slots[0][0]);
			int timeStamp = slots[0][1];
			busyCounter = busyCounter + slots[0][1] - slots[0][0];
			
			//zajecia nie skrajne liczymy petla
			for(int i = 1 ; i < slots.length-1 ; ++i){
				int diff = slots[i][0] - slots[i-1][1];
				
				if(diff > 0){//zajêcia nie zachodz¹ na siebie
					if(diff > getTravelTime(slots[i-1][1]) + getTravelTime(slots[i][0])){//okno wiêksze ni¿ dojazd
						busyCounter = busyCounter + slots[i][1] - slots[i][0] + getTravelTime(slots[i-1][1]) + getTravelTime(slots[i][0]);
					} else{//okno mniejsze rowne dojazd
						busyCounter = busyCounter + slots[i][1] - slots[i][0] + slots[i][0] - slots[i-1][1];
					}
					timeStamp = slots[i][1];
					continue;
				}else{//zajecia zachodza na siebie
					if( slots[i][1] - slots[i-1][1] >= 0 ){//zachodza czesciowo
						timeStamp = slots[i][1];
						busyCounter = busyCounter + slots[i][1] - slots[i-1][1];
					} else{//zachodza calkowicie
						continue;
					}
				}
			}
			
			
			//trzeba jeszcze ostatnie zajecia policzyæ i dojazd timestamp ustawiony na koniec przedostatnich zajec, czas do timestampu
			int diff = slots[slots.length-1][0] - timeStamp;
			if(diff < 0){//je¿eli pocz¹tek ostatniego jest po timeStampie
				if(diff > getTravelTime(slots[slots.length-1][0]) + getTravelTime(slots[slots.length-2][1])){//okno wiêksze ni¿ dojazd
					busyCounter = busyCounter + slots[slots.length-1][1] - slots[slots.length-1][0] + getTravelTime(slots[slots.length-1][1]);
				} else{//okno mniejsze rowne dojazd
					busyCounter = busyCounter + slots[slots.length-1][0] - slots[slots.length-2][1] + slots[slots.length-1][1] - slots[slots.length-1][0];
				}
			} else {//zajecia nachodza na siebie
				if(slots[slots.length-1][1]-slots[slots.length-2][1] >= 0){//zachodza czesciowo
					busyCounter = busyCounter + slots[slots.length-1][1]-slots[slots.length-2][1] + getTravelTime(slots[slots.length-1][1]);
				} //zachodza ca³kowicie brak akcji dodania czasu
			}//Policzenie czasu na zajeciach KONIEC
			busyTime = busyCounter;
		}
		if(slots.length==2){
			//pierwsze zajecia liczymy recznie bo maja jeden dojazd
			int busyCounter = getTravelTime(slots[0][0]);
			int timeStamp = slots[0][1];
			busyCounter = busyCounter + slots[0][1] - slots[0][0];
			
			
			//trzeba jeszcze ostatnie zajecia policzyæ i dojazd timestamp ustawiony na koniec przedostatnich zajec, czas do timestampu
			int diff = slots[slots.length-1][0] - timeStamp;
			if(diff < 0){//je¿eli pocz¹tek ostatniego jest po timeStampie
				if(diff > getTravelTime(slots[slots.length-1][0]) + getTravelTime(slots[slots.length-2][1])){//okno wiêksze ni¿ dojazd
					busyCounter = busyCounter + slots[slots.length-1][1] - slots[slots.length-1][0] + getTravelTime(slots[slots.length-1][1]);
				} else{//okno mniejsze rowne dojazd
					busyCounter = busyCounter + slots[slots.length-1][0] - slots[slots.length-2][1] + slots[slots.length-1][1] - slots[slots.length-1][0];
				}
			} else {//zajecia nachodza na siebie
				if(slots[slots.length-1][1]-slots[slots.length-2][1] >= 0){//zachodza czesciowo
					busyCounter = busyCounter + slots[slots.length-1][1]-slots[slots.length-2][1] + getTravelTime(slots[slots.length-1][1]);
				} //zachodza ca³kowicie brak akcji dodania czasu
			}//Policzenie czasu na zajeciach KONIEC
			busyTime = busyCounter;
		}
		if(slots.length==1){
			//pierwsze zajecia liczymy recznie bo maja jeden dojazd
			int busyCounter = getTravelTime(slots[0][0]);
			int timeStamp = slots[0][1];
			busyCounter = getTravelTime(slots[0][0]) + slots[0][1] - slots[0][0] + getTravelTime(slots[0][1]);
			
			busyTime = busyCounter;
		}
		
		
		
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
