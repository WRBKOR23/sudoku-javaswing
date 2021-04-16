package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import controller.AchievementController;
import controller.TimeControl;
import custom_event.CustomFocusListener;
import custom_event.CustomKeyListener;
import model.AchievementModel;
import model.NodeModel;
import controller.PuzzleSetUp;

public class DisplayPuzzle extends JPanel
{
    private final JPanel gameScreen;
    private final JPanel waitScreen;

    private final PuzzleSetUp puzzleSetUp;
    private final TimeControl timeControl;

    private final ArrayList<JTextField> textFieldList;
    private final AchievementModel achievementModel;

    private boolean flagStart = false;

    private int currPos;

    public DisplayPuzzle(PuzzleSetUp puzzleSetUp, ArrayList<JTextField> textFieldList, TimeControl timeControl)
    {
        achievementModel = new AchievementModel();
        this.gameScreen  = new JPanel();
        this.waitScreen  = new JPanel();

        this.puzzleSetUp   = puzzleSetUp;
        this.textFieldList = textFieldList;
        this.timeControl   = timeControl;

        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        setBackground(Color.red);
        waitScreen();
        switchScreen(true);
    }

    public AchievementModel getAchievementModel()
    {
        return achievementModel;
    }

    private void setCurrPos(int i)
    {
        currPos = i;
    }

    public void resetCurrPos()
    {
        currPos = -1;
    }

    public boolean isFlagStart()
    {
        return flagStart;
    }

    public void setFlagStart(boolean flagStart)
    {
        this.flagStart = flagStart;
    }

    public void resetInfo()
    {
        achievementModel.setCountHints(0);
        achievementModel.setCountChecks(0);
    }


    // --------------- GENERATE ----------------------------------------------

    public void switchScreen(boolean isWait)
    {
        if (isWait)
        {
            removeAll();
            revalidate();
            repaint();
            add(this.waitScreen);
        }
        else
        {
            removeAll();
            revalidate();
            repaint();
            add(this.gameScreen);
        }
    }

    public void waitScreen()
    {
        this.waitScreen.setLayout(new GridLayout(3, 3, 1, 1));

        for (int i = 0; i < 9; i++)
        {
            JPanel extraPanel = new JPanel();
            extraPanel.setLayout(new GridLayout(3, 3, 1, 1));
            extraPanel.setBorder(BorderFactory.createLineBorder(Color.red));

            for (int j = 0; j < 9; j++)
            {
                extraPanel.add(uneditable(""));
            }

            this.waitScreen.add(extraPanel);
        }
    }

    public boolean createNewGame(String mode)
    {
        return puzzleSetUp.changePzArr(mode);
    }

    public void initGUI()
    {
        resetCurrPos();
        resetJTextFields();
        setStatusJTextFields(true);

        this.gameScreen.removeAll();
        this.gameScreen.revalidate();
        this.gameScreen.repaint();

        this.gameScreen.setLayout(new GridLayout(3, 3, 1, 1));

        for (int i = 0; i < 9; i++)
        {
            JPanel extraPanel = new JPanel();
            extraPanel.setLayout(new GridLayout(3, 3, 1, 1));
            extraPanel.setBorder(BorderFactory.createLineBorder(Color.red));

            ArrayList<NodeModel> temp = puzzleSetUp.getNodeList();
            for (int j = (i / 3) * 3; j < (i / 3) * 3 + 3; j++)
            {
                for (int k = (i - ((i / 3) * 3)) * 3; k < (i - ((i / 3) * 3)) * 3 + 3; k++)
                {
                    if (temp.get(9 * j + k).isImmutable())
                    {
                        extraPanel.add(uneditable(String.valueOf((temp.get(9 * j + k).getVal()))));
                    }
                    else
                    {
                        extraPanel.add(editable(9 * j + k));
                    }
                }
            }
            this.gameScreen.add(extraPanel);
        }
        switchScreen(false);
    }

    private JTextField editable(int i)
    {
        textFieldList.get(i).setPreferredSize(new Dimension(70, 70));
        textFieldList.get(i).setHorizontalAlignment(JTextField.CENTER);
        textFieldList.get(i).setFont(new Font("arial", Font.BOLD, 40));
        textFieldList.get(i).setBorder(BorderFactory.createLineBorder(Color.black));

        textFieldList.get(i).addKeyListener(new MyKeyListener(i));
        textFieldList.get(i).addFocusListener(new MyFocusListener(i));

        return textFieldList.get(i);
    }

