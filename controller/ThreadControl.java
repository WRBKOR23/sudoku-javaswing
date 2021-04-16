package controller;

public class ThreadControl
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
