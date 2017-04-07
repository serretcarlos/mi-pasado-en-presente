package itesm.mx.losalacranes_mipasadoenpresente;

import java.util.Date;

/**
 * Created by Carlos on 30/03/2017.
 */

public class Evento {
    private long idEvento;
    private String titulo;
    private String fecha;
    private String descripcion;
    private byte [] imagen;

    public Evento() {
        this.idEvento = 0;
        this.titulo = null;
        this.fecha = null;
        this.descripcion = null;
        this.imagen = null;
    }

    public Evento(String titulo, String fecha, String descripcion, byte[] imagen) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Evento(long idEvento, String titulo, String fecha, String descripcion, byte[] imagen) {
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
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
}
