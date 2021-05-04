package custom_event;

import java.awt.*;

import javax.swing.*;

public class GradientLabel extends JLabel
{
    // ------------------------------ FIELDS ------------------------------

    private Color start;
    private Color end;

    // --------------------------- CONSTRUCTORS ---------------------------


    public GradientLabel(String text, int horizontalAlignment, Color start, Color end)
    {
        super(text, horizontalAlignment);
        setForeground(Color.black);
        this.start = start;
        this.end   = end;
    }

    public GradientLabel(Color start, Color end)
    {
        super();
        this.start = start;
        this.end   = end;
    }

    // -------------------------- OTHER METHODS --------------------------


    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g.create();
        Paint p =
                new GradientPaint(0, 0, Color.decode("#ff33cc"),
                                  getWidth(), getHeight(), Color.decode("#0000ff"), true);
        g2d.setPaint(p);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
        super.paintComponent(g);
    }

}

