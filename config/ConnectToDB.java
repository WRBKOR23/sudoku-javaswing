package config;

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/temp?serverTimezone=UTC";
            String user = "WRBKOR23";
            String password = "hai210501";
            connect = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Connection fail");
            e.printStackTrace();
        }
    }

    public Connection getConnect()
    {
        return connect;
    }
}
