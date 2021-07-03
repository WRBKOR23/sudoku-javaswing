package mvc.controller.main_controller;

import mvc.config_database.ConnectToDB;
import mvc.controller.thread.DatabaseThread;
import mvc.controller.thread.music.MusicController;
import mvc.controller.thread.time.TimeController;
import mvc.gui.AchievementTable;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MenuController
{
    private final ConnectToDB connectToDB;
    private final TimeController timeController;
    private final MusicController musicController;

    public MenuController(ConnectToDB connectToDB, TimeController timeController, MusicController musicController)
    {
        this.connectToDB = connectToDB;
        this.timeController = timeController;
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
                                      "Your have played all the puzzles in this mode!\n" +
                                      "Please choose others mode to continue!",
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
        timeController.pause();
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
        String[] categories = {
                "The rules of Sudoku are very simple. In a typical 9x9 Sudoku grid, ",
                "there are nine rows, nine columns and nine 3x3 boxes. The objective ",
                "is to fill up the grid with the digits from 1 to 9 so that each digit of 1 ",
                "through 9 appears only once in each row, each column and each 3x3 box.", "   ",
                "Made by: Pham Tien Hai and Pham Viet Hoang Minh"};
        JList list = new JList(categories);
        list.setFont(new Font("arial", Font.BOLD, 16));
        JScrollPane scrollpane = new JScrollPane(list);
        scrollpane.setPreferredSize(new Dimension(580, 130));
        JOptionPane.showMessageDialog(null, scrollpane, "Introduction", JOptionPane.PLAIN_MESSAGE);
    }

    public void achievement(JButton button)
    {
        if (connectToDB.getConnect() == null)
        {
            button.setEnabled(true);

            DatabaseThread databaseThread = new DatabaseThread(connectToDB);
            databaseThread.start();

            _showError();
            return;
        }

        try
        {
            AchievementTable achievementTable = new AchievementTable(connectToDB, "Achievement", button);
            achievementTable.display();
        }
        catch (Exception exception)
        {
            button.setEnabled(true);

            DatabaseThread databaseThread = new DatabaseThread(connectToDB);
            databaseThread.start();

            _showError();
        }
    }

    private void _showError()
    {
        String message = "Can not connect to server right now.\n"
                         + "Please, try again after 5-10s";
        JOptionPane.showMessageDialog(new JFrame(), message, "Server Error!",
                                      JOptionPane.ERROR_MESSAGE);
    }
}
