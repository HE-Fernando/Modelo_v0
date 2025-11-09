package modelo;
public class Cliente {
    private int codigoCliente;
    private String nombreCliente;
    private String usuario;
    private String contraseña;
    private String direccion;
    private String telefono;
    //CONSTRUCTORES
    public Cliente(){}
    public Cliente(String nombreCliente, String usuario, String contraseña, String direccion, String telefono){
        this.nombreCliente = nombreCliente;
        this.usuario = usuario;
        this.contraseña = contraseña;
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
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    public String getUsuario(){
        return this.usuario;
    }
    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }
    public String getContraseña(){
        return this.contraseña;
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
    @Override
    public String toString(){
        return "Cliente [ID:" + getCodigoCliente() + 
                "\nNombre: " + getNombreCliente() +
                "\nUsuario: " + getUsuario() +
                "\nContraseña: " + getContraseña() +
                "\nDirección: " + getDireccion() +
                "\nTeléfono: " + getTelefono() + "]\n\n";
    }
}
