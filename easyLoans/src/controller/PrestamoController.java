package controller;

import java.util.Date;
import model.UsuarioDAO;


public class PrestamoController {
    private UsuarioDAO usuarioDAO;

    public PrestamoController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean verificarPrestamo(String codigo, String dni, Date devolucion){
        return usuarioDAO.verificarPrestamo(codigo,dni,devolucion);
    }
}
