# Bridge and Torch Problem

This project was completed for the Artificial Intelligence course of the [Department of Computer Science of the Athens University of Economics and Business](https://www.dept.aueb.gr/el/cs), during the Fall semester of 2020-2021.  

The objective of this assignment was to write a program that implements an AI algorithm (BFS, DFS, A* etc) so an optimal solution can be found for the Bridge and Torch problem. In this project the A* algorithm with closed set was implemented and the programming language that was used was [Java (jdk15)](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html). So given a number of people that want to cross the bridge, the algorithm must find the optimal solution, meaning the minimum time for all people to pass across. 

## Useful Reads
Before proceeding it is highly suggested that you read:
* [Project Assignment](https://github.com/nevwalkalone/Bridge-and-Torch-Problem/blob/main/announcement-report/project-announcement.pdf), that has all the details about the game rules and what exactly was requested.
* [Code Analysis](https://github.com/nevwalkalone/Bridge-and-Torch-Problem/blob/main/announcement-report/project-report.pdf), that has all the details about the algorithm implementation and the analysis of the Java code.

## Program Execution
After you've **cloned this project**, you can either execute Main.java in cmd/terminal or in an IDE of your choice like Intellij or Eclipse. In both scenarios all java files located in the src folder must be in the same directory. Below an example of how to run the program in cmd/terminal is shown.


### Change directory
```properties
cd "Directory where the src folder is located"
```  

### Compile Main.java
```console
javac Main.java
```

### Run Main
```console
java Main
```
When the program runs, the user is asked to give as an input:
1. the number of family members that must cross the bridge,
2. the total time limit that must not be exceeded, 
3. the amount of time that is required for each member to cross from one side to the other. 

Program then ends with a fully displayed path from initial to terminal state (with exact moves in each step) if a solution that does not surpass the time limit was found.
