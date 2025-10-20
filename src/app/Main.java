package src.app;

import src.conexion.Aconexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection con = null;
        con = Aconexion.conectar();
        if (con == null){
            System.out.println("Error al conectar.");
        }
    }
}
