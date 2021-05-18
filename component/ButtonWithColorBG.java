package component;

import utils.Constants;

import javax.swing.*;
import java.awt.*;

public class ButtonWithColorBG extends JButton
{
    public ButtonWithColorBG(String nameButton, int width, int height,
                             int fontSize, Color bgColor, Color fgColor)
    {
        super(nameButton);
        _draw(width, height, fontSize, bgColor, fgColor);
    }

    private void _draw(int width, int height, int fontSize, Color bgColor, Color fgColor)
    {
        this.setPreferredSize(new Dimension(width, height));
        this.setOpaque(true);
        this.setBackground(bgColor);
        this.setBorder(BorderFactory.createLineBorder(Constants.UIConfiguration.LINE_BORDER_2, 3, false));
        this.setFont(new Font(Constants.UIConfiguration.FONT_1, Font.BOLD, fontSize));
        this.setForeground(fgColor);
    }
}
