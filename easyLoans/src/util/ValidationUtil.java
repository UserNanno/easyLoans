// En el nuevo paquete "util" o "validation"
package util;

import javax.swing.JOptionPane;

public class ValidationUtil {

    public static boolean validarCampos(String dni, String apellidos, String nombres) {
        // Verificar que todos los campos estén completos
        if (dni.isEmpty() || apellidos.isEmpty() || nombres.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar el formato del DNI
        if (!validarDni(dni)) {
            return false;
        }

        // Validar que los apellidos y nombres no contengan caracteres numéricos
        if (contieneNumeros(apellidos) || contieneNumeros(nombres)) {
            JOptionPane.showMessageDialog(null, "Los apellidos y nombres no pueden contener números", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Puedes agregar más validaciones según tus requisitos

        return true;
    }

    private static boolean contieneNumeros(String texto) {
        // Verificar si el texto contiene números
        return texto.matches(".*\\d.*");
    }

    private static boolean validarDni(String dni) {
        // Verificar que el DNI tenga 8 caracteres y sean todos numéricos
        if (dni.length() == 8 && dni.matches("\\d+")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El DNI debe tener exactamente 8 caracteres numéricos", "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
