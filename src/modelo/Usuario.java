package src.modelo;

public class Usuario {
    private int codigoUsuario;
    private String nombre;
    private String contraseña;
    private String rol;
    //CONSTRUCTORES
    public Usuario(){}
    public Usuario(int codigoUsuario, String nombre, String contraseña, String rol){
        this.codigoUsuario = codigoUsuario;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rol = rol;
    }
    //GET SET
    public void setCodigoUsuario(int codigoUsuario){
        this.codigoUsuario = codigoUsuario;
    }
    public int getCodigoUsuario(){
        return this.codigoUsuario;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }
    public String getContraseña(){
        return this.contraseña;
    }
    public void setRol(String rol){
        this.rol = rol;
    }
    public String getRol(){
        return this.rol;
    }
    //METODOS
    public String toString(){
        return "DATOS";
    }
}

