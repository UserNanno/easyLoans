package controller;

import java.util.Date;
import model.UsuarioDAO;

public class VerificarController {
    private UsuarioDAO usuarioDAO;

    public VerificarController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean verificarUsuario(String dni) {
        return usuarioDAO.verificarUsuario(dni);
    }
    
    public boolean verificarPrestamo(String codigo){
        return usuarioDAO.verificarPrestamo(codigo);
    }
}
