package wrpv302;

import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Sudoku_Grid {

    Map<Integer, List<Sudoku_Box>> rows;
    Map<Integer, List<Sudoku_Box>> columns;
    Map<Integer, List<Sudoku_Box>> blocks;
    List<Sudoku_Box> boxes;

    public Sudoku_Grid()
    {
        rows = new HashMap<>();
        columns = new HashMap<>();
        blocks = new HashMap<>();
        boxes = new ArrayList<>();

        InitializeValues();
    }

    public void InitializeValues()
    {
        List<Sudoku_Box> row;
        List<Sudoku_Box> column;
        List<Sudoku_Box> block;
        int blockNum = 1;

        for(int i = 1; i <= 9; i++)
        {
            for(int j = 1; j <= 9; j++)
            {
                //Determine Block Number
                if((i<=3 && i>=1) && (j<=3 && j>=1))
                {
                    blockNum = 1;
                }

                if((i<=3 && i>=1) && (j<=6 && j>=4))
                {
                    blockNum = 2;
                }

                if((i<=3 && i>=1) && (j<=9 && j>=7))
                {
                    blockNum = 3;
                }

                if((i<=6 && i>=4)  && (j<=3 && j>=1))
                {
                    blockNum = 4;
                }

                if((i<=6 && i>=4)  && (j<=6 && j>=4))
                {
                    blockNum = 5;
                }

                if((i<=6 && i>=4)  && (j<=9 && j>=7))
                {
                    blockNum = 6;
                }

                if((i<=9 && i>=7) && (j<=3 && j>=1))
                {
                    blockNum = 7;
                }

                if((i<=9 && i>=7) && (j<=6 && j>=4))
                {
                    blockNum = 8;
                }

                if((i<=9 && i>=7) && (j<=9 && j>=7))
                {
                    blockNum = 9;
                }

                Sudoku_Box sudoku_box = new Sudoku_Box(i,j,blockNum,-1);

                boxes.add(sudoku_box);

            }
        }

        for(int i = 1; i <= 9; i++)
        {

            int finalI = i;
            Stream<Sudoku_Box> streamRows = boxes.stream().filter(sudoku_box -> sudoku_box.rowNum == finalI);
            row = streamRows.toList();
            rows.put(i,row);

            Stream<Sudoku_Box> streamColumns = boxes.stream().filter(sudoku_box -> sudoku_box.columnNum == finalI);
            column = streamColumns.toList();
            columns.put(i,column);

            Stream<Sudoku_Box> streamBlocks = boxes.stream().filter(sudoku_box -> sudoku_box.blockNum == finalI);
            block = streamBlocks.toList();
            blocks.put(i,block);

        }

    }

    public void ClearAllBoxes()
    {
        for (Sudoku_Box sudokuBox:boxes) {
            sudokuBox.setValue(-1);
        }
    }

    public void SetBoxValue(int rowNum, int colNum, int value)
    {
        Stream<Sudoku_Box> streamBox = boxes.stream().filter(sudoku_box -> sudoku_box.rowNum == rowNum && sudoku_box.columnNum == colNum);
        List<Sudoku_Box> sudoku_Boxes = streamBox.toList();
        Sudoku_Box sudoku_Box = sudoku_Boxes.get(0);
        sudoku_Box.setValue(value);
    }

    public int GetBoxValue(int rowNum, int colNum)
    {
        Stream<Sudoku_Box> streamBox = boxes.stream().filter(sudoku_box -> sudoku_box.rowNum == rowNum && sudoku_box.columnNum == colNum);
        List<Sudoku_Box> sudoku_Boxes = streamBox.toList();
        Sudoku_Box sudoku_Box = sudoku_Boxes.get(0);
        return sudoku_Box.getValue();
    }

    public boolean IsBoxValueValid(int rowNum, int colNum, int value)
    {
        Stream<Sudoku_Box> streamBox = boxes.stream().filter(sudoku_box -> sudoku_box.rowNum == rowNum && sudoku_box.columnNum == colNum);
        List<Sudoku_Box> sudoku_Boxes = streamBox.toList();
        Sudoku_Box sudoku_Box = sudoku_Boxes.get(0);

        List<Sudoku_Box> sudoku_BoxRow = rows.get(rowNum);
        List<Sudoku_Box> sudoku_BoxCol = columns.get(colNum);
        List<Sudoku_Box> sudoku_BoxBlock = blocks.get(sudoku_Box.getBlockNum());

        boolean isValid = true;
        String invalidReasons = "";

        for (Sudoku_Box currentBox:sudoku_BoxRow) {
            if(currentBox.getValue() == value)
            {
                isValid = false;
                invalidReasons = invalidReasons + "Invalid due to row conflict.\n";
            }
        }

        for (Sudoku_Box currentBox:sudoku_BoxCol) {
            if(currentBox.getValue() == value)
            {
                isValid = false;
                invalidReasons = invalidReasons + "Invalid due to column conflict.\n";
            }
        }

        for (Sudoku_Box currentBox:sudoku_BoxBlock) {
            if(currentBox.getValue() == value)
            {
                isValid = false;
                invalidReasons = invalidReasons + "Invalid due to block conflict.\n";
            }
        }

        //System.out.println(invalidReasons);

        return isValid;
    }

    public List<Sudoku_Box> getBoxes() {
        return boxes;
    }

    public Map<Integer, List<Sudoku_Box>> getBlocks() {
        return blocks;
    }

    public Map<Integer, List<Sudoku_Box>> getColumns() {
        return columns;
    }

    public Map<Integer, List<Sudoku_Box>> getRows() {
        return rows;
    }
}

