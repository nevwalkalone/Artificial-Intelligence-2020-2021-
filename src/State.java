import java.util.*;

/**
 * Class State representing a state of the bridge crossing problem. Comparable
 * is implemented in order to be able to compare the scores of 2 states
 */

public class State implements Comparable<State> {

	// family members on the right side
	private ArrayList<Person> rightSide;

	// family members on the left side
	private ArrayList<Person> leftSide;

	// the side that the light is at the moment(state)
	private String light_side;

	// score attribute needed to evaluate each state , default score used for
	// initial state
	private int score = 1000;

	/**
	 * father state (previous state that resulted to present state) needed mainly
	 * for print reasons
	 */
	private State father = null;

	/**
	 * cost attribute used to represent the time cost needed to achieve the current
	 * state
	 */
	private int cost = 0;

	// Constructors

	public State(ArrayList<Person> init) {
		this.setRightSide(init);
		this.leftSide = new ArrayList<Person>();
		this.setLightSide("right");
	}

	public State(ArrayList<Person> left, ArrayList<Person> right, String lamp, int cost) {
		this.setRightSide(right);
		this.setLeftSide(left);
		this.setLightSide(lamp);
		this.setCost(cost);
	}

	public State() {

	}

	// Getters

	public ArrayList<Person> getRightSide() {
		return rightSide;
	}

	public int getScore() {
		return score;

	}

	public ArrayList<Person> getLeftSide() {
		return leftSide;
	}

	public String getLightSide() {

		return light_side;
	}

	public State getFather() {

		return father;
	}

	public int getCost() {
		return this.cost;
	}

	// Setters

	public void setRightSide(ArrayList<Person> rightSide) {
		this.rightSide = new ArrayList<Person>(rightSide);
	}

	public void setLeftSide(ArrayList<Person> leftSide) {
		this.leftSide = new ArrayList<Person>(leftSide);
	}

	public void setLightSide(String light_side) {
		this.light_side = light_side;
	}

	public void setFather(State father) {
		this.father = father;
	}

	private void setCost(int cost) {
		this.cost += cost;
	}

	private void setScore(int score) {
		this.score = score;
	}

	/**
	 * Necessary function to check whether a state is terminal or not
	 * 
	 * @return true if state is terminal, false otherwise
	 */

	public boolean isTerminal() {
		return rightSide.isEmpty();
	}

	/**
	 * Heuretic function used to calculate the cost of a specific state to reach
	 * terminal state by removing the restriction of 2 max people on the bridge
	 * 
	 * @return the cost of this state
	 */

