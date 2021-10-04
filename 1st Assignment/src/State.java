

import java.util.*; // import needed modules from java.util


// Class State representing a state of the bridge cross problem


	
public class State implements Comparable<State> {		// implementing in order to be able to compare 2 states as we want
	
	// State attributes
	
	private ArrayList<Person> rightSide ;			
													// 2 ArrayLists represent the family members( of class Person) to be
	private ArrayList<Person> leftSide ;			// on each side

	private String light_side;						// the side that the light is at the moment(state)

	private int score = 1000;					// score attribute needed to evaluate each state , default score used for initial state

	private State father = null;			// father state (previous state that resulted to present state) needed mainly
											// for print reasons
											
											
	private int cost=0;						// cost attribute used to represent the time cost needed to
											// achieve the current state
	
	
	
	
	// Constructors

	public State(ArrayList<Person> init){
		this.setRightSide(init);
		this.leftSide = new ArrayList<Person>();
		this.setLightSide("right");
		
	}

	public State(ArrayList<Person> left, ArrayList<Person> right, String lamp,int cost){
		
		
		this.setRightSide(right);
		this.setLeftSide(left);
		this.setLightSide(lamp);
		this.setCost(cost);
		
		
	}

	public State(){

	}
	
	
	
	// Getters

	public ArrayList<Person> getRightSide (){
		return rightSide;
	}

	public int getScore() {
		return score;

	}

	public ArrayList<Person> getLeftSide (){
		return leftSide;
	}	

	public String getLightSide (){

		return light_side;
	}
	
	public State getFather() {
		
		return father;
	}
	
	public int getCost() {
		return this.cost;
	}

	
	
	// Setters 

	public void setRightSide(ArrayList<Person> rightSide){
		this.rightSide =  new ArrayList<Person>(rightSide);
	} 


	public void setLeftSide(ArrayList<Person> leftSide){
		this.leftSide =  new ArrayList<Person>(leftSide);
	} 

	public void setLightSide(String light_side){
		this.light_side = light_side;
	}


	public void setFather (State father){
		this.father = father;
	}
	
	
	private void setCost(int cost) {
		this.cost+=cost;
	}
	
	private void setScore(int score) {
		this.score=score;
	}
	


	// Necessary function in order to check whether a state 
	// is terminal or not
	
	
	public boolean isTerminal(){
		
		return rightSide.isEmpty();		// if right side gets empty(we assume starting from the right side)
										// then everyone passed the bridge and so game finished.
		
	}
	
	

	//Heurestic function used to make an approach of the cost of a specific state
	
	private int heuristic(){

			int h_score = 0;
			int max = 0;

			if (light_side == "right"){					// if the light is on the right side we assume 
														// that all right side people can cross the bridge at the same time.

						for (int i = 0; i < rightSide.size(); i++){

							if (rightSide.get(i).getTime() > max)
								
								max = rightSide.get(i).getTime();

					}

				return max  ;

			}


		    else {												// if the light is on the left side we assume that the fastest left person
																// moves on the right side and then all right side people cross the bridge at the same time
			
					int min = 10000;

					for (int i = 0; i < leftSide.size(); i++){

						if (leftSide.get(i).getTime() < min) {

							min = leftSide.get(i).getTime(); 
							
						}

					}
					
					if(this.getRightSide().isEmpty()){
						
						h_score = min;
						return h_score;
						
					}
		
		
					for (int i = 0; i < rightSide.size(); i++){

						if (rightSide.get(i).getTime() > max) {

							max = rightSide.get(i).getTime();
							
						}
					}
					
				if (max < min) {
							
						max = min;
						
				}
						
				return max + min;
				
		}


}
	

	// A* evaluation , taking into account the time cost to this state  
	// as well as the heuristic approach.
	
	
	private void evaluate(){

	
		setScore(this.getCost()+this.heuristic());
		
		}	


	
	// getChildren function is used to produce all possible 
	// next states of a specific function 
	
