package scheduleOptimalizator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.opencsv.CSVWriter;

public class Population {

	private int NUMBER_OF_THREADS = 8;
	private int size;
	private int elite;
	private int numberOfChilds;
	private int mutationLevel; //w procentach
	private int crossLevel; //w procentach
	private List<Solution> solutions;
	private Random generator = new Random();
	private ArrayList<LinkedList<int[]>> logs = new ArrayList<LinkedList<int[]>>();
	private DataLayer dataLayer;

	Population(int elite, List<Solution> list, int crossLevel, int mutationLevel, DataLayer dataLayer){
		
		if( elite>100 ){
			throw new IllegalArgumentException("Elite must be smaller than 100");
		}
		this.elite = elite;
		if( crossLevel>100 ){
			throw new IllegalArgumentException("crossLevel must be smaller than 100");
		}
		this.crossLevel = crossLevel;
		if( mutationLevel>100 ){
			throw new IllegalArgumentException("mutationLevel must be smaller than 100");
		}
		this.mutationLevel = mutationLevel;
		solutions = new ArrayList<Solution>(list.size()*2);
		for (int i = 0; i < list.size(); i++) {
			solutions.add(list.get(i));
		}
		Collections.sort(solutions);
		size = solutions.size();
		
		this.dataLayer = dataLayer;
		
		for(int i = 0; i < size; i++ ){
			logs.add(new LinkedList<int []>());
		}
		
	}
	
	public void evolve() throws InterruptedException{	
		crossingPhaseOneThread();
		mutatePhase();
		replacmentPhase();
		log();
	}
	
	private void log() {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < size; ++i){
			logs.get(i).add(solutions.get(i).getStats());
		}
		

	}

	private void replacmentPhase() {
		// TODO Auto-generated method stub
		Collections.sort(solutions);
		for(int i = solutions.size()-1; i >= size; i--){
			solutions.remove(i);
		}
		
	}
	
	private void crossingPhaseOneThread() throws InterruptedException{
		
		int pairs = (int) (((size-elite*size/100.0)*crossLevel)/100);
		
		List<Solution> childs = new ArrayList<Solution>(pairs);
		
		for (int i = 0; i < pairs; ++i){
			Solution tmp = solutions.get(generator.nextInt(size)).cross(solutions.get(generator.nextInt(size)));
			tmp.updateValues();
			childs.add(tmp);
		}
		solutions.addAll(childs);
	}

	private void crossingPhase() throws InterruptedException{//losujemy (size-elite*size)*crossLevel/100 par i tworzymy tyle dzieci. Ka¿de dziecko dodawane jest na koniec solutions
		
		CrossingPhase[] cp = new CrossingPhase[NUMBER_OF_THREADS];
		int pairs = (int) (((size-elite*size/100.0)*crossLevel)/100);
		
		
		for(int i =0 ; i < NUMBER_OF_THREADS - 1; ++i){
			cp[i]=new CrossingPhase(pairs/NUMBER_OF_THREADS);
			cp[i].run();			
		}
		cp[NUMBER_OF_THREADS - 1] = new CrossingPhase(pairs/NUMBER_OF_THREADS+pairs%NUMBER_OF_THREADS);
		cp[NUMBER_OF_THREADS - 1].run();
		
		for(int i =0 ; i < NUMBER_OF_THREADS; ++i){
			while(cp[i].ended==false){};	
	
		}
		for(int i =0 ; i < NUMBER_OF_THREADS; ++i){
			solutions.addAll(cp[i].getChilds());
			//test		
		}
		

	}
	
	class CrossingPhase extends Thread{

		private int numberOfPairs;
		private List<Solution> childs = new ArrayList<Solution>();
		public boolean ended = false;
		
		CrossingPhase(int numberOfPairs){
			this.numberOfPairs = numberOfPairs;
			childs = new ArrayList<Solution>(numberOfPairs);
		}
		
		public List<Solution> getChilds() {
			if(ended == false){
				throw new IllegalStateException("w¹tek siê nie zakoñczy³");
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
		}
		
	}
	
	private void mutatePhase() throws InterruptedException{//losujemy (solutions.size()-elite*size)*crossLevel/100 osobników i mutujemy ich, z wy³¹czeniem elity
		
		int numberOfMutations = (solutions.size()-size*elite)*mutationLevel/100;
	
		for (int i = 0; i < numberOfMutations; ++i){
			int index = size*elite + generator.nextInt(solutions.size()-size*elite);
			solutions.get(index).mutate();
			solutions.get(index).updateValues();
		}		
	}

	public void saveResults(String directory) throws IOException {
		
				
		for(int i = 0; i < logs.size(); i++){
			CSVWriter writer = new CSVWriter(new FileWriter(directory+"/"+"solution_"+i+".csv"));
			ListIterator iterator = logs.get(i).listIterator();
			while(iterator.hasNext()){
				int[] nums = (int[]) iterator.next();
				String[] a=Arrays.toString(nums).split("[\\[\\]]")[1].split(", "); 
				writer.writeNext(a);
			}			
			writer.close();
		}
		
		solutions.get(0).saveResults(directory, dataLayer);
		
		// TODO Auto-generated method stub
		
	}
}
