package scheduleOptimalizator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Population {

	private int NUMBER_OF_THREADS = 8;
	private int size;
	private int elite;
	private int numberOfChilds;
	private int mutationLevel; //w procentach
	private int crossLevel; //w procentach
	private List<Solution> solutions;
	private Random generator = new Random();
	private LinkedList<int[]> logsBeast = new LinkedList<int[]>();
	private LinkedList<int[]> logsWorst = new LinkedList<int[]>();
	private LinkedList<int[]> logsMiddle = new LinkedList<int[]>();
	
	Population(int elite, List<Solution> list){
		
		if( elite>100 ){
			throw new IllegalArgumentException("Elite must be smaller than 100");
		}
		solutions = new ArrayList<Solution>(list.size()*2);
		Collections.copy(solutions,list);
		Collections.sort(solutions);
		size = solutions.size();
	}
	
	public void evolve() throws InterruptedException{	
		crossingPhase();
		mutatePhase();
		replacmentPhase();
		log();
	}
	
	private void log() {
		// TODO Auto-generated method stub
		logsBeast.add(solutions.get(0).getStats());
		logsWorst.add(solutions.get(size-1).getStats());
		logsMiddle.add(solutions.get(size/2).getStats());
	}

	private void replacmentPhase() {
		// TODO Auto-generated method stub
		Collections.sort(solutions);
		for(int i = solutions.size(); i >= size; i--){
			solutions.remove(i);
		}
		
	}

	private void crossingPhase() throws InterruptedException{//losujemy (size-elite*size)*crossLevel/100 par i tworzymy tyle dzieci. Ka�de dziecko dodawane jest na koniec solutions
		
		CrossingPhase[] cp = new CrossingPhase[NUMBER_OF_THREADS];
		int pairs = (size-elite*size)*crossLevel/100/NUMBER_OF_THREADS;
		
		
		for(int i =0 ; i < NUMBER_OF_THREADS - 1; ++i){
			cp[i]=new CrossingPhase(pairs/NUMBER_OF_THREADS);
			cp[i].run();
		}
		cp[NUMBER_OF_THREADS - 1] = new CrossingPhase(pairs/NUMBER_OF_THREADS+pairs%NUMBER_OF_THREADS);
		cp[NUMBER_OF_THREADS - 1].run();
		
		for(int i =0 ; i < NUMBER_OF_THREADS; ++i){
			cp[i].wait();
			solutions.addAll(cp[i].getChilds());
		}
		
	}
	
	class CrossingPhase extends Thread{

		private int numberOfPairs;
		private List<Solution> childs;
		private boolean ended = false;
		
		CrossingPhase(int numberOfPairs){
			this.numberOfPairs = numberOfPairs;
			childs = new ArrayList<Solution>(numberOfPairs);
		}
		
		public List<Solution> getChilds() {
			if(ended == false){
				throw new IllegalStateException("w�tek si� nie zako�czy�");
			}
			return childs;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < numberOfPairs; ++i){
				Solution tmp = solutions.get(generator.nextInt(size)).cross(solutions.get(generator.nextInt(size)));
				tmp.updateValues();
				childs.add(tmp);
			}
			ended = true;
			notify();
		}
		
	}
	
	private void mutatePhase() throws InterruptedException{//losujemy (solutions.size()-elite*size)*crossLevel/100 osobnik�w i mutujemy ich, z wy��czeniem elity
		
		int numberOfMutations = (solutions.size()-size*elite)*mutationLevel/100;
	
		for (int i = 0; i < numberOfMutations; ++i){
			int index = size*elite + generator.nextInt(solutions.size()-size*elite);
			solutions.get(index).mutate();
			solutions.get(index).updateValues();
		}		
	}
}
