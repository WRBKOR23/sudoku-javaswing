package model;

public class AchievementModel
{
    private String playerName, mode, time;
    private int countHints, countChecks;

    public AchievementModel()
    {
        this.playerName = "";
        this.mode = "";
        this.countHints = 0;
        this.countChecks = 0;
        this.time = "";
    }

    public AchievementModel(String playerName, String mode, int countHints, int countChecks, String time)
    {
        this.playerName = playerName;
        this.mode = mode;
        this.countHints = countHints;
        this.countChecks = countChecks;
        this.time = time;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public String getMode()
    {
        return mode;
    }

    public void setMode(String mode)
    {
        this.mode = mode;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public int getCountHints()
    {
        return countHints;
    }

    public void setCountHints(int countHints)
    {
        this.countHints = countHints;
    }

    public int getCountChecks()
    {
        return countChecks;
    }

    public void setCountChecks(int countChecks)
    {
        this.countChecks = countChecks;
    }

    public void increaseCountHints(int num)
    {
        countHints += num;
    }

    public void increaseCountChecks(int num)
    {
        countChecks += num;
    }

    @Override
    public String toString()
    {
        return "AchievementModel{" +
                "playerName='" + playerName + '\'' +
                ", nameMode='" + mode + '\'' +
                ", countHints='" + countHints + '\'' +
                ", countChecks='" + countChecks + '\'' +
                ", time=" + time +
                '}';
    }
}
