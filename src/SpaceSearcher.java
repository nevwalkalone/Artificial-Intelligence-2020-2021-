import java.util.*;

// Space Searcher class used to implement 
// the A* function in order to solve the 
// bridge crossing problem , using Closed Set

public class SpaceSearcher{

	private ArrayList<State> states ;	
	private HashSet<State> closedSet ;	
	
	//Constructor

	public SpaceSearcher(){
	
		this.states = null;
		this.closedSet = null;
	} 

	// A* function with closed set 

	public State A_StarClosedSet(State init_state){
		
		this.states = new ArrayList<State>();
		this.closedSet = new HashSet<State>();
		this.states.add(init_state);
		
		while (this.states.size() > 0){	

			// removing from states always the state 
			// with the best score so we can check it
			State curr_state = this.states.remove(0);	      

			// if this is the terminal state, game is finished										
			if (curr_state.isTerminal()){		                 
				return curr_state;
			}
														        
			// if it's not we check the closed Set
			// if this state is not contained in the Closed Set
			if (!closedSet.contains(curr_state)){

				// it's added in there, in order to avoid checking it again
				// and make all possible children states
				// and then sort all states according to their scores
				this.closedSet.add(curr_state);					 
				this.states.addAll(curr_state.getChildren());	
				Collections.sort(this.states);					

			}
		}
		// if terminal state is not found, a null value is returned
		return null; 				
	}
}