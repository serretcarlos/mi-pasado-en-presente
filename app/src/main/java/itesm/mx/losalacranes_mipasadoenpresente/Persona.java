package itesm.mx.losalacranes_mipasadoenpresente;

/**
 * Created by Carlos on 30/03/2017.
 */

public class Persona {
    private long idPersona;
    private String nombre;
    private String apellido;
    private byte [] imagen;
    private String frase;
    //audio queda pendiente

    public Persona() {
        this.idPersona = 0;
        this.nombre = null;
        this.apellido = null;
        this.imagen = null;
        this.frase = null;
    }

    public Persona(String nombre, String apellido, byte[] imagen, String frase) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.imagen = imagen;
        this.frase = frase;
    }

    public Persona(long idPersona, String nombre, String apellido, byte[] imagen, String frase) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.imagen = imagen;
        this.frase = frase;
    }

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }
}
