
package utilidades;

public class Convertidor_utilidades {
    public static int convertirLetraAPosicionArreglo(char letra) {
    int posicionArreglo = 0;
    int numeroAscii = letra;
    int codigoAsciiInicioAbecedario = 65;
    int codigoAsciiFinAbecedario = 90;

    for (int i = 0; i <= 26; i++) {
      if (letra == (char) codigoAsciiInicioAbecedario + i) {
        return i;
      }
    }

    return -1;
  }
}
