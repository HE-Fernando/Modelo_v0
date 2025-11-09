package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Aconexion;
import modelo.Cliente;

public class Cliente_UsuarioDao {
    public boolean registrar(Cliente cliente){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado;
        String sql = "INSERT INTO cliente (nombre, usuario, contraseña, direccion, telefono) VALUES (?, ?, ?, ?, ?)";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, cliente.getNombreCliente());
            stm.setString(2, cliente.getUsuario());
            stm.setString(3, cliente.getContraseña());
            stm.setString(4, cliente.getDireccion());
            stm.setString(5, cliente.getTelefono());
            stm.executeUpdate();
            stm.close();
            con.close();
            System.out.println("¡Cliente registrado exitosamente!");
            completado = true;
        } catch (SQLException e){
            if (e.getErrorCode() == 1062){
                System.out.println("Error: Ya existe un cliente con ese ID.");
            }
            System.out.println("Error: ClienteDao (registrar)");
            completado = false;
        }
        return completado;
    }
    public List<Cliente> obtenerClientes(){
        Connection con = null;
        ResultSet rs = null; //contendra los valores del SELECT
        Aconexion parametros = new Aconexion();
        String sql = "SELECT * FROM cliente ORDER BY IDcliente";
        List<Cliente> listaCliente = new ArrayList<>();
        try{
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){ //mientras la siguiente posicion de la fila exista
                Cliente cliente = new Cliente();
                cliente.setCodigoCliente(rs.getInt("IDcliente")); //En vez de pasarle el indice de la columna le paso la etiqueta de la columna 
                cliente.setNombreCliente(rs.getString("nombre"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setContraseña(rs.getString("contraseña"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                listaCliente.add(cliente);
            }
            stm.close();
            rs.close();
            con.close();
            System.out.println("¡Listado de clientes obtenido!");
        } catch (SQLException e){
            System.out.println("Error: ClienteDao (obtenerClientes)");
            e.printStackTrace();
        }
        return listaCliente;
    }
    public boolean actualizar(int codigoCliente, String nombre, String direccion, String telefono){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado = false;
        String sql = "UPDATE cliente SET nombre = ?, direccion = ?, telefono = ? WHERE IDcliente = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, nombre);
            stm.setString(2, direccion);
            stm.setString(3, telefono);
            stm.setInt(4, codigoCliente);
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Cliente actualizado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
                completado = true;
            } else{
                System.out.println("No se encontró el cliente con ese ID.");
                completado = false;
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: ClienteDao (actualizar)");
            completado = false;
        }
        return completado;
    }
    public boolean eliminar(int codigoCliente){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado;
        String sql = "DELETE FROM cliente WHERE IDcliente = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, codigoCliente);
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Cliente eliminado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
                completado = true;
                
            } else{
                System.out.println("No se encontró el cliente con ese ID.");
                completado = false;
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: ClienteDao (eliminar)");
            completado = false;
        }
        return completado;
    }
    public boolean actualizarContraseña(int codigoCliente, String contraseña){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado;
        String sql = "UPDATE cliente SET contraseña = ? WHERE IDcliente = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, contraseña);
            stm.setInt(2, codigoCliente);
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Contraseña actualizada correctamente!");
                completado = true;
            } else {
                System.out.println("No se encontró el cliente con ese ID.");
                completado = false;
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: ClienteDao (actualizarContraseña)");
            completado = false;
        }
        return completado;
    }
    
}
