package component;

import component.TextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TextFields
{
    private final ArrayList<JTextField> textFieldList = new ArrayList<>();

    public TextFields()
    {
        setUp();
    }

    public void setUp()
    {
        for (int i = 0; i < 81; i++)
        {
            textFieldList.add(new TextField(70, 70, 40, Color.white, Color.decode("#0f81d8")));
        }
    }

    public ArrayList<JTextField> getTextFieldList()
    {
        return textFieldList;
    }
}
