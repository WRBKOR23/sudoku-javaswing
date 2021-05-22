package mvc.controller;

import mvc.controller.thread.MusicThread;

public class MusicController
{
    private final MusicThread      musicThread;
    private final ThreadController threadController;

    public MusicController()
    {
        this.threadController = new ThreadController();
        this.musicThread      = new MusicThread(threadController);
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
