package scheduleOptimalizator;

import java.util.List;

public class Schedule extends Solution {

	//Tutaj bym zrobi� jakies odwzorowanie student�w na zaj�cia. Jaka� macie� idStudenta x przedmioty.
	List<Student> students;
	List<Class> classes;
	
	
	

	public static Schedule generate(List<Student> students, List<Class> classes) {
		return new Schedule();
	}

	@Override
	public Solution cross(Solution sollution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solution mutate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRating() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateValues() {
		// TODO Auto-generated method stub
		
	}

}
