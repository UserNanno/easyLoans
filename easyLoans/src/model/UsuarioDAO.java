package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.ConexionBD;
import util.DniManager;

public class UsuarioDAO {

    private final ConexionBD conexion;
    private final DniManager dniManager;

    public UsuarioDAO() {
        this.conexion = ConexionBD.obtenerInstancia();
        this.dniManager = new DniManager();
        cargarUsuariosRegistrados();
    }

    private void cargarUsuariosRegistrados() {
        try (Connection connection = conexion.obtenerConexion(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT dni_usuario FROM usuarios"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                dniManager.agregarDni(resultSet.getString("dni_usuario"));
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar usuarios registrados: " + e.getMessage());
        }
    }

    public boolean verificarUsuario(String dni) {
        return dniManager.contieneDni(dni);
    }

    public boolean registrarNuevoUsuario(String dni, String apellidos, String nombres) {
        try (Connection connection = conexion.obtenerConexion()) {
            connection.setAutoCommit(false);

            if (!dniManager.contieneDni(dni)) {
                String insertQuery = "INSERT INTO usuarios (dni_usuario, apellidos, nombres) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
                    pstmt.setString(1, dni);
                    pstmt.setString(2, apellidos);
                    pstmt.setString(3, nombres);

                    int filasAfectadas = pstmt.executeUpdate();
                    if (filasAfectadas > 0) {
                        dniManager.agregarDni(dni);
                        connection.commit();
                        return true;
                    }
                }
            }

            connection.rollback(); // Revierte la transacción si no se cumple la condición
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
        return false;
    }

}
