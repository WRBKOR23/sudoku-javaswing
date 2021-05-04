package custom_event;

import java.awt.*;

import javax.swing.*;

public class GradientJPanel extends JPanel
{
    // ------------------------------ FIELDS ------------------------------

    private Color start;
    private Color end;

    // --------------------------- CONSTRUCTORS ---------------------------


    public GradientJPanel()
    {
        super();
    }

    public GradientJPanel(Color start, Color end)
    {
        super();
        this.start = start;
        this.end   = end;
    }

    // -------------------------- OTHER METHODS --------------------------


    @Override
    protected void paintComponent(Graphics g)
    {
        if (g instanceof Graphics2D) {
            Paint p =
                    new GradientPaint(0, 0, Color.decode("#ff33cc"),
                            getWidth(), getHeight(), Color.decode("#0000ff"), true);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setPaint(p);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        } else {
            super.paintComponent(g);
        }
    }

}
