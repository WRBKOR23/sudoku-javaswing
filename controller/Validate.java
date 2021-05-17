package controller;

import model.NodeModel;

import java.util.ArrayList;

public class Validate
{
    private final char[][] puzzleAnswer = new char[9][9];

    public void setPuzzleAnswer(ArrayList<NodeModel> puzzleAnswer)
    {
        char[][] temp = new char[9][9];

        int i = 0;
        for (int j = 0; j < 9; j++)
        {
            for (int k = 0; k < 9; k++)
            {
                temp[j][k] = puzzleAnswer.get(i).getVal();

                i++;
            }
        }

        solveSudoku(temp, 0, 0);

        i = 0;
        for (int j = 0; j < 9; j++)
        {
            for (int k = 0; k < 9; k++)
            {
                puzzleAnswer.get(i).setAnswer(this.puzzleAnswer[j][k]);

                i++;
            }
        }
    }

    private void copyPuzzleAnswer(char[][] temp)
    {
        for (int i = 0; i < 9; i++)
        {
            System.arraycopy(temp[i], 0, this.puzzleAnswer[i], 0, 9);
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
        else if (temp[x][y] == ' ')
        {
            for (int k = 1; k <= 9; k++)
            {
                if (checkValid(temp, x, y, Integer.toString(k).charAt(0)))
                {
                    temp[x][y] = Integer.toString(k).charAt(0);
                    solveSudoku(temp, x, y + 1);
                    temp[x][y] = ' ';
                }
            }
        }
        else
        {
            solveSudoku(temp, x, y + 1);
        }
    }

    boolean checkValid(char[][] temp, int x, int y, char k)
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
}
