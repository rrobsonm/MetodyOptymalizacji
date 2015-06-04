package scheduleOptimalizator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.*;

public class Schedule extends Solution {

	//Tutaj bym zrobi³ jakies odwzorowanie studentów na zajêcia. Jakaœ macie¿ idStudenta x przedmioty.
	//List<Student> students;
	//List<Class> classes;
	private static double minpercentofclasses; // %- of required classes ( from 0 to 1)
	private static double classesfilledfactor; // classes fill factor (from 0 to 1) 1 - all required classes 0 -min nb of classes (linear)
	private int rating;
	

	public static Schedule generate(ArrayList<Student> students, ArrayList<Class> classes) {
		Schedule schedule = new Schedule();
		
		for (Student student : students) {
			Random generator = new Random();
			int nbofrequiredclasses = student.requiredClasses.length;
			int nbofminclasses = (int)Math.ceil(nbofrequiredclasses*minpercentofclasses);
			int randomnbofclasses=(int) (nbofminclasses*(1-classesfilledfactor)+generator.nextInt((int)Math.ceil(2*(nbofrequiredclasses-nbofminclasses)*classesfilledfactor)));
			int nbofclasses = randomnbofclasses > nbofrequiredclasses ? nbofrequiredclasses :randomnbofclasses;
			for (int C=0; C<nbofclasses;C++) {
				
				int whattype = student.requiredClasses[C];
				List<Class> tmpclass = classes;
				tmpclass.removeIf(p -> p.getType()!=whattype);

				StudentClassProjection classprojection = new StudentClassProjection(student, tmpclass.get(generator.nextInt(tmpclass.size())));
				schedule.studentclassprojection.add(classprojection);
			}
		}
		return schedule;
	}

	@Override
	public Solution cross(Solution sollution) {
		Random generator = new Random();
		Solution newsollution = new scheduleOptimalizator.Schedule();
		int minnbofprojection = studentclassprojection.size() > sollution.studentclassprojection.size() ? 2 : 1;
		List<StudentClassProjection> currentprojection;
		if(minnbofprojection==1) {
			currentprojection=this.studentclassprojection;
		} else {
			currentprojection=sollution.studentclassprojection;
		}
		int changestudentfromplace = generator.nextInt(currentprojection.size());
		int countprojection=0;
		for(StudentClassProjection projection : currentprojection) {
			if(changestudentfromplace>countprojection) {
				
			} 
			countprojection++;
		}
		newsollution.studentclassprojection=currentprojection;
		return newsollution;
	}

	@Override
	public Solution mutate() {
		// TODO Auto-generated method stub
		Random generator = new Random();
		int nbofmutation = generator.nextInt(maxnbofmutation);
		nbofmutation = nbofmutation > this.studentclassprojection.size() ? this.studentclassprojection.size() : nbofmutation;
		for (int i =0;i<nbofmutation;i++) {
			this.studentclassprojection.add(this.studentclassprojection.get(generator.nextInt(this.studentclassprojection.size())));
		}
		return this;
	}

	@Override
	public int getRating() {
		// TODO Auto-generated method stub
		
		return this.rating;
	}

	@Override
	public int[] getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateValues() {
		// TODO Auto-generated method stub
		int allstudentsrating = 0;
		for (StudentClassProjection projection : studentclassprojection) {
			ArrayList<Student> studentsinprojection = new ArrayList<Student>();
			studentsinprojection = this.getStudents();
			
			for( Student student : studentsinprojection) {
				int[][] slots;
				List<StudentClassProjection> classesinstudent;
				classesinstudent= (studentclassprojection.stream().filter(x -> x.students.getId() == student.getId()).collect(Collectors.toList()));
				slots = new int[classesinstudent.size()][2];
				int[] simpleclasses;
				simpleclasses = new int[classesinstudent.size()];
				for(int i=0; i<classesinstudent.size();i++) {
					slots[i][1]=classesinstudent.get(i).classes.getStartSlot();
					slots[i][2]=classesinstudent.get(i).classes.getStartSlot()+classesinstudent.get(i).classes.getNumberOfSlots();
					simpleclasses[i]=classesinstudent.get(i).classes.getType();
				}
				student.updateValues(slots, simpleclasses);
				allstudentsrating+=(int)Math.floor(10000/student.busyTime-10*student.clashes);
			}
		}
		this.rating=allstudentsrating;
		
	}

}
