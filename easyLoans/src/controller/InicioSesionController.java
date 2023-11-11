package controller;

import model.AdministradorDAO;
import persistencia.ConexionMysql;

public class InicioSesionController {
    private final AdministradorDAO adminDAO;

    public InicioSesionController() {
        this.adminDAO = new AdministradorDAO(new ConexionMysql()); // Instanciar el DAO
    }

    public boolean iniciarSesion(String nombreUsuario, String contraseña) {
        return adminDAO.verificarCredenciales(nombreUsuario, contraseña);
    }
}
