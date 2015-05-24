package scheduleOptimalizator;

public class Class {

	private int type;
	private int startSlot;
	private int numberOfSlots;
	private int maxNumberofStudents;
	
	public int getType() {
		return type;
	}
	public int getStartSlot() {
		return startSlot;
	}
	public int getNumberOfSlots() {
		return numberOfSlots;
	}
	public int getMaxNumberofStudents() {
		return maxNumberofStudents;
	}
	
	public Class(int type, int startSlot, int numberOfSlots,
			int maxNumberofStudents) {
		super();
		this.type = type;
		this.startSlot = startSlot;
		this.numberOfSlots = numberOfSlots;
		this.maxNumberofStudents = maxNumberofStudents;
	}
}
