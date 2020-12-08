
package utilidades;

import entidades.Cliente;
import entidades.Pelicula;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Tabla_utilidades {
    public static void centrarElementosTabla(JTable tabla){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tabla.setDefaultRenderer(String.class, centerRenderer);
    }
    
    public static void limpiarDatosTabla(JTable tabla){
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
    }
    
    
    public static void iniciarRendererTabla(JTable tabla){
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(new Tabla_renderer());
        }
    }
    
    public static void agregarDatosALaTabla(JTable tabla, Cliente[][] datosSala){
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        
        
        for(int i = 0; i< datosSala.length; i++){
            for(int j = 0; j<datosSala[i].length; j++){
                  
                    model.addRow(new Object[]{(char) ('A' + i ), (j+1), datosSala[i][j] == null ? "disponible" : "ocupado"});
                }
            }
        
    }
    
    public static void agregarDatosALaTabla(JTable tabla, Pelicula[] peliculas){
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        
        
            for(Pelicula pelicula : peliculas){
                
                if(pelicula != null){
                    model.addRow(new Object[]{pelicula.getNombre(), String.valueOf(pelicula.getNumeroSala()+1), String.valueOf(pelicula.getFechaYHoraFuncion().getMonthValue()), String.valueOf(pelicula.getFechaYHoraFuncion().getYear())});
                }
                
                 
            }
            
        
    }
    
    public static Object obtenerDatoTabla(int fila, int columna, DefaultTableModel modeloTabla){
      
        Object dato;
        
        dato = modeloTabla
        .getValueAt(fila, columna);
        
        return dato;
    }
}
