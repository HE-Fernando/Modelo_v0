package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import conexion.Aconexion;
import modelo.Producto;

public class ProductoDao {
    public boolean registrar(Producto producto){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado;
        String sql = "INSERT INTO producto (proveedor, descripcion, stock) VALUES (?, ?, ?)";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, producto.getProveedor());
            stm.setString(2, producto.getDescripcion());
            stm.setInt(3, producto.getStock());
            stm.executeUpdate();
            stm.close();
            con.close();
            System.out.println("¡Producto registrado exitosamente!");
            completado = true;
        } catch (SQLException e){
            if (e.getErrorCode() == 1062){
                System.out.println("Error: Ya existe un producto con ese ID.");
                completado = false;
            }
            System.out.println("Error: ProductoDao");
            completado = false;
        }
        return completado;
    }
    public List<Producto> obtenerProductos(){
        Connection con = null;
        ResultSet rs = null;
        Aconexion parametros = new Aconexion();
        String sql = "SELECT * FROM producto ORDER BY IDproducto";
        List<Producto> listaProducto = new ArrayList<>();
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                Producto producto = new Producto();
                producto.setCodigoProducto(rs.getInt("IDproducto"));
                producto.setProveedor(rs.getString("proveedor"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setStock(rs.getInt("stock"));
                listaProducto.add(producto);
            }
            stm.close();
            rs.close();
            con.close();
            System.out.println("¡Listado de clientes obtenido!");
        } catch (SQLException e){
            System.out.println("Error: ProductoDao (obtenerProductos)");
            e.printStackTrace();
        }
        return listaProducto;
    }
    public boolean actualizar(int codigoProducto, String proveedor, String descripcion, int stock){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado = false;
        String sql = "UPDATE producto SET proveedor = ?, descripcion = ?, stock = ? WHERE IDproducto = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, proveedor);
            stm.setString(2, descripcion);
            stm.setInt(3, stock);
            stm.setInt(4, codigoProducto);
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Producto actualizado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
                completado = true;
            } else{
                System.out.println("No se encontró el producto con ese ID.");
                completado = false;
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: ProductoDao (actualizar)");
            e.printStackTrace();
            completado = false;
        }
        return completado;
    }
    public boolean eliminar (int codigoProducto){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado = false;
        String sql = "DELETE FROM producto WHERE IDproducto = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, codigoProducto);
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Producto eliminado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
                completado = true;
            }else {
                System.out.println("No se encontró el producto con ese ID.");
                completado = false;
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: ProductoDao (eliminar)");
            completado = false;
        }
        return completado;
    }
}
