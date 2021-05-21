package gui;

import component.CustomTable;
import controller.AchievementController;
import custom_event.CustomWindowListener;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AchievementTable extends JFrame
{
    private final JButton button;
    public AchievementTable(String title, JButton button) throws HeadlessException
    {
        super(title);
        this.button = button;
        JScrollPane scrollPane = new JScrollPane(createTable());

        add(createTitle(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addWindowListener(new ChangeAchievementBtStatus());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 500);
        setVisible(true);
    }

    private JPanel createTitle()
    {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel titleLabel = new JLabel("ACHIEVEMENT");
        titleLabel.setPreferredSize(new Dimension(300, 70));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font(Constants.UIConfiguration.FONT_1, Font.BOLD, 20));


        titlePanel.add(titleLabel);

        return titlePanel;
    }

    private JTable createTable()
    {
        AchievementController achievementController = new AchievementController();

        ResultSet resultSet = achievementController.getData();

        Vector<Vector<Object>> row = new Vector<>();
        Vector<Object> title = new Vector<>();
        title.add("Player Name");
        title.add("Mode");
        title.add("Hints");
        title.add("Checks");
        title.add("Time");

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

                row.add(data);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return new CustomTable(row, title);
    }

    class ChangeAchievementBtStatus extends CustomWindowListener
    {
        @Override
        public void windowOpened(WindowEvent e)
        {
            button.setEnabled(false);
        }

        @Override
        public void windowClosed(WindowEvent e)
        {
            button.setEnabled(true);
        }
    }
}
