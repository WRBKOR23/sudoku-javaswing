package component;

import javax.swing.*;
import java.util.Vector;

public class CustomTable extends JTable
{
    private final Vector<Vector<Object>> v;

    public CustomTable(Vector<Vector<Object>> v, Vector<Object> v1)
    {
        super(v, v1);
        this.v = v;
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
}
