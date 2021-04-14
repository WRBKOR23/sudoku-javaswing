package controller;

import model.NodeModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PuzzleSetUp
{
    private final Validate validate;

    private static final String FILE_PATH_EASY = "D:/Sudoku/src/puzzles/easy_mode.txt";
    private static final String FILE_PATH_NORMAL = "D:/Sudoku/src/puzzles/normal_mode.txt";
    private static final String FILE_PATH_HARD = "D:/Sudoku/src/puzzles/hard_mode.txt";
    private static final String FILE_PATH_CHALLENGE = "D:/Sudoku/src/puzzles/challenge_mode.txt";

    private ArrayList<String> easyList;
    private ArrayList<String> normalList;
    private ArrayList<String> hardList;
    private ArrayList<String> challengeList;

    private final ArrayList<NodeModel> nodeList = new ArrayList<>();


    public ArrayList<NodeModel> getNodeList()
    {
        return nodeList;
    }

    public PuzzleSetUp()
    {
        for (int j = 0; j < 81; j++)
        {
            nodeList.add(new NodeModel());
        }

        this.validate = new Validate();

        setUpPuzzles();

    }

    private void setUpPuzzles()
    {
        easyList = getAllPuzzles(FILE_PATH_EASY);
        normalList = getAllPuzzles(FILE_PATH_NORMAL);
        hardList = getAllPuzzles(FILE_PATH_HARD);
        challengeList = getAllPuzzles(FILE_PATH_CHALLENGE);
    }

    public boolean changePzArr(String mode)
    {
        return setPzArr(mode);
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

            case "challenge" -> {
                if (challengeList.size() == 0)
                {
                    return false;
                }

                input = challengeList.get(0);
                challengeList.remove(0);
            }
        }

        parsePuzzle(input);

        validate.setPuzzleAnswer(this.nodeList);

        return true;
    }

    private void parsePuzzle(String input)
    {

        int i = 0;
        for (int j = 0; j < 9; j++)
        {
            for (int k = 0; k < 9; k++)
            {
                nodeList.get(i).setRow(j);
                nodeList.get(i).setCol(k);
                nodeList.get(i).setVal(input.charAt(i));
                nodeList.get(i).setImmutable(nodeList.get(i).getVal() != ' ');

                i++;
            }
        }

    }
}
