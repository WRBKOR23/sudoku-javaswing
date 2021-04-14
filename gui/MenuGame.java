package gui;

import controller.MusicControl;
import custom_event.CustomActionListener;
import custom_event.CustomKeyListener;
import custom_event.CustomMouseListener;
import custom_event.CustomWindowListener;
import model.Time;
import controller.TimeControl;
import model.Music;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class MenuGame extends JPanel
{

    private final DisplayPuzzle display;

    // thread
    private final Time clock; // time
    private final Music music; // time

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

    // even listener
    private GameControl gameControl;
    private ChangeAchievementBtStatus changeAchievementBtStatus;
    private MousePosition mousePosition;
    private GetTextFromField getTextFromField;

    public MenuGame(DisplayPuzzle display)
    {
        this.display = display;

        TimeControl timeControl = new TimeControl();
        MusicControl musicControl = new MusicControl();

        clock = new Time(display.getFlag(), timeControl);
        clock.start();
        music = new Music(musicControl);
        music.start();

        initGUI();
    }

    private void generateEvent()
    {
        gameControl = new GameControl();
        changeAchievementBtStatus = new ChangeAchievementBtStatus();
        mousePosition = new MousePosition();
        getTextFromField = new GetTextFromField();
    }

    private void generateComponents()
    {
        hintBt = createFeatureButton("hint");
        checkBt = createFeatureButton("check");
        musicBt = createFeatureButton("music");
        introBt = createFeatureButton("introductions");
        achievBt = createFeatureButton("achievement");

        resumeBt = createControlButton("resume");
        pauseBt = createControlButton("pause");

        easyModeBt = createModeButton("Easy");
        normalModeBt = createModeButton("Normal");
        hardModeBt = createModeButton("Hard");
        challengeModeBt = createModeButton("Challenge");
    }

    public void waitScreen()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel userNameLabel = new JLabel("Nickname: ");
        userNameLabel.setFont(new Font("arial", Font.BOLD, 15));

        JTextField playerNameField = new JTextField();
        playerNameField.setPreferredSize(new Dimension(200, 30));
        playerNameField.setFont(new Font("arial", Font.BOLD, 15));

        getTextFromField.setPlayerNameField(playerNameField);
        playerNameField.addKeyListener(getTextFromField);

        JPanel playerNamePanel = new JPanel();
        playerNamePanel.add(userNameLabel);
        playerNamePanel.add(playerNameField);

        JButton confirmBt = new JButton("START");
        confirmBt.setPreferredSize(new Dimension(100, 40));
        confirmBt.setActionCommand("start");
        confirmBt.addActionListener(gameControl);

        JPanel confirmPanel = new JPanel();
        confirmPanel.setMaximumSize(new Dimension(350, 70));
        confirmPanel.add(confirmBt);

        add(playerNamePanel);
        add(confirmPanel);
    }

    private void initGUI()
    {
        removeAll();
        revalidate();
        repaint();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(Color.red);

        generateEvent();
        generateComponents();

        addMouseListener(mousePosition);

        add(createFeatureArea());
        add(timeControl());
        add(createControlArea());
        add(createModeArea());
    }

    // ------ FEATURE AREA --------------------------------------------

    private JPanel createFeatureArea()
    {
        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

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

    private JPanel timeControl()
    {
        JPanel timeArea = new JPanel();
        timeArea.setPreferredSize(new Dimension(300, 150));

        JLabel timeTitleLabel = new JLabel("TIME", SwingConstants.CENTER);
        timeTitleLabel.setPreferredSize(new Dimension(300, 70));
        timeTitleLabel.setFont(new Font("arial", Font.BOLD, 20));

        JLabel clockLabel = clock.getClockLabel();
        clockLabel.setPreferredSize(new Dimension(300, 50));
        //        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        clockLabel.setFont(new Font("arial", Font.BOLD, 40));

        timeArea.add(timeTitleLabel);
        timeArea.add(clockLabel);

        timeArea.setOpaque(true);
        timeArea.setBackground(Color.blue);

        return timeArea;
    }

    // -----------------------------------------------------------------------



    // -------- CONTROL AREA ------------------------------------------

    private JPanel createControlArea()
    {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

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

        modeArea.add(createModePanel(challengeModeBt));
        modeArea.add(createModePanel(hardModeBt));
        modeArea.add(createModePanel(normalModeBt));
        modeArea.add(createModePanel(easyModeBt));

        modeArea.setPreferredSize(new Dimension(300, 250));

        return modeArea;
    }

    private JPanel createModePanel(JButton button)
    {
        JPanel modePanel = new JPanel();
        modePanel.add(button);
        modePanel.setOpaque(true);
        modePanel.setBackground(Color.green);

        return modePanel;
    }

    private JButton createModeButton(String mode)
    {
        JButton button = new JButton(mode);
        button.setPreferredSize(new Dimension(130, 50));
        button.setFont(button.getFont().deriveFont(18.0f));

        button.setActionCommand(mode.toLowerCase(Locale.ROOT));
        button.addActionListener(gameControl);
        button.addMouseListener(mousePosition);

        return button;
    }

    //------------------------------------------------------------

    private Image getIconButton(String nameButton, int size)
    {
        String directory = "src/icon/" + nameButton + "_button.png";
        Image img = null;

        try
        {
            img = ImageIO.read(new File(directory));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        assert img != null;
        img = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);

        return img;
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

        private void start()
        {
            if (display.getAchievementModel().getPlayerName().equals(""))
            {
                return;
            }

            initGUI();
        }

        private void createNewGame(String mode)
        {
            int choice = JOptionPane.showOptionDialog(null,
                    "Bạn có muốn chơi mới không?",
                    "",
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

                clock.setTime(0);
                clock.wake();

                display.getAchievementModel().setMode(mode);
                display.resetInfo();
                display.setFlagStart(true);
                display.initGUI();
            }
        }

        private void control(String actionCommand)
        {
            switch (actionCommand)
            {
                case "resume" -> {
                    display.setFlag(actionCommand);
                    display.switchScreen(false);

                    clock.wake();

                    hintBt.addActionListener(gameControl);
                    checkBt.addActionListener(gameControl);
                }

                case "pause" -> {
                    display.setFlag(actionCommand);
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


    class GetTextFromField extends CustomKeyListener
    {
        JTextField playerNameField;

        public void setPlayerNameField(JTextField playerNameField)
        {
            this.playerNameField = playerNameField;
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            String playerName = playerNameField.getText();
            display.getAchievementModel().setPlayerName(playerName);
        }
    }

    class MousePosition extends CustomMouseListener
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            display.resetCurrPos();

            if (e.getComponent().getX() == 130 &&
                e.getComponent().getY() == 10)
            {
                if (e.getButton() == 1)
                {
                    if (music.isForceStop())
                    {
                        music.setForceStop(false);
                        music.wake();
                    }
                    else
                    {
                        music.setForceStop(true);
                    }
                }
                else if (e.getButton() == 3)
                {
                    music.setForceSkip(true);
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
