package entidades;

public class Cine {

  private Pelicula[] peliculas;
  private Cliente[][][] salas;
  private double precioBoletoAdulto;
  private double precioBoletoAdultoMayor;
  private double precioBoletoNinio;
  private int numeroReservacion;

  public Cine() {
    this.peliculas = new Pelicula[3];
    this.salas = new Cliente[3][10][8];
    this.precioBoletoAdulto = 2800;
    this.precioBoletoAdultoMayor = 2300;
    this.precioBoletoNinio = 2000;
    this.numeroReservacion = 1000;
  }

  public Pelicula[] getPeliculas() {
    return peliculas;
  }

  public Pelicula getPelicula(String nombrePelicula) {
    Pelicula peliculaBuscada = null;
    for (Pelicula peliculaDisponible : peliculas) {
      if (peliculaDisponible != null) {
        if (peliculaDisponible.getNombre() == nombrePelicula) {
          peliculaBuscada = peliculaDisponible;
          break;
        }
      }
    }

    return peliculaBuscada;
  }

  public void setPelicula(Pelicula pelicula) {
    int numeroSalaNuevaPelicula = pelicula.getNumeroSala();
    this.peliculas[numeroSalaNuevaPelicula] = pelicula;
  }

  public void eliminarPelicula(String nombrePeliculaAEliminar) {
    Pelicula[] peliculasActualizadas = new Pelicula[peliculas.length];
    int posicionArreglo = 0;

    for (Pelicula pelicula : peliculas) {
      if (pelicula != null) {
        if (pelicula.getNombre() != nombrePeliculaAEliminar) {
          peliculasActualizadas[posicionArreglo] = pelicula;
        }
      }

      posicionArreglo++;
    }

    this.peliculas = peliculasActualizadas;
  }

  public Cliente[][] getSala(int numeroSala) {
    return salas[numeroSala];
  }

  public Cliente[][][] getSalas() {
    return salas;
  }

  public void setSala(Cliente[][] sala, int numeroSala) {
    this.salas[numeroSala] = sala;
  }

  public double getPrecioBoletoAdulto() {
    return precioBoletoAdulto;
  }

  public double getPrecioBoletoAdultoMayor() {
    return precioBoletoAdultoMayor;
  }

  public double getPrecioBoletoNinio() {
    return precioBoletoNinio;
  }

  public int getNumeroReservacion() {
    return numeroReservacion;
  }

  public void incrementarNumeroReservacion() {
    this.numeroReservacion = this.numeroReservacion + 1;
  }

  private Cliente[][] actualizarSalaConDatosDelCliente(
    Cliente nuevoCliente,
    int numeroSala
  ) {
    Cliente cliente;
    Cliente[][] salaActualizada = salas[numeroSala];

    for (int i = 0; i < salaActualizada.length; i++) {
      for (int j = 0; j < salaActualizada[i].length; j++) {
        cliente = salaActualizada[i][j];

        if (cliente != null) {
          if (cliente.getNombre().equals("default")) {
            salaActualizada[i][j] = nuevoCliente;
          }
        }
      }
    }

    return salaActualizada;
  }

  public Cliente[][] confirmarCompra(Cliente cliente, int numeroSala) {
    Cliente[][] nuevaSala = actualizarSalaConDatosDelCliente(
      cliente,
      numeroSala
    );

    return nuevaSala;
  }

  public void verificarExistenciaPelicula(Pelicula peliculaAInsertar)
    throws Exception {
    for (Pelicula pelicula : peliculas) {
      if (pelicula != null) {
        if (pelicula.getNombre().equals(peliculaAInsertar.getNombre())) {
          throw new Exception(
            "La película a registrar ya se encuentra registrada."
          );
        }
      }
    }
  }

  private Cliente[][] eliminarCamposDeClientesPorDefectoEnSala(int numeroSala) {
    Cliente[][] salaActualizada = salas[numeroSala];
    Cliente cliente;

    for (int i = 0; i < salaActualizada.length; i++) {
      for (int j = 0; j < salaActualizada[i].length; j++) {
        cliente = salaActualizada[i][j];

        if (cliente != null) {
          if (cliente.getNombre().equals("default")) {
            salaActualizada[i][j] = null;
          }
        }
      }
    }

    return salaActualizada;
  }

