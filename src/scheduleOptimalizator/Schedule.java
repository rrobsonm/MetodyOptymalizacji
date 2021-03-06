package scheduleOptimalizator;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.*;

import scheduleOptimalizator.Solution.StudentClassProjection;

public class Schedule extends Solution  {

	//Tutaj bym zrobi³ jakies odwzorowanie studentów na zajêcia. Jakaœ macie¿ idStudenta x przedmioty.
	//List<Student> students;
	//List<Class> classes;
	private static double minpercentofclasses; // %- of required classes ( from 0 to 1)
	private static double classesfilledfactor; // classes fill factor (from 0 to 1) 1 - all required classes 0 -min nb of classes (linear)
	private int rating;
	private int absentstudents;
	private int clashes;
	private int classesoverload;
	static private int target_penalty_absent=5000;
	static private int target_penalty_clashes=500000;
	static private int target_add_busytime=2;
	static private int interstudentchange=50;
	static private int studentexchangenb=50;
	static private int target_penalty_overload;
	
	

	public Schedule(Schedule schedule) {
		minpercentofclasses = schedule.minpercentofclasses;
		classesfilledfactor = schedule.classesfilledfactor;
		rating = schedule.rating;
		absentstudents = schedule.absentstudents;
		clashes = schedule.clashes;
		target_penalty_absent = schedule.target_penalty_absent;
		target_penalty_clashes = schedule.target_penalty_clashes;
		target_add_busytime = schedule.target_add_busytime;
		studentclassprojection = new ArrayList<StudentClassProjection>(schedule.studentclassprojection);
		// TODO Auto-generated constructor stub
	}
	
	public Schedule() {
		// TODO Auto-generated constructor stub
	}

