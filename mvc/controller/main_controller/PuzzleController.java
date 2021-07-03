package mvc.controller.main_controller;

import mvc.model.NodeModel;
import mvc.utils.PuzzlesFileUtils;

import java.util.ArrayList;

public class PuzzleController
{
    private final Validate validate;

    private ArrayList<String> easyList;
    private ArrayList<String> normalList;
    private ArrayList<String> hardList;
    private ArrayList<String> challengeList;

    private final ArrayList<NodeModel> nodeList = new ArrayList<>();


    public ArrayList<NodeModel> getNodeList()
    {
        return nodeList;
    }

    public PuzzleController()
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
        easyList = getAllPuzzles("easy");
        normalList = getAllPuzzles("normal");
        hardList = getAllPuzzles("hard");
        challengeList = getAllPuzzles("challenge");
    }

    public boolean changePzArr(String mode)
    {
        return setPzArr(mode);
    }

    private ArrayList<String> getAllPuzzles(String mode)
    {

        ArrayList<String> list;
        list = PuzzlesFileUtils.readPuzzles(mode);

        return list;
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

                input = _getRandomPuzzle(easyList);
            }

            case "normal" -> {
                if (normalList.size() == 0)
                {
                    return false;
                }

                input = _getRandomPuzzle(normalList);
            }

            case "hard" -> {
                if (hardList.size() == 0)
                {
                    return false;
                }

                input = _getRandomPuzzle(hardList);
            }

            case "challenge" -> {
                if (challengeList.size() == 0)
                {
                    return false;
                }

                input = _getRandomPuzzle(challengeList);
            }
        }

        parsePuzzle(input);

        validate.setPuzzleAnswer(this.nodeList);

        return true;
    }

    private String _getRandomPuzzle(ArrayList<String> arrayList)
    {
        int index = (int) (Math.random() * (arrayList.size() - 1));
        String input = arrayList.get(index);
        arrayList.remove(input);

        return input;
    }

    private void parsePuzzle(String input)
    {

        int i = 0;
        for (int j = 0; j < 9; j++)
        {
            for (int k = 0; k < 9; k++)
            {
                nodeList.get(i).setVal(input.charAt(i));
                nodeList.get(i).setImmutable(nodeList.get(i).getVal() != ' ');
                i++;
            }
        }

    }
}
