package itesm.mx.losalacranes_mipasadoenpresente;

import java.io.Serializable;
import java.util.Date;

/*
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
