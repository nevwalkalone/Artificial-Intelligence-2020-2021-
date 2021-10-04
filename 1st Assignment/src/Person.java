public class Person{


	//Person attributes

	protected int id;	// unique id of person given from main function during reading family members
	
	protected int time;	 // time that person needs to pass from one side to the other 
	
	

	
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