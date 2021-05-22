package mvc.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationFileUtils
{
    public static void readInitConfigFile() throws IOException
    {
        FileReader     fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader(Constants.INIT_FILE);
            br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null)
            {
                if (line.startsWith(Constants.DB_DRIVER))
                {
                    String[] data = line.split("=");
                    if (data.length == 2)
                    {
                        Constants.InitConfiguration.DB_DRIVER = data[1];
                    }
                    else
                    {
                        Constants.InitConfiguration.DB_DRIVER = "com.mysql.cj.jdbc.Driver";
                    }
                }
                if (line.startsWith(Constants.DB_URL))
                {
                    String[] data = line.split("=");
                    if (data.length == 2)
                    {
                        Constants.InitConfiguration.DB_URL = data[1];
                    }
                    else
                    {
                        Constants.InitConfiguration.DB_URL = "jdbc:mysql://localhost:3306/";
                    }
                }
                if (line.startsWith(Constants.DB_NAME))
                {
                    String[] data = line.split("=");
                    if (data.length == 2)
                    {
                        Constants.InitConfiguration.DB_NAME = data[1];
                    }
                    else
                    {
                        Constants.InitConfiguration.DB_NAME = "temp";
                    }
                }
                if (line.startsWith(Constants.DB_TIME_ZONE))
                {
                    String[] data = line.split("=");
                    if (data.length == 2)
                    {
                        Constants.InitConfiguration.DB_TIME_ZONE = data[1];
                    }
                    else
                    {
                        Constants.InitConfiguration.DB_TIME_ZONE = "serverTimezone=UTC";
                    }
                }
                if (line.startsWith(Constants.DB_USER))
                {
                    String[] data = line.split("=");
                    if (data.length == 2)
                    {
                        Constants.InitConfiguration.DB_USER = data[1];
                    }
                    else
                    {
                        Constants.InitConfiguration.DB_USER = "root";
                    }
                }
                if (line.startsWith(Constants.DB_PASS))
                {
                    String[] data = line.split("=");
                    if (data.length == 2)
                    {
                        Constants.InitConfiguration.DB_PASS = data[1];
                    }
                    else
                    {
                        Constants.InitConfiguration.DB_PASS = "";
                    }
                }
            }
        }
        finally
        {
            if (br != null)
            {
                br.close();
            }
            if (fr != null)
            {
                fr.close();
            }
        }
    }
}
