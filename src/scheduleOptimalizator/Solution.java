package scheduleOptimalizator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;

public abstract class Solution implements Comparable<Solution> {
	static public class StudentClassProjection implements Cloneable {
		public int id;
		public Student students;
		public Class classes;
		public StudentClassProjection(Student student, Class lecture) {
			id=student.getId();
			students=student;
			classes =lecture;
		}
		public StudentClassProjection() {
			
		}
		public boolean equals(Object obj) {
	        if (obj == this) {
	            return true;
	        }
	        if (!(obj instanceof StudentClassProjection)) {
	            return false;
	        }
	        StudentClassProjection other = (StudentClassProjection) obj;
	        return this.students.equals(other.students);
	    }
		
		public StudentClassProjection(StudentClassProjection in) {
			this.students = in.students;
			this.classes = in.classes;
			id=students.getId();
		}
		
	}
	abstract public Solution cross(Solution sollution);
	abstract public Solution  mutate();
	abstract public int getRating();//ma tylko zwracac wartoœæ, nie liczyæ za duzo
	abstract public void updateValues();//to bede wywo³ywa³ jak zmutuje lub uzyskam nowe przez krzy¿owanie
	abstract public int[] getStats();//tutaj w wektorze te dane z poszczegolnych iteracji chcia³bym, iloœæ zajêæ przepe³nionych, iloœæ studentów z nieobecnoœciami itd
	
	public int compareTo(Solution arg0) {
		// TODO Auto-generated method stub
		 return arg0.getRating() - this.getRating();
	}
	
	public ArrayList<Student> getStudents() {
		// Remove Duplicates: place them in new list (see above example).
		ArrayList<Student> result = new ArrayList<>();
		HashSet<Student> set = new HashSet<>();
		for (StudentClassProjection item : this.studentclassprojection ) {
		    if (!set.contains(item.students)) {
			result.add(item.students);
			set.add(item.students);
		    }
		}
	
		return result;
	    }
	public List<StudentClassProjection> getClassesForStudent(int studentid) {
		
		return this.studentclassprojection.stream().filter(x -> x.students.getId() == studentid).collect(Collectors.toList());
	}
	public int countStudentClasses(int studentid) {
		return (int)this.studentclassprojection.stream().filter(x -> x.students.getId() == studentid).count();
		}
	public List<StudentClassProjection> studentclassprojection;
	public boolean instudentcopy; // kopiowanie zajec w obrebie studenta
	public boolean intertypecopy; // krzyzowanie zajec o roznych typach
	public static int maxnbofmutation;
	public Solution() {
		
	}
	public void saveResults(String directory, DataLayer dataLayer) throws IOException {
		// TODO Auto-generated method stub
		CSVWriter writer = new CSVWriter(new FileWriter(directory+"/"+"student_schedule.csv"));
		for(Student student : this.getStudents()) {
			List<String> schedule = new ArrayList<String>();
			schedule.add(dataLayer.getStudentStringIdForInt(student.getId()));
			
			List<StudentClassProjection> classesinstudent= (studentclassprojection.stream().filter(x -> x.students.getId() == student.getId()).collect(Collectors.toList()));
			for (StudentClassProjection classes : classesinstudent) {
				schedule.add(dataLayer.getClassStringIdForInt(classes.classes.getType()));
				
			}
			writer.writeNext(Arrays.toString(schedule.toArray()).split("[\\[\\]]")[1].split(", "));
		}
		writer.close();
		
	}
}

