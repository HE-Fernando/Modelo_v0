package modelo;

public class DetalleVenta {
    private int codigoDetalle;
    private int codigoVenta;
    private int codigoProducto;
    private int cantidad;
    private double precio;
    private double subtotal;
    private String descripcionProducto;

    //CONSTRUCTORES
    public DetalleVenta(){}
    public DetalleVenta(int codigoProducto, int codigoVenta, int cantidad, double precio){
        this.codigoVenta = codigoVenta;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = this.precio * cantidad;
    }
    //GET SET
    public void setCodigoDetalle(int codigoDetalle){
        this.codigoDetalle = codigoDetalle;
    }
    public int getCodigoDetalle(){
        return this.codigoDetalle;
    }
    public void setCodigoVenta(int codigoVenta){
        this.codigoVenta = codigoVenta;
    }
    public int getCodigoVenta(){
        return this.codigoVenta;
    }
    public void setCodigoProducto(int codigoProducto){
        this.codigoProducto = codigoProducto;
    }
    public int getCodigoProducto(){
        return this.codigoProducto;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public int getCantidad(){
        return this.cantidad;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    public double getPrecio(){
        return this.precio;
    }
    public void setSubtotal(){
        this.subtotal = getCantidad() * getPrecio();
    }
    public double getSubtotal(){
        setSubtotal();
        return this.subtotal;
    }
    public String getDescripcionProducto(){
        return this.descripcionProducto;
    }
    public void setDescripcionProducto(String descripcionProducto){
        this.descripcionProducto = descripcionProducto;
    }
    //METODOS
    @Override
    public String toString(){
        return "Detalle de Venta [ID Detalle:" + getCodigoDetalle() + 
                "\nID Venta: " + getCodigoVenta() +
                "\nProducto: " + getCodigoProducto() +
                "\nPrecio unitario: $" + getPrecio() +
                "\nCantidad: " + getCantidad() +
                "\nSubtotal: $" + getSubtotal() + "]\n\n";
    }
}
