package scheduleOptimalizator;

import java.io.IOException;

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
			Schedule sol = Schedule.generate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.toString(),"EXCEPTION",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