	private int heuristic() {

		int h_score = 0;
		int max = 0;

		/**
		 * if the light is on the right side we assume that all right side people can
		 * cross the bridge at the same time cost is equal to the maximum time
		 */
		if (light_side == "right") {

			for (int i = 0; i < rightSide.size(); i++) {
				if (rightSide.get(i).getTime() > max)
					max = rightSide.get(i).getTime();
			}
			return max;
		}

		/**
		 * If the light is on the left side we assume that the fastest left person moves
		 * on the right side and then all right side people cross the bridge at the same
		 * time cost is equal to the minimum time of the left side plus the max time of
		 * the right side
		 */
		else {
			int min = 10000;
			for (int i = 0; i < leftSide.size(); i++) {
				if (leftSide.get(i).getTime() < min) {
					min = leftSide.get(i).getTime();
				}
			}
			if (this.getRightSide().isEmpty()) {
				h_score = min;
				return h_score;
			}
			for (int i = 0; i < rightSide.size(); i++) {
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

	/**
	 * A* evaluation, taking into account the time cost to reach the specific state,
	 * as well as the heuristic approach
	 */

	private void evaluate() {
		setScore(this.getCost() + this.heuristic());
	}

	/**
	 * This funcion is used to produce all possible children-states of a specific
	 * state
	 * 
	 * @return the arraylist that contains all children
	 */

	public ArrayList<State> getChildren() {

		ArrayList<State> children = new ArrayList<State>();
		ArrayList<Person> temp = new ArrayList<Person>();
		State child;
		int maxim = 0;
		int time1 = 0;
		int time2 = 0;

		if (light_side == "right") {

			// this is a special case of only 1 family member playing the game
			if (this.leftSide.isEmpty() && (this.rightSide.size() == 1)) {
				child = new State(this.leftSide, this.rightSide, "right", this.cost);
				child.setFather(this);
				child.setCost(this.rightSide.get(0).time);
				temp.add(this.rightSide.get(0));
				child.moveLeft(temp);

				if (child.getCost() <= Main.max_time) {
					children.add(child);
				}
			}

			/**
			 * 2 people or more if the light is on the right side we produce states coming
			 * out of moves from 2 persons to the left states coming out of of moves from 1
			 * person only won't lead to an optimal solution
			 */
			else {

				for (int i = 0; i < rightSide.size(); i++) {
					for (int j = i + 1; j < rightSide.size(); j++) {
						temp.clear();
						child = new State(this.leftSide, this.rightSide, "right", this.cost);
						child.setFather(this);
						temp.add(this.rightSide.get(i));
						temp.add(this.rightSide.get(j));
						time1 = this.rightSide.get(i).getTime();
						time2 = this.rightSide.get(j).getTime();
						maxim = Math.max(time1, time2);
						child.moveLeft(temp);
						child.setCost(maxim);
						child.evaluate();

						/**
						 * we add this child to the children array only if its cost doesn't surpass the
						 * time restriction
						 */
						if (child.getCost() <= Main.max_time) {
							children.add(child);
						}
					}
				}
			}
		}

		/**
		 * if the light is on the left side we produce states coming out of moves from
		 * only 1 person each time back to the right
		 */
		else if (light_side == "left") {

			for (int i = 0; i < this.leftSide.size(); i++) {
				child = new State(this.leftSide, this.rightSide, this.light_side, this.cost);
				Person person = this.leftSide.get(i);
				time1 = person.getTime();
				child.moveRight(person);
				child.setCost(time1);
				child.evaluate();
				child.setFather(this);

				/**
				 * we add this child to the children array only if its cost doesn't surpass the
				 * time restriction
				 */
				if (child.getCost() <= Main.max_time) {
					children.add(child);
				}
			}
		}
		return children;
	}

	/**
	 * This function is used to move 1 or 2 people to the left side
	 * 
	 * @param people the arraylist that contains people that want to move left
	 */

	private void moveLeft(ArrayList<Person> people) {
		for (int i = 0; i < people.size(); i++) {
			if (this.rightSide.remove(people.get(i))) {
				this.leftSide.add(people.get(i));
			}
		}
		this.setLightSide("left");
	}

	/**
	 * This function is used to move only 1 person back to the right side. Moving
	 * more than 1 person to the right will not lead to an optimal solution.
	 * 
	 * @param person Person that wants to move to the right
	 */

	private void moveRight(Person person) {
		if (this.leftSide.remove(person)) {
			this.rightSide.add(person);
		}
		this.setLightSide("right");
	}

	/**
	 * Compares the scores of 2 different states
	 * 
	 * @param s other state to be compared
	 * @return the difference between the 2 scores
	 */

	@Override
	public int compareTo(State s) {
		return Double.compare(this.score, s.score);
	}

	/**
	 * Print function used to represent each state by the way people and the light
	 * are located between 2 sides.
	 * 
	 * @param side Side that the lamp currently is
	 */

	public void print(String side) {

		if (side == "left") {
			System.out.print("[lamp] ");
		}

		for (int j = 0; j < this.getLeftSide().size(); j++) {
			System.out.print("p" + this.getLeftSide().get(j).getId() + " ");
		}

		System.out.print("                                                                     ");

		if (side == "right") {
			System.out.print("[lamp] ");
		}

		for (int j = 0; j < this.getRightSide().size(); j++) {
			System.out.print("p" + this.getRightSide().get(j).getId() + " ");
		}

		System.out.println();
		System.out.print("--------------------------------------" + "______________________________"
				+ "--------------------------------------\n\n");
		// System.out.print("___________________________"+"-----------------------"+"___________________________\n\n");
	}

}
