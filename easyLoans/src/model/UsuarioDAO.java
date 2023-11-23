package model;

import model.estructuras.ArbolBinario;
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

    public boolean verificarPrestamo(String codigo, String dni, Date devolucion){
        boolean ver = false;
        try{
            if(conexion!=null){
            String query1 = "SELECT id_libro FROM prestamos";
            PreparedStatement pstmt1 = conexion.obtenerConexion().prepareStatement(query1);
            ResultSet resultSet = pstmt1.executeQuery();
            
            ArrayList<Integer> listaPrestamo = new ArrayList<>();
            
            while (resultSet.next()) {
                    int valor = resultSet.getInt("id_libro");
                    listaPrestamo.add(valor);
                }    
            quickSort(listaPrestamo, 0, listaPrestamo.size() - 1);
            int codigoInt = Integer.parseInt(codigo);
            if (binarySearch(listaPrestamo, codigoInt) != -1) {
                ver = true;
                }
            }     
        }catch(SQLException e){
            System.out.println("Error al verificarr prestamo: " + e.getMessage());
        }
        return ver;
    }
    
    public void quickSort(ArrayList<Integer> array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);

            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    private int partition(ArrayList<Integer> array, int low, int high) {
        int pivot = array.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array.get(j) <= pivot) {
                i++;
                int temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
            }
        }

        int temp = array.get(i + 1);
        array.set(i + 1, array.get(high));
        array.set(high, temp);

        return i + 1;
    }
    private int binarySearch(ArrayList<Integer> array, int target) {
    int left = 0;
    int right = array.size() - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (array.get(mid) == target) {
            return mid; // Encontrado
        } else if (array.get(mid) < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1; // No encontrado
}
    
    }