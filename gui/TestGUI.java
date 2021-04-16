package gui;

import controller.MusicControl;
import controller.PuzzleSetUp;
import controller.TimeControl;

public class TestGUI
{
    private static MainFrame mainFrame;
    private static DisplayPuzzle displayPuzzle;
    private static MenuGame menuGame;

    public TestGUI()
    {
        PuzzleSetUp puzzleSetUp = new PuzzleSetUp();
        JTextFields playField = new JTextFields();
        TimeControl timeControl = new TimeControl();
        MusicControl musicControl = new MusicControl();

        displayPuzzle = new DisplayPuzzle(puzzleSetUp, playField.getTextFieldList(), timeControl);
        menuGame      = new MenuGame(displayPuzzle, timeControl, musicControl);

        mainFrame = new MainFrame(menuGame, displayPuzzle);
    }
}
