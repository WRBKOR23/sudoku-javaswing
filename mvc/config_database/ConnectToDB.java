package mvc.config_database;

import mvc.utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB
{
    private Connection connect = null;

    public ConnectToDB()
    {
        try
        {
            Class.forName(Constants.InitConfiguration.DB_DRIVER);
            String url = Constants.InitConfiguration.DB_URL + Constants.InitConfiguration.DB_NAME;
            url += "?" + Constants.InitConfiguration.DB_TIME_ZONE;

            String user     = Constants.InitConfiguration.DB_USER;
            String password = Constants.InitConfiguration.DB_PASS;
            connect = DriverManager.getConnection(url+"da", user, password);
        }
        catch (ClassNotFoundException | SQLException ignored)
        {

        }
    }

    public Connection getConnect()
    {
        return connect;
    }
}
