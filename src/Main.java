import java.util.*;

/**
 * Runner program solving the Bridge Crossing problem
 */

public class Main {

	public static int max_time;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		// Asking user for the number of family members
		System.out.println("Give the number of family members");

		int N = in.nextInt();

		// Arraylist holding all family members
		ArrayList<Person> family = new ArrayList<Person>();

		// Getting time input for all members
		for (int i = 0; i < N; i++) {

			System.out.println(
					"Give the amount of time required (in seconds) to cross the bridge for the family member with id:"
							+ (i + 1));

			int time = in.nextInt();
			Person temp = new Person(i + 1, time);
			family.add(temp);
		}
		System.out.println("Give the total time limit ");

		// Time restriction for the problem to be solved
		max_time = in.nextInt();

		// Initial state of the problem
		State init_state = new State(family);

		// Initializing a new space_searcher in order to solve the problem
		SpaceSearcher space_searcher = new SpaceSearcher();

		State terminal = null;

		/**
		 * Holding start time and end time, to count time needed to find the game
		 * solution
		 */
		long start = System.currentTimeMillis();

		// Running the A* algorithm on the initial state
		terminal = space_searcher.A_StarClosedSet(init_state);
		long end = System.currentTimeMillis();

		if (terminal == null) {
			System.out.println("\nSorry, couldn't find solution ");
		}

		else {

			State temp = terminal;

			// Path that will hold all states, from initial state to terminal
			ArrayList<State> path = new ArrayList<State>();
			path.add(terminal);

			while (temp.getFather() != null) {

				// adding each state to the arraylist
				path.add(temp.getFather());
				temp = temp.getFather();
			}
			// Reversing the path so that the first element is the root
			Collections.reverse(path);

			/**
			 * PRINTING THE PATH AND ALL MOVES THAT WERE COMPLETED IN /* ORDER FOR ALL
			 * FAMILY MEMBERS TO CROSS IN THE MINIMUM TIME
			 */

			System.out.println("********************************************************");
			System.out.println("Finished in " + (path.size() - 1) + " steps!");
			System.out.println("\n********************************************************");
			int counter = 0;
			String side = "";

			for (State item : path) {

				if (item.getFather() == null) {
					System.out.println("\nInitial State");
					side = "right";
				} else {
					System.out.println("********************************************************\n");
					System.out.print("STEP " + counter + ":");

					if (counter % 2 == 1) {
						side = "left";
						System.out.println(" Moved from Right to Left\n");

						for (Person j : item.getLeftSide()) {

							if (!(item.getFather().getLeftSide().contains(j))) {
								System.out.println("Person " + j.getId() + " with time: " + j.getTime() + " sec");
							}
						}
					}

					else {
						side = "right";
						System.out.println(" Moved from Left to Right\n");

						for (Person j : item.getRightSide()) {

							if (!(item.getFather().getRightSide().contains(j))) {
								System.out.println("Person " + j.getId() + " with time: " + j.getTime() + " sec");
							}
						}
					}
				}
				counter++;
				System.out.println("\n");
				item.print(side);
				System.out.println("Time passed: " + item.getCost() + " sec");
				System.out.println("\n");
			}
			System.out.println("Minimum time for all members to cross the bridge from initial state: "
					+ terminal.getCost() + " sec");
			System.out.println("Bridge crossed successfully by all members within the time limit (" + (max_time) + ")");
		}
		System.out.println("A* with closed set search time : " + (double) (end - start) / 1000 + " sec\n");
		in.close();
	}
}
