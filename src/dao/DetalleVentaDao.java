package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Aconexion;
import modelo.DetalleVenta;

public class DetalleVentaDao {
    public void actualizarStock(int idProducto, int cantidad, String accion, boolean restar){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        switch (accion) {
            case "Registrar":
                String sqlRegistro = "UPDATE producto SET stock = stock - ? WHERE IDproducto = ? AND stock >= ?";
                try {
                    con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
                    PreparedStatement stm = con.prepareStatement(sqlRegistro);
                    stm.setInt(1, cantidad);
                    stm.setInt(2, idProducto);
                    stm.setInt(3, cantidad);
                    int filas = stm.executeUpdate();
                    stm.close();
                    con.close();
                    if (filas > 0){
                        System.out.println("¡Stock modificado (registrar-reduccion)!");
                        System.out.println("Filas afectadas: " + filas);
                    } else {
                        System.out.println("No se actualizó el stock. Verificar ID o si hay stock suficiente");
                    }
                } catch (SQLException e){
                    System.out.println("Error: DetalleVentaDao actualizarStock.Registrar");
                    e.printStackTrace();
                }
                break;
            case "Editar":
                if (restar){
                    //SI VA A RESTAR STOCK DE LA BASE DE DATOS, ES DECIR AL EDITAR EL DETALLE AUMENTAMOS LA CANTIDAD DEL PRODUCTO SELECCIONADO
                    String sqlEditar = "UPDATE producto SET stock = stock - ? WHERE IDproducto = ? AND stock >= ?";
                    try {
                        con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
                        PreparedStatement stm = con.prepareStatement(sqlEditar);
                        stm.setInt(1, cantidad);
                        stm.setInt(2, idProducto);
                        stm.setInt(3, cantidad);
                        int filas = stm.executeUpdate();
                        stm.close();
                        con.close();
                        if (filas > 0){
                            System.out.println("¡Stock modificado (editar-reduccion)!");
                            System.out.println("Filas afectadas: " + filas);
                        } else {
                            System.out.println("No se actualizó el stock. Verificar ID o si hay stock suficiente");
                        }
                    } catch (SQLException e){
                        System.out.println("Error: DetalleVentaDao actualizarStock.editar(reduccion)");
                        e.printStackTrace();
                    }
                } else {
                    String sqlEditar = "UPDATE producto SET stock = stock + ? WHERE IDproducto = ?";
                    try {
                        con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
                        PreparedStatement stm = con.prepareStatement(sqlEditar);
                        stm.setInt(1, cantidad);
                        stm.setInt(2, idProducto);
                        int filas = stm.executeUpdate();
                        stm.close();
                        con.close();
                        if (filas > 0){
                            System.out.println("¡Stock modificado (editar-aumento)!");
                            System.out.println("Filas afectadas: " + filas);
                        } else {
                            System.out.println("No se actualizó el stock. Verificar ID");
                        }
                    } catch (SQLException e){
                        System.out.println("Error: DetalleVentaDao actualizarStock.editar(aumento)");
                        e.printStackTrace();
                    }
                }
                break;
            case "Eliminar":
                String sqlEliminar = "UPDATE producto SET stock = stock + ? WHERE IDproducto = ?";
                try {
                    con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
                    PreparedStatement stm = con.prepareStatement(sqlEliminar);
                    stm.setInt(1, cantidad);
                    stm.setInt(2, idProducto);
                    int filas = stm.executeUpdate(); 
                    stm.close();
                    con.close();
                    if (filas > 0){
                        System.out.println("¡Stock modificado (eliminar-aumento)!");
                        System.out.println("Filas afectadas: " + filas);
                    } else {
                        System.out.println("No se actualizó el stock. Verificar ID");
                    }
                } catch (SQLException e){
                    System.out.println("Error: DetalleVentaDao actualizarStock.eliminar");
                    e.printStackTrace();
                }
                break;
            default:
                throw new AssertionError();
        }
    }
    public boolean registrar(DetalleVenta detalleVenta){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado;
        String sql = "INSERT INTO detalle (IDventa, IDproducto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, detalleVenta.getCodigoVenta());
            stm.setInt(2, detalleVenta.getCodigoProducto());
            stm.setInt(3, detalleVenta.getCantidad());
            stm.setDouble(4, detalleVenta.getPrecio());
            stm.setDouble(5, detalleVenta.getSubtotal());
            stm.executeUpdate();
            stm.close();
            con.close();
            System.out.println("¡Detalle registrado exitosamente!");
            completado = true;
        } catch (SQLException e){
            System.out.println("Error: DetalleVentaDao (registrar)");
            e.printStackTrace();
            completado = false;
        }
        actualizarStock(detalleVenta.getCodigoProducto(), detalleVenta.getCantidad(), "Registrar", true);
        return completado;
    }
    public List<DetalleVenta> obtenerDetalleVentas(int codigoVenta){
        Connection con = null;
        ResultSet rs = null;
        Aconexion parametros = new Aconexion();
        String sql = """
                         SELECT detalle.IDdetalle,
                         detalle.IDventa,
                         detalle.IDproducto,
                         producto.descripcion,
                         detalle.cantidad,
                         detalle.precio_unitario,
                         detalle.subtotal
                         FROM detalle
                         INNER JOIN producto ON detalle.IDproducto = producto.IDproducto
                         WHERE detalle.IDventa = ?
                         """;
        //String sql = "SELECT * FROM detalle WHERE IDventa = ?";
        List<DetalleVenta> listaDetalleVentas = new ArrayList<>();
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, codigoVenta);
            rs = stm.executeQuery();
            while (rs.next()){
                DetalleVenta detalle = new DetalleVenta();
                detalle.setCodigoDetalle(rs.getInt("IDdetalle"));
                detalle.setCodigoVenta(rs.getInt("IDventa"));
                detalle.setCodigoProducto(rs.getInt("IDproducto"));
                detalle.setDescripcionProducto(rs.getString("descripcion"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio(rs.getDouble("precio_unitario"));
                detalle.setSubtotal();
                listaDetalleVentas.add(detalle);
            }
            stm.close();
            rs.close();
            con.close();
            System.out.println("¡Listado de detalles de la venta N°: " + codigoVenta + " obtenido!");
        } catch (SQLException e){
            System.out.println("Error: DetalleVentaDao (obtenerDetalleVentas)");
            e.printStackTrace();
        }
        return listaDetalleVentas;
    }
    /* NO UTILIZO
    public List<DetalleVenta> obtenerDetalleVentasTotales(){
        Connection con = null;
        ResultSet rs = null;
        Aconexion parametros = new Aconexion();
        String sql = "SELECT * FROM detalle";
        List<DetalleVenta> listaDetalleVentas = new ArrayList<>();
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                DetalleVenta detalle = new DetalleVenta();
                detalle.setCodigoDetalle(rs.getInt("IDdetalle"));
                detalle.setCodigoVenta(rs.getInt("IDventa"));
                detalle.setCodigoProducto(rs.getInt("IDproducto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio(rs.getDouble("precio_unitario"));
                detalle.setSubtotal();
                listaDetalleVentas.add(detalle);
            }
            stm.close();
            rs.close();
            con.close();
            System.out.println("¡Listado de detalles de todas las ventas obtenido!");
        } catch (SQLException e){
            System.out.println("Error: DetalleVentaDao (obtenerDetalleVentas)");
            e.printStackTrace();
        }
        return listaDetalleVentas;
    }
    */
    public boolean actualizar(int codigoDetalle, int cantidad, double precio_unitario, int codigoProducto, boolean restar, int stockActualizar){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado;
        String sql = "UPDATE detalle SET IDproducto = ?, cantidad = ?, precio_unitario = ?, subtotal = ? WHERE IDdetalle = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, codigoProducto);
            stm.setInt(2, cantidad);
            stm.setDouble(3, precio_unitario);
            stm.setDouble(4, cantidad * precio_unitario);
            stm.setInt(5, codigoDetalle);
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Detalle actualizado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
                completado = true;
            } else {
                System.out.println("No se encontró el detalle con ese ID.");
                completado = false;
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: DetalleVentaDao (actualizar)");
            e.printStackTrace();
            completado = false;
        }
        actualizarStock(codigoProducto, stockActualizar, "Editar", restar);
        return completado;
    }
    public boolean eliminar (int codigoDetalle){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado;
        String sqlProducto = "SELECT * from detalle WHERE IDdetalle = ?";
        String sql = "DELETE FROM detalle WHERE IDdetalle = ?";
        int idProducto = 0;
        int cantidad = 0;
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            PreparedStatement stmP = con.prepareStatement(sqlProducto);
            stmP.setInt(1, codigoDetalle);
            ResultSet rs = stmP.executeQuery();
            if (rs.next()){
                idProducto = rs.getInt("IDproducto");
                cantidad = rs.getInt("cantidad");
            }
            rs.close();
            stmP.close();
            stm.setInt(1, codigoDetalle);
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Detalle eliminado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
                completado = true;
            } else {
                System.out.println("No se encontró el detalle con ese ID.");
                completado = false;
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: DetalleVentaDao (eliminar)");
            e.printStackTrace();
            completado = false;
        }
        actualizarStock(idProducto, cantidad, "Eliminar", true);
        return completado;
    }
}
