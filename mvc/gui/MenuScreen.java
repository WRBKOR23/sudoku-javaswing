package mvc.gui;

import mvc.gui.component.ButtonWithColorBG;
import mvc.gui.component.ButtonWithImageBG;
import mvc.gui.component.Label;
import mvc.gui.component.PanelWithImageBG;
import mvc.gui.component.TextField;
import mvc.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends PanelWithImageBG
{
    // player name field
    private TextField         playerNameField;
    // confirm button
    private ButtonWithColorBG confirmBt;

    // feature button
    private ButtonWithImageBG hintBt;
    private ButtonWithImageBG checkBt;
    private ButtonWithImageBG musicBt;
    private ButtonWithImageBG introBt;
    private ButtonWithImageBG achievBt;

    // control button
    private ButtonWithImageBG resumeBt;
    private ButtonWithImageBG pauseBt;

    // mode button
    private ButtonWithColorBG easyModeBt;
    private ButtonWithColorBG normalModeBt;
    private ButtonWithColorBG hardModeBt;
    private ButtonWithColorBG challengeModeBt;

    // time label
    Label clockLabel;

    // mode label
    Label modeLabel;

    public MenuScreen(Label clockLabel)
    {
        super(Constants.UIConfiguration.MENU_SCREEN_WIDTH,
              Constants.UIConfiguration.MENU_SCREEN_HEIGHT,
              "menu1");

        this.clockLabel = clockLabel;

        generateComponents();
        waitScreen();
    }

    public TextField getPlayerNameField()
    {
        return playerNameField;
    }

    public JButton getConfirmBt()
    {
        return confirmBt;
    }

    public ButtonWithImageBG getHintBt()
    {
        return hintBt;
    }

    public ButtonWithImageBG getCheckBt()
    {
        return checkBt;
    }

    public ButtonWithImageBG getMusicBt()
    {
        return musicBt;
    }

    public ButtonWithImageBG getIntroBt()
    {
        return introBt;
    }

    public ButtonWithImageBG getAchievBt()
    {
        return achievBt;
    }

    public ButtonWithImageBG getResumeBt()
    {
        return resumeBt;
    }

    public ButtonWithImageBG getPauseBt()
    {
        return pauseBt;
    }

    public ButtonWithColorBG getEasyModeBt()
    {
        return easyModeBt;
    }

    public ButtonWithColorBG getNormalModeBt()
    {
        return normalModeBt;
    }

    public ButtonWithColorBG getHardModeBt()
    {
        return hardModeBt;
    }

    public ButtonWithColorBG getChallengeModeBt()
    {
        return challengeModeBt;
    }

    private void generateComponents()
    {
        playerNameField = new TextField(230, 30, 20,
                                        Constants.UIConfiguration.BG_COLOR_3, Constants.UIConfiguration.FG_GROUND_2);

        confirmBt = new ButtonWithColorBG("CONFIRM", 100, 40, 18,
                                          Constants.UIConfiguration.BG_COLOR_1, Constants.UIConfiguration.FG_GROUND_2);

        hintBt   = new ButtonWithImageBG("hint", false, 40, 40);
        checkBt  = new ButtonWithImageBG("check", false, 40, 40);
        musicBt  = new ButtonWithImageBG("music", false, 40, 40);
        introBt  = new ButtonWithImageBG("introductions", false, 40, 40);
        achievBt = new ButtonWithImageBG("achievement", false, 40, 40);

        resumeBt = new ButtonWithImageBG("resume", true, 50, 50);
        pauseBt  = new ButtonWithImageBG("pause", true, 50, 50);

        easyModeBt = new ButtonWithColorBG("Easy", 130, 50, 18,
                                           Constants.UIConfiguration.EASY_BUTTON_BG, Constants.UIConfiguration.FG_GROUND_3);

        normalModeBt = new ButtonWithColorBG("Normal", 130, 50, 18,
                                             Constants.UIConfiguration.NORMAL_BUTTON_BG, Constants.UIConfiguration.FG_GROUND_3);

        hardModeBt = new ButtonWithColorBG("Hard", 130, 50, 18,
                                           Constants.UIConfiguration.HARD_BUTTON_BG, Constants.UIConfiguration.FG_GROUND_3);

        challengeModeBt = new ButtonWithColorBG("Challenge", 130, 50, 18,
                                                Constants.UIConfiguration.CHALLENGE_BUTTON_BG, Constants.UIConfiguration.FG_GROUND_3);

        modeLabel = new Label("None", 150, 50, 25,
                              Color.decode("#fd5f92"), Color.decode("#2c2c62"));
    }

    public void waitScreen()
    {
        removeAll();
        revalidate();
        repaint();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createPanelWaitScreen());
    }

    private JPanel createPanelWaitScreen()
    {
        PanelWithImageBG panel = new PanelWithImageBG(302, 646, "menu");
        panel.setPreferredSize(new Dimension(302, 100));

        JLabel userNameLabel = new JLabel(" Enter player name: ");
        userNameLabel.setFont(new Font(Constants.UIConfiguration.FONT_1, Font.BOLD, 30));
        userNameLabel.setForeground(Constants.UIConfiguration.FG_GROUND_2);

        panel.add(Box.createRigidArea(new Dimension(302, 220)));
        panel.add(userNameLabel);
        panel.add(playerNameField);
        panel.add(confirmBt);

        return panel;
    }

    public void initGUI()
    {
        removeAll();
        revalidate();
        repaint();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createFeatureArea());
        add(createGameDetailArea());
        add(createControlArea());
        add(createModeArea());
    }

    // ------ FEATURE AREA --------------------------------------------

    private JPanel createFeatureArea()
    {
        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        featurePanel.setBackground(new Color(0, 0, 0, 0));

        featurePanel.add(hintBt);
        featurePanel.add(checkBt);
        featurePanel.add(musicBt);
        featurePanel.add(introBt);
        featurePanel.add(achievBt);

        return featurePanel;
    }

    //------------------------------------------------------------------------


    //----------- TIME AREA --------------------------------------------------

    private JPanel createGameDetailArea()
    {
        JPanel gameDetailArea = new JPanel();
        gameDetailArea.setLayout(new BoxLayout(gameDetailArea, BoxLayout.X_AXIS));

        gameDetailArea.add(_createPartOfGameDetailArea("TIME", clockLabel, 1));
        gameDetailArea.add(_createPartOfGameDetailArea("MODE", modeLabel, -1));

        return gameDetailArea;
    }

    private JPanel _createPartOfGameDetailArea(String str, JLabel label, int pos)
    {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        if (pos == 1)
        {
            panel.setBorder(BorderFactory.createMatteBorder(4, 0, 4, 2,
                                                            Constants.UIConfiguration.LINE_BORDER_1));
        }
        else
        {
            panel.setBorder(BorderFactory.createMatteBorder(4, 2, 4, 0,
                                                            Constants.UIConfiguration.LINE_BORDER_1));
        }

        panel.add(_createTitlePanel(str));
        panel.add(_createStatusGamePanel(label));

        return panel;
    }

    private JPanel _createTitlePanel(String str)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setMaximumSize(new Dimension(151, 50));
        panel.add(_createLabel(str));

        return panel;
    }

    private JPanel _createStatusGamePanel(JLabel label)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setMaximumSize(new Dimension(151, 50));
        panel.add(label);

        return panel;
    }

    private JLabel _createLabel(String str)
    {
        JLabel label = new Label(str, 150, 50, 25,
                                 Constants.UIConfiguration.BG_COLOR_1, Constants.UIConfiguration.FG_GROUND_2);

        return label;
    }

    // -----------------------------------------------------------------------


    // -------- CONTROL AREA ------------------------------------------

    private JPanel createControlArea()
    {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        controlPanel.setBackground(new Color(0, 0, 0, 0));
        controlPanel.setPreferredSize(new Dimension(300, 50));

        controlPanel.add(resumeBt);
        controlPanel.add(pauseBt);

        return controlPanel;
    }

    //----------------------------------------------------------------


    // ------ MODE AREA ---------------------------------------------------------

    private JPanel createModeArea()
    {
        JPanel modeArea = new JPanel();
        modeArea.setLayout(new BoxLayout(modeArea, BoxLayout.Y_AXIS));
        modeArea.setBackground(new Color(0, 0, 0, 0));

        modeArea.add(createModePanel(challengeModeBt));
        modeArea.add(createModePanel(hardModeBt));
        modeArea.add(createModePanel(normalModeBt));
        modeArea.add(createModePanel(easyModeBt));

        return modeArea;
    }

    private JPanel createModePanel(JButton button)
    {
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        modePanel.setBackground(new Color(0, 0, 0, 0));
        modePanel.add(button);

        return modePanel;
    }

    //------------------------------------------------------------

    public void changeMusicButtonIcon(String buttonName)
    {
        musicBt.setIconImage(buttonName, 40, 40);
    }


    public void createNewGame(String mode)
    {
        clockLabel.setText("00:00:00");
        switch (mode)
        {
            case "easy" -> {
                modeLabel.setText(easyModeBt.getText());
            }

            case "normal" -> {
                modeLabel.setText(normalModeBt.getText());
            }

            case "hard" -> {
                modeLabel.setText(hardModeBt.getText());
            }

            case "challenge" -> {
                modeLabel.setText(challengeModeBt.getText());
            }
        }
    }


}
