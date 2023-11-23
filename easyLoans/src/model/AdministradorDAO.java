package model;

import persistencia.ConexionBD.ConexionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class AdministradorDAO {

    private final ConexionDB conexion;
    
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

    public List<String> obtenerUsuariosDesdeBD() {
        List<String> usuarios = new ArrayList<>();

        try {
            Connection connection = conexion.obtenerConexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT user FROM administradores");

            while (resultSet.next()) {
                String user = resultSet.getString("user");
                usuarios.add(user);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

}
