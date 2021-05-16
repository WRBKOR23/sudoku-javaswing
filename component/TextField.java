package component;

import gui.JTextFields;

import javax.swing.*;
import java.awt.*;

public class TextField extends JTextField
{
    public TextField(int width, int height, int fontSize, Color fgColor)
    {
        super("");
        _draw(width, height, fontSize, fgColor);
    }

    private void _draw(int width, int height, int fontSize, Color fgColor)
    {
        this.setPreferredSize(new Dimension(width, height));
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.setFont(new Font("arial", Font.BOLD, fontSize));
        this.setForeground(fgColor);
    }
}
