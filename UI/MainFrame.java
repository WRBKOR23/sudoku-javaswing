package UI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    public MainFrame() throws HeadlessException
    {
        initGUI();
    }

    private void initGUI()
    {
        setTitle("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
