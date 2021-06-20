package mvc.build;

import mvc.utils.ConfigurationFileUtils;

import java.io.IOException;

public class TestApp
{

    public static void main(String[] args)
    {
        try
        {
            ConfigurationFileUtils.readInitConfigFile();
        }
        catch (IOException e)
        {
            System.out.println("Can not read config file!!");
            System.exit(-1);
        }

        App app = new App();
        app.run();
    }
}
