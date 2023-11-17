package controller;

import model.UsuarioDAO;

public class RegistroController {
    private UsuarioDAO usuarioDAO;

    public RegistroController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean registrarUsuario(String dni, String apellidos, String nombres) {
        // Llamada al método de inserción en el DAO
        return usuarioDAO.registrarNuevoUsuario(dni, apellidos, nombres);
    }
}
