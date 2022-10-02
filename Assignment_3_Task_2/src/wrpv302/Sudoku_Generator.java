package wrpv302;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class Sudoku_Generator{

    Sudoku_Grid sudoku_grid;
    Map<Integer, List<Sudoku_Box>> rows;
    Map<Integer, List<Sudoku_Box>> columns;
    Map<Integer, List<Sudoku_Box>> blocks;
    List<Sudoku_Box> boxes;

    public Sudoku_Generator(){
        sudoku_grid = new Sudoku_Grid();
        rows = sudoku_grid.getRows();
        columns = sudoku_grid.getColumns();
        blocks = sudoku_grid.getBlocks();
        boxes = sudoku_grid.getBoxes();
    }

    public int WCF_Algorithm(){


        List<Sudoku_Box> row;
        List<Sudoku_Box> column;
        List<Sudoku_Box> block;

        int numBoxesInvalid = 0;
        List<Sudoku_Box> tempBoxes = boxes;

        //WCF Loop Begins
        while(tempBoxes.size() > 0)
        {
            try
            {
                if(CheckIfGridIsInvalid())
                {
                    return -1;
                }
                //Sudoku_Box leastPossibilityBox = tempBoxes.get(i);
                //Sudoku_Box leastPossibilityBox = (Sudoku_Box) tempBoxes.stream().min(new Sudoku_BoxComparator()).get();
                Sudoku_Box leastPossibilityBox = Collections.min(tempBoxes, new Sudoku_BoxComparator());

                Collections.shuffle(leastPossibilityBox.possibilities);
                Integer nextValue = leastPossibilityBox.possibilities.get(0);

                int colNum = leastPossibilityBox.getColumnNum();
                int rowNum = leastPossibilityBox.getRowNum();
                int blockNum = leastPossibilityBox.getBlockNum();

                row = rows.get(rowNum);
                column = columns.get(colNum);
                block = blocks.get(blockNum);

                if(sudoku_grid.IsBoxValueValid(rowNum, colNum,nextValue))
                {

                    //System.out.println("Sudoku Grid Possibilities Before Removal: ");
                    //PrintSudokuGridPossibilities();
                    //System.out.println(row);
                    for (Sudoku_Box sudoku_Box:row) {
                        if(!sudoku_Box.equals(leastPossibilityBox))
                        {
                            sudoku_Box.possibilities.remove(nextValue);
                        }
                    }
                    //System.out.println("Sudoku Grid Possibilities After Row Removal: ");
                    //PrintSudokuGridPossibilities();
                    for (Sudoku_Box sudoku_Box:column) {
                        if(!sudoku_Box.equals(leastPossibilityBox))
                        {
                            sudoku_Box.possibilities.remove(nextValue);
                        }
                    }
                    //System.out.println("Sudoku Grid Possibilities After Column Removal: ");
                    //PrintSudokuGridPossibilities();
                    for (Sudoku_Box sudoku_Box:block) {
                        if(!sudoku_Box.equals(leastPossibilityBox))
                        {
                            sudoku_Box.possibilities.remove(nextValue);
                        }
                    }
                    //System.out.println("Sudoku Grid Possibilities After Block Removal: ");
                    //PrintSudokuGridPossibilities();

                    leastPossibilityBox.setValue(nextValue);
                    leastPossibilityBox.possibilities.clear();
                    leastPossibilityBox.possibilities.add(nextValue);
                    tempBoxes.remove(leastPossibilityBox);
                    //System.out.println("Sudoku Grid Possibilities End Of Removal: ");
                    //PrintSudokuGridPossibilities();

                }
                else
                {
                    numBoxesInvalid++;
                }

            /*
            PrintSudokuGrid();

            System.out.println("----------------------------------------------");
            System.out.println("Number of Boxes Invalid: " + numBoxesInvalid);
            System.out.println("");

            System.out.println("Press Enter...");
            try
            {
                System.in.read();
            }
            catch(IOException e)
            {
                System.out.println(e);
            }
            */
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return -1;
            }
        }
        //End WCF Loop

        return 1;
        //System.out.println("Sudoku Grid Possibilities End: ");
        //PrintSudokuGridPossibilities();
    }

    public void PrintSudokuGrid()
    {
        String boxValues;
        for(int i = 1; i <= 9; i++)
        {
            boxValues = "";
            for(int j = 0; j < 9; j++)
            {
                List<Sudoku_Box> row = rows.get(i);
                Sudoku_Box sudoku_Box = row.get(j);
                boxValues = boxValues + "["+sudoku_Box.getValue()+"]";
            }
            System.out.println(boxValues);
        }
    }

    public void PrintSudokuGridPossibilities()
    {
        String boxValues;
        for(int i = 1; i <= 9; i++)
        {
            boxValues = "";
            for(int j = 0; j < 9; j++)
            {
                List<Sudoku_Box> row = rows.get(i);
                Sudoku_Box sudoku_Box = row.get(j);
                boxValues = boxValues + "["+sudoku_Box.possibilities.size()+"]";
            }
            System.out.println(boxValues);
        }
    }

    public boolean CheckIfGridIsInvalid()
    {
        for (Sudoku_Box sudoku_Box:boxes) {
            if(sudoku_Box.possibilities.size() <= 0)
            {
                //System.out.println("Sudoku Grid is Invalid!");
                return true;
            }
        }
        return false;
    }
}
