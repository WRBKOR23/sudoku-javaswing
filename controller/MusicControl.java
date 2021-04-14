package controller;

public class MusicControl
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
