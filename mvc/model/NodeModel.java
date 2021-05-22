package mvc.model;

public class NodeModel
{
    private int row, col;
    private char val, answer;
    private boolean isImmutable;

    public NodeModel(int row, int col, char val, boolean isImmutable)
    {
        this.row         = row;
        this.col         = col;
        this.val         = val;
        this.isImmutable = isImmutable;
    }

    public NodeModel()
    {
        this.row         = -1;
        this.col         = -1;
        this.val         = ' ';
        this.isImmutable = false;
    }

    public boolean check()
    {
        return val == answer;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    public char getVal()
    {
        return val;
    }

    public void setVal(char val)
    {
        this.val = val;
    }

    public char getAnswer()
    {
        return answer;
    }

    public void setAnswer(char answer)
    {
        this.answer = answer;
    }

    public boolean isImmutable()
    {
        return isImmutable;
    }

    public void setImmutable(boolean immutable)
    {
        isImmutable = immutable;
    }
}
