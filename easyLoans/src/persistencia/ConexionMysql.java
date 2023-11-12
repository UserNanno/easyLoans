package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import persistencia.ConexionBD.ConexionDB;

public class ConexionMysql implements ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/easyloans";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    @Override
    public Connection obtenerConexion() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conexion exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }

    @Override
    public void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexion cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la Conexion: " + e.getMessage());
            }
        }
    }
}
