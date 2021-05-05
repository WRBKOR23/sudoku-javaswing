package gui;

import controller.MusicControl;
import custom_event.*;
import controller.TimeControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MenuGame extends JPanelWithBackground
{

    private final DisplayPuzzle display;

    // control thread
    private final TimeControl timeControl;
    private final MusicControl musicControl;

    // player name field
    private JTextField playerNameField;

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

    // even listener
    private GameControl gameControl;
    private ChangeAchievementBtStatus changeAchievementBtStatus;
    private MousePosition mousePosition;
    private HotKeyConfirm hotKeyConfirm;

    public MenuGame(DisplayPuzzle display, TimeControl timeControl, MusicControl musicControl)
    {
        super(310, 646, "menu1");
        this.display = display;

        this.timeControl  = timeControl;
        this.musicControl = musicControl;

        generateEvents();
        generateComponents();

        waitScreen();
    }

    private void generateEvents()
    {
        gameControl               = new GameControl();
        changeAchievementBtStatus = new ChangeAchievementBtStatus();
        mousePosition             = new MousePosition();
        hotKeyConfirm             = new HotKeyConfirm();
    }

    private void generateComponents()
    {
        hintBt   = createFeatureButton("hint");
        checkBt  = createFeatureButton("check");
        musicBt  = createFeatureButton("music");
        introBt  = createFeatureButton("introductions");
        achievBt = createFeatureButton("achievement");

        resumeBt = createControlButton("resume");
        pauseBt  = createControlButton("pause");

        easyModeBt      = createModeButton("Easy", "#1cde12");
        normalModeBt    = createModeButton("Normal", "#c312de");
        hardModeBt      = createModeButton("Hard", "#ff9317");
        challengeModeBt = createModeButton("Challenge", "#ff1717");

        clockLabel = createClockLabel();
        modeLabel  = createModeGameLabel();
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
        JPanelWithBackground panel = new JPanelWithBackground(310, 646, "menu4");
        panel.setPreferredSize(new Dimension(300, 100));

        JLabel userNameLabel = new JLabel(" Enter player name: ");
        userNameLabel.setFont(new Font("arial", Font.BOLD, 30));
        userNameLabel.setForeground(Color.decode("#2c2c62"));

        playerNameField = new JTextField();
        playerNameField.setPreferredSize(new Dimension(200, 30));
        playerNameField.setFont(new Font("arial", Font.BOLD, 20));
        playerNameField.addActionListener(hotKeyConfirm);
        playerNameField.setForeground(Color.decode("#2c2c62"));

        JButton confirmBt = new JButton("START");
        confirmBt.setPreferredSize(new Dimension(100, 40));
        confirmBt.setOpaque(true);
        confirmBt.setBackground(Color.decode("#fd5f92"));
        confirmBt.setBorder(BorderFactory.createLineBorder(Color.white, 3, false));
        confirmBt.setFont(new Font("arial", Font.BOLD, 18));
        confirmBt.setForeground(Color.decode("#2c2c62"));

        confirmBt.setActionCommand("start");
        confirmBt.addActionListener(gameControl);

        panel.add(Box.createRigidArea(new Dimension(300, 220)));
        panel.add(userNameLabel);
        panel.add(playerNameField);
        panel.add(confirmBt);

        return panel;
    }

    private void initGUI()
    {
        removeAll();
        revalidate();
        repaint();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addMouseListener(mousePosition);

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

    private JButton createFeatureButton(String nameButton)
    {
        Image img = getIconButton(nameButton, 40);

        JButton button = new JButton(new ImageIcon(img));
        button.setPreferredSize(new Dimension(40, 40));
        button.setActionCommand(nameButton);
        button.addActionListener(gameControl);

        if (!nameButton.equals("hint"))
        {
            button.addMouseListener(mousePosition);
        }

        return button;
    }

    //------------------------------------------------------------------------


    //----------- TIME AREA --------------------------------------------------

    private JPanel createGameDetailArea()
    {
        JPanel gameDetailArea = new JPanel();
        gameDetailArea.setLayout(new BoxLayout(gameDetailArea, BoxLayout.X_AXIS));

        gameDetailArea.add(createTimeDetailArea());
        gameDetailArea.add(modeDetailArea());

        return gameDetailArea;
    }

    private JPanel createTimeDetailArea()
    {
        JPanel timeArea = new JPanel();

        timeArea.setLayout(new BoxLayout(timeArea, BoxLayout.Y_AXIS));
        timeArea.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 1, Color.decode("#2197f1")));

        timeArea.add(createTimeTitlePanel());
        timeArea.add(createClockPanel());

        return timeArea;
    }

    private JPanel createTimeTitlePanel()
    {
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        timePanel.setMaximumSize(new Dimension(150, 50));

        timePanel.add(createTimeTitleLabel());

        return timePanel;
    }

    private JLabel createTimeTitleLabel()
    {
        JLabel timeTitleLabel = new JLabel("TIME", SwingConstants.CENTER);
        timeTitleLabel.setPreferredSize(new Dimension(150, 50));
        timeTitleLabel.setOpaque(true);
        timeTitleLabel.setBackground(Color.decode("#fd5f92"));
        timeTitleLabel.setFont(new Font("arial", Font.BOLD, 25));
        timeTitleLabel.setForeground(Color.decode("#2c2c62"));

        return timeTitleLabel;
    }

    private JPanel createClockPanel()
    {
        JPanel clockPanel = new JPanel();
        clockPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        clockPanel.setMaximumSize(new Dimension(150, 50));
        clockPanel.add(clockLabel);

        return clockPanel;
    }

    private JLabel createClockLabel()
    {
        JLabel clockLabel = timeControl.getTimeThread().getClockLabel();
        clockLabel.setPreferredSize(new Dimension(150, 50));
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        clockLabel.setOpaque(true);
        clockLabel.setBackground(Color.decode("#fd5f92"));
        clockLabel.setFont(new Font("arial", Font.BOLD, 25));
        clockLabel.setForeground(Color.decode("#2c2c62"));

        return clockLabel;
    }

    private JPanel modeDetailArea()
    {
        JPanel modeDetailArea = new JPanel();

        modeDetailArea.setLayout(new BoxLayout(modeDetailArea, BoxLayout.Y_AXIS));
        modeDetailArea.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 0, Color.decode("#2197f1")));

        modeDetailArea.add(createModeTitlePanel());
        modeDetailArea.add(createModeGamePanel());

        return modeDetailArea;
    }

    private JPanel createModeTitlePanel()
    {
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        modePanel.setMaximumSize(new Dimension(150, 50));
        modePanel.add(createModeTitleLabel());

        return modePanel;
    }

    private JPanel _createTitlePanel(String str)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setMaximumSize(new Dimension(150, 50));
        panel.add(_createlabel(str));

        return panel;
    }

    private JLabel createModeTitleLabel()
    {
        JLabel modeTitleLabel = new JLabel("MODE", SwingConstants.CENTER);
        modeTitleLabel.setPreferredSize(new Dimension(150, 50));
        modeTitleLabel.setOpaque(true);
        modeTitleLabel.setBackground(Color.decode("#fd5f92"));
        modeTitleLabel.setFont(new Font("arial", Font.BOLD, 25));
        modeTitleLabel.setForeground(Color.decode("#2c2c62"));

        return modeTitleLabel;
    }

    private JPanel createModeGamePanel()
    {
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        modePanel.setMaximumSize(new Dimension(150, 50));
        modePanel.add(modeLabel);

        return modePanel;
    }

    private JLabel _createlabel(String str)
    {
        JLabel label = new JLabel(str);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(150, 50));
        label.setOpaque(true);
        label.setBackground(Color.decode("#fd5f92"));
        label.setFont(new Font("arial", Font.BOLD, 25));
        label.setForeground(Color.decode("#2c2c62"));

        return label;
    }

    private JLabel createModeGameLabel()
    {
        JLabel modeLabel = new JLabel("None");

        modeLabel.setPreferredSize(new Dimension(150, 50));
        modeLabel.setOpaque(true);
        modeLabel.setBackground(Color.decode("#fd5f92"));
        modeLabel.setHorizontalAlignment(JLabel.CENTER);
        modeLabel.setFont(new Font("arial", Font.BOLD, 25));
        modeLabel.setForeground(Color.decode("#2c2c62"));

        return modeLabel;
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

    private JButton createControlButton(String nameButton)
    {
        Image img = getIconButton(nameButton, 60);

        JButton button = new JButton(new ImageIcon(img));
        button.setPreferredSize(new Dimension(50, 50));
        button.setActionCommand(nameButton);
        button.addActionListener(gameControl);
        button.addMouseListener(mousePosition);

        return button;
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

    private JButton createModeButton(String mode, String color)
    {
        JButton button = new JButton(mode);

        button.setPreferredSize(new Dimension(130, 50));
        button.setOpaque(true);
        button.setBackground(Color.decode(color));
        button.setBorder(BorderFactory.createLineBorder(Color.white, 3, false));
        button.setFont(new Font("arial", Font.BOLD, 18));
        button.setForeground(Color.white);

        button.setActionCommand(mode.toLowerCase());
        button.addActionListener(gameControl);
        button.addMouseListener(mousePosition);

        return button;
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
        img = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);

        return img;
    }

    private void changeMusicButtonIcon(String buttonName)
    {
        Icon icon = new ImageIcon((getIconButton(buttonName, 40)));
        musicBt.setIcon(icon);
    }

    private void start()
    {
        String playerName = playerNameField.getText();
        if (playerName.equals(""))
        {
            return;
        }

        display.getAchievementModel().setPlayerName(playerName);
        initGUI();

        timeControl.start();
        musicControl.start();
    }

    private void displayMode(String mode)
    {
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

    class GameControl extends CustomActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            switch (e.getActionCommand())
            {
                case "start" -> {
                    start();
                }

                case "easy", "normal", "hard", "challenge" -> {
                    createNewGame(e.getActionCommand());
                }

                case "resume", "pause" -> {
                    if (!display.isFlagStart())
                    {
                        System.out.println("A new game has not been started!");
                        return;
                    }

                    control(e.getActionCommand());
                }

                case "hint", "check", "introductions", "achievement" -> {
                    feature(e.getActionCommand());
                }
            }
        }

        private void createNewGame(String mode)
        {
            int choice = JOptionPane.showOptionDialog(null,
                                                      "Are you sure want to start a new game?",
                                                      "Start new game",
                                                      JOptionPane.YES_NO_OPTION,
                                                      JOptionPane.QUESTION_MESSAGE,
                                                      null, null, null);

            if (choice == JOptionPane.YES_OPTION)
            {
                if (!display.createNewGame(mode))
                {
                    JOptionPane.showMessageDialog(null,
                                                  "Bạn đã chơi hết các câu đố ở chế độ này\n" +
                                                  "Vui lòng chọn chế độ khác để tiếp tục chơi!",
                                                  "",
                                                  JOptionPane.INFORMATION_MESSAGE);

                    return;
                }

                timeControl.startOver();
                displayMode(mode);

                display.getAchievementModel().setMode(mode);
                display.resetInfo();
                display.setFlagStart(true);
                display.initGUI();
                display.switchScreen(false);
            }
        }

        private void control(String actionCommand)
        {
            switch (actionCommand)
            {
                case "resume" -> {
                    timeControl.resume();

                    display.switchScreen(false);

                    hintBt.addActionListener(gameControl);
                    checkBt.addActionListener(gameControl);
                }

                case "pause" -> {
                    timeControl.forceStop();

                    display.switchScreen(true);

                    hintBt.removeActionListener(gameControl);
                    checkBt.removeActionListener(gameControl);
                }
            }
        }

        private void feature(String actionCommand)
        {
            switch (actionCommand)
            {
                case "hint" -> {
                    if (!display.isFlagStart())
                    {
                        System.out.println("A new game has not been started!");
                        return;
                    }

                    display.hint();

                }

                case "check" -> {
                    if (!display.isFlagStart())
                    {
                        System.out.println("A new game has not been started!");
                        return;
                    }

                    display.check();
                }

                case "introductions" -> {

                }

                case "achievement" -> {
                    DisplayAchievementTable achievementTable = new DisplayAchievementTable("Achievement");
                    achievementTable.addWindowListener(changeAchievementBtStatus);
                }
            }
        }
    }

    class HotKeyConfirm extends CustomActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            start();
        }
    }

    class MousePosition extends CustomMouseListener
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            display.resetCurrPos();

            if (e.getComponent().getX() == 131 &&
                e.getComponent().getY() == 10)
            {
                if (e.getButton() == 1)
                {
                    if (musicControl.getMusicThread().isForceStop())
                    {
                        changeMusicButtonIcon("music");
                        musicControl.resume();
                    }
                    else
                    {
                        changeMusicButtonIcon("musicoff");
                        musicControl.stop();
                    }
                }
                else if (e.getButton() == 3)
                {
                    musicControl.skip();
                }
            }
        }
    }

    class ChangeAchievementBtStatus extends CustomWindowListener
    {
        @Override
        public void windowOpened(WindowEvent e)
        {
            achievBt.removeActionListener(gameControl);
        }

        @Override
        public void windowClosed(WindowEvent e)
        {
            achievBt.addActionListener(gameControl);
        }
    }

}
