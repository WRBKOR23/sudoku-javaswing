package mvc.gui;

import mvc.gui.component.Label;
import mvc.gui.component.PanelWithImageBG;
import mvc.gui.component.TextFields;
import mvc.controller.main_controller.PuzzleController;
import mvc.model.NodeModel;
import mvc.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PuzzleScreen extends JPanel
{
    private final JPanel           gameScreen;
    private final PanelWithImageBG waitScreen;

    private final PuzzleController puzzleController;

    private final ArrayList<JTextField> textFieldList;

    public PuzzleScreen()
    {
        this.gameScreen = new JPanel();
        this.waitScreen = new PanelWithImageBG(646, 646, "game");

        this.puzzleController = new PuzzleController();
        this.textFieldList    = new TextFields().getTextFieldList();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        introScreen();
        switchScreen(true);
    }

    public PuzzleController getPuzzleSetUp()
    {
        return puzzleController;
    }

    public ArrayList<JTextField> getTextFieldList()
    {
        return textFieldList;
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

    private void introScreen()
    {
        this.waitScreen.setPreferredSize(new Dimension(646, 646));
    }

    public void initGUI()
    {
        this.gameScreen.removeAll();
        this.gameScreen.revalidate();
        this.gameScreen.repaint();

        this.gameScreen.setLayout(new GridLayout(3, 3, 1, 1));

        for (int i = 0; i < 9; i++)
        {
            JPanel extraPanel = new JPanel();
            extraPanel.setLayout(new GridLayout(3, 3, 1, 1));
            extraPanel.setBorder(BorderFactory.createLineBorder(Constants.UIConfiguration.LINE_BORDER_3));

            ArrayList<NodeModel> temp = puzzleController.getNodeList();
            for (int j = (i / 3) * 3; j < (i / 3) * 3 + 3; j++)
            {
                for (int k = (i - ((i / 3) * 3)) * 3; k < (i - ((i / 3) * 3)) * 3 + 3; k++)
                {
                    if (temp.get(9 * j + k).isImmutable())
                    {
                        extraPanel.add(uneditableNode(String.valueOf((temp.get(9 * j + k).getVal()))));
                    }
                    else
                    {
                        extraPanel.add(editableNode(9 * j + k));
                    }
                }
            }
            this.gameScreen.add(extraPanel);
        }
    }

    private JTextField editableNode(int i)
    {
        return textFieldList.get(i);
    }

    private JLabel uneditableNode(String value)
    {
        JLabel label = new Label(value, 70, 70, 40,
                                 Constants.UIConfiguration.BG_COLOR_2, Constants.UIConfiguration.FG_GROUND_3);

        return label;
    }
    // ----------------------------------------------------------------------------


    /* ------- FEATURE ------------------------------------------------------------*/


    /* -------------------------------------------------------------------------------*/


    // ------- MODIFY JTEXTFIELDS --------------------------------------------


    // ----------------------------------------------------------------------------


    // ----------- FINISH ----------------------------------------------------

}
