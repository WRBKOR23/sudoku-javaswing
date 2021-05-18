package gui;

import component.CustomTable;
import controller.AchievementController;
import utils.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AchievementTable extends JFrame
{
    public AchievementTable(String title) throws HeadlessException
    {
        super(title);

        JScrollPane scrollPane = new JScrollPane(createTable());

        add(createTitle(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

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

        Vector<Vector<Object>> row   = new Vector<>();
        Vector<Object>         title = new Vector<>();
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
        return fillData(row, title);
    }

    private JTable fillData(Vector<Vector<Object>> row, Vector<Object> title)
    {
        CustomTable table = new CustomTable(row, title);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        table.setDefaultRenderer(String.class, centerRenderer);
        table.setDefaultRenderer(Integer.class, centerRenderer);
        table.setFont(new Font(Constants.UIConfiguration.FONT_1, Font.PLAIN, 20));
        table.setRowHeight(30);

        JTableHeader tableHeader = table.getTableHeader();
        Font         headerFont  = new Font(Constants.UIConfiguration.FONT_1, Font.BOLD, 20);
        tableHeader.setFont(headerFont);
        tableHeader.setPreferredSize(new Dimension(10, 40));
        return table;
    }

    public static void main(String[] args)
    {
        AchievementTable d = new AchievementTable("fsdf");
    }
}
