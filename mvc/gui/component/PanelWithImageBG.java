package mvc.gui.component;

import mvc.utils.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PanelWithImageBG extends JPanel
{

    private Image backgroundImage;

    public PanelWithImageBG(int width, int height, String nameBG)
    {
        super();
        try
        {
            this.backgroundImage = ImageIO.read(new File(Constants.ICON_IMAGE_FILE_LOCATION + nameBG + "_background.png"));
            this.backgroundImage = this.backgroundImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //        addComponentListener(new ComponentAdapter() {
        //            @Override
        //            public void componentResized(ComponentEvent e)
        //            {
        //                resizeBackground();
        //            }
        //        });
    }

    //    public void resizeBackground()
    //    {
    //        System.out.println(getPreferredSize());
    //        System.out.println(getWidth());
    //        this.backgroundImage = this.backgroundImage.getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH);
    //    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
