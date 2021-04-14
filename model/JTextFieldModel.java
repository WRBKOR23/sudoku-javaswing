package model;

import javax.swing.*;
import java.util.ArrayList;

public class JTextFieldModel
{
    private final ArrayList<JTextField> textFieldList= new ArrayList<>();

    public JTextFieldModel()
    {
        setUp();
    }

    public void setUp()
    {
        for (int i = 0; i < 81; i++)
        {
            textFieldList.add(new JTextField(""));
        }
    }

    public ArrayList<JTextField> getTextFieldList()
    {
        return textFieldList;
    }
}
