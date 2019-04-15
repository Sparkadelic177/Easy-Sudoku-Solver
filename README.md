# Easy-Sudoku-Solver
The program solves the easiest sudoku puzzles. 

- In the main java file, there is a place to type in a local file path (CSV files) that have sudoku cell values. 
- The program will then find all the empty cells and and checks all rows, columns, and box for the possible values that belong in the empty cells
- The program will find cells where it has only one possible candidate and place it in that grid space
- The program will first check if that number is valid and if it passes all the test, the value is placed.
- Once the candidate has been place, it will update the board and deleted any same value candidate in the same row column and box.
- The porgram will complete once all the values have been placed on to the board.
