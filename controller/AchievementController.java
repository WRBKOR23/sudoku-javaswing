package controller;

import config.ConnectToDB;
import model.AchievementModel;

import java.sql.*;

public class AchievementController
{
    private final ConnectToDB connectToDB = new ConnectToDB();

    public void insert(AchievementModel achievementModel)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        StringBuilder sql = new StringBuilder("INSERT INTO Achievement (Player_Name,");
        sql.append(" Mode, Number_Of_Hints, Number_Of_Checks, Time)");
        sql.append(" VALUES(?, ?, ?, ?, ?)");

        try
        {
            connection = connectToDB.getConnect();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(sql.toString());
            setParameter(statement, achievementModel.getPlayerName(), achievementModel.getMode(),
                    achievementModel.getCountHints(), achievementModel.getCountChecks(),
                    achievementModel.getTime());

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e)
        {
            e.printStackTrace();
            if (connection != null)
            {
                try
                {
                    connection.rollback();

                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        } finally
        {
            try
            {
                if (connection != null)
                {
                    connection.close();
                }
                if (statement != null)
                {
                    statement.close();
                }

            } catch (SQLException e2)
            {
                e2.printStackTrace();
            }
        }
    }

    public ResultSet getData()
    {

        Connection connection = null;
        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/temp?serverTimezone=UTC";
            String user = "WRBKOR23";
            String password = "hai210501";
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        String sql = "SELECT Player_Name, Mode, Number_Of_Hints, Number_Of_Checks, Time FROM achievement";
        ResultSet result = null;

        try
        {
            assert connection != null;
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(sql);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    private void setParameter(PreparedStatement statement, Object... parameters)
    {
        try
        {
            for (int i = 0; i < parameters.length; i++)
            {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long)
                {
                    statement.setLong(index, (Long) parameter);
                }
                else if (parameter instanceof String)
                {
                    statement.setString(index, (String) parameter);
                }
                else if (parameter instanceof Integer)
                {
                    statement.setInt(index, (Integer) parameter);
                }
                else if (parameter instanceof Timestamp)
                {
                    statement.setTimestamp(index, (Timestamp) parameter);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
