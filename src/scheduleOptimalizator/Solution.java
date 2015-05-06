package scheduleOptimalizator;

public abstract class Solution implements Comparable<Solution> {

	abstract protected void generate();
	abstract public Solution cross(Solution sollution);
	abstract public Solution  mutate();
	abstract public int valuate();
	
	public int compareTo(Solution arg0) {
		// TODO Auto-generated method stub
		 return arg0.valuate() - this.valuate();
	}
		
}
