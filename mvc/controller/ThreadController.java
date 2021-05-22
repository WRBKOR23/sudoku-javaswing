package mvc.controller;

public class ThreadController
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
