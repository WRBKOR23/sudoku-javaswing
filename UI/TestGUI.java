package UI;

import Model.*;

import java.awt.*;

public class TestGUI
{
    private static SudokuModel sudokuModel;
    private static JTextModel playField;

    private static MainFrame mainFrame;
    private static DisplayPuzzle displayPuzzle;

    private static MenuGame menuGame;

    public TestGUI()
    {
        mainFrame = new MainFrame();

        sudokuModel = new SudokuModel();
        sudokuModel.setUpPuzzles();

        playField = new JTextModel();
        displayPuzzle = new DisplayPuzzle(playField.getTextFields());

        menuGame = new MenuGame(displayPuzzle, sudokuModel, playField.getTextFields());

        displayPuzzle.setPuzzle(sudokuModel.getPzArr());
    }

    public void run()
    {
        mainFrame.add(displayPuzzle.display(),BorderLayout.CENTER);
        mainFrame.add(menuGame, BorderLayout.EAST);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
