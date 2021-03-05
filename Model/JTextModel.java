package Model;

import javax.swing.*;

public class JTextModel
{
    private JTextField[][] textFields;

    public JTextModel()
    {
        this.textFields = new JTextField[9][9];
    }

    public JTextField[][] getTextFields()
    {
        return textFields;
    }

    public void setTextFields(JTextField[][] textFields)
    {
        this.textFields = textFields;
    }
}