    private JLabel uneditable(String value)
    {
        JLabel label = new JLabel(value);

        label.setPreferredSize(new Dimension(70, 70));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("arial", Font.BOLD, 40));

        label.setOpaque(true);
        label.setBackground(Color.blue);
        label.setForeground(Color.white);

        return label;
    }
    // ----------------------------------------------------------------------------


    /* ------- FEATURE ------------------------------------------------------------*/

    public void hint()
    {
        if (currPos == -1)
        {
            System.out.println("Please choose a position!");
            return;
        }

        char tempVal = puzzleSetUp.getNodeList().get(currPos).getAnswer();
        puzzleSetUp.getNodeList().get(currPos).setVal(tempVal);
        textFieldList.get(currPos).setText(String.valueOf(tempVal));

        achievementModel.increaseCountHints(1);

        finishPuzzle();
    }

    public void check()
    {
        ArrayList<NodeModel> nodeList = puzzleSetUp.getNodeList();
        for (int i = 0; i < nodeList.size(); i++)
        {
            if (nodeList.get(i).getVal() != nodeList.get(i).getAnswer())
            {
                textFieldList.get(i).setForeground(Color.red);
            }
        }

        achievementModel.increaseCountChecks(1);
    }

    /* -------------------------------------------------------------------------------*/


    // ------- MODIFY JTEXTFIELDS --------------------------------------------

    public void resetJTextFields()
    {
        for (int i = 0; i < 81; i++)
        {
            this.textFieldList.get(i).setText("");
        }
    }

    private void setStatusJTextFields(boolean status)
    {
        for (int i = 0; i < 81; i++)
        {
            textFieldList.get(i).setEnabled(status);
        }
    }

    // ----------------------------------------------------------------------------


    // ----------- FINISH ----------------------------------------------------
    private boolean checkAll()
    {
        ArrayList<NodeModel> temp = puzzleSetUp.getNodeList();

        for (NodeModel node : temp)
        {
            if (!node.check())
            {
                return false;
            }
        }

        return true;
    }

    private void finishPuzzle()
    {
        if (!flagStart)
        {
            return;
        }

        if (checkAll())
        {
            resetCurrPos();
            setStatusJTextFields(false);
            setFlagStart(false);

            timeControl.forceStop();

            achievementModel.setTime(timeControl.getTime());

            AchievementController achievementController = new AchievementController();
            achievementController.insert(achievementModel);

            JOptionPane.showMessageDialog(null,
                    "Bạn đã hoàn thành phần chơi!\n" +
                    "Hãy chọn game mới để tiếp tục chơi",
                    "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }


    class MyKeyListener extends CustomKeyListener
    {
        private final int i;

        public MyKeyListener(int i)
        {
            this.i = i;
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            String input = textFieldList.get(i).getText();
            if (!input.matches("^[1-9]$"))
            {
                textFieldList.get(i).setText("");
                puzzleSetUp.getNodeList().get(i).setVal(' ');

                return;
            }

            puzzleSetUp.getNodeList().get(i).setVal(input.charAt(0));

            finishPuzzle();
        }
    }

    class MyFocusListener extends CustomFocusListener
    {
        private final int i;

        public MyFocusListener(int i)
        {
            this.i = i;
        }

        @Override
        public void focusGained(FocusEvent e)
        {
            setCurrPos(i);

            for (int i = 0; i < 81; i++)
            {
                textFieldList.get(i).setForeground(Color.black);
            }
        }

        @Override
        public void focusLost(FocusEvent e)
        {
            for (int i = 0; i < textFieldList.size(); i++)
            {
                if (textFieldList.get(i).getText().equals("") ||
                    puzzleSetUp.getNodeList().get(i).isImmutable())
                {
                    continue;
                }

                if (!textFieldList.get(i).getText().matches("^[1-9]$"))
                {
                    textFieldList.get(i).setText("");
                    puzzleSetUp.getNodeList().get(i).setVal(' ');
                }
                else
                {
                    char tempVal = textFieldList.get(i).getText().charAt(0);
                    puzzleSetUp.getNodeList().get(i).setVal(tempVal);
                }
            }

            finishPuzzle();
        }
    }
}
