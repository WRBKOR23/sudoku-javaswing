package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private JPanel menuScreen;
    private JPanel gameScreen;

    public MainFrame(JPanel menuScreen, JPanel gameScreen) throws HeadlessException
    {
        this.menuScreen = menuScreen;
        this.gameScreen = gameScreen;

        initGUI();

    }

    private void initGUI()
    {
        setTitle("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(gameScreen, BorderLayout.CENTER);
        add(menuScreen, BorderLayout.EAST);

        pack();
        setVisible(true);
    }
}