	public ArrayList<State> getChildren(){
		
		ArrayList<State> children = new ArrayList<State>();
		ArrayList<Person> temp = new ArrayList<Person>();
		State child; 
		int maxim=0;
		int time1=0;
		int time2=0;
		


		if (light_side == "right"){												
																				
			if (this.leftSide.isEmpty() && (this.rightSide.size()==1)) {
				
				
				child=new State(this.leftSide,this.rightSide,"right",this.cost);
				
				child.setFather(this);
				
				child.setCost(this.rightSide.get(0).time);
				
				temp.add(this.rightSide.get(0));
				
				child.moveLeft(temp);
				
				if (child.getCost()<=Main.max_time){

							children.add(child);
							
							}
			}
		
																	// if light is on the right side of the current state 
																	// we only check next states coming out of moves form 2 person
																	// to left , since moving only 1 wound had not any interest on our goal
			
				else {														 
				
					for (int i = 0; i < rightSide.size(); i++){

						for (int j = i + 1; j < rightSide.size() ; j++){

							temp.clear();
					
					
						
							child=new State(this.leftSide,this.rightSide,"right",this.cost);
							child.setFather(this);
							temp.add(this.rightSide.get(i));

							temp.add(this.rightSide.get(j));
	
							time1=this.rightSide.get(i).getTime();
							time2=this.rightSide.get(j).getTime();
						
						
							maxim=Math.max(time1, time2);
						

							child.moveLeft(temp);
						
							child.setCost(maxim);

							child.evaluate();
							
							if (child.getCost()<=Main.max_time){

							children.add(child);
							
							}

					}
				}
			}
		}
		
		
		
		else if (light_side == "left"){								// if light is on the left side of the current state 
																	// we only check next states coming out of moves form only 1 person
																	// right, since moving 2 would had no interest
			
				for (int i = 0; i < this.leftSide.size(); i++){

						child = new State(this.leftSide, this.rightSide, this.light_side,this.cost);
						
						
						Person person=this.leftSide.get(i);
						
						time1=person.getTime();
						
						child.moveRight(person);
						
						child.setCost(time1);
						
						child.evaluate();

						child.setFather(this);

						if (child.getCost()<=Main.max_time){

							children.add(child);
							
							}

			}
		}
		
		return children;
		
		
	}





	// move functions used to move family members/member 
	// given as argument to the left or right side


	private void moveLeft(ArrayList<Person> people){
		
		
		for (int i = 0; i < people.size(); i++){
			
				if(this.rightSide.remove(people.get(i)))
					this.leftSide.add(people.get(i));
		}
		
	
		
		this.setLightSide("left");
		
		
		
	}

	private void moveRight(Person person){
	
		
			if(this.leftSide.remove(person)){
				this.rightSide.add(person);
			
		}
			
			
			this.setLightSide("right");
	}
	
	
	
	// CompareTo function being overriden in order to
	// sort states by their scores , so in A* we can choose the best 
	
	@Override

	public int compareTo(State s){						

		return Double.compare(this.score, s.score);

	}
	
	
	
	//Print function used to represent each state by the way
	// people and the light are located between 2 sides.
	
	public void print(String side) {
		
		if (side=="left") {
			System.out.print("[lamp] ");
		}
		
		for(int j=0; j<this.getLeftSide().size();j++){
		System.out.print("p"+this.getLeftSide().get(j).getId()+" ");
		}
		
		System.out.print("                                                                     ");
		
		if(side=="right") {
			System.out.print("[lamp] ");
		}
		
		for(int j=0; j<this.getRightSide().size();j++){
			System.out.print("p"+this.getRightSide().get(j).getId()+" ");
			}
			
		System.out.println();
		
		
		System.out.print("--------------------------------------"+"______________________________"+"--------------------------------------\n\n");
		//System.out.print("___________________________"+"-----------------------"+"___________________________\n\n");
		
	}


}
