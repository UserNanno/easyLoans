package controller;

import model.UsuarioDAO;

public class PrestamoController {
    private UsuarioDAO usuarioDAO;

    public PrestamoController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean verificarPrestamo(String codigo, String dni) {
        // Verificar la existencia del usuario y obtener su información
        if (usuarioDAO.verificarUsuario(dni)) {
            // Verificar la existencia del libro y su disponibilidad
            return usuarioDAO.verificarPrestamo(codigo, dni);
        } else {
            System.out.println("No existe un usuario con el DNI proporcionado.");
            return false;
        }
    }
}
