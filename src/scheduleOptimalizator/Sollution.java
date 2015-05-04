package scheduleOptimalizator;

public abstract class Sollution implements Comparable<Sollution> {

	abstract protected void generate();
	abstract public Sollution cross(Sollution sollution);
	abstract public Sollution  mutate();
	abstract public int valuate();
	
	public int compareTo(Sollution arg0) {
		// TODO Auto-generated method stub
		 return arg0.valuate() - this.valuate();
	}
		
}
