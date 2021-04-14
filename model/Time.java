package model;

import controller.TimeControl;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Time extends Thread
{
    private final TimeControl timeControl;
    private final String[] flag;
    private volatile boolean forceStop = false;

    private long time;
    private final JLabel clockLabel;

    public Time(String[] flag, TimeControl timeControl)
    {
        this.timeControl = timeControl;
        clockLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        this.flag = flag;
    }

    public boolean isForceStop()
    {
        return forceStop;
    }

    public void setForceStop(boolean forceStop)
    {
        this.forceStop = forceStop;
    }

    @Override
    public synchronized void run()
    {
        while (true)
        {
            try
            {
                if (flag[0].equals("pause"))
                {
                    timeControl.hold();
                }

                clockLabel.setText(convertToTime(time));
                flag[1] = clockLabel.getText();

                time++;
                TimeUnit.MILLISECONDS.sleep(1000);

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void wake()
    {
        timeControl.wake();
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
