package model;

import persistencia.ConexionBD.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDAO {
    private final ConexionDB conexion;

    // Constructor que recibe la conexión
    public AdministradorDAO(ConexionDB conexion) {
        this.conexion = conexion;
    }

    public boolean verificarCredenciales(String nombreUsuario, String contrasena) {
        try {
            Connection connection = conexion.obtenerConexion();
            if (connection != null) {
                String query = "SELECT * FROM administradores WHERE user = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nombreUsuario);
                preparedStatement.setString(2, contrasena);
                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.next(); // Si existe un resultado, las credenciales son correctas
            }
        } catch (SQLException e) {
        }
        return false; // Si hay errores o no se encuentra coincidencia
    }
}
