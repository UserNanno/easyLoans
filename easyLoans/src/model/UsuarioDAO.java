package model;

import persistencia.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private ConexionBD conexion;

    public UsuarioDAO() {
        this.conexion = ConexionBD.obtenerInstancia();
    }

    public boolean verificarUsuario(String dni) {
        try {
            Connection connection = conexion.obtenerConexion();
            if (connection != null) {
                String query = "SELECT * FROM usuario_prestamo WHERE dni_usuario = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, dni);
                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.next(); // Si existe un resultado, el usuario existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si hay errores o no se encuentra el usuario
    }
}
