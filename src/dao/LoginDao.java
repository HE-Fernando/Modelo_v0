package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexion.Aconexion;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hirte
 */
public class LoginDao {
    public boolean autentificar(String usuario, String contraseña){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean autentificado = false;
        ResultSet rs = null;
        String sql = "SELECT * FROM cliente WHERE usuario = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, usuario);
            rs = stm.executeQuery();
            if (rs.next()){
                String contraseñaDB = rs.getString("contraseña");
                if (contraseñaDB.equals(contraseña)){
                autentificado = true;
            }
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: LoginDao (autentificar)");
            e.printStackTrace();
        }
        return autentificado;
    }
}
