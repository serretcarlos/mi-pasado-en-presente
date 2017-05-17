package itesm.mx.losalacranes_mipasadoenpresente;

import android.provider.BaseColumns;
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

public final class DataBaseSchema {
    private DataBaseSchema() { }

    public static class UsuarioTable implements BaseColumns {
        public static final String TABLE_NAME = "Usuario";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_EDAD = "edad";
        public static final String COLUMN_NAME_FECHA_NACIMIENTO = "fechaNacimiento";
        public static final String COLUMN_NAME_LUGAR_NACIMIENTO = "lugarNacimiento";
        public static final String COLUMN_NAME_ESTADO_CIVIL = "estadoCivil";
        public static final String COLUMN_NAME_NIETOS = "nietos";
        public static final String COLUMN_NAME_HIJOS = "hijos";
        public static final String COLUMN_NAME_IMAGEN = "imagen";
    }

    public static class PersonaTable implements BaseColumns {
        public static final String TABLE_NAME = "Persona";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_IMAGEN = "imagen";
        public static final String COLUMN_NAME_FRASE = "frase";
        public static final String COLUMN_NAME_TIPO = "tipo";
        public static final String COLUMN_NAME_SONIDO = "sonido";
    }

    public static class EventoTable implements  BaseColumns {
        public static final String TABLE_NAME = "Evento";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_FECHA = "fecha";
        public static final String COLUMN_NAME_DESCRIPCION = "descripcion";
        public static final String COLUMN_NAME_IMAGEN = "imagen";
        public static final String COLUMN_NAME_SONIDO= "sonido";
    }

    public static class RelacionUsuarioPersona implements BaseColumns {
        public static final String TABLE_NAME = "RelacionUsuarioPersona";
        public static final String COLUMN_NAME_ID_USUARIO = "idUsuario";
        public static final String COLUMN_NAME_ID_PERSONA = "idPersona";
        public static final String COLUMN_NAME_RELACION = "relacion";
    }

    public static class RelacionUsuarioEvento implements BaseColumns {
        public static final String TABLE_NAME = "RelacionUsuarioEvento";
        public static final String COLUMN_NAME_ID_USUARIO = "idUsuario";
        public static final String COLUMN_NAME_ID_EVENTO = "idEvento";
        public static final String COLUMN_NAME_TIPO = "tipo";
    }
}
