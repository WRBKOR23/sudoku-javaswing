package model;

import controller.MusicControl;
import jaco.mp3.player.MP3Player;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Music extends Thread
{
    private final MusicControl musicControl;
    private volatile boolean forceStop = false;
    private volatile boolean forceSkip = false;

    public Music(MusicControl musicControl)
    {
        this.musicControl = musicControl;
    }

    public boolean isForceStop()
    {
        return forceStop;
    }

    public void setForceStop(boolean forceStop)
    {
        this.forceStop = forceStop;
    }

    public boolean isForceSkip()
    {
        return forceSkip;
    }

    public void setForceSkip(boolean forceSkip)
    {
        this.forceSkip = forceSkip;
    }

    @Override
    public synchronized void run()
    {
        MP3Player mp3 = new MP3Player();
        for (int i = 1; i < 13; i++)
        {
            mp3.addToPlayList(new File("D:/Sudoku/src/music/"+i+".mp3"));
        }
        mp3.setShuffle(true);
        mp3.play();

        while (true)
        {
            try
            {
                if (forceStop)
                {
                    mp3.pause();
                    musicControl.hold();
                }

                if ((mp3.isStopped() || mp3.isPaused()) &&
                        !forceStop)
                {
                    mp3.play();
                }

                if (forceSkip)
                {
                    mp3.skipForward();
                    forceSkip = false;
                }

                TimeUnit.MILLISECONDS.sleep(500);

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void wake()
    {
        musicControl.wake();
    }
}
