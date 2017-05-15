package itesm.mx.losalacranes_mipasadoenpresente;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Carlos on 30/03/2017.
 */

public class Evento implements Serializable {
    private long idEvento;
    private String titulo;
    private String fecha;
    private String descripcion;
    private byte [] imagen;
    private String sonido;

    public Evento() {
        this.idEvento = 0;
        this.titulo = null;
        this.fecha = null;
        this.descripcion = null;
        this.imagen = null;
        this.sonido = null;
    }

    public Evento(String titulo, String fecha, String descripcion, byte[] imagen, String sonido) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.sonido = sonido;
    }

    public Evento(long idEvento, String titulo, String fecha, String descripcion, byte[] imagen, String sonido) {
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.sonido = sonido;
    }

    public long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getSonido() {
        return sonido;
    }

    public void setSonido(String sonido) {
        this.sonido = sonido;
    }
}
