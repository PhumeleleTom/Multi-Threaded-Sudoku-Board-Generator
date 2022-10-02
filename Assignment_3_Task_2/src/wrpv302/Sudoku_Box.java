package wrpv302;

import java.util.ArrayList;
import java.util.List;

public class Sudoku_Box implements Comparable {
    int rowNum;
    int columnNum;
    int blockNum;
    int value;
    List<Integer> possibilities;

    public Sudoku_Box(int rowNum, int columnNum, int blockNum, int value)
    {
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.blockNum = blockNum;
        this.value = value;

        possibilities = new ArrayList<>();

        for(int i=1; i<=9; i++)
        {
            possibilities.add(i);
        }
    }

    @Override
    public String toString() {
        return "Sudoku_Box{" +
                "rowNum=" + rowNum +
                ", columnNum=" + columnNum +
                ", blockNum=" + blockNum +
                ", value=" + value +
                ", possibilities="+possibilities+
                "}\n";
    }

    public int getBlockNum() {
        return blockNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getValue() {
        return value;
    }

    public void setBlockNum(int blockNum) {
        this.blockNum = blockNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        Sudoku_Box o2 = (Sudoku_Box) o;
        if(rowNum == o2.rowNum)
        {
            if(columnNum > o2.columnNum)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }

        if(rowNum > o2.rowNum)
        {
            return 1;
        }
        else if(rowNum < o2.rowNum)
        {
            return -1;
        }

        return 0;
    }
}
