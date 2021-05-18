package component;

import utils.Constants;
import javax.swing.*;

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
            textFieldList.add(new TextField(70, 70, 40,
                                            Constants.UIConfiguration.BG_COLOR_3, Constants.UIConfiguration.FG_GROUND_1));
        }
    }

    public ArrayList<JTextField> getTextFieldList()
    {
        return textFieldList;
    }
}
