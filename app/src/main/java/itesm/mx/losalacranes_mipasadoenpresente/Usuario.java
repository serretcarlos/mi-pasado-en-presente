package itesm.mx.losalacranes_mipasadoenpresente;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Carlos on 30/03/2017.
 */

public class Usuario implements Serializable{
    private long idUsuario;
    private String nombre;
    private String apellido;
    private int edad;
    private String fechaNacimiento;
    private String estadoCivil;
    private int nietos;
    private int hijos;
    private byte [] imagen;

    public Usuario() {
        this.idUsuario = 0;
        this.nombre = null;
        this.apellido = null;
        this.edad = 0;
        this.fechaNacimiento = null;
        this.estadoCivil = null;
        this.nietos = 0;
        this.hijos = 0;
        this.imagen = null;
    }

    public Usuario(long idUsuario, String nombre, String apellido, int edad, String fechaNacimiento,
                   String estadoCivil, int nietos, int hijos, byte[] imagen) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.nietos = nietos;
        this.hijos = hijos;
        this.imagen = imagen;
    }


    public Usuario(String nombre, String apellido, int edad, String fechaNacimiento,
                   String estadoCivil, int nietos, int hijos, byte[] imagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.nietos = nietos;
        this.hijos = hijos;
        this.imagen = imagen;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getNietos() {
        return nietos;
    }

    public void setNietos(int nietos) {
        this.nietos = nietos;
    }

    public int getHijos() {
        return hijos;
    }

    public void setHijos(int hijos) {
        this.hijos = hijos;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}


