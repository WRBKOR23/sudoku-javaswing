package gui;

import component.ButtonWithColorBG;
import component.ButtonWithImageBG;
import component.Label;
import component.PanelWithImageBG;
import component.TextField;
import controller.MusicController;
import controller.TimeController;
import custom_event.CustomActionListener;
import custom_event.CustomMouseListener;
import custom_event.CustomWindowListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class MenuScreen extends PanelWithImageBG
{
    // player name field
    private JTextField playerNameField;
    // confirm button
    private JButton confirmBt;

    // feature button
    private JButton hintBt;
    private JButton checkBt;
    private JButton musicBt;
    private JButton introBt;
    private JButton achievBt;

    // control button
    private JButton resumeBt;
    private JButton pauseBt;

    // mode button
    private JButton easyModeBt;
    private JButton normalModeBt;
    private JButton hardModeBt;
    private JButton challengeModeBt;

    // time label
    JLabel clockLabel;

    // mode label
    JLabel modeLabel;

    public MenuScreen(JLabel clockLabel)
    {
        super(310, 646, "menu1");

        this.clockLabel = clockLabel;

        generateComponents();
        waitScreen();
    }

    public JTextField getPlayerNameField()
    {
        return playerNameField;
    }

    public JButton getConfirmBt()
    {
        return confirmBt;
    }

    public JButton getHintBt()
    {
        return hintBt;
    }

    public JButton getCheckBt()
    {
        return checkBt;
    }

    public JButton getMusicBt()
    {
        return musicBt;
    }

    public JButton getIntroBt()
    {
        return introBt;
    }

    public JButton getAchievBt()
    {
        return achievBt;
    }

    public JButton getResumeBt()
    {
        return resumeBt;
    }

    public JButton getPauseBt()
    {
        return pauseBt;
    }

    public JButton getEasyModeBt()
    {
        return easyModeBt;
    }

    public JButton getNormalModeBt()
    {
        return normalModeBt;
    }

    public JButton getHardModeBt()
    {
        return hardModeBt;
    }

    public JButton getChallengeModeBt()
    {
        return challengeModeBt;
    }

    private void generateComponents()
    {
        playerNameField = new TextField(230, 30, 20, Color.white, Color.decode("#2c2c62"));
        confirmBt       = new ButtonWithColorBG("CONFIRM", 100, 40, 18, Color.decode("#fd5f92"), Color.decode("#2c2c62"));

        hintBt   = new ButtonWithImageBG("hint", false, 40, 40);
        checkBt  = new ButtonWithImageBG("check", false, 40, 40);
        musicBt  = new ButtonWithImageBG("music", false, 40, 40);
        introBt  = new ButtonWithImageBG("introductions", false, 40, 40);
        achievBt = new ButtonWithImageBG("achievement", false, 40, 40);

        resumeBt = new ButtonWithImageBG("resume", true, 50, 50);
        pauseBt  = new ButtonWithImageBG("pause", true, 50, 50);

        easyModeBt      = new ButtonWithColorBG("Easy", 130, 50, 18, Color.decode("#1cde12"), Color.white);
        normalModeBt    = new ButtonWithColorBG("Normal", 130, 50, 18, Color.decode("#c312de"), Color.white);
        hardModeBt      = new ButtonWithColorBG("Hard", 130, 50, 18, Color.decode("#ff9317"), Color.white);
        challengeModeBt = new ButtonWithColorBG("Challenge", 130, 50, 18, Color.decode("#ff1717"), Color.white);

        modeLabel = new Label("None", 150, 50, 25, Color.decode("#fd5f92"), Color.decode("#2c2c62"));
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
        PanelWithImageBG panel = new PanelWithImageBG(310, 646, "menu4");
        panel.setPreferredSize(new Dimension(300, 100));

        JLabel userNameLabel = new JLabel(" Enter player name: ");
        userNameLabel.setFont(new Font("arial", Font.BOLD, 30));
        userNameLabel.setForeground(Color.decode("#2c2c62"));


        panel.add(Box.createRigidArea(new Dimension(300, 220)));
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
            panel.setBorder(BorderFactory.createMatteBorder(4, 0, 4, 2, Color.decode("#2197f1")));
        }
        else
        {
            panel.setBorder(BorderFactory.createMatteBorder(4, 2, 4, 0, Color.decode("#2197f1")));
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
                                 Color.decode("#fd5f92"), Color.decode("#2c2c62"));

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

    private Image getIconButton(String nameButton, int size)
    {
        String directory = "src/icon_image/" + nameButton + "_button.png";
        Image  img       = null;

        try
        {
            img = ImageIO.read(new File(directory));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        assert img != null;
        img = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);

        return img;
    }

    public void changeMusicButtonIcon(String buttonName)
    {
        Icon icon = new ImageIcon((getIconButton(buttonName, 40)));
        musicBt.setIcon(icon);
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
