package gui;

import controller.PuzzleSetUp;
import model.*;

public class TestGUI
{
    private static MainFrame mainFrame;
    private static DisplayPuzzle displayPuzzle;
    private static MenuGame menuGame;

    public TestGUI()
    {
        PuzzleSetUp puzzleSetUp = new PuzzleSetUp();
        JTextFieldModel playField = new JTextFieldModel();

        displayPuzzle = new DisplayPuzzle(puzzleSetUp, playField.getTextFieldList());
        menuGame = new MenuGame(displayPuzzle);

        mainFrame = new MainFrame(menuGame, displayPuzzle);
    }
}
