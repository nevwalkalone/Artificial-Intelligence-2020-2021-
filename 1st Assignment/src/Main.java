import java.util.*;


// Main class solving the bridge cross problem 

public class Main{
	
	public static int max_time;
	
	public static void main(String[] args){

		Scanner in = new Scanner(System.in);
		System.out.println("Give the number of family members");	// Asking user for the num of members in the problem
		int N = in.nextInt();
		
		ArrayList<Person> family = new ArrayList<Person>(); 		// arraylist for the members to be put in

		for (int i = 0; i < N; i++){
			
			
			if (i == 0 ){
				
				System.out.println("Give the time of the " + (i + 1)  + "st member");
			}
			
			if (i == 1 ){
				
				System.out.println("Give the time of the " + (i + 1)  + "nd member");
			}
			
			if (i == 2){
				
				System.out.println("Give the time of the " + (i + 1)  + "rd member");
			}
			
			if (i > 2){
				
				System.out.println("Give the time of the " + (i + 1)  + "th member");
			}
			
			int time = in.nextInt();
			
			Person temp = new Person(i+1, time);
			
			family.add(temp);

		} 
		
		System.out.println("Give the total time limit ");

		max_time = in.nextInt();
		
		
		State init_state = new State(family);			// Initial state of the problem
		

		SpaceSearcher space_searcher = new SpaceSearcher();		// Initializing a new space_searcher in order to solve the problem

		State terminal = null;

		long start = System.currentTimeMillis();				// Holding start time and end time , to count time needed to find the game solution

		terminal = space_searcher.A_StarClosedSet(init_state);		// running the A* algorithm on the initial state 
		
		
		long end = System.currentTimeMillis();

		if (terminal == null){
			
			System.out.println("Sorry, couldn't find solution ");
		}

		else{																// if a solution was found , we print the states path
																			// starting from the initial state until the terminal
			
			State temp = terminal;
			ArrayList<State> path = new ArrayList<State>();
			path.add(terminal);
			
			while(temp.getFather() != null) {
				
				path.add(temp.getFather());
				temp = temp.getFather();
				
				

			}
			Collections.reverse(path);
			System.out.println("********************************************************");
			System.out.println("Finished in " + (path.size()-1) + " steps!");
			System.out.println("\n********************************************************");
			
			
			int counter=0;
			String side="";
			
			for(State item : path) {
				
				if(item.getFather()==null) {
					System.out.println("\nInitial State");
					side="right";
				}
				
				else {

					System.out.println("********************************************************\n");
					System.out.print("STEP "+counter+":");
			
					if (counter%2==1) {
						side="left";
						System.out.println(" Moved from Right to Left\n");
						
						for(Person j:item.getLeftSide()) {
							
							if(!(item.getFather().getLeftSide().contains(j))){
								System.out.println("Person "+j.getId()+" with time: "+j.getTime()+" sec");
							}
						}
					}
					
					else {
						
						side="right";
						System.out.println(" Moved from Left to Right\n");
						
						for(Person j:item.getRightSide()) {
							
							if(!(item.getFather().getRightSide().contains(j))){
								System.out.println("Person "+j.getId()+" with time: "+j.getTime()+" sec");
							}
						}
					}
				}
				
				counter++;
				System.out.println("\n");
				item.print(side);
				System.out.println("Time passed: "+item.getCost()+" sec");
				System.out.println("\n");
				
			}
			
			
		System.out.println("Minimum time for all members to cross the bridge from initial state: "+terminal.getCost()+" sec");
		System.out.println("Bridge crossed successfully by all members within the time limit ("+(max_time)+")");
		}
		
	

		System.out.println("A* with closed set search time : " + (double)(end - start)/1000 + " sec");
		in.close();
	}


}