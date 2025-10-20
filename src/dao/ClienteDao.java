package src.dao;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.conexion.Aconexion;
import src.modelo.Cliente;

public class ClienteDao {
    public void registrar(Cliente cliente){
        Connection con;
        Aconexion parametros = new Aconexion();
        String sql = "INSERT INTO cliente (IDcliente, nombre, direccion, telefono) VALUES (?, ?, ?, ?)";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, cliente.getCodigoCliente());
            stm.setString(2, cliente.getNombreCliente());
            stm.setString(3, cliente.getDireccion());
            stm.setString(4, cliente.getTelefono());
            stm.executeUpdate();
            stm.close();
            con.close();
            System.out.println("¡Cliente registrado exitosamente!");
        } catch (SQLException e){
            if (e.getErrorCode() == 1062){
                System.out.println("Error: Ya existe un cliente con ese ID.");
            }
            System.out.println("Error: ClienteDao (registrar)");
        }
    }
    public List<Cliente> obtenerClientes(){
        Connection con;
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
    public void actualizar(Cliente cliente){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        String sql = "UPDATE cliente SET nombre = ?, direccion = ?, telefono = ? WHERE IDcliente = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, cliente.getNombreCliente());
            stm.setString(2, cliente.getDireccion());
            stm.setString(3, cliente.getTelefono());
            stm.setInt(4, cliente.getCodigoCliente());
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Cliente actualizado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
            } else{
                System.out.println("No se encontró el cliente con ese ID.");
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: ClienteDao (actualizar)");
        }
    }
    public void eliminar(Cliente cliente){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        String sql = "DELETE FROM cliente WHERE IDcliente = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, cliente.getCodigoCliente());
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Cliente eliminado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
            } else{
                System.out.println("No se encontró el cliente con ese ID.");
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: ClienteDao (eliminar)");
        }
    }
    
}