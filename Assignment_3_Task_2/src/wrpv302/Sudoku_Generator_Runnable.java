package wrpv302;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sudoku_Generator_Runnable implements  Runnable{

    private final static int DELAY = 100;
    private final static int REPETITIONS = 10000;

    String sudokuGeneratorName;
    int sudokuBoardStartNum;
    int sudokuBoardNum;
    int addedReps;



    public Sudoku_Generator_Runnable(int sudokuBoardStartNum, String sudokuGeneratorName) throws IOException {
        this.sudokuBoardStartNum = sudokuBoardStartNum;
        sudokuBoardNum = sudokuBoardStartNum;
        this.sudokuGeneratorName = sudokuGeneratorName;
        addedReps = 0;
    }

    void SaveSudokuBoard(Sudoku_Generator sudokuBoard) throws FileNotFoundException {
        Map<Integer,List<Sudoku_Box>> sudokuRows = sudokuBoard.rows;
        String outputPath = "Sudoku_Boards\\"+sudokuBoardNum+".txt";
        PrintWriter outputFile = null;
        try{
            outputFile = new PrintWriter(outputPath);
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
            System.out.println(outputPath + " cannot be found.");
            System.exit(2);
        }

        String boxValues = "";
        for(int i = 1; i <= 9; i++)
        {
            boxValues = "";
            for(int j = 0; j < 9; j++)
            {
                List<Sudoku_Box> row = sudokuRows.get(i);
                Sudoku_Box sudoku_Box = row.get(j);
                boxValues = boxValues + "["+sudoku_Box.getValue()+"]";
            }
            outputFile.println(boxValues);
        }
        System.out.println(outputPath + " was saved successfully.");
        outputFile.close();
    }

    boolean CheckUniqueness(Sudoku_Generator sudokuBoard)
    {
        boolean isSame = false; //check if sudokuBoard is the same as a board in the uniqueSudokuBoard list
        boolean is90Same = false; //check if sudokuBoard rotated 90 degrees is the same as a board in the uniqueSudokuBoard list
        boolean is180Same = false; //check if sudokuBoard rotated 180 degrees is the same as a board in the uniqueSudokuBoard list
        boolean is270Same = false; //check if sudokuBoard rotated 270 degrees is the same as a board in the uniqueSudokuBoard list
        boolean isMirroredRowSame = false; //check if sudokuBoard mirrored on rows is the same as a board in the uniqueSudokuBoard list
        boolean is90MirroredRowSame = false; //check if sudokuBoard rotated 90 degrees and mirrored on rows is the same as a board in the uniqueSudokuBoard list
        boolean is180MirroredRowSame = false; //check if sudokuBoard rotated 180 degrees and mirrored on rows is the same as a board in the uniqueSudokuBoard list
        boolean is270MirroredRowSame = false; //check if sudokuBoard rotated 270 degrees and mirrored on rows is the same as a board in the uniqueSudokuBoard list
        boolean isMirroredColumnSame = false; //check if sudokuBoard mirrored on columns is the same as a board in the uniqueSudokuBoard list
        boolean is90MirroredColumnSame = false; //check if sudokuBoard rotated 90 degrees and mirrored on columns is the same as a board in the uniqueSudokuBoard list
        boolean is180MirroredColumnSame = false; //check if sudokuBoard rotated 180 degrees and mirrored on columns is the same as a board in the uniqueSudokuBoard list
        boolean is270MirroredColumnSame = false; //check if sudokuBoard rotated 270 degrees and mirrored on columns is the same as a board in the uniqueSudokuBoard list

        //Check Uniqueness over all stored sudoku boards
        if(sudokuBoard != null && Main.uniqueSudokuBoards.size() > 1)
        {

            List<Sudoku_Box> sudokuBoxes = sudokuBoard.boxes;
            //Rotated 90 Degrees
            Sudoku_Generator sudokuBoxes90 = new Sudoku_Generator();
            for(int i = 1; i <= 9; i++)
            {
                List<Sudoku_Box> row = new ArrayList<>();
                List<Sudoku_Box> column = new ArrayList<>();
                for(int j = 0; j < 9; j++)
                {
                    row.add(sudokuBoard.columns.get(i).get(8-j));
                    column.add(sudokuBoard.rows.get(10-i).get(j));
                }
                sudokuBoxes90.rows.put(i,row);
                sudokuBoxes90.columns.put(i,column);
            }

            //Rotated 180 Degrees
            Sudoku_Generator sudokuBoxes180 = new Sudoku_Generator();
            for(int i = 1; i <= 9; i++)
            {
                List<Sudoku_Box> row = new ArrayList<>();
                List<Sudoku_Box> column = new ArrayList<>();
                for(int j = 0; j < 9; j++)
                {
                    row.add(sudokuBoxes90.columns.get(i).get(8-j));
                    column.add(sudokuBoxes90.rows.get(10-i).get(j));
                }
                sudokuBoxes180.rows.put(i,row);
                sudokuBoxes180.columns.put(i,column);
            }
            //Rotated 270 Degrees
            Sudoku_Generator sudokuBoxes270 = new Sudoku_Generator();
            for(int i = 1; i <= 9; i++)
            {
                List<Sudoku_Box> row = new ArrayList<>();
                List<Sudoku_Box> column = new ArrayList<>();
                for(int j = 0; j < 9; j++)
                {
                    row.add(sudokuBoxes180.columns.get(i).get(8-j));
                    column.add(sudokuBoxes180.rows.get(10-i).get(j));
                }
                sudokuBoxes270.rows.put(i,row);
                sudokuBoxes270.columns.put(i,column);
            }
            //Mirrored Rows
            Sudoku_Generator sudokuBoxesMirroredRows = new Sudoku_Generator();
            for(int i = 1; i <= 9; i++)
            {
                List<Sudoku_Box> row = new ArrayList<>();
                List<Sudoku_Box> column = new ArrayList<>();
                for(int j = 0; j < 9; j++)
                {
                    row.add(sudokuBoard.rows.get(10-i).get(j));
                    column.add(sudokuBoard.columns.get(i).get(8-j));
                }
                sudokuBoxesMirroredRows.rows.put(i,row);
                sudokuBoxesMirroredRows.columns.put(i,column);
            }
            //Rotated 90 Degrees, Mirrored Rows
            Sudoku_Generator sudokuBoxes90MirroredRows = new Sudoku_Generator();
            for(int i = 1; i <= 9; i++)
            {
                List<Sudoku_Box> row = new ArrayList<>();
                List<Sudoku_Box> column = new ArrayList<>();
                for(int j = 0; j < 9; j++)
                {
                    row.add(sudokuBoxes90.rows.get(10-i).get(j));
                    column.add(sudokuBoxes90.columns.get(i).get(8-j));
                }
                sudokuBoxes90MirroredRows.rows.put(i,row);
                sudokuBoxes90MirroredRows.columns.put(i,column);
            }
            //Rotated 180 Degrees, Mirrored Rows
            Sudoku_Generator sudokuBoxes180MirroredRows = new Sudoku_Generator();
            for(int i = 1; i <= 9; i++)
            {
                List<Sudoku_Box> row = new ArrayList<>();
                List<Sudoku_Box> column = new ArrayList<>();
                for(int j = 0; j < 9; j++)
                {
                    row.add(sudokuBoxes180.rows.get(10-i).get(j));
                    column.add(sudokuBoxes180.columns.get(i).get(8-j));
                }
                sudokuBoxes180MirroredRows.rows.put(i,row);
                sudokuBoxes180MirroredRows.columns.put(i,column);
            }
            //Rotated 270 Degrees, Mirrored Rows
            Sudoku_Generator sudokuBoxes270MirroredRows = new Sudoku_Generator();
            for(int i = 1; i <= 9; i++)
            {
                List<Sudoku_Box> row = new ArrayList<>();
                List<Sudoku_Box> column = new ArrayList<>();
                for(int j = 0; j < 9; j++)
                {
                    row.add(sudokuBoxes270.rows.get(10-i).get(j));
                    column.add(sudokuBoxes270.columns.get(i).get(8-j));
                }
                sudokuBoxes270MirroredRows.rows.put(i,row);
                sudokuBoxes270MirroredRows.columns.put(i,column);
            }
            //Mirrored Columns
            Sudoku_Generator sudokuBoxesMirroredColumns = new Sudoku_Generator();
            for(int i = 1; i <= 9; i++)
            {
                List<Sudoku_Box> row = new ArrayList<>();
                List<Sudoku_Box> column = new ArrayList<>();
                for(int j = 0; j < 9; j++)
                {
                    row.add(sudokuBoard.rows.get(i).get(8-j));
                    column.add(sudokuBoard.columns.get(10-i).get(j));
                }
                sudokuBoxesMirroredColumns.rows.put(i,row);
                sudokuBoxesMirroredColumns.columns.put(i,column);
            }
            //Rotated 90 Degrees, Mirrored Columns
            Sudoku_Generator sudokuBoxes90MirroredColumns = new Sudoku_Generator();
            for(int i = 1; i <= 9; i++)
            {
                List<Sudoku_Box> row = new ArrayList<>();
                List<Sudoku_Box> column = new ArrayList<>();
                for(int j = 0; j < 9; j++)
                {
                    row.add(sudokuBoxes90.rows.get(i).get(8-j));
                    column.add(sudokuBoxes90.columns.get(10-i).get(j));
                }
                sudokuBoxes90MirroredColumns.rows.put(i,row);
                sudokuBoxes90MirroredColumns.columns.put(i,column);
            }
            //Rotated 180 Degrees, Mirrored Columns
            Sudoku_Generator sudokuBoxes180MirroredColumns = new Sudoku_Generator();
            for(int i = 1; i <= 9; i++)
            {
                List<Sudoku_Box> row = new ArrayList<>();
                List<Sudoku_Box> column = new ArrayList<>();
                for(int j = 0; j < 9; j++)
                {
                    row.add(sudokuBoxes180.rows.get(i).get(8-j));
                    column.add(sudokuBoxes180.columns.get(10-i).get(j));
                }
                sudokuBoxes180MirroredColumns.rows.put(i,row);
                sudokuBoxes180MirroredColumns.columns.put(i,column);
            }
            //Rotated 270 Degrees, Mirrored Columns
            Sudoku_Generator sudokuBoxes270MirroredColumns = new Sudoku_Generator();
            for(int i = 1; i <= 9; i++)
            {
                List<Sudoku_Box> row = new ArrayList<>();
                List<Sudoku_Box> column = new ArrayList<>();
                for(int j = 0; j < 9; j++)
                {
                    row.add(sudokuBoxes270.rows.get(i).get(8-j));
                    column.add(sudokuBoxes270.columns.get(10-i).get(j));
                }
                sudokuBoxes270MirroredColumns.rows.put(i,row);
                sudokuBoxes270MirroredColumns.columns.put(i,column);
            }

            for (Sudoku_Generator sudokuGenerator:Main.uniqueSudokuBoards) {
                for(int i = 1; i <= 9; i++)
                {
                    //Rows For Comparison
                    List<Sudoku_Box> sudokuBoardRow = new ArrayList<>();
                    List<Sudoku_Box> sudokuBoxes90Row = new ArrayList<>();
                    List<Sudoku_Box> sudokuBoxes180Row = new ArrayList<>();
                    List<Sudoku_Box> sudokuBoxes270Row = new ArrayList<>();
                    List<Sudoku_Box> sudokuBoardMirroredRowsRow = new ArrayList<>();
                    List<Sudoku_Box> sudokuBoxes90MirroredRowsRow = new ArrayList<>();
                    List<Sudoku_Box> sudokuBoxes180MirroredRowsRow = new ArrayList<>();
                    List<Sudoku_Box> sudokuBoxes270MirroredRowsRow = new ArrayList<>();
                    List<Sudoku_Box> sudokuBoardMirroredColumnsRow = new ArrayList<>();
                    List<Sudoku_Box> sudokuBoxes90MirroredColumnsRow = new ArrayList<>();
                    List<Sudoku_Box> sudokuBoxes180MirroredColumnsRow = new ArrayList<>();
                    List<Sudoku_Box> sudokuBoxes270MirroredColumnsRow = new ArrayList<>();
                    List<Sudoku_Box> sudokuGeneratorRow = new ArrayList<>();
                    //Intiialize Rows
                    sudokuBoardRow = sudokuBoard.rows.get(i);
                    sudokuBoxes90Row = sudokuBoxes90.rows.get(i);
                    sudokuBoxes180Row = sudokuBoxes180.rows.get(i);
                    sudokuBoxes270Row = sudokuBoxes270.rows.get(i);
                    sudokuBoardMirroredRowsRow = sudokuBoxesMirroredRows.rows.get(i);
                    sudokuBoxes90MirroredRowsRow = sudokuBoxes90MirroredRows.rows.get(i);
                    sudokuBoxes180MirroredRowsRow = sudokuBoxes180MirroredRows.rows.get(i);
                    sudokuBoxes270MirroredRowsRow = sudokuBoxes270MirroredRows.rows.get(i);
                    sudokuBoardMirroredColumnsRow = sudokuBoxesMirroredColumns.rows.get(i);
                    sudokuBoxes90MirroredColumnsRow = sudokuBoxes90MirroredColumns.rows.get(i);
                    sudokuBoxes180MirroredColumnsRow = sudokuBoxes180MirroredColumns.rows.get(i);
                    sudokuBoxes270MirroredColumnsRow = sudokuBoxes270MirroredColumns.rows.get(i);
                    sudokuGeneratorRow = sudokuGenerator.rows.get(i);

                    for(int j = 0; j < 9; j++)
                    {
                        //Compare Rows
                        if(sudokuBoardRow.get(j) != sudokuGeneratorRow.get(j))
                        {
                            isSame = true;
                        }
                        if(sudokuBoxes90Row.get(j) != sudokuGeneratorRow.get(j))
                        {
                            is90Same = true;
                        }
                        if(sudokuBoxes180Row.get(j) != sudokuGeneratorRow.get(j))
                        {
                            is180Same = true;
                        }
                        if(sudokuBoxes270Row.get(j) != sudokuGeneratorRow.get(j))
                        {
                            is270Same = true;
                        }

                        //Compare Rows Mirrored By Rows
                        if(sudokuBoardMirroredRowsRow.get(j) != sudokuGeneratorRow.get(j))
                        {
                            isMirroredRowSame = true;
                        }
                        if(sudokuBoxes90MirroredRowsRow.get(j) != sudokuGeneratorRow.get(j))
                        {
                            is90MirroredRowSame = true;
                        }
                        if(sudokuBoxes180MirroredRowsRow.get(j) != sudokuGeneratorRow.get(j))
                        {
                            is180MirroredRowSame = true;
                        }
                        if(sudokuBoxes270MirroredRowsRow.get(j) != sudokuGeneratorRow.get(j))
                        {
                            is270MirroredRowSame = true;
                        }

                        //Compare Rows Mirrored By Columns
                        if(sudokuBoardMirroredColumnsRow.get(j) != sudokuGeneratorRow.get(j))
                        {
                            isMirroredColumnSame = true;
                        }
                        if(sudokuBoxes90MirroredColumnsRow.get(j) != sudokuGeneratorRow.get(j))
                        {
                            is90MirroredColumnSame = true;
                        }
                        if(sudokuBoxes180MirroredColumnsRow.get(j) != sudokuGeneratorRow.get(j))
                        {
                            is180MirroredColumnSame = true;
                        }
                        if(sudokuBoxes270MirroredColumnsRow.get(j) != sudokuGeneratorRow.get(j))
                        {
                            is270MirroredColumnSame = true;
                        }
                    }

                }
            }
            //isSame Comparisons
            if(isSame == false)
            {
                return false;
            }
            if(is90Same == false)
            {
                return false;
            }
            if(is180Same == false)
            {
                return false;
            }
            if(is270Same == false)
            {
                return false;
            }
            //isMirroredRowSame Comaprisons
            if(isMirroredRowSame == false)
            {
                return false;
            }
            if(is90MirroredRowSame == false)
            {
                return false;
            }
            if(is180MirroredRowSame == false)
            {
                return false;
            }
            if(is270MirroredRowSame == false)
            {
                return false;
            }
            //isMirroredColumn Comparisons
            if(isMirroredColumnSame == false)
            {
                return false;
            }
            if(is90MirroredColumnSame == false)
            {
                return false;
            }
            if(is180MirroredColumnSame == false)
            {
                return false;
            }
            if(is270MirroredColumnSame == false)
            {
                return false;
            }
        }

        return true;

        /*
            //Print Comparisons
            System.out.println("---------------------------------------------------------------------------------\n");
            sudokuBoard.PrintSudokuGrid();
            System.out.println("Compared To:\n");
            sudokuBoxesMirroredRows.PrintSudokuGrid();
            System.out.println("Compared To:\n");
            sudokuBoxesMirroredColumns.PrintSudokuGrid();
            System.out.println("Compared To:\n");
            sudokuBoxes90.PrintSudokuGrid();
            System.out.println("Compared To:\n");
            sudokuBoxes90MirroredRows.PrintSudokuGrid();
            System.out.println("Compared To:\n");
            sudokuBoxes90MirroredColumns.PrintSudokuGrid();
            System.out.println("Compared To:\n");
            sudokuBoxes180.PrintSudokuGrid();
            System.out.println("Compared To:\n");
            sudokuBoxes180MirroredRows.PrintSudokuGrid();
            System.out.println("Compared To:\n");
            sudokuBoxes180MirroredColumns.PrintSudokuGrid();
            System.out.println("Compared To:\n");
            sudokuBoxes270.PrintSudokuGrid();
            System.out.println("Compared To:\n");
            sudokuBoxes270MirroredRows.PrintSudokuGrid();
            System.out.println("Compared To:\n");
            sudokuBoxes270MirroredColumns.PrintSudokuGrid();
            System.out.println("---------------------------------------------------------------------------------\n");
            System.exit(123456);
            */

    }

    @Override
    public void run() {
        for(int i = sudokuBoardStartNum; i < (REPETITIONS+sudokuBoardStartNum+addedReps); i++)
        {
            //System.out.println("Num: " + sudokuBoardStartNum);
            //System.out.println("Repetitions: " + REPETITIONS);
            //System.out.println("Loop Count: "+ i);
            //System.out.println("Loop End Condition: i < "+(REPETITIONS+sudokuBoardStartNum));
            try
            {
                Sudoku_Generator sudoku_generator = new Sudoku_Generator();
                int j = sudoku_generator.WCF_Algorithm();

                while(sudoku_generator.WCF_Algorithm() == -1)
                {
                    sudoku_generator = new Sudoku_Generator();
                }
                Main.checkUniquenessLock.lock();
                if(CheckUniqueness(sudoku_generator) == true)
                {
                    Main.uniqueSudokuBoards.add(sudoku_generator);
                    Main.checkUniquenessLock.unlock();
                    Main.saveBoardLock.lock();
                    try{
                        SaveSudokuBoard(sudoku_generator);
                    }
                    catch(FileNotFoundException e)
                    {

                    }
                    Main.saveBoardLock.unlock();
                    //System.out.println("--------------------------------------");
                    //System.out.println("Board Number: " + sudokuBoardNum);
                    //sudoku_generator.PrintSudokuGrid();
                    //System.out.println("--------------------------------------");
                    sudokuBoardNum++;
                }
                else
                {
                    addedReps++;
                }

                Thread.sleep(DELAY);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Thread Interrupted!");
                System.exit(3);
            }
        }
        System.out.println(sudokuGeneratorName+" finished.\nProcess terminating...");
    }
}
