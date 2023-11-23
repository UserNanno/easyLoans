package model;

import persistencia.ConexionBD;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class PrestamoDAO {

    private final ConexionBD conexion;

    public PrestamoDAO() {
        this.conexion = ConexionBD.obtenerInstancia();
    }

    public boolean generarPrestamo(int idLibro, int idUsuario, Date fechaDevolucion) {
        try {
            if (conexion != null) {
                Connection connection = conexion.obtenerConexion();
                if (connection != null) {
                    String query = "INSERT INTO prestamos (id_libro, id_usuario, fecha_prestamo, fecha_devolucion, estado_prestamo) VALUES (?, ?, NOW(), ?, 'PRESTADO')";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, idLibro);
                    preparedStatement.setInt(2, idUsuario);
                    preparedStatement.setDate(3, new java.sql.Date(fechaDevolucion.getTime()));

                    int filasAfectadas = preparedStatement.executeUpdate();

                    // Devuelve true si se insertó correctamente
                    return filasAfectadas > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al generar préstamo: " + e.getMessage());
        }
        return false; // Si hay errores o no se pudo generar el préstamo
    }

    public int obtenerIdLibroPorCodigo(String codigo) throws SQLException {
        try {
            if (conexion != null) {
                Connection connection = conexion.obtenerConexion();
                if (connection != null) {
                    String query = "SELECT id_libro FROM libros WHERE codigo_libro = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, codigo);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            return resultSet.getInt("id_libro");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ID del libro: " + e.getMessage());
        }
        return -1;
    }
}
