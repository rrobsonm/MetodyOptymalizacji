package scheduleOptimalizator;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeProvider {
	
	private static final int slotLength=1;

	public static int timeToTimeSlotNumber(DayOfWeek day, LocalTime time){
		int timeSlotNumber=0;
		
		if(60%slotLength != 0) throw new IllegalArgumentException("slotLength problem");
		
		if(time.getSecond() != 0) throw new IllegalArgumentException("time in timeToTimeSlotNumber musn't has seconds");
		
		timeSlotNumber = (day.getValue()-1)*24*60/slotLength;
		timeSlotNumber = timeSlotNumber + time.getHour()*60/slotLength;
		timeSlotNumber = timeSlotNumber + time.getMinute()/slotLength;
		
		return timeSlotNumber;
	}
}
