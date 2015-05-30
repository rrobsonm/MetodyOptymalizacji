package scheduleOptimalizator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ProgramStarter implements Runnable {

	String classesPath, studentsPath;
	int size, elite, mutationLevel, crossLevel, maxIterations;
	
	
	
	
	public ProgramStarter(String classesPath, String studentsPath, int size,
			int elite, int mutationLevel, int crossLevel, int maxIterations) {
		super();
		this.classesPath = classesPath;
		this.studentsPath = studentsPath;
		this.size = size;
		this.elite = elite;
		this.mutationLevel = mutationLevel;
		this.crossLevel = crossLevel;
		this.maxIterations = maxIterations;
	}

	public void run(){
		try {
			
			DataLayer dataLayer = new DataLayer(classesPath,studentsPath);

			List<Solution> solutions = new ArrayList<Solution>();
			for(int i = 0; i < size; i++){
				solutions.add(Schedule.generate(dataLayer.getClassList(), dataLayer.getStudentsList()));
			}
			
			Population population = new Population(elite, solutions);
			
			for(int i = 0; i < maxIterations ; ++i){
				population.evolve();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.toString(),"EXCEPTION",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