  public Cliente[][] rechazarCompra(int numeroSala) {
    Cliente[][] salaActualizada = eliminarCamposDeClientesPorDefectoEnSala(
      numeroSala
    );

    return salaActualizada;
  }

  public int[] obtenerReporteAsientosReservados() {
    int totalAsientosReservados = 0;
    int[] asientosReservadosPorSala = new int[salas.length];
    int numeroSala = 0;

    for (Cliente[][] sala : salas) {
      totalAsientosReservados = obtenerAsientosReservadosPorSala(sala);

      asientosReservadosPorSala[numeroSala] = totalAsientosReservados;
      numeroSala++;
    }

    return asientosReservadosPorSala;
  }

  private boolean validarExistenciaCliente(
    Cliente[] listaClientes,
    Cliente clienteEncontrado
  ) {
    for (Cliente clienteExistente : listaClientes) {
      if (clienteExistente != null) {
        if (
          clienteExistente.getNumeroReservacion() ==
          clienteEncontrado.getNumeroReservacion()
        ) {
          return true;
        }
      }
    }

    return false;
  }

  private double obtenerIngresoPorSala(Cliente[][] sala) {
    double montoIngresos = 0;
    int totalAsientos = sala.length * sala[0].length;
    Cliente[] clientesNoDuplicados = new Cliente[totalAsientos];
    int posicionArregloClientesNoDuplicados = 0;
    boolean clienteDuplicado;

    for (Cliente[] fila : sala) {
      for (Cliente clienteEncontrado : fila) {
        if (clienteEncontrado != null) {
          clienteDuplicado =
            validarExistenciaCliente(clientesNoDuplicados, clienteEncontrado);

          if (!clienteDuplicado) {
            clientesNoDuplicados[posicionArregloClientesNoDuplicados] =
              clienteEncontrado;
            posicionArregloClientesNoDuplicados++;
          }
        }
      }
    }

    for (Cliente clienteNoDuplicado : clientesNoDuplicados) {
      if (clienteNoDuplicado != null) {
        montoIngresos += clienteNoDuplicado.getTotalPagado();
      }
    }

    return montoIngresos;
  }

  private double[] obtenerIngresosDeSalas(Cliente[][][] salas) {
    double[] ingresosPorSala = new double[salas.length];
    double montoIngresos = 0;
    int numeroSala = 0;

    for (Cliente[][] sala : salas) {
      montoIngresos = obtenerIngresoPorSala(sala);

      ingresosPorSala[numeroSala] = montoIngresos;
      numeroSala++;
    }

    return ingresosPorSala;
  }

  public double[] obtenerReporteIngresoPorSala() {
    double[] ingresosPorSala = new double[salas.length];

    ingresosPorSala = obtenerIngresosDeSalas(salas);

    return ingresosPorSala;
  }

  public int obtenerAsientosDisponibles(Cliente[][] sala) {
    int cantidadAsientosReservados = obtenerAsientosReservadosPorSala(sala);
    int cantidadAsientosPorSala = sala.length * sala[0].length;
    int cantidadAsientosDisponibles =
      cantidadAsientosPorSala - cantidadAsientosReservados;

    return cantidadAsientosDisponibles;
  }

  public void hayAsientosDisponibles(
    int numeroSala,
    int cantidadAsientosSolicitados
  )
    throws Exception {
    Cliente[][] salaPeliculaSeleccionada = salas[numeroSala];
    int cantidadAsientosDisponibles = obtenerAsientosDisponibles(
      salaPeliculaSeleccionada
    );

    if (cantidadAsientosDisponibles < cantidadAsientosSolicitados) {
      throw new Exception(
        "No hay asientos disponibles para la película seleccionada."
      );
    }
  }

  public int obtenerAsientosReservadosPorSala(Cliente[][] sala) {
    int totalAsientosReservados = 0;

    for (Cliente[] fila : sala) {
      for (Cliente cliente : fila) {
        if (cliente != null) {
          totalAsientosReservados++;
        }
      }
    }

    return totalAsientosReservados;
  }
}
