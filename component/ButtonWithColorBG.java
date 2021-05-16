package component;

import javax.swing.*;
import java.awt.*;

public class ButtonWithColorBG extends JButton
{
    public ButtonWithColorBG(String nameButton, int width, int height, int fontSize, Color color)
    {
        super(nameButton);
        _draw(width, height, fontSize, color);
    }

    private void _draw(int width, int height, int fontSize, Color color)
    {
        this.setPreferredSize(new Dimension(width, height));
        this.setOpaque(true);
        this.setBackground(color);
        this.setBorder(BorderFactory.createLineBorder(Color.white, 3, false));
        this.setFont(new Font("arial", Font.BOLD, fontSize));
        this.setForeground(Color.white);
    }
}
