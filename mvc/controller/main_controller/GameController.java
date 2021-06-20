package mvc.controller.main_controller;

import mvc.config_database.ConnectToDB;
import mvc.controller.thread.music.MusicController;
import mvc.controller.thread.time.TimeController;
import mvc.gui.PuzzleScreen;
import mvc.model.AchievementModel;

import javax.swing.*;

public class GameController
{
    private final MenuController menuController;
    private final NodeController nodeController;

    public GameController(PuzzleScreen puzzleScreen, TimeController timeController, ConnectToDB connectToDB,
                          MusicController musicController, AchievementModel achievementModel)
    {
        this.menuController = new MenuController(connectToDB, timeController, musicController);
        this.nodeController = new NodeController(connectToDB, puzzleScreen, timeController, achievementModel);
    }

    public boolean start(String playerName)
    {
        if (playerName.equals(""))
        {
            return false;
        }

        menuController.start();
        nodeController.start(playerName);

        return true;
    }

    public boolean createNewGame(String mode)
    {
        int choice = menuController.createNewGame();
        if (choice == JOptionPane.NO_OPTION)
        {
            return false;
        }
        boolean succeed = nodeController.createNewGame(mode);
        if (succeed)
        {
            menuController.createNewGameSucceed();
            return true;
        }
        else
        {
            menuController.createNewGameFailed();
            return false;
        }
    }

    public void resume()
    {
        if (!nodeController.isStart())
        {
            return;
        }
        menuController.resume();
        nodeController.resume();
    }

    public void pause()
    {
        if (!nodeController.isStart())
        {
            return;
        }
        menuController.pause();
        nodeController.pause();
    }

    public void hint()
    {
        nodeController.hint();
    }

    public void check()
    {
        nodeController.check();
    }

    public boolean turnMusicOnOff()
    {
        return menuController.turnMusicOnOff();
    }

    public void skipMusic()
    {
        menuController.skipMusic();
    }

    public void introductions()
    {
        menuController.introduction();
    }

    public void achievement(JButton button)
    {
        menuController.achievement(button);
    }

    public void resetCurrentPosition()
    {
        nodeController.resetCurrentPosition();
    }

}
