package test;
import conexion.Aconexion;
import modelo.*;
import dao.*;
import java.time.LocalDate;
import java.util.List;
import vista.*;

/**
 *
 * @author hirte
 */
public class PruebaConexion {
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(() -> {
        FrmLogin login = new FrmLogin();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        });
        
        /*CLIENTE - CLIENTEDAO
        Cliente cliente1 = new Cliente("Hirt Esteban", "Hfernando", "123456789", "Mariano Moreno 453", "3743457272");
        Cliente_UsuarioDao controladorUsuario = new Cliente_UsuarioDao();
     
        REGISTRAR FUNCIONANDO  
        controladorUsuario.registrar(cliente1);
        
        OBTENERCLIENTES FUNCIONANDO
            List<Cliente> clientes = controladorUsuario.obtenerClientes();
            for (int i = 0; i < clientes.size(); i++){
                Cliente c = clientes.get(i);
                System.out.println(c);
            }
        }
        
        ACTUALIZAR FUNCIONANDO
        cliente1.setCodigoCliente(2);
        cliente1.setNombreCliente("Hirt Fernando");
        cliente1.setDireccion("Av. 9 de Julio 1521");
        cliente1.setTelefono("3743457271");
        controladorUsuario.actualizar(cliente1);
        
        ELIMINAR FUNCIONANDO
        cliente1.setCodigoCliente(2);
        controladorUsuario.eliminar(cliente1);
        
        ACTUALIZARCONTRASEÑA FUNCIONANDO
        controladorUsuario.actualizarContraseña("1234", 3);
        */
        
        /*PRODUCTO - PRODUCTODAO
        Producto producto1 = new Producto("Proveedor 1", "Descripcion 1", 12250.25, 1);
        ProductoDao controladorProducto = new ProductoDao();
        
        REGISTRAR FUNCIONANDO
        controladorProducto.registrar(producto1);
        
        OBTENER PRODUCTOS FUNCIONANDO
        List<Producto> productos = controladorProducto.obtenerProductos();
        for (int i = 0; i < productos.size(); i++){
            Producto p = productos.get(i);
            System.out.println(p);
        }
        
        ACTUALIZAR FUNCIONANDO
        producto1.setDescripcion("Descripcion 2");
        producto1.setPrecio(1);
        producto1.setProveedor("Proveedor 2");
        producto1.setStock(2);
        producto1.setCodigoProducto(1);
        controladorProducto.actualizar(producto1);
        
        ELIMINAR FUNCIONANDO
        producto1.setCodigoProducto(1);
        controladorProducto.eliminar(producto1);
        */
        
        /*VENTA - VENTADAO
        Venta venta1 = new Venta(1, LocalDate.now());
        VentaDao controladorVenta = new VentaDao();
       
        REGISTRAR FUNCIONANDO
        controladorVenta.registrar(venta1);
        
        OBTENER VENTAS FUNCIONANDO
        List<Venta> ventas = controladorVenta.obtenerVentas();
        for (int i = 0;i < ventas.size();i++){
            Venta v = ventas.get(i);
            System.out.println(v);
        }
        
        ACTUALIZAR TOTAL FUNCIONANDO (SOLO ACTUALIZA EL MONTO TOTAL DE LA VENTA)
        controladorVenta.actualizarTotal(1, 200000.05);
        
        ELIMINAR FUNCIONANDO
        controladorVenta.eliminar(1);
        */
        
        //DetalleVentaDao controladorDetalle = new DetalleVentaDao();
        /*DETALLEVENTA - DETALLEVENTADAO 
        CARGA DE ENTRADAS NECESARIAS EN LA DB PARA LA CREACION DE UN DETALLE (PRODUCTO, CLIENTE, VENTA)
        Producto producto1 = new Producto("Proveedor 1", "Descripcion 1", 5000, 5);
        Producto producto2 = new Producto("Proveedor 1", "Descripcion 2", 2500, 10);
        ProductoDao controladorProducto = new ProductoDao();
        controladorProducto.registrar(producto1);
        controladorProducto.registrar(producto2);
        Cliente cliente1 = new Cliente("Esteban", "Hesteban", "1234", "Mariano Moreno 453", "3743457272");
        Cliente_UsuarioDao controladorCliente = new Cliente_UsuarioDao();
        controladorCliente.registrar(cliente1);
        Venta venta1 = new Venta(2, LocalDate.of(2025, 10, 28));
        VentaDao controladorVenta = new VentaDao();
        controladorVenta.registrar(venta1);
        */
        
        /*REPLICO LOS VALORES EXACTOS QUE TENGO EN LA DB Y REGISTRO EL DETALLE
        //ID CLIENTE 2
        Cliente cliente1 = new Cliente("Esteban", "Hesteban", "1234", "Mariano Moreno 453", "3743457272");
        cliente1.setCodigoCliente(2);
        
        //ID VENTA 1
        Venta venta1 = new Venta(2, LocalDate.of(2025, 10, 28));
        venta1.setCodigoVenta(1);
        
        //ID PRODUCTO 1 , 2
        Producto producto1 = new Producto("Proveedor 1", "Descripcion 1", 5000, 5);
        producto1.setCodigoProducto(1);
        Producto producto2 = new Producto("Proveedor 1", "Descripcion 2", 2500, 10);
        producto2.setCodigoProducto(2);
        
        //REGISTRAR FUNCIONANDO
        DetalleVenta detalle1 = new DetalleVenta(producto1, venta1, 3);
        DetalleVenta detalle2 = new DetalleVenta(producto2, venta1, 2);
        controladorDetalle.registrar(detalle1);
        controladorDetalle.registrar(detalle2);
        
        //OBTENER DETALLES FUNCIONANDO
        TODOS LOS DETALLES
        List<DetalleVenta> detalles = controladorDetalle.obtenerDetalleVentasTotales();
        System.out.println("Detalles totales");
        for (int i = 0;i < detalles.size();i++){
            DetalleVenta v = detalles.get(i);
            System.out.println(v);
        }
        DETALLES DE VENTA ESPECIFICA
        System.out.println("Detalles Venta N°: " + 2);
        List<DetalleVenta> detalle2 = controladorDetalle.obtenerDetalleVentas(2);
        for (int i = 0;i < detalle2.size();i++){
            DetalleVenta v2 = detalle2.get(i);
            System.out.println(v2);
        }
        
        ACTUALIZAR FUNCIONANDO (modifica IDproducto, cantidad, precio, subtotal)
        controladorDetalle.actualizar(1, 5, 10, 2);
        
        ELIMINAR FUNCIONANDO
        controladorDetalle.eliminar(3);
        */
        
        
        
        
        
        
    }
    
}
