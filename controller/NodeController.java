package controller;

import custom_event.CustomFocusListener;
import custom_event.CustomKeyListener;
import gui.PuzzleScreen;
import model.AchievementModel;
import model.NodeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class NodeController
{
    private final PuzzleScreen          puzzleScreen;
    private final TimeController        timeController;
    private final AchievementModel      achievementModel;
    private final PuzzleController      puzzleController;
    private final ArrayList<JTextField> textFieldList;

    private boolean isStart = false;
    private int     currPos;

    public NodeController(PuzzleScreen puzzleScreen, TimeController timeController, AchievementModel achievementModel)
    {
        this.puzzleScreen     = puzzleScreen;
        this.puzzleController = puzzleScreen.getPuzzleSetUp();
        this.textFieldList    = puzzleScreen.getTextFieldList();
        this.timeController   = timeController;
        this.achievementModel = achievementModel;

        for (int i = 0; i < this.textFieldList.size(); i++)
        {
            this.textFieldList.get(i).addKeyListener(new ValidateInput(i));
            this.textFieldList.get(i).addFocusListener(new MyFocusListener(i));
        }
    }


    private void setCurrPos(int i)
    {
        currPos = i;
    }

    public void resetCurrentPosition()
    {
        currPos = -1;
    }

    public boolean isStart()
    {
        return this.isStart;
    }

    public void setStart(boolean start)
    {
        this.isStart = start;
    }

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

    private void resetInfo()
    {
        achievementModel.setCountHints(0);
        achievementModel.setCountChecks(0);
    }


    private void _switchScreen(boolean isWait)
    {
        puzzleScreen.switchScreen(isWait);
    }

    public void start(String playerName)
    {
        achievementModel.setPlayerName(playerName);
    }

    public boolean createNewGame(String mode)
    {

        boolean success = puzzleController.changePzArr(mode);
        if (success)
        {
            resetCurrentPosition();
            resetJTextFields();
            setStatusJTextFields(true);
            resetInfo();
            setStart(true);

            achievementModel.setMode(mode);
            puzzleScreen.initGUI();
            puzzleScreen.switchScreen(false);
        }

        return success;
    }

    public void resume()
    {
        _switchScreen(false);
    }

    public void pause()
    {
        _switchScreen(true);
    }

    public void hint()
    {
        if (!isStart)
        {
            System.out.println("A new game has not been started!");
            return;
        }

        if (currPos == -1)
        {
            System.out.println("Please choose a position!");
            return;
        }

        char tempVal = puzzleController.getNodeList().get(currPos).getAnswer();
        puzzleController.getNodeList().get(currPos).setVal(tempVal);
        textFieldList.get(currPos).setText(String.valueOf(tempVal));

        achievementModel.increaseCountHints(1);

        finishPuzzle();
    }

    public void check()
    {
        if (!isStart)
        {
            System.out.println("A new game has not been started!");
            return;
        }

        ArrayList<NodeModel> nodeList = puzzleController.getNodeList();
        for (int i = 0; i < nodeList.size(); i++)
        {
            if (nodeList.get(i).getVal() != nodeList.get(i).getAnswer())
            {
                textFieldList.get(i).setForeground(Color.red);
            }
        }

        achievementModel.increaseCountChecks(1);
    }

    private boolean checkAll()
    {
        ArrayList<NodeModel> temp = puzzleController.getNodeList();

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
        if (!isStart)
        {
            return;
        }

        if (checkAll())
        {
            resetCurrentPosition();
            setStatusJTextFields(false);
            setStart(false);

            timeController.pause();


            JOptionPane.showMessageDialog(null,
                                          "Bạn đã hoàn thành phần chơi!\n" +
                                          "Hãy chọn game mới để tiếp tục chơi",
                                          "",
                                          JOptionPane.INFORMATION_MESSAGE);

            _pushResultToDB();
        }
    }

    private void _pushResultToDB()
    {
        try
        {
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        achievementModel.setTime(timeController.getTime());

        AchievementController achievementController = new AchievementController();
        achievementController.insert(achievementModel);
    }

    class ValidateInput extends CustomKeyListener
    {
        private final int i;

        public ValidateInput(int i)
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
                puzzleController.getNodeList().get(i).setVal(' ');

                return;
            }

            puzzleController.getNodeList().get(i).setVal(input.charAt(0));

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
                textFieldList.get(i).setForeground(Color.decode("#0f81d8"));
            }
        }

        @Override
        public void focusLost(FocusEvent e)
        {
            for (int i = 0; i < textFieldList.size(); i++)
            {
                if (textFieldList.get(i).getText().equals("") ||
                    puzzleController.getNodeList().get(i).isImmutable())
                {
                    continue;
                }

                if (!textFieldList.get(i).getText().matches("^[1-9]$"))
                {
                    textFieldList.get(i).setText("");
                    puzzleController.getNodeList().get(i).setVal(' ');
                }
                else
                {
                    char tempVal = textFieldList.get(i).getText().charAt(0);
                    puzzleController.getNodeList().get(i).setVal(tempVal);
                }
            }

            finishPuzzle();
        }
    }
}
