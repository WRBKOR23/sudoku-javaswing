package mvc.model;

public class AchievementModel
{
    private String playerName, modeName, time;
    private int countHints, countChecks, modeID;

    public AchievementModel()
    {
        this.playerName = "";
        this.modeName = "";
        this.countHints = 0;
        this.countChecks = 0;
        this.time = "";
    }

    public AchievementModel(String playerName, String modeName, int countHints, int countChecks, String time)
    {
        this.playerName = playerName;
        this.modeName = modeName;
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

    public String getModeName()
    {
        return modeName;
    }

    public void setModeName(String modeName)
    {
        this.modeName = modeName;
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

    public int getModeID()
    {
        return modeID;
    }

    public void setModeID(String mode)
    {
        switch (mode)
        {
            case "easy" -> this.modeID = 1;
            case "normal" -> this.modeID = 2;
            case "hard" -> this.modeID = 3;
            case "challenge" -> this.modeID = 4;
        }
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
               ", nameMode='" + modeName + '\'' +
               ", countHints='" + countHints + '\'' +
               ", countChecks='" + countChecks + '\'' +
               ", time=" + time +
               '}';
    }
}
