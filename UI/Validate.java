package UI;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class Validate
{
    private final char[][] puzzle = new char[9][9];

    public char[][] getPuzzle()
    {
        return puzzle;
    }

    public void setPuzzle(char[][] puzzle)
    {
        char[][] temp = new char[9][9];

        for (int i = 0; i < 9; i++)
        {
            System.arraycopy(puzzle[i], 0, temp[i], 0, 9);
        }
        solveSudoku(temp, 0, 0);
    }

    private void copyPuzzleAnswer(char[][] temp)
    {
        for (int i = 0; i < 9; i++)
        {
            System.arraycopy(temp[i], 0, this.puzzle[i], 0, 9);
        }
    }

    void solveSudoku(char[][] temp, int x, int y)
    {
        if (y == 9)
        {
            if (x != 8)
            {
                solveSudoku(temp, x + 1, 0);
            }
            else
            {
                copyPuzzleAnswer(temp);
            }

        }
        else if (temp[x][y] == '0')
        {
            for (int k = 1; k <= 9; k++)
            {
                if (checkValid(temp, x, y, Integer.toString(k).charAt(0)))
                {
                    temp[x][y] = Integer.toString(k).charAt(0);
                    solveSudoku(temp, x, y + 1);
                    temp[x][y] = '0';
                }
            }
        }
        else
        {
            solveSudoku(temp, x, y + 1);
        }
    }

    boolean checkValid(char[][] temp,int x, int y, char k)
    {
        for (int i = 0; i < 9; i++)
        {
            if (temp[x][i] == k) return false;
        }
        for (int i = 0; i < 9; i++)
        {
            if (temp[i][y] == k) return false;
        }
        int a = x / 3, b = y / 3;
        for (int i = 3 * a; i < 3 * a + 3; i++)
        {
            for (int j = 3 * b; j < 3 * b + 3; j++)
            {
                if (temp[i][j] == k) return false;
            }
        }
        return true;
    }

    public char hint(int[] curr)
    {
        return puzzle[curr[0]][curr[1]];
    }

    public void printPz()
    {
        System.out.print("  | ");
        for (int i = 0; i < 9; i++) {
            System.out.print((i+1)+"  ");
        }
        System.out.println();


        System.out.print("----");
        for (int i = 0; i < 9; i++) {
            System.out.print("---");
        }
        System.out.println();

        int i = 1;
        for (char[] ints : this.puzzle) {
            System.out.print(i+" | ");
            for (char anInt : ints) {
                System.out.print((anInt!='0'?anInt:"_")+"  ");
            }
            System.out.println();
            i++;
        }

        System.out.println();
    }

}
