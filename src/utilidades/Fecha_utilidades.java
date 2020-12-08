
package utilidades;

import java.time.LocalDate;

public class Fecha_utilidades {
    
    public static int[] obtenerListaAnios(){
        LocalDate fechaActual = LocalDate.now();
        int anioActual = fechaActual.getYear();
        int[] listaAnios = new int[30];
        
        for (int i = 0; i < 30; i++) {
            listaAnios[i] = anioActual++;
        }
        
        return listaAnios;
    }
}
