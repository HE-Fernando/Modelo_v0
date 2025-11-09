package modelo;
public class Producto {
    private int codigoProducto;
    private String proveedor;
    private String descripcion;
    private int stock;
    //CONSTRUCTORES
    public Producto(){}
    public Producto(String proveedor, String descripcion, int stock){
        this.proveedor = proveedor;
        this.descripcion = descripcion;
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
    public void setStock(int stock){
        this.stock = stock;
    }
    public int getStock(){
        return this.stock;
    }
    //METODOS
    @Override
    public String toString(){
        return "Producto [ID:" + getCodigoProducto()+ 
                "\nProveedor: " + getProveedor()+ 
                "\nDescripción: " + getDescripcion() + 
                "\nStock: " + getStock() + "]\n\n";
    }
}
