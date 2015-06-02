package scheduleOptimalizator;

import java.util.List;

public abstract class Solution implements Comparable<Solution> {
	static public class StudentClassProjection {
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
	}
	abstract public Solution cross(Solution sollution);
	abstract public Solution  mutate();
	abstract public int getRating();//ma tylko zwracac warto��, nie liczy� za duzo
	abstract public void updateValues();//to bede wywo�ywa� jak zmutuje lub uzyskam nowe przez krzy�owanie
	abstract public int[] getStats();//tutaj w wektorze te dane z poszczegolnych iteracji chcia�bym, ilo�� zaj�� przepe�nionych, ilo�� student�w z nieobecno�ciami itd
	
	public int compareTo(Solution arg0) {
		// TODO Auto-generated method stub
		 return arg0.getRating() - this.getRating();
	}

	public List<StudentClassProjection> studentclassprojection;
	public boolean instudentcopy; // kopiowanie zajec w obrebie studenta
	public boolean intertypecopy; // krzyzowanie zajec o roznych typach
	public Solution() {
		
	}
}

