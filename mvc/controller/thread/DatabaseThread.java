package mvc.controller.thread;

import mvc.config_database.ConnectToDB;

public class DatabaseThread extends Thread
{
    private final ConnectToDB connectToDB;

    public DatabaseThread(ConnectToDB connectToDB)
    {
        this.connectToDB = connectToDB;
    }

    @Override
    public void run()
    {
        connectToDB.connect();
    }
}
