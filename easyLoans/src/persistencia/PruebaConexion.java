package persistencia;

import java.sql.Connection;
import persistencia.ConexionBD.ConexionDB;

public class PruebaConexion {
    public static void main(String[] args) {
        ConexionDB conexion = new ConexionMysql(); // Instancia de la conexión

        Connection connection = conexion.obtenerConexion(); // Intentar obtener la conexión

        if (connection != null) {
            System.out.println("Conexion exitosa a la base de datos.");
            conexion.cerrarConexion(); // Cerrar la conexión luego de la prueba
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
    }
}
