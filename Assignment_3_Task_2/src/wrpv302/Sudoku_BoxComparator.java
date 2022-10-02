package wrpv302;

import java.util.Comparator;

public class Sudoku_BoxComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Sudoku_Box sudoku_Box1 = (Sudoku_Box) o1;
        Sudoku_Box sudoku_Box2 = (Sudoku_Box) o2;

        if(sudoku_Box1.possibilities.size() > sudoku_Box2.possibilities.size())
        {
            return 1;
        }
        else if(sudoku_Box1.possibilities.size() < sudoku_Box2.possibilities.size())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
