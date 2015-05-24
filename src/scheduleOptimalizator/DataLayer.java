package scheduleOptimalizator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;

public class DataLayer {
	
	private HashMap<String, Integer> classesNamesTranslations = new HashMap<String, Integer>();
	
	DataLayer(String classesPath, String studentsPath) throws FileNotFoundException, IOException{
			
		//csv file containing data
	      String strFile = classesPath;
	      System.out.println(classesPath);
	      CSVReader reader = new CSVReader(new FileReader(strFile));
	      
	      String [] nextLine;
	      int lineNumber = 0;
	      while ((nextLine = reader.readNext()) != null) {
	        lineNumber++;
	        System.out.println("Line # " + lineNumber);

	        // nextLine[] is an array of values from the line
	        System.out.println(nextLine[0] + " etc...");
	      }
	        
	}
	
	public void getStudentsList(){
		
	}
	
	public void getClassList(){
		
	}
	
	private Student readStudentFromLine(String line){
		return new Student();
	}
}
