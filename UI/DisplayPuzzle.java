package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class DisplayPuzzle
{
    private final JPanel mainPanel;

    private final JTextField[][] textFields;

    private char[][] puzzle = new char[9][9];

    private int currCol, currRow;

    public char[][] getPuzzle()
    {
        return puzzle;
    }

    public JTextField[][] getTextFields()
    {
        return textFields;
    }

    public void setPuzzle(char[][] puzzle)
    {
        this.puzzle = puzzle;
    }

    private void setCurr(int i, int j)
    {
        currRow = i;
        currCol = j;
    }

    public int[] getCurr()
    {
        return new int[]{currRow, currCol};
    }

    public void suggestion(String val)
    {
        textFields[currRow][currCol].setText(val);
    }

    public DisplayPuzzle(JTextField[][] textFields)
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3, 1, 1));

        this.textFields = textFields;

        setupWaitScreen();
    }

    public JPanel display()
    {
        return mainPanel;
    }

    public void setupWaitScreen()
    {
        mainPanel.removeAll();

        for (int i = 0; i < 9; i++)
        {
            JPanel extraPanel = new JPanel();
            extraPanel.setLayout(new GridLayout(3, 3, 1, 1));
            extraPanel.setBorder(BorderFactory.createLineBorder(Color.red));

            for (int j = 0; j < 9; j++)
            {
                extraPanel.add(uneditable(""));
            }

            mainPanel.add(extraPanel);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void initGUI()
    {
        mainPanel.removeAll();

        for (int i = 0; i < 9; i++)
        {
            JPanel extraPanel = new JPanel();
            extraPanel.setLayout(new GridLayout(3, 3, 1, 1));
            extraPanel.setBorder(BorderFactory.createLineBorder(Color.red));

            for (int j = (i / 3) * 3; j < (i / 3) * 3 + 3; j++)
            {
                for (int k = (i - ((i / 3) * 3)) * 3; k < (i - ((i / 3) * 3)) * 3 + 3; k++)
                {
                    if (puzzle[j][k] != '0')
                    {
                        textFields[j][k] = new JTextField("");

                        extraPanel.add(uneditable(String.valueOf((puzzle[j][k]))));
                    }
                    else
                    {
                        extraPanel.add(editable(j, k));
                    }
                }
            }
            mainPanel.add(extraPanel);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JTextField editable(int i, int j)
    {
        textFields[i][j] = new JTextField();

        textFields[i][j].setPreferredSize(new Dimension(70, 70));
        textFields[i][j].setHorizontalAlignment(JTextField.CENTER);
        textFields[i][j].setFont(textFields[i][j].getFont().deriveFont(40.0f));
        textFields[i][j].setBorder(BorderFactory.createLineBorder(Color.black));

        textFields[i][j].addKeyListener(new ChangeValue(i, j));
        textFields[i][j].addMouseListener(new MousePosition(i, j));
        textFields[i][j].addFocusListener(new CustomFocusListener());

        return textFields[i][j];
    }

    private JLabel uneditable(String value)
    {
        JLabel label = new JLabel(value);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(label.getFont().deriveFont(40.0f));
        label.setOpaque(true);
        label.setBackground(Color.blue);
        label.setForeground(Color.white);
        label.setPreferredSize(new Dimension(70, 70));

        return label;
    }

    class ChangeValue implements KeyListener
    {
        private final int i, j;

        public ChangeValue(int i, int j)
        {
            this.i = i;
            this.j = j;
        }

        @Override
        public void keyTyped(KeyEvent e)
        {

        }

        @Override
        public void keyPressed(KeyEvent e)
        {

        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            if (!textFields[i][j].getText().matches("^[1-9]$"))
            {
                textFields[i][j].setText("");

                puzzle[i][j] = '0';

                return;
            }

            puzzle[i][j] = textFields[i][j].getText().charAt(0);
        }
    }

    class MousePosition extends MouseAdapter
    {
        private final int i;
        private final int j;

        public MousePosition(int i, int j)
        {
            this.i = i;
            this.j = j;
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            setCurr(i, j);
        }
    }

    class CustomFocusListener implements FocusListener
    {

        @Override
        public void focusGained(FocusEvent e)
        {
            for (int i = 0; i < 9; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    textFields[i][j].setForeground(Color.black);
                }
            }
        }

        @Override
        public void focusLost(FocusEvent e)
        {
        }
    }
}
