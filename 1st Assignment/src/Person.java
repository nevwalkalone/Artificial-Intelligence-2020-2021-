
//Class representing a family member

public class Person{

	// unique id of person given from main function during reading family members
	protected int id;	

	// time this person needs to pass from one side to the other
	protected int time;	 

	//Constructors

	public Person(int id, int time){
		this.id = id;
		this.time = time;
	} 

	public Person(){

	}
	
	//Getters

	public int getTime(){
		return time;
	}

	public int getId(){
		return id;
	}

	//Setters

	public void setTime(int time){
		this.time = time;
	}

	public void setId(int id){
		this.id = id;
	}
}