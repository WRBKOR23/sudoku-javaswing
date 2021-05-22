package mvc.controller.thread.music;

import mvc.controller.thread.thread_controller.ThreadController;

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
