package itesm.mx.losalacranes_mipasadoenpresente;

import android.provider.BaseColumns;

/**
 * Created by Carlos on 30/03/2017.
 */

public final class DataBaseSchema {
    private DataBaseSchema() { }

    public static class UsuarioTable implements BaseColumns {
        public static final String TABLE_NAME = "Usuario";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_EDAD = "edad";
        public static final String COLUMN_NAME_FECHA_NACIMIENTO = "fechaNacimiento";
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
    }

    public static class EventoTable implements  BaseColumns {
        public static final String TABLE_NAME = "Evento";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_FECHA = "fecha";
        public static final String COLUMN_NAME_DESCRIPCION = "descripcion";
        public static final String COLUMN_NAME_IMAGEN = "imagen";
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
