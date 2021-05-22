package mvc.gui.component;

import mvc.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class TextField extends JTextField
{
    public TextField(int width, int height, int fontSize, Color bgColor, Color fgColor)
    {
        super("");
        _draw(width, height, fontSize, bgColor, fgColor);
    }

    private void _draw(int width, int height, int fontSize, Color bgColor, Color fgColor)
    {
        this.setPreferredSize(new Dimension(width, height));
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setOpaque(true);
        this.setBackground(bgColor);
        this.setFont(new Font(Constants.UIConfiguration.FONT_1, Font.BOLD, fontSize));
        this.setForeground(fgColor);
    }
}
