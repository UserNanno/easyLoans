package model;

import model.busqueda.BusquedaBinaria;
import model.estructuras.ArbolBinario;
import model.ordenamiento.Quicksort;
import persistencia.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    private final ConexionBD conexion;

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

    public String[] obtenerInformacionUsuario(String dni) {
        try {
            if (conexion != null) {
                Connection connection = conexion.obtenerConexion();
                if (connection != null) {
                    String query = "SELECT * FROM usuarios WHERE dni_usuario = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, dni);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        return new String[]{
                            resultSet.getString("dni_usuario"),
                            resultSet.getString("apellidos"),
                            resultSet.getString("nombres")
                        };
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener información del usuario: " + e.getMessage());
        }
        return null; // Retorna null si hay errores o no se encuentra el usuario
    }

    public boolean registrarNuevoUsuario(String dni, String apellidos, String nombres) {
        boolean resp = false;
        try {
            if (conexion != null) {
                // Crear arbol binario
                ArbolBinario arbol = new ArbolBinario();

                String query1 = "SELECT dni_usuario FROM usuarios";
                PreparedStatement pstmt1 = conexion.obtenerConexion().prepareStatement(query1);
                ResultSet resultSet = pstmt1.executeQuery();

                // Insertar valores en el árbol desde la base de datos
                while (resultSet.next()) {
                    int valor = resultSet.getInt("dni_usuario");
                    arbol.insertar(valor);
                }

                // Imprimir mensaje indicando el uso del árbol
                System.out.println("Se ha utilizado la estructura de datos ArbolBinario para verificar duplicados.");

                // Insertar valores en la base de datos
                if (!arbol.contiene(Integer.parseInt(dni))) {
                    String query = "INSERT INTO usuarios (dni_usuario, apellidos, nombres) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = conexion.obtenerConexion().prepareStatement(query);
                    pstmt.setString(1, dni);
                    pstmt.setString(2, apellidos);
                    pstmt.setString(3, nombres);
                    pstmt.executeUpdate();

                    resp = true;
                } else {
                    resp = false;
                    JOptionPane.showMessageDialog(null, "DNI duplicado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
        return resp;
    }

    public boolean verificarPrestamo(String codigo, String dni) {
        boolean ver = false;

        try {
            if (conexion != null) {
                // 1. Verificar que existe un usuario con cierto DNI
                if (verificarUsuario(dni)) {
                    // 2. Verificar que existe un libro con su código de libro
                    String queryLibro = "SELECT * FROM libros WHERE codigo_libro = ?";
                    PreparedStatement pstmtLibro = conexion.obtenerConexion().prepareStatement(queryLibro);
                    pstmtLibro.setString(1, codigo);
                    ResultSet resultSetLibro = pstmtLibro.executeQuery();

                    if (resultSetLibro.next()) {
                        // 3. Verificar que el libro tiene stock disponible
                        int stock = resultSetLibro.getInt("stock");
                        if (stock > 0) {
                            // El libro tiene stock disponible, se puede prestar
                            ver = true;
                        } else {
                            System.out.println("El libro no tiene stock disponible.");
                        }
                    } else {
                        System.out.println("No existe un libro con el código proporcionado.");
                    }
                } else {
                    System.out.println("No existe un usuario con el DNI proporcionado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar prestamo: " + e.getMessage());
        }

        return ver;
    }

    public void quickSort(ArrayList<Integer> array, int low, int high) {
        Quicksort quicksort = new Quicksort();
        quicksort.quickSort(array, low, high);
    }
}
