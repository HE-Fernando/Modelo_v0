package src.clases;
public class Producto {
    private int codigoProducto;
    private String proveedor;
    private String descripcion;
    private double precio;
    private double stock;
    //CONSTRUCTORES
    public Producto(){}
    public Producto(int codigoProducto, String proveedor, String descripcion, double precio, int stock){
        this.codigoProducto = codigoProducto;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }
    //SET GET
    public void setCodigoProducto(int codigoProducto){
        this.codigoProducto = codigoProducto;
    }
    public int getCodigoProducto(){
        return this.codigoProducto;
    }
    public void setProveedor(String proveedor){
        this.proveedor = proveedor;
    }
    public String getProveedor(){
        return this.proveedor;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    public double getPrecio(){
        return this.precio;
    }
    public void setStock(double stock){
        this.stock = stock;
    }
    public double getStock(){
        return this.stock;
    }
    //METODOS
    public String toString(){
        return "DATOS";
    }
}
