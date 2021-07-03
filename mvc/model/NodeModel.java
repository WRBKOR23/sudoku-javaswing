package mvc.model;

public class NodeModel
{
    private char val, answer;
    private boolean isImmutable;

    public NodeModel(int row, int col, char val, boolean isImmutable)
    {
        this.val         = val;
        this.isImmutable = isImmutable;
    }

    public NodeModel()
    {
        this.val         = ' ';
        this.isImmutable = false;
    }

    public boolean check()
    {
        return val == answer;
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
