package mvc.gui.component;

import mvc.utils.Constants;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Vector;

public class CustomTable extends JTable
{
    private final Vector<Vector<Object>> v;

    public CustomTable(Vector<Vector<Object>> v, Vector<Object> v1)
    {
        super(v, v1);
        this.v = v;
        _draw();
    }

    private void _draw()
    {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setRowHeight(40);
        this.setShowGrid(false);
        this.setDefaultRenderer(String.class, centerRenderer);
        this.setDefaultRenderer(Integer.class, centerRenderer);
        this.setFont(new Font(Constants.UIConfiguration.FONT_1, Font.PLAIN, 20));

        _customTableHeader();
    }

    private void _customTableHeader()
    {
        JTableHeader tableHeader = this.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(10, 50));
        tableHeader.setBackground(Constants.UIConfiguration.BG_COLOR_1);
        tableHeader.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4,
                                                              Constants.UIConfiguration.LINE_BORDER_2));
        tableHeader.setFont(new Font(Constants.UIConfiguration.FONT_1, Font.BOLD, 20));
        tableHeader.setForeground(Constants.UIConfiguration.FG_GROUND_2);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return this.v.get(0).get(columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int col)
    {
        DefaultTableCellRenderer comp = new DefaultTableCellRenderer();
        Object value = getModel().getValueAt(row, 2);

        super.prepareRenderer(comp, row, col);

        comp.setPreferredSize(new Dimension(100, 70));
        comp.setHorizontalAlignment(JLabel.CENTER);
        comp.setOpaque(true);
        comp.setForeground(Constants.UIConfiguration.FG_GROUND_3);
        comp.setPreferredSize(new Dimension(10, 60));
        comp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
                                                       Constants.UIConfiguration.LINE_BORDER_2));
        comp.setFont(new Font(Constants.UIConfiguration.FONT_1, Font.BOLD, 20));
        if (value.equals("easy"))
        {
            comp.setBackground(Constants.UIConfiguration.EASY_BUTTON_BG);
        }
        else if (value.equals("normal"))
        {
            comp.setBackground(Constants.UIConfiguration.NORMAL_BUTTON_BG);
        }
        else if (value.equals("hard"))
        {
            comp.setBackground(Constants.UIConfiguration.HARD_BUTTON_BG);
        }
        else
        {
            comp.setBackground(Constants.UIConfiguration.CHALLENGE_BUTTON_BG);
        }

        return comp;
    }
}
