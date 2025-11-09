package src.dao;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.conexion.Aconexion;
import src.modelo.Usuario;

public class UsuarioDao {
    public List<Usuario> obtenerUsuarios(){
        Connection con = null;
        ResultSet rs = null;
        Aconexion parametros = new Aconexion();
        String sql = "SELECT * FROM usuario ORDER BY IDusuario";
        List<Usuario> listaUsuario = new ArrayList<>();
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());                PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                Usuario usuario = new Usuario();
                usuario.setCodigoUsuario(rs.getInt("IDusuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setRol(rs.getString("rol"));
                listaUsuario.add(usuario);
            }
            stm.close();
            rs.close();
            con.close();
            System.out.println("¡Listado de usuarios obtenido!");
        } catch (SQLException e){
            System.out.println("Error: UsuarioDao (obtenerUsuarios)");
            e.printStackTrace();
        }
        return listaUsuario;
    }
    public void actualizar (Usuario usuario){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        String sql = "UPDATE usuario SET nombre = ?, contraseña = ?, rol = ? WHERE IDusuario = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, usuario.getNombre());
            stm.setString(2, usuario.getContraseña());
            stm.setString(3, usuario.getRol());
            stm.setInt(4, usuario.getCodigoUsuario());
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Usuario actualizado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);                } else{
                System.out.println("No se encontró el usuario con ese ID.");
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: UsuarioDao (actualizar)");
        }
    }
    public void eliminar (Usuario usuario){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        String sql = "DELETE FROM usuario WHERE IDusuario = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, usuario.getCodigoUsuario());
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Usuario eliminado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);                } else{
                System.out.println("No se encontró el usuario con ese ID.");
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: UsuarioDao (eliminar)");
        }
    }
}
