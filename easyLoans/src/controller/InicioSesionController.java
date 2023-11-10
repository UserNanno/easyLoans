package controller;

import model.UsuarioDAO;
import persistencia.ConexionMysql;

public class InicioSesionController {
    private UsuarioDAO usuarioDAO;

    public InicioSesionController() {
        this.usuarioDAO = new UsuarioDAO(new ConexionMysql()); // Instanciar el DAO
    }

    public boolean iniciarSesion(String nombreUsuario, String contraseña) {
        return usuarioDAO.verificarCredenciales(nombreUsuario, contraseña);
    }
}
