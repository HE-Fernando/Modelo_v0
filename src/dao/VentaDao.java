package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Aconexion;
import modelo.Venta;

public class VentaDao {
    public boolean registrar(Venta venta){
        Connection con = null;
        boolean completado;
        Aconexion parametros = new Aconexion();
        String sql = "INSERT INTO venta (IDcliente, fecha) VALUES (?, ?)";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm2 = con.prepareStatement(sql);
            stm2.setInt(1, venta.getCodigoCliente());
            stm2.setDate(2, java.sql.Date.valueOf(venta.getFecha())); //NECESITO CONVERTIR EL TIPO PARA UTILIZAR ESTA FUNCION
            stm2.executeUpdate();
            stm2.close();
            con.close();
            System.out.println("¡Venta registrada exitosamente!");
            completado = true;
        } catch (SQLException e){
            System.out.println("Error: VentaDao (regisstrar)");
            e.printStackTrace();
            completado = false;
        }
        return completado;
    }
    public List<Venta> obtenerVentas(){
        Connection con = null;
        ResultSet rs = null;
        Aconexion parametros = new Aconexion();
        String sql = "SELECT * FROM venta ORDER BY fecha";
        List<Venta> listaVentas = new ArrayList<>();
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                Venta venta = new Venta();
                venta.setCodigoVenta(rs.getInt("IDventa"));
                venta.setCodigoCliente(rs.getInt("IDcliente"));
                venta.setFecha(rs.getDate("fecha").toLocalDate());
                venta.setTotal(rs.getDouble("total"));
                listaVentas.add(venta);
            }
            stm.close();
            rs.close();
            con.close();
            System.out.println("¡Listado de ventas obtenido!");
        } catch (SQLException e){
            System.out.println("Error: VentaDao (obtenerVentas)");
            e.printStackTrace();
        }
        return listaVentas;
    }
    public boolean actualizarTotal(int codigoVenta ,double total){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado;
        String sql = "UPDATE venta SET total = ? WHERE IDventa = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setDouble(1, total);
            stm.setInt(2, codigoVenta);
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Monto total actualizado correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
                completado = true;
            } else {
                System.out.println("No se encontró la venta con ese ID.");
                completado = false;
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: VentaDao (actualizar)");
            completado = false;
        }
        return completado;
    }
    public boolean eliminar (int codigoVenta){
        Connection con = null;
        Aconexion parametros = new Aconexion();
        boolean completado;
        String sql = "DELETE FROM venta WHERE IDventa = ?";
        try {
            con = DriverManager.getConnection(parametros.getUrl(), parametros.getUsuario(), parametros.getPassword());
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, codigoVenta);
            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("¡Venta eliminada correctamente!");
                System.out.println("Cantidad de filas afectadas: " + filasAfectadas);
                completado = true;
            } else{
                System.out.println("No se encontró la venta con ese ID.");
                completado = false;
            }
            stm.close();
            con.close();
        } catch (SQLException e){
            System.out.println("Error: VentaDao (eliminar)");
            completado = false;
        }
        return completado;
    }
}
