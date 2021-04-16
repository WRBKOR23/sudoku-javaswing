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
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
//        Point newLocation = new Point(middle.x - (this.getWidth() / 2),
//                middle.y - (this.getHeight() / 2));
//        setLocation(newLocation);
        setLocation(150, 150);
        add(gameScreen, BorderLayout.CENTER);
        add(menuScreen, BorderLayout.EAST);

        pack();
        setVisible(true);
    }
}
