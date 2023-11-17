package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.ConexionBD.ConexionDB;

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
                String selectQuery = "SELECT * FROM administradores WHERE user = ? AND password = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setString(1, nombreUsuario);
                    preparedStatement.setString(2, contrasena);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        return resultSet.next(); // Si existe un resultado, las credenciales son correctas
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        } finally {
            if (conexion != null) {
                conexion.cerrarConexion();
            }
        }
        return false; // Si hay errores o no se encuentra coincidencia
    }
}
