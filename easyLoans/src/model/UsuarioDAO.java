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
                // Obtener lista de códigos de libros de la tabla de libros
                ArrayList<Integer> listaCodigosLibros = obtenerListaCodigosLibros();

                // Ordenar la lista utilizando el algoritmo de ordenamiento rápido (Quicksort)
                System.out.println("Utilizando Quicksort para ordenar la lista de codigos de libros.");
                Quicksort quicksort = new Quicksort();
                quicksort.quickSort(listaCodigosLibros, 0, listaCodigosLibros.size() - 1);

                // Verificar si el código está presente utilizando la búsqueda binaria
                int codigoInt = Integer.parseInt(codigo);
                System.out.println("Utilizando busqueda binaria para verificar la presencia del codigo en la lista ordenada.");
                if (BusquedaBinaria.busquedaBinariaEnArrayList(listaCodigosLibros, codigoInt) != -1) {
                    // El código del libro existe en la lista ordenada
                    // Aquí puedes agregar más lógica si es necesario
                    ver = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar prestamo: " + e.getMessage());
        }

        return ver;
    }

    private ArrayList<Integer> obtenerListaCodigosLibros() throws SQLException {
        ArrayList<Integer> listaCodigosLibros = new ArrayList<>();

        String queryLibros = "SELECT codigo_libro FROM libros";
        try (PreparedStatement pstmtLibros = conexion.obtenerConexion().prepareStatement(queryLibros); ResultSet resultSetLibros = pstmtLibros.executeQuery()) {

            while (resultSetLibros.next()) {
                int codigoLibro = resultSetLibros.getInt("codigo_libro");
                listaCodigosLibros.add(codigoLibro);
            }
        }

        return listaCodigosLibros;
    }

    public void quickSort(ArrayList<Integer> array, int low, int high) {
        Quicksort quicksort = new Quicksort();
        quicksort.quickSort(array, low, high);
    }
}
