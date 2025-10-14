package src.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Aconexion {
    public static Connection conectar(){
        Connection con = null;

        String password = "";
        String usuario = "root";
        String url = "jdbc:mysql://localhost:3306/test?user=" + usuario + "&password=" + password;
        try {
            con = DriverManager.getConnection(url);
            if (con != null){
                System.out.println("Conectado.");
            }
        } catch (SQLException e){
            System.out.println("No se pudo conectar a la Base de Datos.");
        }
        return con;
    }
}
