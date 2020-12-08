
package entidades;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Pelicula {
    
    private String nombre;
    private int numeroSala;
    private double precio;
    private LocalDate fechaYHoraFuncion;

    public Pelicula(String nombre, int numeroSala, double precio, LocalDate fechaYHoraFuncion) {
        this.nombre = nombre;
        this.numeroSala = numeroSala;
        this.precio = precio;
        this.fechaYHoraFuncion = fechaYHoraFuncion;
    }

    public Pelicula() {
        this.nombre = "";
        this.numeroSala = 1;
        this.precio = 0;
        this.fechaYHoraFuncion = LocalDate.now();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaYHoraFuncion() {
        return fechaYHoraFuncion;
    }

    public void setFechaYHoraFuncion(LocalDate fechaYHoraFuncion) {
        this.fechaYHoraFuncion = fechaYHoraFuncion;
    }
    
}
