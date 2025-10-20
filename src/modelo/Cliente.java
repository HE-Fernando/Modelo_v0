package src.modelo;
public class Cliente {
    private int codigoCliente;
    private String nombreCliente;
    private String direccion;
    private String telefono;
    //CONSTRUCTORES
    public Cliente(){}
    public Cliente(int codigoCliente, String nombreCliente, String direccion, String telefono){
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    //SET GET
    public void setCodigoCliente(int codigoCliente){
        this.codigoCliente = codigoCliente;
    }
    public int getCodigoCliente(){
        return this.codigoCliente;
    }
    public void setNombreCliente(String nombreCliente){
        this.nombreCliente = nombreCliente;
    }
    public String getNombreCliente(){
        return this.nombreCliente;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public String getDireccion(){
        return this.direccion;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public String getTelefono(){
        return this.telefono;
    }
    //METODOS
    public String toString(){
        return "DATOS";
    }
}
