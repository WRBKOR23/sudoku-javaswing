package component;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel
{
    public Label (String text, int width, int height, int fontSize,
                  Color bgColor, Color fgColor)
    {
        super(text);
        _draw(width, height, fontSize, bgColor, fgColor);
    }
    
    private void _draw(int width, int height, int fontSize, Color bgColor, Color fgColor)
    {
        this.setPreferredSize(new Dimension(width, height));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(true);
        this.setBackground(bgColor);
        this.setFont(new Font("arial", Font.BOLD, fontSize));
        this.setForeground(fgColor);
    }
}