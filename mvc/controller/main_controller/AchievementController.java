package mvc.controller.main_controller;

import mvc.config_database.ConnectToDB;
import mvc.model.AchievementModel;

import java.sql.*;

public class AchievementController
{
    private final ConnectToDB connectToDB;

    public AchievementController(ConnectToDB connectToDB)
    {
        this.connectToDB = connectToDB;
    }

    public void insert(AchievementModel achievementModel) throws SQLException
    {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "INSERT INTO " +
                     "achievement " +
                     "(" +
                     "player_name, mode_id, num_of_hints, num_of_checks, time" +
                     ") " +
                     "VALUES " +
                     "(?, ?, ?, ?, ?)";
        try
        {
            connection = connectToDB.getConnect();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(sql);
            setParameter(statement, achievementModel.getPlayerName(), achievementModel.getModeID(),
                         achievementModel.getCountHints(), achievementModel.getCountChecks(),
                         achievementModel.getTime());

            statement.executeUpdate();
            connection.commit();
        }
        catch (SQLException exception)
        {
            if (connection != null)
            {
                connection.rollback();
            }

            throw exception;
        }
        finally
        {
            if (statement != null)
            {
                statement.close();
            }
        }
    }

    public ResultSet getData() throws SQLException
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        connection = connectToDB.getConnect();
        String sql = "SELECT " +
                     "player_name, mode_name, num_of_hints, num_of_checks, time " +
                     "FROM " +
                     "achievement a, mode m " +
                     "WHERE " +
                     "a.mode_id = m.mode_id " +
                     "ORDER BY " +
                     "time ASC";

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        result = statement.executeQuery(sql);

        return result;
    }

    private void setParameter(PreparedStatement statement, Object... parameters) throws SQLException
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
    }
}
