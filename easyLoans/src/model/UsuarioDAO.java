package model;

import arbolBinario.ArbolBinario;
import persistencia.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

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
        boolean resp = false;
        try {
            if (conexion != null) {
                //crear arbol binario
                ArbolBinario arbol = new ArbolBinario();
                
                //consulta a base de datos
                String query1="SELECT dni_usuario FROM usuarios";
                PreparedStatement pstmt1 = conexion.obtenerConexion().prepareStatement(query1);
                ResultSet resultSet = pstmt1.executeQuery();
                
                // Insertar valores en el árbol desde la base de datos
                while (resultSet.next()) {
                int valor = resultSet.getInt("dni_usuario");
                    arbol.insertar(valor);
                }
                
                //arbol.imprimirOrden();
                //
                
                //Insertando valores en la base de datos
                if(!arbol.contiene(Integer.parseInt(dni))){
                String query = "INSERT INTO usuarios (dni_usuario, apellidos, nombres) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conexion.obtenerConexion().prepareStatement(query);
                pstmt.setString(1, dni);
                pstmt.setString(2, apellidos);
                pstmt.setString(3, nombres);
                pstmt.executeUpdate();
                
                resp= true;
                }
                else{
                    resp= false;
                    JOptionPane.showMessageDialog(null,"DNI duplicado", "Error", JOptionPane.ERROR_MESSAGE);
                }
                    
                
               
                

            }
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
       return resp; 
    }
    
    public boolean verificarPrestamo(String codigo){
        boolean ver=false;
        try {
        if (conexion != null) {
             String query1="SELECT id_libro FROM prestamos";
                PreparedStatement pstmt1 = conexion.obtenerConexion().prepareStatement(query1);
                ResultSet resultSet = pstmt1.executeQuery();
                
                ArrayList<Integer> listaPrestamo = new ArrayList<>();
                
                // Insertar valores en el árbol desde la base de datos
                while (resultSet.next()) {
                int valor = resultSet.getInt("id_libro");
                    listaPrestamo.add(valor);
                }
            }
        } catch (SQLException e) {
        System.out.println("Error al verificar prestamo: " + e.getMessage());
    }
      
        return ver;
    }
    
    private void quickSort(ArrayList<Integer> array, int low, int high) {
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
    
}

