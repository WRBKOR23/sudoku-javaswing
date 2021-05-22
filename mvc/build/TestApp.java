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
            e.printStackTrace();
        }

        App app = new App();
        app.run();
    }
}
