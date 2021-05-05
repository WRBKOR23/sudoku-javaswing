package thread;

import config.Constants;
import controller.ThreadControl;
import custom_event.GradientLabel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TimeThread extends Thread
{
    private final ThreadControl threadControl;
    private volatile boolean forceStop = true;

    private long time;
    private final JLabel clockLabel;

    public TimeThread(ThreadControl threadControl)
    {
        this.threadControl = threadControl;
        clockLabel = new JLabel("00:00:00", SwingConstants.CENTER);
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
                    threadControl.hold();
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
        threadControl.wake();
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

    public JLabel getClockLabel()
    {
        return clockLabel;
    }
}
