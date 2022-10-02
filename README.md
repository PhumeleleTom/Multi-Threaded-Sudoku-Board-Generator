# Multi-Threaded-Sudoku-Board-Generator

Assignment 3

Topics
 Collections
 Threads

Instructions
You may implement this as a Java console app or an Android app, although a Java app may be easier to debug.

Task 1: Sudoku Generator
This task was an exam question in previous years.
For this task, you are required to write a Sudoku generator using the Wave Collapse Function algorithm. You must make use of the streaming API wherever possible.
Hint: consider the data structure(s) you use carefully.
The Rules of Sudoku
The classic Sudoku game involves a grid of 81 boxes. The grid is divided into nine blocks, each containing nine boxes.
The rules of the game are simple:
 each of the nine blocks has to contain all the numbers 1-9 within its boxes; and
 each number can only appear once in a row, column or block.
One way to randomly generate a valid Sudoku grid is using the Wave Function Collapse algorithm13 from Quantum Mechanics. This algorithm can be used to generate many things, including a Sudoku grid.
The algorithm works by initially having each box contain a set of all possible valid values, e.g. 1, 2, 3, 4, 5, 6, 7, 8, 9. So initially the grid contains all possible Sudoku grids that can ever be generated.
Then using a process of elimination, the possible Sudoku grids are reduced until finally a single particular Sudoku board is generated.
The process of reducing the possibilities works as follows:
 pick a box, b, with the least number of possibilities remaining14, then randomly pick one of the possibilities, p, in that box. This becomes fact for box b (i.e. there are no other possibilities for it);

 remove p from the possibilities of all boxes in the same row, column and block as b.
Repeat this process until all the boxes contain only facts.
At this stage, a valid Sudoku grid should have been generated, where all the boxes have a single value (fact) and none of the rules have been violated.

When picking a new box, one of boxes D2, E2 or F2 will be selected as they all have 6 possibilities left. If box E2 was selected, then one of the possibilities 1, 4, 5, 7, 8 or 9 would be randomly chosen. If, for example, 9 was chosen, then E2 would hold the fact 9 and all the 9s would be removed from the possibilities of the boxes in row 2, column E and the block in which E2 resides namely (D1, E1, F1, D2, F2, E3 and F3).
Should there ever exist a box with 0 possibilities, then the grid is invalid15. Display an error message and try again.
Write two classes named SudokuGenerator and SudokuGrid.
The SudokuGrid class represents a board and the values (the final facts) placed in it. It must provide functionality to set, retrieve and clear box values, as well as a method to determine whether placing a value in a given box is valid, invalid due to row conflict, invalid due to column conflict or invalid due to block conflict. Note that it is possible to be invalid for any combination of the three reasons (e.g. invalid due to row conflict AND invalid due to column conflict).
The SudokuGenerator class is responsible for generating a valid complete SudokuGrid using the Wave Function Collapse algorithm described above.
You must make appropriate use of the Java Collection Framework (collections, algorithms, streaming API, etc.) when completing this task.
Demonstrate that your implementation works correctly by displaying generated boards after the user presses “enter” and display the number of invalid boxes on the board (using the method you wrote previously).

Task 2: Threaded Generation of Sudoku Boards
In Task 1, you wrote an algorithm to generate Sudoku game boards (full solutions). Now you need to write a program that uses threads to do the following:

 generate 50,00016 unique Sudoku game boards, allocate each a unique number and save these to a file – as quickly as possible.
Game boards are numbered 0, 1, 2, 3… in the order that they are generated. A game board is saved in a text file named 0.txt 1.txt, 2.txt, etc. which simply contains all the numbers in each box of the game board.
A game board is considered the same regardless of how it is rotated, flipped along the rows, flipped along the columns, rotated 90, 180 or 270, or any combination therefore17. So a game board is considered the same as a mirrored or rotated (or mirrored and rotated) version of itself, etc.
You will need to have threads to generate game boards, some to check for uniqueness and another for saving unique game boards to file.
The correct choices in data structures and algorithms used are very important in this task.
