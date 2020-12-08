
package utilidades;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class JtextField_utilidades {
    public static void validarIngresoNumeros(java.awt.event.KeyEvent evt){

            char caracter=evt.getKeyChar();
            if(!Character.isDigit(caracter) || (caracter==KeyEvent.VK_BACK_SPACE))
            {
                evt.consume();

            }
            
    }
    
    public static int obtenerValorNumericoIngresado(JTextField jTextField) throws Exception{
        String texto = jTextField.getText();
        
        if(texto.length() > 9){
            jTextField.setText(texto.substring(0, 9));
            throw new Exception("El valor numerico ingresado no puede ser mayor a 9 digitos");
        }
        
        int valorEntero = Integer.parseInt(
            jTextField.getText().equals("")
              ? "0"
              : jTextField.getText()
          );
        
        return valorEntero;
    }

    
}
