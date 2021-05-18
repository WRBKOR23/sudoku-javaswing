package controller;

import javax.swing.*;

public class MenuController
{
    private final TimeController  timeController;
    private final MusicController musicController;

    public MenuController(TimeController timeController, MusicController musicController)
    {
        this.timeController  = timeController;
        this.musicController = musicController;
    }

    public void start()
    {
        timeController.start();
        musicController.start();
    }

    public int createNewGame()
    {
        int choice = JOptionPane.showOptionDialog(null,
                                                  "Are you sure want to start a new game?",
                                                  "Start new game",
                                                  JOptionPane.YES_NO_OPTION,
                                                  JOptionPane.QUESTION_MESSAGE,
                                                  null, null, null);

        return choice;
    }

    public void createNewGameFailed()
    {
        JOptionPane.showMessageDialog(null,
                                      "Bạn đã chơi hết các câu đố ở chế độ này\n" +
                                      "Vui lòng chọn chế độ khác để tiếp tục chơi!",
                                      "",
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    public void createNewGameSucceed()
    {
        timeController.startOver();
    }

    public void resume()
    {
        timeController.resume();
    }

    public void pause()
    {
        timeController.forceStop();
    }

    public boolean turnMusicOnOff()
    {
        if (musicController.getMusicThread().isForceStop())
        {
            musicController.resume();
            return true;
        }
        else
        {
            musicController.stop();
            return false;
        }
    }

    public void skipMusic()
    {
        musicController.skip();
    }

    public void introduction()
    {

    }

    public void achievement()
    {

    }
}
