package entidades;

import utilidades.Identificador;
import java.time.LocalDate;
import java.util.Date;

public class Cliente {

  private String nombre;
  private int numeroReservacion;
  private Identificador.tipoTarjeta tipoTarjeta;
  private long numeroTarjeta;
  private LocalDate fechaVencimiento;
  private int codigoSeguridadTarjeta;
  private double totalPagado;

  public Cliente() {
    this.nombre = "default";
    this.numeroReservacion = 1000;
    this.tipoTarjeta = tipoTarjeta.VISA;
    this.numeroTarjeta = 0;
    this.fechaVencimiento = LocalDate.now();
    this.codigoSeguridadTarjeta = 743;
    this.totalPagado = 0;
  }

  public Cliente(
    String nombre,
    int numeroReservacion,
    Identificador.tipoTarjeta tipoTarjeta,
    long numeroTarjeta,
    LocalDate fechaVencimiento,
    int codigoSeguridadTarjeta,
    double totalPagado
  ) {
    this.nombre = nombre;
    this.numeroReservacion = numeroReservacion;
    this.tipoTarjeta = tipoTarjeta;
    this.numeroTarjeta = numeroTarjeta;
    this.fechaVencimiento = fechaVencimiento;
    this.codigoSeguridadTarjeta = codigoSeguridadTarjeta;
    this.totalPagado = totalPagado;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getNumeroReservacion() {
    return numeroReservacion;
  }

  public void setNumeroReservacion(int numeroReservacion) {
    this.numeroReservacion = numeroReservacion;
  }

  public Identificador.tipoTarjeta getTipoTarjeta() {
    return tipoTarjeta;
  }

  public void setTipoTarjeta(Identificador.tipoTarjeta tipoTarjeta) {
    this.tipoTarjeta = tipoTarjeta;
  }

  public long getNumeroTarjeta() {
    return numeroTarjeta;
  }

  public void setNumeroTarjeta(long numeroTarjeta) {
    this.numeroTarjeta = numeroTarjeta;
  }

  public LocalDate getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(LocalDate fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }

  public int getCodigoSeguridadTarjeta() {
    return codigoSeguridadTarjeta;
  }

  public void setCodigoSeguridadTarjeta(int codigoSeguridadTarjeta) {
    this.codigoSeguridadTarjeta = codigoSeguridadTarjeta;
  }

  public double getTotalPagado() {
    return totalPagado;
  }

  public void setTotalPagado(double totalPagado) {
    this.totalPagado = totalPagado;
  }

  private void verificarCaracterNumerico(String contrasenia) throws Exception {
    try {
      Integer.parseInt(contrasenia);
    } catch (Exception e) {
      throw new Exception("La contraseña es invalida");
    }
  }

  private void validarTamanioContrasenia(String contrasenia) throws Exception {
    int tamanioContrasenia = contrasenia.length();
    if (tamanioContrasenia > 4 || tamanioContrasenia < 4) {
      throw new Exception("La contraseña es invalida");
    }
  }

  private void validarFormatoContrasenia(String contrasenia) throws Exception {
    verificarCaracterNumerico(contrasenia);
    validarTamanioContrasenia(contrasenia);
  }

  private void verificarDuplicadoDeDigitos(String contrasenia)
    throws Exception {
    for (
      int posicionDigito = 0;
      posicionDigito < contrasenia.length();
      posicionDigito++
    ) {
      if (posicionDigito > 0) {
        char caracterAnterior = contrasenia.charAt(posicionDigito - 1);
        char caracterActual = contrasenia.charAt(posicionDigito);
        int digitoAnterior = Character.getNumericValue(caracterAnterior);
        int digitoActual = Character.getNumericValue(caracterActual);
        int digitoAnteriorDuplicado = digitoAnterior * 2;
        int digitoEsperado = digitoAnteriorDuplicado;

        if (digitoAnteriorDuplicado > 9) {
          digitoEsperado = digitoAnteriorDuplicado % 10;
        }

        if (digitoActual != digitoEsperado) {
          throw new Exception("La contraseña es invalida");
        }
      }
    }
  }

  public void iniciarSession(String contrasenia) throws Exception {
    validarFormatoContrasenia(contrasenia);
    verificarDuplicadoDeDigitos(contrasenia);
  }

  private void validarCantidadDigitosTarjeta(long numeroTarjeta)
    throws Exception {
    String numeroTarjetaString = String.valueOf(numeroTarjeta);

    if (
      numeroTarjetaString.length() < 16 || numeroTarjetaString.length() > 16
    ) {
      throw new Exception("El número de tarjeta debe de contener 16 dígitos");
    }
  }

  private void validarCodigoSeguridadTarjeta(int codigoSeguridadTarjeta)
    throws Exception {
    if (codigoSeguridadTarjeta < 100 || codigoSeguridadTarjeta > 999) {
      throw new Exception("El codigo de seguridad es invalido.");
    }
  }

  private void validarFechaVencimientoTarjeta(
    LocalDate fechaVencimientoTarjeta
  )
    throws Exception {
    LocalDate fechaActual = LocalDate.now();
    int anioVencimientoTarjeta = fechaVencimientoTarjeta.getYear();
    int mesVencimientoTarjeta = fechaVencimientoTarjeta.getMonthValue();

    if (anioVencimientoTarjeta < fechaActual.getYear()) {
      throw new Exception("El año de vencimiento de la tarjeta es invalido");
    }

    if (
      mesVencimientoTarjeta < fechaActual.getMonthValue() &&
      anioVencimientoTarjeta == fechaActual.getYear()
    ) {
      throw new Exception("El mes de vencimiento de la tarjeta es invalido.");
    }
  }

  private void validarTajetaVisa(long numeroTarjeta) throws Exception {
    String numeroTarjetaString = Long.toString(numeroTarjeta);
    char delimitadorVisa = '4';

    if (numeroTarjetaString.charAt(0) != delimitadorVisa) {
      throw new Exception(
        "El número de tarjeta ingresado no pertenece a una visa."
      );
    }
  }

  private void validarTarjetaMastercard(long numeroTarjeta) throws Exception {
    String numeroTarjetaString = Long.toString(numeroTarjeta);
    int delimitadorInicialMastercard = 51;
    int delimitadorFinalMastercard = 55;
    int primerosDosCaracteresMastercard = Integer.parseInt(
      numeroTarjetaString.substring(0, 2)
    );

    if (
      primerosDosCaracteresMastercard < delimitadorInicialMastercard ||
      primerosDosCaracteresMastercard > delimitadorFinalMastercard
    ) {
      throw new Exception(
        "El número de tarjeta ingresado no pertenece a una mastercard"
      );
    }
  }

  private void validarRequerimientosCompañia(
    Identificador.tipoTarjeta tipoTarjeta,
    long numeroTarjeta
  )
    throws Exception {
    if (tipoTarjeta == Identificador.tipoTarjeta.VISA) {
      validarTajetaVisa(numeroTarjeta);
    } else {
      validarTarjetaMastercard(numeroTarjeta);
    }
  }

  private void validarAlgoritmoLuhn(Long numeroTarjeta) throws Exception {
    String numeroTarjetaString = String.valueOf(numeroTarjeta);
    int sumaDigitos = 0;
    boolean duplicarDigito = false;

    for (
      int posicionDigitoTarjeta = numeroTarjetaString.length() - 1;
      posicionDigitoTarjeta >= 0;
      posicionDigitoTarjeta--
    ) {
      int digitoTarjeta = Integer.parseInt(
        numeroTarjetaString.substring(
          posicionDigitoTarjeta,
          posicionDigitoTarjeta + 1
        )
      );
      if (duplicarDigito) {
        digitoTarjeta *= 2;
        if (digitoTarjeta > 9) {
          digitoTarjeta = (digitoTarjeta % 10) + 1;
        }
      }
      sumaDigitos += digitoTarjeta;
      duplicarDigito = !duplicarDigito;
    }

    if (sumaDigitos % 10 != 0) {
      throw new Exception("El número de tarjeta no es valido");
    }
  }

  private void validarNumeroTarjeta(
    Identificador.tipoTarjeta tipoTarjeta,
    long numeroTarjeta
  )
    throws Exception {
    validarRequerimientosCompañia(tipoTarjeta, numeroTarjeta);
    validarAlgoritmoLuhn(numeroTarjeta);
  }

  public void validarDatosTarjeta() throws Exception {
    validarCantidadDigitosTarjeta(numeroTarjeta);
    validarCodigoSeguridadTarjeta(codigoSeguridadTarjeta);
    validarFechaVencimientoTarjeta(fechaVencimiento);
    validarNumeroTarjeta(tipoTarjeta, numeroTarjeta);
  }
}
