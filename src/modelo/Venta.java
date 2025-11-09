package modelo;
import java.time.LocalDate;
public class Venta {
    private int codigoVenta;
    private int codigoCliente;
    private LocalDate fecha;
    private double total;
    //CONSTRUCTORES
    public Venta(){}
    public Venta(int codigoCliente, LocalDate fecha){
        this.codigoCliente = codigoCliente;
        this.fecha = fecha;
    }
    //GET SET
    public void setCodigoVenta(int codigoVenta){
        this.codigoVenta = codigoVenta;
    }
    public int getCodigoVenta(){
        return this.codigoVenta;
    }
    public void setCodigoCliente(int codigoCliente){
        this.codigoCliente = codigoCliente;
    }
    public int getCodigoCliente(){
        return this.codigoCliente;
    }
    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }
    public LocalDate getFecha(){
        return this.fecha;
    }
    public void setTotal(double total){
        this.total = total;
    }
    public double getTotal(){
        return this.total;
    }
    //METODOS
    @Override
    public String toString(){
        return "Venta [ID:" + getCodigoVenta()+ 
                "\nCliente ID: " + getCodigoCliente()+
                "\nFecha: " + getFecha()+
                "\nTotal: " + getTotal()+ "]\n\n";
    }
}
