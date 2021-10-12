# Bridge and Torch Problem
The objective of this assignment was to write a program that implements an AI algorithm (BFS, DFS, A* etc) so an optimal solution can be found for the Bridge and Torch problem. In this project the A* algorithm with closed set was implemented and the programming language that was used was [Java (jdk15)](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html). So given a number of people that want to cross the bridge, the algorithm must find the optimal solution, meaning the minimum time for all people to pass across. It is highly suggested that you read about the rules of the game and what exactly was requested [here](https://github.com/nevwalkalone/Artificial-Intelligence-2020-2021-AUEB/blob/main/1st%20Assignment/announcement-report/project-announcement.pdf), before you proceed. More details about the algorithm implementation and code analysis can be found [here](https://github.com/nevwalkalone/Artificial-Intelligence-2020-2021-AUEB/blob/main/1st%20Assignment/announcement-report/project-report.pdf).

## Execution
After you've **downloaded locally this repository or cloned this project**, you can either execute Main.java in cmd/terminal or in an IDE of your choice like Intellij or Eclipse. In both scenarios all java files located in the src folder must be in the same directory. Below an example of how to run the program in cmd/terminal is shown.


### Change directory
```properties
cd "Directory where the 1st Assignment src folder is located"
```  

### Compile Main.java
```console
javac Main.java
```

### Run Main
```console
java Main
```
When the program runs, the user is asked to give as an input the number of family members that must cross the bridge, the total time limit that must not be exceeded, as well as the amount of time that is required for each member to cross from one side to the other. Program then ends with a fully displayed path from initial to terminal state (with exact moves in each step) if a solution that does not surpass the time limit was found.






