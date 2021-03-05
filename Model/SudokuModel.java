package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SudokuModel
{
    private static final String FILE_PATH_EASY = "D:/Sudoku/src/Puzzles/easy_mode.txt";
    private static final String FILE_PATH_NORMAL = "D:/Sudoku/src/Puzzles/normal_mode.txt";
    private static final String FILE_PATH_HARD = "D:/Sudoku/src/Puzzles/hard_mode.txt";

    private ArrayList<String> easyList;
    private ArrayList<String> normalList;
    private ArrayList<String> hardList;

    private final char[][] pzArr = new char[9][9];

    public SudokuModel()
    {

        for (int j = 0; j < 9; j++)
        {
            for (int k = 0; k < 9; k++)
            {
                pzArr[j][k] = ' ';
            }
        }

    }

    public char[][] getPzArr()
    {
        return pzArr;
    }

    public boolean changePzArr(String mode)
    {
        return setPzArr(mode);
    }

    public void setUpPuzzles()
    {
        easyList = getAllPuzzles(FILE_PATH_EASY);
        normalList = getAllPuzzles(FILE_PATH_NORMAL);
        hardList = getAllPuzzles(FILE_PATH_HARD);
    }

    private ArrayList<String> getAllPuzzles(String path)
    {
        ArrayList<String> list = new ArrayList<>();

        try
        {
            File file = new File(path);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
            {
                list.add(sc.nextLine());
            }

        } catch (IOException e)
        {
            e.printStackTrace();

        } finally
        {
            return list;
        }
    }

    public boolean setPzArr(String mode)
    {
        String input = "";

        switch (mode)
        {
            case "easy" -> {
                if (easyList.size() == 0)
                {
                    return false;
                }

                input = easyList.get(0);
                easyList.remove(0);
            }
            case "normal" -> {
                if (normalList.size() == 0)
                {
                    return false;
                }

                input = normalList.get(0);
                normalList.remove(0);
            }
            case "hard" -> {
                if (hardList.size() == 0)
                {
                    return false;
                }

                input = hardList.get(0);
                hardList.remove(0);
            }
        }

        int i = 0;
        for (int j = 0; j < 9; j++)
        {
            for (int k = 0; k < 9; k++)
            {
                pzArr[j][k] = input.charAt(i);
                i++;
            }
        }

        return true;
    }
}
