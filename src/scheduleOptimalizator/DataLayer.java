package scheduleOptimalizator;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.opencsv.CSVReader;

public class DataLayer {
	
	private HashMap<String, Integer> classesNamesTranslations = new HashMap<String, Integer>();
	private HashMap<String, Integer> studentsIdTranslations = new HashMap<String, Integer>();
	private ArrayList<Class> classes = new ArrayList<Class>();
	private ArrayList<Student> students = new ArrayList<Student>();
	private final static int TRAVEL_TIME_TABLE_LENGTH = 24;
	
	DataLayer(String classesPath, String studentsPath) throws Exception{
			
	      
	      CSVReader reader = new CSVReader(new FileReader(classesPath));
	      
	      String [] nextLine;
	      int lineNumber = 0;
	      while ((nextLine = reader.readNext()) != null) {
	        lineNumber++;
	        classesNamesTranslations.put(nextLine[0],lineNumber);
	      }
	      
	      
	      reader = new CSVReader(new FileReader(studentsPath));
	      
	      lineNumber = 0;
	      while ((nextLine = reader.readNext()) != null) {
	        lineNumber++;
	        if(studentsIdTranslations.containsKey(nextLine[0])) throw new IllegalArgumentException("Duplication of student id:"+nextLine[0]);
	        studentsIdTranslations.put(nextLine[0],lineNumber);
	      }
	      
	      
	       reader = new CSVReader(new FileReader(classesPath));
	      
	      lineNumber = 0;
	      while ((nextLine = reader.readNext()) != null) {
	        lineNumber++;
	        
	        DayOfWeek day = DayOfWeek.valueOf(nextLine[1]);
	        LocalTime time = LocalTime.parse(nextLine[2], DateTimeFormatter.ofPattern("HH:mm") );
	        int length = Integer.parseInt(nextLine[3]);
	        int maxNumberOfStudents = Integer.parseInt(nextLine[4]);
	        int classCode = classesNamesTranslations.get(nextLine[0]);
	        int startSlot = TimeProvider.timeToTimeSlotNumber(day, time);
	        
	        classes.add(new Class(classCode,startSlot,length,maxNumberOfStudents));
	      }
	      
	      
	      reader = new CSVReader(new FileReader(studentsPath));
	      
	      lineNumber = 0;
	      while ((nextLine = reader.readNext()) != null) {
	        lineNumber++;
	        
	        //first param id
	        int id = getStudentIntIdForStringId(nextLine[0]);
	        
	        //second  param preparation travelTimes
	        ArrayList<Integer> tmpTravelTimes = new ArrayList<Integer>(24);
	        for (int i = 1; i <= TRAVEL_TIME_TABLE_LENGTH; i++) {
	        	tmpTravelTimes.add(Integer.parseInt(nextLine[i]));
			}
	        
	       //second  param travelTimes
	        int[] travelTimes = tmpTravelTimes.stream().mapToInt(i->i).toArray();
	        
	        //third param preparation requiredClasses
	        ArrayList<Integer> tmpRequiredClasses = new ArrayList<Integer>(24);
	        for (int i = 1 + TRAVEL_TIME_TABLE_LENGTH; i < nextLine.length; i++) {
	        	String className = nextLine[i];
	        	if(className.equals("")) continue; //skipping empty values
	        	if(classesNamesTranslations.containsKey(className)){
	        		tmpRequiredClasses.add(classesNamesTranslations.get(className));
	        	} else{
	        		throw new IllegalArgumentException("Class "+className+" is required by student id:"+nextLine[0]+", this class is not defined in shedule");
	        	}
			}
	        //third param requiredClasses       
	        int[] requiredClasses = tmpRequiredClasses.stream().mapToInt(i->i).toArray();
	        
	        students.add(new Student(id, travelTimes, requiredClasses));
	         
	      }
	             
	}
	
	public ArrayList<Student> getStudentsList(){
		return students;
	}
	
	public ArrayList<Class> getClassList(){
		return classes;
	}
	
	public String getStudentStringIdForInt(int i){
		for (Entry<String, Integer> entry : studentsIdTranslations.entrySet()) {
	        if (Objects.equals(i, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	public String getClassStringIdForInt(int i){
		for (Entry<String, Integer> entry : classesNamesTranslations.entrySet()) {
	        if (Objects.equals(i, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	public int getStudentIntIdForStringId(String str){
		return studentsIdTranslations.get(str);
	}

}
