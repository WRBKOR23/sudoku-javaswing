package controller;

import thread.MusicThread;

public class MusicControl
{
    private final MusicThread musicThread;
    private final ThreadControl threadControl;

    public MusicControl()
    {
        this.threadControl = new ThreadControl();
        this.musicThread   = new MusicThread(threadControl);
    }

    public void start()
    {
        this.musicThread.start();
    }

    public MusicThread getMusicThread()
    {
        return musicThread;
    }

    public void stop()
    {
        musicThread.setForceStop(true);
    }

    public void resume()
    {
        musicThread.setForceStop(false);
        musicThread.wake();
    }

    public void skip()
    {
        musicThread.setForceSkip(true);
    }
}
