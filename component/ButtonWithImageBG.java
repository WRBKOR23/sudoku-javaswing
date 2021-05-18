package component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ButtonWithImageBG extends JButton
{
    public ButtonWithImageBG(String nameButton, boolean border, int width, int height)
    {
        super();
        _draw(nameButton, border, width, height);
    }

    private void _draw(String nameButton, boolean border, int width, int height)
    {
        this.setPreferredSize(new Dimension(width, height));
        if (border)
        {
            this.setBorder(BorderFactory.createLineBorder(Color.white, 3, false));
        }
        setIconImage(nameButton, width, height);
    }

    public void setIconImage(String nameIcon, int width, int height)
    {
        ImageIcon imageIcon = new ImageIcon(_getIconButton(nameIcon, width, height));
        Icon      icon      = imageIcon;
        setIcon(icon);
    }

    private Image _getIconButton(String nameIcon, int width, int height)
    {
        String directory = "src/icon_image/" + nameIcon + "_button.png";
        Image  img       = null;

        try
        {
            img = ImageIO.read(new File(directory));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        assert img != null;
        img = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

        return img;
    }
}
