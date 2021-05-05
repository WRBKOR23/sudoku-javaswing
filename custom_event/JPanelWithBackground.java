package custom_event;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JPanelWithBackground extends JPanel
{

    private Image backgroundImage;

    public JPanelWithBackground(int width, int height, String direct)
    {
        super();
        try
        {
            this.backgroundImage = ImageIO.read(new File("src/icon_image/" + direct + "_background.png"));
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
