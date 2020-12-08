
package utilidades;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Tabla_renderer extends DefaultTableCellRenderer {
    @Override
   public Component getTableCellRendererComponent (JTable table, Object value,
                                                boolean isSelected, boolean hasFocus,
                                                int row, int column){
     JLabel jLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String estado = String.valueOf(table.getValueAt(row, 2));
                if(estado.equals("ocupado")){
                    jLabel.setBackground(Color.RED);
                    jLabel.setForeground(Color.WHITE);
                }else{
                    jLabel.setBackground(Color.WHITE);
                    jLabel.setForeground(Color.BLACK);
                }
                return jLabel;
    }
}
