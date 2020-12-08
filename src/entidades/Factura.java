
package entidades;

import java.time.LocalDate;

public class Factura {
    
     private Cliente cliente;
    private Pelicula pelicula;
    private Boleto[] boletosPelicula;
    private int[] filasAsientos;
    private int[] columnasAsientos;
    private int numeroReservacion;
    private LocalDate fechaActual;
    
    public Factura() {
        this.cliente = null;
        this.pelicula = null;
        this.boletosPelicula = null;
        this.filasAsientos = new int[0];
        this.columnasAsientos = new int[0];
        this.numeroReservacion = 1000;
        this.fechaActual = LocalDate.now();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Boleto[] getBoletosPelicula() {
        return boletosPelicula;
    }

    public void setBoletosPelicula(Boleto[] boletosPelicula) {
        this.boletosPelicula = boletosPelicula;
    }

    public int[] getFilasAsientos() {
        return filasAsientos;
    }

    public void setFilaAsientos(int filaAsientos) {
        int[] arregloFilas = getFilasAsientos();
        int tamanioArreglo = arregloFilas.length;
        int[] arregloTemporal = new int[tamanioArreglo+1];
        
        for (int i = 0; i < tamanioArreglo; i++) 
            arregloTemporal[i] = arregloFilas[i]; 
  
        arregloTemporal[tamanioArreglo] = filaAsientos; 
        
        this.filasAsientos = arregloTemporal;
        
    }

    public int[] getColumnasAsientos() {
        return columnasAsientos;
    }

   
    public void setColumnaAsientos(int columnaAsientos) {
        int[] arregloColumnas = getColumnasAsientos();
        int tamanioArreglo = arregloColumnas.length;
        int[] arregloTemporal = new int[tamanioArreglo+1];
        
        for (int i = 0; i < tamanioArreglo; i++) 
            arregloTemporal[i] = arregloColumnas[i]; 
  
        arregloTemporal[tamanioArreglo] = columnaAsientos; 
        
        this.columnasAsientos = arregloTemporal;
    }
    
    public void eliminarDatos(){
        this.cliente = null;
        this.pelicula = null;
        this.boletosPelicula = null;
        this.filasAsientos = new int[0];
        this.columnasAsientos = new int[0];
    }
    
    public int getNumeroReservacion(){
        return this.numeroReservacion;
    }
    
    public void incrementarNumeroReservacion(){
        this.numeroReservacion = this.numeroReservacion+1;
    }

    public LocalDate getFechaActual() {
        return fechaActual;
    }
    
}
