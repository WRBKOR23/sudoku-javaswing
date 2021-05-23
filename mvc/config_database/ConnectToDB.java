package mvc.config_database;

import mvc.utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB
{
    private Connection connect = null;

    public ConnectToDB() throws SQLException
    {
        try
        {
            Class.forName(Constants.InitConfiguration.DB_DRIVER);
            String url = Constants.InitConfiguration.DB_URL;
            String timeZone = Constants.InitConfiguration.DB_TIME_ZONE;
            String dbName = Constants.InitConfiguration.DB_NAME + "?";
            String port = Constants.InitConfiguration.DB_PORT + "/";
            String user = Constants.InitConfiguration.DB_USER;
            String password = Constants.InitConfiguration.DB_PASS;

            connect = DriverManager.getConnection(url + port + dbName + timeZone, user, password);
        }
        catch (ClassNotFoundException ignored)
        {
        }
    }

    public Connection getConnect()
    {
        return connect;
    }
}
