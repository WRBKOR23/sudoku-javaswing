package thread;

import component.Label;
import controller.ThreadController;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TimeThread extends Thread
{
    private final ThreadController threadController;
    private volatile boolean forceStop = true;

    private long time;
    private final Label clockLabel;

    public TimeThread(ThreadController threadController)
    {
        this.threadController = threadController;
        clockLabel            = new Label("00:00:00", 150, 50, 25,
                                          Color.decode("#fd5f92"), Color.decode("#2c2c62"));
    }

    public void setForceStop(boolean forceStop)
    {
        this.forceStop = forceStop;
    }

    public String getTime()
    {
        return convertToTime(time);
    }

    @Override
    public synchronized void run()
    {
        while (true)
        {
            try
            {
                if (forceStop)
                {
                    threadController.hold();
                }
                TimeUnit.MILLISECONDS.sleep(1000);
                clockLabel.setText(convertToTime(++time));

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void wake()
    {
        threadController.wake();
    }

    public String convertToTime(long t)
    {
        String time = "";

        long h = t / 3600;
        long m = (t % 3600) / 60;
        long s = (t % 3600) % 60;

        time += h < 10 ? "0" + h : String.valueOf(h);
        time += ":";
        time += m < 10 ? "0" + m : String.valueOf(m);
        time += ":";
        time += s < 10 ? "0" + s : String.valueOf(s);

        return time;
    }

    public void setTime(long time)
    {
        this.time = time;
    }

    public Label getClockLabel()
    {
        return clockLabel;
    }
}
