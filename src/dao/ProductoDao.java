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

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import src.conexion.Aconexion;
import src.modelo.Producto;

public class ProductoDao {
    public void registrar(Producto producto){
        Connection con;
        Aconexion parametros = new Aconexion();
        String sql = "INSERT INTO producto (IDproducto, proveedor, descripcion, precio, stock) VALUES (?, ?, ?, ?, ?)";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, producto.getCodigoProducto());
            stm.setString(2, producto.getProveedor());
            stm.setString(3, producto.getDescripcion());
            stm.setDouble(4, producto.getPrecio());
            stm.setInt(5, producto.getStock());
            stm.executeUpdate();
            stm.close();
            con.close();
            System.out.println("¡Producto registrado exitosamente!");
        } catch (SQLException e){
            if (e.getErrorCode() == 1062){
                System.out.println("Error: Ya existe un producto con ese ID.");
            }
            System.out.println("Error: ProductoDao");
        }

    }
    public List<Producto> obtenerProductos(){
        Connection con;
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
                producto.setPrecio(rs.getDouble("precio"));
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
    public void actualizar(Producto producto){
        Connection con;
        Aconexion parametros = new Aconexion();
        String sql = "UPDATE producto SET proveedor = ?, descripcion = ?, precio = ?, stock = ? WHERE IDproducto = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, producto.getProveedor());
            stm.setString(2, producto.getDescripcion());
            stm.setDouble(3, producto.getPrecio());
            stm.setInt(4, producto.getStock());
            stm.setInt(5, producto.getCodigoProducto());
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Producto actualizado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
            } else{
                System.out.println("No se encontró el producto con ese ID.");
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: ProductoDao (actualizar)");
        }
    }
    public void eliminar (Producto producto){
        Connection con;
        Aconexion parametros = new Aconexion();
        String sql = "DELETE FROM producto WHERE IDproducto = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, producto.getCodigoProducto());
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Producto eliminado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
            }else {
                System.out.println("No se encontró el producto con ese ID.");
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: ProductoDao (eliminar)");
        }
    }
}
