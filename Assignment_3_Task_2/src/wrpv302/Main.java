package wrpv302;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static final List<Sudoku_Generator> uniqueSudokuBoards = new ArrayList<>();
    //public static Queue boardThreadQueue;
    //public static Queue boardSaveQueue;
    //public static Queue checkUniquenessQueue;
    public static final Lock checkUniquenessLock = new ReentrantLock();
    public static final Lock saveBoardLock = new ReentrantLock();

    public static void main(String[] args) throws IOException {

        //boardThreadQueue = new ConcurrentLinkedQueue();
        //boardSaveQueue = new ConcurrentLinkedQueue();
        //checkUniquenessQueue = new ConcurrentLinkedQueue();

        Runnable sudokuGeneratorRunnable_1 = new Sudoku_Generator_Runnable(0, "Sudoku Generator 1");
        Thread sudokuGeneratorThread_1 = new Thread(sudokuGeneratorRunnable_1);
        Runnable sudokuGeneratorRunnable_2 = new Sudoku_Generator_Runnable(10000,"Sudoku Generator 2");
        Thread sudokuGeneratorThread_2 = new Thread(sudokuGeneratorRunnable_2);
        Runnable sudokuGeneratorRunnable_3 = new Sudoku_Generator_Runnable(20000,"Sudoku Generator 3");
        Thread sudokuGeneratorThread_3 = new Thread(sudokuGeneratorRunnable_3);
        Runnable sudokuGeneratorRunnable_4 = new Sudoku_Generator_Runnable(30000,"Sudoku Generator 4");
        Thread sudokuGeneratorThread_4 = new Thread(sudokuGeneratorRunnable_4);
        Runnable sudokuGeneratorRunnable_5 = new Sudoku_Generator_Runnable(40000,"Sudoku Generator 5");
        Thread sudokuGeneratorThread_5 = new Thread(sudokuGeneratorRunnable_5);
        sudokuGeneratorThread_1.start();
        sudokuGeneratorThread_2.start();
        sudokuGeneratorThread_3.start();
        sudokuGeneratorThread_4.start();
        sudokuGeneratorThread_5.start();

        //System.out.println("Process terminating...");
    }
}
