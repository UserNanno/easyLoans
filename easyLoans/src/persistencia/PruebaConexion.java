package persistencia;

import java.sql.Connection;
import persistencia.ConexionBD.ConexionDB;

public class PruebaConexion {
    public static void main(String[] args) {
        ConexionDB conexion = new ConexionMysql(); 

        Connection connection = conexion.obtenerConexion(); 

        if (connection != null) {
            System.out.println("Conexion exitosa a la base de datos.");
            conexion.cerrarConexion(); 
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
    }
}
