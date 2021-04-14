package controller;

public class TimeControl
{
    public synchronized void hold() throws InterruptedException
    {
        wait();
    }

    public synchronized void wake()
    {
        notify();
    }

}
