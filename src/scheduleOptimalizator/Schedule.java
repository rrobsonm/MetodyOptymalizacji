package scheduleOptimalizator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
	private int absentstudents;
	private int clashes;
	private int target_penalty_absent=100;
	private int target_penalty_clashes=20;
	private int target_add_busytime=25000;
	
	

	public static Schedule generate(ArrayList<Student> students, ArrayList<Class> classes,int minpercentofclassesp, int classesfilledfactorp) {
		Schedule schedule = new Schedule();
		classesfilledfactor = ((double)minpercentofclassesp)/100;
		minpercentofclasses = ((double)minpercentofclassesp)/100;
		schedule.studentclassprojection = new ArrayList<StudentClassProjection>();
		for (Student student : students) {
			Random generator = new Random();
			int nbofrequiredclasses = student.requiredClasses.length;
			int nbofminclasses = (int)Math.ceil(nbofrequiredclasses*minpercentofclasses);
			int randomnbofclasses=(int) (nbofminclasses*(classesfilledfactor)+ ((nbofrequiredclasses-nbofminclasses) > 0 ? generator.nextInt((int)Math.ceil(2*(nbofrequiredclasses-nbofminclasses)*classesfilledfactor)) : 1));
			int nbofclasses = randomnbofclasses > nbofrequiredclasses ? nbofrequiredclasses :randomnbofclasses;
			shuffleArray(student.requiredClasses);
			for (int C=0; C<nbofclasses;C++) {
				
				int whattype = student.requiredClasses[C];
				List<Class> tmpclass = new ArrayList<Class>(classes);
				tmpclass.removeIf(p -> p.getType()!=whattype);

				StudentClassProjection classprojection = new StudentClassProjection(student, tmpclass.get(generator.nextInt(tmpclass.size())));
				schedule.studentclassprojection.add(classprojection);
			}
			
			
		}
		if(schedule.getStudents().size()!=4) {
			int i = 0;
			i=1;
		}
		return schedule;
	}

	@Override
	public Solution cross(Solution sollution) {
		Random generator = new Random();
		Solution newsollution = new scheduleOptimalizator.Schedule();
		int minnbofprojection = studentclassprojection.size() > sollution.studentclassprojection.size() ? 2 : 1;
		List<StudentClassProjection> currentprojection;
		List<StudentClassProjection> otherprojection;

		//List<StudentClassProjection> newprojection = new ArrayList<Solution.StudentClassProjection>();

		Schedule currentsollution;
		Schedule othersollution;
		if(minnbofprojection==1) {
			currentprojection=this.studentclassprojection;
			otherprojection=sollution.studentclassprojection;
			currentsollution=this;
			othersollution=(Schedule) sollution;
		} else {
			currentprojection=sollution.studentclassprojection;
			otherprojection=this.studentclassprojection;
			currentsollution=(Schedule) sollution;
			othersollution=this;
		}
		/*int changestudentfromplace=0;
		if(currentprojection.size()>0) {
			changestudentfromplace = generator.nextInt(currentprojection.size());
		}*/
		int countprojection=0;
		int countprojection2=0;
		//Collections.shuffle(currentprojection);
		//Collections.shuffle(otherprojection);
		
		List<StudentClassProjection> newprojection = new ArrayList<StudentClassProjection>(currentprojection);
		List<StudentClassProjection> newprojection2 = new ArrayList<StudentClassProjection>(currentprojection);
		/*for(StudentClassProjection projection : currentprojection) {
			if(changestudentfromplace>countprojection) {
				StudentClassProjection newproj = otherprojection.get(countprojection);
				//newproj.students=currentprojection.get(countprojection).students;
				int randomnb =generator.nextInt(2);
				int currentclasses=currentsollution.countStudentClasses(newproj.students.getId());
				int countstudentreq=newproj.students.requiredClasses.length;
				if(countstudentreq>currentclasses) {
					//newproj.students=currentprojection.get(countprojection).students;
					newprojection.add(newproj);
					//countprojection2++;
				} else if (countstudentreq<currentclasses) {
					//if(countprojection<newprojection.size())
					newprojection.remove(countprojection2);
					countprojection2--;
				} else{
					//if(countprojection<newprojection.size())
					newprojection.set(countprojection2, newproj);
				}
			} 
			countprojection++;
			countprojection2++;
		}
		*/
		int changestudentfromplace=0;
		if(currentprojection.size()>0) {
			changestudentfromplace = generator.nextInt(currentsollution.getStudents().size());
		}
		if(currentsollution.getStudents().size()!=4) {
			int i = 0;

			i=currentsollution.getStudents().size();
			i=currentsollution.getStudents().size();
		}
		for(Student student : currentsollution.getStudents()) {
			if(changestudentfromplace>countprojection) {
				List<StudentClassProjection> new2 = othersollution.getClassesForStudent(student.getId());
				if(new2.size()>0) {
					//newprojection.removeIf(x -> x.students.getId() == student.getId());
					//newprojection.addAll(new2);
				}
				//for(StudentClassProjection projection : currentsollution.getClassesForStudent(student.getId())) {
				//	
				//}
			}
			countprojection++;
			int currentclasses=othersollution.countStudentClasses(student.getId());
			int countstudentreq=student.requiredClasses.length;
			StudentClassProjection newproj=othersollution.studentclassprojection.get((new Random()).nextInt(othersollution.studentclassprojection.size()));
			newproj.students=student;
			if(countstudentreq>currentclasses) {
				//newproj.students=currentprojection.get(countprojection).students;
				newprojection.add(newproj);
				//countprojection2++;
			} else if (countstudentreq<currentclasses) {
				//if(countprojection<newprojection.size())
			//	newprojection.remove(newprojection.stream().filter(x -> x.students.getId()==student.getId()).collect(Collectors.toList()).get(0));
				//countprojection2--;
			}
		}
		newsollution.studentclassprojection=newprojection2;

		//newsollution.studentclassprojection.addAll(newprojection);
		if(newsollution.getStudents().size()!=4) {
			int i =0;
			i=newsollution.getStudents().size();
		}
		return newsollution;
	}

	@Override
	public Solution mutate() {
		// TODO Auto-generated method stub
		Random generator = new Random();
		int nbofmutation = generator.nextInt(maxnbofmutation);
		nbofmutation = nbofmutation > this.studentclassprojection.size() ? this.studentclassprojection.size() : nbofmutation;
		for (int i =0;i<nbofmutation;i++) {
	//		this.studentclassprojection.add(this.studentclassprojection.get(generator.nextInt(this.studentclassprojection.size())));
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
		int[] result = new int[3];
		result[0]=this.rating;
		result[1]=this.clashes;
		result[2]=this.absentstudents;
		return result;
	}

	@Override
	public void updateValues() {
		// TODO Auto-generated method stub
		int allstudentsrating = 0;
		int allclashes=0;
		int allabsent=0;
		//for (StudentClassProjection projection : studentclassprojection) {
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
					slots[i][0]=classesinstudent.get(i).classes.getStartSlot();
					slots[i][1]=classesinstudent.get(i).classes.getStartSlot()+classesinstudent.get(i).classes.getNumberOfSlots();
					simpleclasses[i]=classesinstudent.get(i).classes.getType();		
				}
				student.updateValues(slots, simpleclasses);
				allstudentsrating+=(int)Math.floor(target_add_busytime/(double)student.busyTime-target_penalty_clashes*(double)student.clashes-target_penalty_absent*(double)student.absent);
				allclashes+=student.clashes;
				allabsent+=student.absent;
			}
		//}
		this.rating=allstudentsrating;
		this.absentstudents=allabsent;
		this.clashes=allclashes;
		
	}
	  static void shuffleArray(int[] ar)
	  {
	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
}
