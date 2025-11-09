package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Aconexion {
    private String password = "";
    private String usuario = "root";
    private String url = "jdbc:mysql://localhost:3306/sistema?useSSL=false&serverTimezone=UTC";
    //GET SET
    public String getPassword(){
        return this.password;
    }
    public String getUsuario(){
        return this.usuario;
    }
    public String getUrl(){
        return this.url;
    }
    //CREO LOS GET Y SET PARA QUE PUEDA ACCEDER A LOS DATOS DE CONEXION, ADEMAS TENGO UN METODO "CONECTAR" PARA PROBAR LA CONEXION
    //SIN NECESIDAD DE LLAMAR A UN CONTROLADOR
    public Connection conectar(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(getUrl(), getUsuario(), getPassword());
            if (con != null){
                System.out.println("Conectado.");
                con.close(); //CIERRO CONEXION
            }
        } catch (SQLException e){
            System.out.println("No se pudo conectar a la Base de Datos.");
        }
        return con;
    }
}
