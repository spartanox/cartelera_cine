
package entidades;

import utilidades.Identificador;

public class Boleto {
    
     private String tipoPersona;
    private int cantidadBoletos;

    public Boleto(String tipoPersona, int cantidadBoletos) {
        this.tipoPersona = tipoPersona;
        this.cantidadBoletos = cantidadBoletos;
    }

    public Boleto() {
        this.tipoPersona = "adulto regular";
        this.cantidadBoletos = 0;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public void setCantidadBoletos(int cantidadBoletos) {
        this.cantidadBoletos = cantidadBoletos;
    }
    
    
    
}
