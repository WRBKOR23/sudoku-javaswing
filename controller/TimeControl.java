package controller;

import thread.TimeThread;

public class TimeControl
{
    private final TimeThread timeThread;
    private final ThreadControl threadControl;

    public TimeControl()
    {
        this.threadControl = new ThreadControl();
        this.timeThread    = new TimeThread(threadControl);
    }

    public void start()
    {
        this.timeThread.start();
    }

    public void forceStop()
    {
        timeThread.setForceStop(true);
    }

    public void resume()
    {
        timeThread.setForceStop(false);
        timeThread.wake();
    }

    public void startOver()
    {
        timeThread.setTime(0);
        timeThread.setForceStop(false);
        timeThread.wake();
    }

    public TimeThread getTimeThread()
    {
        return timeThread;
    }

    public String getTime()
    {
        return timeThread.getTime();
    }
}
