/*Memorical
        Copyright (C) 2017

        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

package itesm.mx.losalacranes_mipasadoenpresente;

import java.io.Serializable;

/**
 * Created by Carlos on 30/03/2017.
 */

public class Persona implements Serializable {
    private long idPersona;
    private String nombre;
    private String apellido;
    private byte [] imagen;
    private String frase;
    private String relacion;
    private String sonido;
    //audio queda pendiente

    public Persona() {
        this.idPersona = 0;
        this.nombre = null;
        this.apellido = null;
        this.imagen = null;
        this.frase = null;
        this.relacion = null;
        this.sonido = null;
    }

    public Persona(String nombre, String apellido, byte[] imagen, String frase, String relacion, String sonido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.imagen = imagen;
        this.frase = frase;
        this.relacion = relacion;
        this.sonido = sonido;

    }

    public Persona(long idPersona, String nombre, String apellido, byte[] imagen, String frase, String relacion, String sonido) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.imagen = imagen;
        this.frase = frase;
        this.relacion = relacion;
        this.sonido = sonido;

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

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getSonido() {
        return sonido;
    }

    public void setSonido(String sonido) {
        this.sonido = sonido;
    }
}