	public static Schedule generate(ArrayList<Student> students, ArrayList<Class> classes,int minpercentofclassesp, int classesfilledfactorp, int penaltyabsent, int penaltyclashes, int addbusytime, int interstudentchanges, int studentexchanges, int maxmutation, int targetov)  {
		target_penalty_absent=penaltyabsent;
		target_penalty_clashes=penaltyclashes;
		target_add_busytime=addbusytime;
		interstudentchange=interstudentchanges;
		studentexchangenb=studentexchanges;
		maxnbofmutation=maxmutation;
		target_penalty_overload=targetov;
		
		Schedule schedule = new Schedule();
		classesfilledfactor = ((double)minpercentofclassesp)/100;
		minpercentofclasses = ((double)minpercentofclassesp)/100;
		//student.size() x classes.size()
		schedule.studentclassprojection = new ArrayList<StudentClassProjection>(students.size()*classes.size());
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
	public Solution cross2(Solution sollution) {
		Random generator = new Random();
		int studentexchange = 50;
		Solution out = new Schedule(this);
		for(int i = 0; i < this.studentclassprojection.size()*studentexchange/100 ; i++){
			int index = generator.nextInt(out.studentclassprojection.size()-1);
			StudentClassProjection tmp = out.studentclassprojection.get(index);		
			if(out.getClassesForStudent(tmp.students.getId()).size()==1){
				continue;
			}
			
			int index2 = generator.nextInt(sollution.studentclassprojection.size()-1);
			StudentClassProjection tmp2 = sollution.studentclassprojection.get(index2);	
			
			out.studentclassprojection.set(index, new StudentClassProjection(tmp2));
		}
		return out;
	}
	
	@Override
	public Solution cross(Solution sollution) {
		
		Random generator = new Random();
		Solution newsollution = new Schedule(this);
		Schedule currentsollution;
		Schedule othersollution;
		if(true) {
			currentsollution=this;
			othersollution=(Schedule)sollution;
		} else {
			currentsollution=(Schedule)sollution;
			othersollution=this;
		}

		//List<StudentClassProjection> newprojection = new ArrayList<StudentClassProjection>(currentsollution.studentclassprojection);
		
		for(Student student : currentsollution.getStudents()) {
			int randsol =(int)Math.ceil(studentexchangenb*newsollution.getStudents().size()/100);
			randsol = randsol>0 ? randsol : 1;
			if(generator.nextInt(randsol)==0) {
				
			//int otherstudentid= othersollution.getStudents().get(generator.nextInt(othersollution.getStudents().size())).getId();
				for(int i=0;i<Math.ceil(currentsollution.getClassesForStudent(student.getId()).size()*interstudentchange/100);i++) {
					int classesid = othersollution.getClassesForStudent(student.getId()).size();
					if(classesid ==0) {
						
						int i2 =1;
					}
					Class otherclass = othersollution.getClassesForStudent(student.getId()).get(generator.nextInt(classesid)).classes;
					StudentClassProjection newinterstudentprojection = new StudentClassProjection(student, otherclass);
					
					newsollution.getClassesForStudent(student.getId()).get(generator.nextInt(newsollution.countStudentClasses(student.getId())));
				}
			}
		}
		if(othersollution.getStudents().size()!=4) {
			
			int b=1;
		}
		if(newsollution.getStudents().size()!=4) {
			
			int b=1;
		}
		for(Student student : currentsollution.getStudents()) {
			
			int randsol =(int)Math.ceil(studentexchangenb*newsollution.getStudents().size()/100);
			randsol = randsol>0 ? randsol : 1;
			if(generator.nextInt(randsol)==0) {
				
				int otherstudentid = othersollution.getStudents().get(generator.nextInt(othersollution.getStudents().size())).getId();
				int thisstudentid = student.getId();
				if(thisstudentid==otherstudentid)
					break;

				newsollution.studentclassprojection.removeIf(x -> x.students.getId() == student.getId());
				
				List<StudentClassProjection> new2 = new ArrayList<StudentClassProjection>(othersollution.getClassesForStudent(otherstudentid).size());

			    for(StudentClassProjection item: othersollution.getClassesForStudent(otherstudentid)) new2.add(new StudentClassProjection(item));
		
				
				for(StudentClassProjection proj : new2) {
					proj.students=student;
					proj.id=student.getId();
				}
				
		
				newsollution.studentclassprojection.addAll(new2);
				
				
			}
		}
		
		
		return newsollution;
	}
	
	/*@Override
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
		int changestudentfromplace=0;
		if(currentprojection.size()>0) {
			changestudentfromplace = generator.nextInt(currentprojection.size());
		}
		int countprojection=0;
		int countprojection2=0;
		Collections.shuffle(currentprojection);
		Collections.shuffle(otherprojection);
		
		List<StudentClassProjection> newprojection = new ArrayList<StudentClassProjection>(currentprojection);
		
		for(StudentClassProjection projection : currentprojection) {
			if(changestudentfromplace>countprojection) {
				StudentClassProjection newproj = otherprojection.get(countprojection);
				//newproj.students=currentprojection.get(countprojection).students;
				int randomnb =generator.nextInt(2);
				int currentclasses=currentsollution.countStudentClasses(newproj.students.getId());
				int countstudentreq=newproj.students.requiredClasses.length;
				if(countstudentreq>currentclasses) {
					//newproj.students=currentprojection.get(countprojection).students;
					newprojection.add(new StudentClassProjection(newproj));
					//countprojection2++;
				} else if (countstudentreq<currentclasses) {
					//if(countprojection<newprojection.size())
					newprojection.remove(countprojection2);
					countprojection2--;
				} else if (currentclasses>0){
					//if(countprojection<newprojection.size())
					newprojection.set(countprojection2, new StudentClassProjection(newproj));
				}
			} 
			countprojection++;
			countprojection2++;
			
		}
		/*int changestudentfromplace=0;
		if(currentprojection.size()>0) {
			changestudentfromplace = generator.nextInt(currentsollution.getStudents().size());
		}
		
		for(Student student : currentsollution.getStudents()) {
			if(changestudentfromplace>countprojection) {
				for(int i=0;i<Math.ceil(currentsollution.getClassesForStudent(student.getId()).size()*interstudentchange/100);i++) {
					
					StudentClassProjection newinterstudentprojection = new StudentClassProjection(student, lecture);
					
				}
				//List<StudentClassProjection> new2 = new ArrayList<StudentClassProjection>(othersollution.getClassesForStudent(student.getId()));
				//if(new2.size()>0) {
					//newprojection.removeIf(x -> x.students.getId() == student.getId());
				//	newprojection.addAll(new2);
				//}
				//for(StudentClassProjection projection : currentsollution.getClassesForStudent(student.getId())) {
				//	
				//}
			}
			countprojection++;
			int currentclasses=othersollution.countStudentClasses(student.getId());
			int countstudentreq=student.requiredClasses.length;
			StudentClassProjection newproj=new StudentClassProjection(othersollution.studentclassprojection.get((new Random()).nextInt(othersollution.studentclassprojection.size())));
			
			newproj.students=student;
			if(countstudentreq+1>currentclasses) {
				//newproj.students=currentprojection.get(countprojection).students;
				newprojection.add(newproj);
				//countprojection2++;
			} else if (countstudentreq-1<currentclasses) {
				if(countprojection<newprojection.size())
				newprojection.remove(newprojection.stream().filter(x -> x.students.getId()==student.getId()).collect(Collectors.toList()).get(0));
				//countprojection2--;
			}
		}
		newsollution.studentclassprojection=newprojection;

		
		return newsollution;
	}
*/
	@Override
	public Solution mutate() {
		// TODO Auto-generated method stub
		Random generator = new Random();
		int nbm = (int)Math.ceil(maxnbofmutation*this.studentclassprojection.size()/100);
		nbm = nbm>0 ? nbm : 1;
		int nbofmutation = generator.nextInt(nbm);
		nbofmutation = nbofmutation > this.studentclassprojection.size() ? this.studentclassprojection.size() : nbofmutation;
		for (int i =0;i<nbofmutation;i++) {
			int studentid = generator.nextInt(this.getStudents().size());
	//		this.studentclassprojection.add(this.studentclassprojection.get(generator.nextInt(this.studentclassprojection.size())));
			int currentclasses=this.countStudentClasses(studentid);
			int countstudentreq=this.getStudents().get(studentid).requiredClasses.length;
			StudentClassProjection newproj=new StudentClassProjection(this.studentclassprojection.get((new Random()).nextInt(this.studentclassprojection.size())));
			
			newproj.students=this.getStudents().get(studentid);
			if(countstudentreq>currentclasses) {
				//newproj.students=currentprojection.get(countprojection).students;
				this.studentclassprojection.add(newproj);
				//countprojection2++;
			} else if (countstudentreq<currentclasses) {
				
				StudentClassProjection toremove =  this.studentclassprojection.stream().filter(x -> x.students.getId()==studentid).collect(Collectors.toList()).get(0);
				if(this.getClassesForStudent(toremove.students.getId()).size()>1) {
					this.studentclassprojection.remove(toremove);
				}
				//countprojection2--;
			}
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
		int[] result = new int[4];
		result[0]=this.rating;
		result[1]=this.clashes;
		result[2]=this.absentstudents;
		result[3]=this.classesoverload;
		return result;
	}

	@Override
	public void updateValues() {
		// TODO Auto-generated method stub
		int allstudentsrating = 100000000;
		int allclashes=0;
		int allabsent=0;
		int classoverload=0;
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
				allstudentsrating+=(int)Math.floor(-target_add_busytime*(double)student.busyTime-target_penalty_clashes*(double)student.clashes-target_penalty_absent*(double)student.absent);
				allclashes+=student.clashes;
				allabsent+=student.absent;
			}
			for (Class classes : this.getClasses()) {
				classoverload+=target_penalty_overload*(classes.getMaxNumberofStudents() < this.countStudentsForClasses(classes.getType()) ? 0 : this.countStudentsForClasses(classes.getType())-classes.getMaxNumberofStudents());
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
