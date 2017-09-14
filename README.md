# explores-Mars

You are part of the team that explores Mars by sending remotely controlled vehicles to the surface of the planet. Develop an API that translates the commands sent from earth to instructions that are understood by the rover.
 
Requirements
- You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
- The rover receives a character array of commands.
- Implement commands that move the rover forward/backward (f,b).
- Implement commands that turn the rover left/right (l,r).
- The output of the rover should be its final co-ordinates and heading.
- Implement wrapping from one edge of the grid to another. (planets are spheres after all)
- Obstacles can be placed anywhere on the grid.
- Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point, aborts the sequence and reports the obstacle.
 
The solution should be implemented in Java and provided with tests that cover all the requirements above.