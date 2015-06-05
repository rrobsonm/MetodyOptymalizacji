package scheduleOptimalizator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ProgramStarter implements Runnable {

	String classesPath, studentsPath, outputPath;
	int size, elite, mutationLevel, crossLevel, maxIterations, minpercentofclasses, classesfilledfactor;
	
	
	
	
	public ProgramStarter(
			String classesPath, 
			String studentsPath, 
			String outputPath, 
			int size,
			int elite, 
			int mutationLevel, 
			int crossLevel, 
			int maxIterations,
			int minpercentofclasses,
			int classesfilledfactor) {
		super();
		this.classesPath = classesPath;
		this.studentsPath = studentsPath;
		this.outputPath = outputPath;
		this.size = size;
		this.elite = elite;
		this.mutationLevel = mutationLevel;
		this.crossLevel = crossLevel;
		this.maxIterations = maxIterations;
		this.minpercentofclasses = minpercentofclasses;
		this.classesfilledfactor = classesfilledfactor;
	}

	public void run(){
		try {
			
			DataLayer dataLayer = new DataLayer(classesPath,studentsPath);

			List<Solution> solutions = new ArrayList<Solution>();
			for(int i = 0; i < size; i++){
				solutions.add(Schedule.generate(dataLayer.getStudentsList(), (dataLayer.getClassList()), minpercentofclasses, classesfilledfactor));
			}
			
			Population population = new Population(elite, solutions, crossLevel, mutationLevel, dataLayer);
			
			for(int i = 0; i < maxIterations ; ++i){
				population.evolve();
			}
				population.saveResults(outputPath);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.toString(),"EXCEPTION",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
