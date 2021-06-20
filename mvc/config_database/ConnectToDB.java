package mvc.config_database;

import mvc.utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB
{
    private final String url;
    private final String timeZone;
    private final String dbName;
    private final String port;
    private final String user;
    private final String password;

    private Connection connect = null;

    public ConnectToDB()
    {
        try
        {
            Class.forName(Constants.InitConfiguration.DB_DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Can not found driver!");
        }

        this.url = Constants.InitConfiguration.DB_URL;
        this.timeZone = Constants.InitConfiguration.DB_TIME_ZONE;
        this.dbName = Constants.InitConfiguration.DB_NAME + "?";
        this.port = Constants.InitConfiguration.DB_PORT + "/";
        this.user = Constants.InitConfiguration.DB_USER;
        this.password = Constants.InitConfiguration.DB_PASS;
    }

    public void connect()
    {
        try
        {
            connect = DriverManager.getConnection(url + port + dbName + timeZone, user, password);
        }
        catch (Exception exception)
        {
            connect = null;
        }
    }

    public Connection getConnect()
    {
        return connect;
    }
}
