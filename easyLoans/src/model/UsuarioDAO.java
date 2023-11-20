package model;

import arbolBinario.ArbolBinario;
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
        if (conexion != null) {
            Connection connection = conexion.obtenerConexion();
            if (connection != null) {
                String query = "SELECT * FROM usuarios WHERE dni_usuario = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, dni);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next(); // Si existe un resultado, el usuario existe
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al verificar usuario: " + e.getMessage());
    }
    return false; // Si hay errores o no se encuentra el usuario
}

    public boolean registrarNuevoUsuario(String dni, String apellidos, String nombres) {
        
        
        
        
        try {
            if (conexion != null) {
                //
                String query1="SELECT dni_usuario FROM usuarios";
                PreparedStatement pstmt1 = conexion.obtenerConexion().prepareStatement(query1);
                ResultSet resultSet = pstmt1.executeQuery();
                
                
                // Insertar valores en el árbol desde la base de datos
                ArbolBinario arbol = new ArbolBinario();
                
               
                while (resultSet.next()) {
                int valor = resultSet.getInt("dni_usuario");
                    System.out.println(valor);
                    arbol.insertar(valor);
                }
                
//                arbol.imprimirOrden();
                //
                
                
                
                String query = "INSERT INTO usuarios (dni_usuario, apellidos, nombres) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conexion.obtenerConexion().prepareStatement(query);
                pstmt.setString(1, dni);
                pstmt.setString(2, apellidos);
                pstmt.setString(3, nombres);

                int filasAfectadas = pstmt.executeUpdate();
                return filasAfectadas > 0; // Retorna true si se insertaron filas en la tabla
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
        return false;
    }
}
