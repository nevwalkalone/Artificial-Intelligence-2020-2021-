# Bridge and Torch Problem

This project was completed for the Artificial Intelligence course of the [Department of Computer Science of the Athens University of Economics and Business](https://www.dept.aueb.gr/el/cs), during the Fall semester of 2020-2021.

The objective of this assignment was to write a program that implements an AI algorithm (BFS, DFS, A* etc), so that an optimal solution can be found for the [Bridge and Torch](https://en.wikipedia.org/wiki/Bridge_and_torch_problem) problem. In this project the A* algorithm with closed set was implemented. Given a number of people that want to cross the bridge, the algorithm must find the optimal solution, meaning the minimum amount of time for all people to pass across.

## Useful Reads

For a full overview it is highly suggested that you read:

- [Project Assignment](https://github.com/nevwalkalone/Bridge-and-Torch-Problem/blob/main/assignment-report/project-assignment.pdf), that contains all the details about the game rules and what exactly was requested.
- [Project Report](https://github.com/nevwalkalone/Bridge-and-Torch-Problem/blob/main/assignment-report/project-report.pdf), that contains all the details about the algorithm implementation and the analysis of the code.

## Dependencies

- [Java (jdk15)](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html).

## Program Execution

1. **Clone** repository and change directory to src.

   ```console
   git clone https://github.com/nevwalkalone/Bridge-and-Torch-Problem.git
   cd src
   ```

2. **Compile** Main.java

   ```console
   javac Main.java
   ```

3. **Run** Main

   ```console
   java Main
   ```

When the program runs, the user is asked to give as an input:

<ol type="a">
  <li>the number of family members that must cross the bridge,</li>
  <li>the total time limit that must not be exceeded, </li>
  <li>the amount of time that is required for each member to cross from one side to the other.  </li>
</ol>

Program then ends with a fully displayed path from initial to terminal state (with exact moves in each step) if a solution that does not surpass the time limit is found.

## Collaborators

- [nevwalkalone](https://github.com/nevwalkalone)
- [Petros247](https://github.com/Petros247)
- [frostedpenguin](https://github.com/frostedpenguin)
