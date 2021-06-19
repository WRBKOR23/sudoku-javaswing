package mvc.gui;

import mvc.config_database.ConnectToDB;
import mvc.gui.component.CustomTable;
import mvc.gui.component.Label;
import mvc.controller.main_controller.AchievementController;
import mvc.gui.custom_event.CustomWindowListener;
import mvc.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AchievementTable extends JFrame
{
    private final ConnectToDB connectToDB;
    private final JButton button;
    private final JTabbedPane tabbedPane;

    public AchievementTable(ConnectToDB connectToDB, String title, JButton button) throws HeadlessException
    {
        super(title);
        this.connectToDB = connectToDB;
        this.button = button;
        this.tabbedPane = new JTabbedPane();
    }

    public void display()
    {
        _createTable();
        _customTabPane();

        add(_createTitle(), BorderLayout.NORTH);
        add(this.tabbedPane, BorderLayout.CENTER);

        addWindowListener(new ChangeAchievementBtStatus());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(900, 500);
        setResizable(false);
        setVisible(true);
    }

    private void _customTabPane()
    {
        this.tabbedPane.setOpaque(true);
        this.tabbedPane.setBackground(Color.white);
    }

    private JPanel _createTitle()
    {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(Color.white);

        Label titleLabel = new Label("ACHIEVEMENT", 300, 70, 20,
                                     Color.white, Constants.UIConfiguration.FG_GROUND_2);

        titlePanel.add(titleLabel);

        return titlePanel;
    }

    private void _createTable()
    {
        AchievementController achievementController = new AchievementController(connectToDB);

        ResultSet resultSet = achievementController.getData();

        Vector<Vector<Object>> easy = new Vector<>();
        Vector<Vector<Object>> normal = new Vector<>();
        Vector<Vector<Object>> hard = new Vector<>();
        Vector<Vector<Object>> challenge = new Vector<>();

        int easyIndex = 1;
        int normalIndex = 1;
        int hardIndex = 1;
        int challengeIndex = 1;

        Vector<Object> tableHeader = new Vector<>();
        tableHeader.add("Ranking");
        tableHeader.add("Player Name");
        tableHeader.add("Mode");
        tableHeader.add("Hints");
        tableHeader.add("Checks");
        tableHeader.add("Time");

        try
        {
            while (resultSet.next())
            {
                Vector<Object> data = new Vector<>();
                data.add(resultSet.getString(1));
                data.add(resultSet.getString(2));
                data.add(resultSet.getInt(3));
                data.add(resultSet.getInt(4));
                data.add(resultSet.getString(5));

                if (data.get(1).equals("Easy"))
                {
                    data.add(0, easyIndex);
                    easy.add(data);
                    easyIndex++;
                }
                if (data.get(1).equals("Normal"))
                {
                    data.add(0, normalIndex);
                    normal.add(data);
                    normalIndex++;
                }
                if (data.get(1).equals("Hard"))
                {
                    data.add(0, hardIndex);
                    hard.add(data);
                    hardIndex++;
                }
                if (data.get(1).equals("Challenge"))
                {
                    data.add(0, challengeIndex);
                    challenge.add(data);
                    challengeIndex++;
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        this.tabbedPane.add("Easy", new JScrollPane(new CustomTable(easy, tableHeader)));
        this.tabbedPane.add("Normal", new JScrollPane(new CustomTable(normal, tableHeader)));
        this.tabbedPane.add("Hard", new JScrollPane(new CustomTable(hard, tableHeader)));
        this.tabbedPane.add("Challenge", new JScrollPane(new CustomTable(challenge, tableHeader)));
    }


    class ChangeAchievementBtStatus extends CustomWindowListener
    {
        @Override
        public void windowClosed(WindowEvent e)
        {
            button.setEnabled(true);
        }
    }
}
