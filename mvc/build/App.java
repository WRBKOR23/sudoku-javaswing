package mvc.build;

import mvc.controller.main_controller.GameController;
import mvc.controller.thread.music.MusicController;
import mvc.controller.thread.time.TimeController;
import mvc.gui.custom_event.CustomActionListener;
import mvc.gui.custom_event.CustomMouseListener;
import mvc.gui.custom_event.CustomWindowListener;
import mvc.gui.MenuScreen;
import mvc.gui.PuzzleScreen;
import mvc.model.AchievementModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class App extends JFrame
{
    private final MenuScreen   menuScreen;
    private final PuzzleScreen puzzleScreen;

    private GameControl                gameControl;
    private MusicControl_MouseTracking musicControl_MouseTracking;
    private ChangeAchievementBtStatus  changeAchievementBtStatus;
    private MouseTracking              mouseTracking;
    private HotKeyConfirm              hotKeyConfirm;

    private final GameController gameController;


    public App() throws HeadlessException
    {
        TimeController   timeController   = new TimeController();
        MusicController  musicController  = new MusicController();
        AchievementModel achievementModel = new AchievementModel();

        this.menuScreen   = new MenuScreen(timeController.getTimeThread().getClockLabel());
        this.puzzleScreen = new PuzzleScreen();

        this.gameController = new GameController(this.puzzleScreen, timeController,
                                                 musicController, achievementModel);

        generateEvents();
        addEventToComponents();
    }

    public void run()
    {
        initGUI();
    }

    private void generateEvents()
    {
        gameControl                = new GameControl();
        musicControl_MouseTracking = new MusicControl_MouseTracking();
        changeAchievementBtStatus  = new ChangeAchievementBtStatus();
        mouseTracking              = new MouseTracking();
        hotKeyConfirm              = new HotKeyConfirm();
    }

    private void addEventToComponents()
    {
        menuScreen.addMouseListener(mouseTracking);

        menuScreen.getPlayerNameField().addActionListener(hotKeyConfirm);
        menuScreen.getConfirmBt().setActionCommand("start");
        menuScreen.getConfirmBt().addActionListener(gameControl);

        menuScreen.getHintBt().setActionCommand("hint");
        menuScreen.getHintBt().addActionListener(gameControl);

        menuScreen.getCheckBt().setActionCommand("check");
        menuScreen.getCheckBt().addActionListener(gameControl);
        menuScreen.getCheckBt().addMouseListener(mouseTracking);

        menuScreen.getMusicBt().setActionCommand("music");
        menuScreen.getMusicBt().addActionListener(gameControl);
        menuScreen.getMusicBt().addMouseListener(musicControl_MouseTracking);

        menuScreen.getIntroBt().setActionCommand("introductions");
        menuScreen.getIntroBt().addActionListener(gameControl);
        menuScreen.getIntroBt().addMouseListener(mouseTracking);

        menuScreen.getAchievBt().setActionCommand("achievement");
        menuScreen.getAchievBt().addActionListener(gameControl);
        menuScreen.getAchievBt().addMouseListener(mouseTracking);

        menuScreen.getResumeBt().setActionCommand("resume");
        menuScreen.getResumeBt().addActionListener(gameControl);
        menuScreen.getResumeBt().addMouseListener(mouseTracking);

        menuScreen.getPauseBt().setActionCommand("pause");
        menuScreen.getPauseBt().addActionListener(gameControl);
        menuScreen.getPauseBt().addMouseListener(mouseTracking);

        menuScreen.getEasyModeBt().setActionCommand("easy");
        menuScreen.getEasyModeBt().addActionListener(gameControl);
        menuScreen.getEasyModeBt().addMouseListener(mouseTracking);

        menuScreen.getNormalModeBt().setActionCommand("normal");
        menuScreen.getNormalModeBt().addActionListener(gameControl);
        menuScreen.getNormalModeBt().addMouseListener(mouseTracking);

        menuScreen.getHardModeBt().setActionCommand("hard");
        menuScreen.getHardModeBt().addActionListener(gameControl);
        menuScreen.getHardModeBt().addMouseListener(mouseTracking);

        menuScreen.getChallengeModeBt().setActionCommand("challenge");
        menuScreen.getChallengeModeBt().addActionListener(gameControl);
        menuScreen.getChallengeModeBt().addMouseListener(mouseTracking);
    }

    private void initGUI()
    {
        setTitle("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(this.puzzleScreen, BorderLayout.CENTER);
        add(this.menuScreen, BorderLayout.EAST);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        int posX = (width-950)/2;
        int posY = (height-650)/2;

        setLocation(posX, posY);
        pack();
        setResizable(false);
        setVisible(true);
    }


    class GameControl extends CustomActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            switch (e.getActionCommand())
            {
                case "start" -> {
                    boolean flag = gameController.start(menuScreen.getPlayerNameField().getText());
                    if (flag)
                    {
                        menuScreen.initGUI();
                    }
                }

                case "easy", "normal", "hard", "challenge" -> {
                    boolean flag = gameController.createNewGame(e.getActionCommand());
                    if (flag)
                    {
                        menuScreen.createNewGame(e.getActionCommand());
                    }
                }

                case "resume" -> {
                    gameController.resume();
                }

                case "pause" -> {
                    gameController.pause();
                }

                case "hint" -> {
                    gameController.hint();
                }

                case "check" -> {
                    gameController.check();
                }

                case "introductions" -> {
                    gameController.introductions();
                }

                case "achievement" -> {
                    menuScreen.getAchievBt().setEnabled(false);
                    gameController.achievement(menuScreen.getAchievBt());
                }

            }
        }
    }

    class HotKeyConfirm extends CustomActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            boolean flag = gameController.start(menuScreen.getPlayerNameField().getText());
            if (flag)
            {
                menuScreen.initGUI();
            }
        }
    }

    class MouseTracking extends CustomMouseListener
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            gameController.resetCurrentPosition();
        }
    }

    class MusicControl_MouseTracking extends CustomMouseListener
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            gameController.resetCurrentPosition();
            if (e.getButton() == 1)
            {
                boolean flag = gameController.turnMusicOnOff();
                if (flag)
                {
                    menuScreen.changeMusicButtonIcon("music");
                }
                else
                {
                    menuScreen.changeMusicButtonIcon("musicoff");
                }
            }
            else if (e.getButton() == 3)
            {
                gameController.skipMusic();
            }
        }
    }

    class ChangeAchievementBtStatus extends CustomWindowListener
    {
        @Override
        public void windowOpened(WindowEvent e)
        {
            menuScreen.getAchievBt().removeActionListener(gameControl);
        }

        @Override
        public void windowClosed(WindowEvent e)
        {
            menuScreen.getAchievBt().addActionListener(gameControl);
        }
    }
}

