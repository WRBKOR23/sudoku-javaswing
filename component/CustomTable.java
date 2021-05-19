package component;

import utils.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
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

        this.setRowHeight(30);
        this.setDefaultRenderer(String.class, centerRenderer);
        this.setDefaultRenderer(Integer.class, centerRenderer);
        this.setFont(new Font(Constants.UIConfiguration.FONT_1, Font.PLAIN, 20));

        JTableHeader tableHeader = this.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(10, 40));
        tableHeader.setFont(new Font(Constants.UIConfiguration.FONT_1, Font.BOLD, 20));
        tableHeader.setOpaque(false);
        tableHeader.setBackground(Constants.UIConfiguration.BG_COLOR_1);
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
        Component comp  = super.prepareRenderer(renderer, row, col);
        Object    value = getModel().getValueAt(row, 1);

        if (value.equals("easy"))
        {
            comp.setBackground(Color.decode("#1cde12"));
        }
        else if (value.equals("normal"))
        {
            comp.setBackground(Color.green);
        }
        else
        {
            comp.setBackground(Color.white);
        }

        return comp;
    }
}
