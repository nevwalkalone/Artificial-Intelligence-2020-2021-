import java.util.*;

// Space Searcher class used to implement 
// the A* function in order to solve the 
// bridge cross problem , using closed Set

public class SpaceSearcher{
	
	// Attributes
	
	private ArrayList<State> states ;	// states = metwpo anazitisis
	private HashSet<State> closedSet ;	// closed set = kleisto synolo
	
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
			
			State curr_state = this.states.remove(0);	// removing from states always the best score 
														// state, to check it
														
			
			
				
				
				
					if (curr_state.isTerminal()){		// if that state is terminal, game is finished
				
						return curr_state;
				}												// if it's not we check the closed Set
			
					if (!closedSet.contains(curr_state)){			// if this state is not contained in the Closed Set

						this.closedSet.add(curr_state);						// it's added in there, in order to avoid checking it again
						this.states.addAll(curr_state.getChildren());		// and make all possible children states
						Collections.sort(this.states);						// and then sort all states according to their scores

						}
															
			

			}
		
		return null; 				// if terminal state is not found, is returned null

		
	}

}