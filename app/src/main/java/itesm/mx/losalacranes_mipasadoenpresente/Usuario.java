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
package itesm.mx.losalacranes_mipasadoenpresente;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Carlos on 30/03/2017.
 */

public class Usuario implements Serializable {
    private long idUsuario;
    private String nombre;
    private String apellido;
    private int edad;
    private String fechaNacimiento;
    private String lugarNacimiento;
    private String estadoCivil;
    private int nietos;
    private int hijos;
    private byte[] imagen;

    public Usuario() {
        this.idUsuario = 0;
        this.nombre = null;
        this.apellido = null;
        this.edad = 0;
        this.fechaNacimiento = null;
        this.lugarNacimiento = null;
        this.estadoCivil = null;
        this.nietos = 0;
        this.hijos = 0;
        this.imagen = null;
    }

    public Usuario(long idUsuario, String nombre, String apellido, int edad, String fechaNacimiento,
                   String lugarNacimiento, String estadoCivil, int nietos, int hijos, byte[] imagen) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.estadoCivil = estadoCivil;
        this.nietos = nietos;
        this.hijos = hijos;
        this.imagen = imagen;
    }


    public Usuario(String nombre, String apellido, int edad, String fechaNacimiento,
                   String lugarNacimiento, String estadoCivil, int nietos, int hijos, byte[] imagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
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

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }
}
