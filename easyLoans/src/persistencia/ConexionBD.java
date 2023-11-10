package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/easyloans";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    private ConexionBD() {} // Para prevenir la creación de instancias se crea un constructor privado

    public static Connection obtenerConexion() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Conexion exitosa a la base de datos.");
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexion cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la Conexion: " + e.getMessage());
            }
        }
    }
    
    public interface ConexionDB {
    Connection obtenerConexion();
    void cerrarConexion();
}
}
