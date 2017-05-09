package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Carlos on 30/03/2017.
 */

public class DataBaseOperations {

    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;
    private Usuario usuario;
    private Persona persona;
    private Evento evento;

    public DataBaseOperations(Context context){
        dbHelper = new DataBaseHelper(context);
    }

    public void open() throws SQLException{
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e){
            Log.e("SQLOPEN", e.toString());
        }
    }

    public void close(){
        db.close();
    }


    /*
    * Operaciones para usuario
     */

    public long addUsuario(Usuario usuario){
        long newID = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_NOMBRE, usuario.getNombre());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_APELLIDO, usuario.getApellido());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_EDAD, usuario.getEdad());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_FECHA_NACIMIENTO, usuario.getFechaNacimiento());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_LUGAR_NACIMIENTO, usuario.getLugarNacimiento());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_ESTADO_CIVIL, usuario.getEstadoCivil());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_NIETOS, usuario.getNietos());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_HIJOS, usuario.getHijos());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_IMAGEN, usuario.getImagen());
            newID = db.insert(DataBaseSchema.UsuarioTable.TABLE_NAME, null, values);
        } catch(SQLiteException e){
            Log.e("SQLADD", e.toString());
        }
        return newID;
    }

    public Usuario findUsuario(long id){
        String query = "Select * FROM " +
                DataBaseSchema.UsuarioTable.TABLE_NAME +
                " WHERE " + DataBaseSchema.UsuarioTable._ID +
                " = \"" + id + "\"";
        try  {
            Cursor cursor = db.rawQuery(query, null);
            usuario = null;
            if (cursor.moveToFirst()){
                usuario = new Usuario(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        Integer.parseInt(cursor.getString(7)),
                        Integer.parseInt(cursor.getString(8)),
                        cursor.getBlob(9));
            }
            cursor.close();
        } catch (SQLiteException e){
            Log.e("SQLFIND", e.toString());
        }
        return usuario;
    }

    public boolean deleteUsuario(long id){
        boolean result = false;
        String query = "Select * FROM " + DataBaseSchema.UsuarioTable.TABLE_NAME +
                " Where " + DataBaseSchema.UsuarioTable._ID + " = " + id;
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()){
                int idUser = Integer.parseInt(cursor.getString(0));
                db.delete(DataBaseSchema.UsuarioTable.TABLE_NAME,
                        DataBaseSchema.UsuarioTable._ID + " = ?",
                        new String[]{String.valueOf(idUser)});
                db.execSQL("Delete from " + DataBaseSchema.PersonaTable.TABLE_NAME + " WHERE " +
                        DataBaseSchema.PersonaTable._ID + " IN ( SELECT " +
                        DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_PERSONA + " FROM " +
                        DataBaseSchema.RelacionUsuarioPersona.TABLE_NAME + " WHERE " +
                        DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_USUARIO + " = " + id);
                db.execSQL("DELETE FROM " + DataBaseSchema.RelacionUsuarioPersona.TABLE_NAME +
                        "WHERE " + DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_USUARIO +
                        " = " + id);
                result = true;
            }
            cursor.close();
        } catch (SQLiteException e){
            Log.e("SQLDELETE", e.toString());
        }
        return result;
    }

    public ArrayList<Usuario> getAllUsers() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DataBaseSchema.UsuarioTable.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()){
                do {
                    usuario = new Usuario(Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            Integer.parseInt(cursor.getString(3)),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            Integer.parseInt(cursor.getString(7)),
                            Integer.parseInt(cursor.getString(8)),
                            cursor.getBlob(9));
                    listaUsuarios.add(usuario);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e){
            Log.e("SQLList", e.toString());
        }
        return listaUsuarios;
    }

    public boolean updateUsuario(Usuario usuario){
        boolean result = false;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_NOMBRE, usuario.getNombre());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_APELLIDO, usuario.getApellido());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_EDAD, usuario.getEdad());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_FECHA_NACIMIENTO, usuario.getFechaNacimiento());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_LUGAR_NACIMIENTO, usuario.getLugarNacimiento());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_ESTADO_CIVIL, usuario.getEstadoCivil());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_NIETOS, usuario.getNietos());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_HIJOS, usuario.getHijos());
            values.put(DataBaseSchema.UsuarioTable.COLUMN_NAME_IMAGEN, usuario.getImagen());
            db.update(DataBaseSchema.UsuarioTable.TABLE_NAME, values, DataBaseSchema.UsuarioTable._ID +
                    " = ? ", new String[]{String.valueOf(usuario.getIdUsuario())});
            result = true;
        } catch (SQLiteException e){
            Log.e("SQLUPDATE", e.toString());
        }
        return result;
    }









    /*
    *  OPERACIONES PARA PERSONA
     */

    public long addPersona(Persona persona, long idUsuario, String tipo){
        long newID = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.PersonaTable.COLUMN_NAME_NOMBRE, persona.getNombre());
            values.put(DataBaseSchema.PersonaTable.COLUMN_NAME_APELLIDO, persona.getApellido());
            values.put(DataBaseSchema.PersonaTable.COLUMN_NAME_IMAGEN, persona.getImagen());
            values.put(DataBaseSchema.PersonaTable.COLUMN_NAME_FRASE, persona.getFrase());
            values.put(DataBaseSchema.PersonaTable.COLUMN_NAME_TIPO, persona.getRelacion());
            newID = db.insert(DataBaseSchema.PersonaTable.TABLE_NAME, null, values);
            AgregarRelacionPersona(idUsuario, newID, tipo);
        } catch(SQLiteException e){
            Log.e("SQLADD", e.toString());
        }
        return newID;
    }

    public Persona findPersona(long id){
        String query = "Select * FROM " +
                DataBaseSchema.PersonaTable.TABLE_NAME +
                " WHERE " + DataBaseSchema.PersonaTable._ID +
                " = \"" + id + "\"";
        try  {
            Cursor cursor = db.rawQuery(query, null);
            persona = null;
            if (cursor.moveToFirst()){
                persona = new Persona(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getBlob(3),
                        cursor.getString(4),
                        cursor.getString(5));
            }
            cursor.close();
        } catch (SQLiteException e){
            Log.e("SQLFIND", e.toString());
        }
        return persona;
    }


    public boolean deletePersona(long id){
        boolean result = false;
        String query = "Select * FROM " + DataBaseSchema.PersonaTable.TABLE_NAME +
                " Where " + DataBaseSchema.PersonaTable._ID +
                " = " + id;
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()){
                int idPersona = Integer.parseInt(cursor.getString(0));
                db.delete(DataBaseSchema.RelacionUsuarioPersona.TABLE_NAME,
                        DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_PERSONA + " = ?",
                        new String[]{String.valueOf(idPersona)});
                db.delete(DataBaseSchema.PersonaTable.TABLE_NAME,
                        DataBaseSchema.PersonaTable._ID + " = ?",
                        new String[]{String.valueOf(idPersona)});
                result = true;
            }
            cursor.close();
        } catch (SQLiteException e){
            Log.e("SQLDELETE", e.toString());
        }
        return result;
    }

    public ArrayList<Persona> getAllAmigos(long idUsuario) {
        ArrayList<Persona> listaAmigos = new ArrayList<>();
        String selectQuery = "SELECT " + DataBaseSchema.PersonaTable._ID + ", " +
                DataBaseSchema.PersonaTable.COLUMN_NAME_NOMBRE + ", " +
                DataBaseSchema.PersonaTable.COLUMN_NAME_APELLIDO + ", " +
                DataBaseSchema.PersonaTable.COLUMN_NAME_IMAGEN + ", " +
                DataBaseSchema.PersonaTable.COLUMN_NAME_FRASE + ", " +
                DataBaseSchema.PersonaTable.COLUMN_NAME_TIPO +
                " FROM " + DataBaseSchema.PersonaTable.TABLE_NAME + ", " +
                DataBaseSchema.RelacionUsuarioPersona.TABLE_NAME +
                " WHERE " + DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_USUARIO + " = " +
                idUsuario + " AND " + DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_RELACION +
                " = \"Amigo\" AND " + DataBaseSchema.PersonaTable._ID + " = " +
                DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_PERSONA;
        Log.d("getallamig", selectQuery);
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()){
                do {
                    persona = new Persona(Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getBlob(3),
                            cursor.getString(4),
                            cursor.getString(5));
                    listaAmigos.add(persona);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e){
            Log.e("SQLList", e.toString());
        }
        return listaAmigos;
    }

    public ArrayList<Persona> getAllFamiliares(long id) {
        ArrayList<Persona> listaFamiliares = new ArrayList<>();
        String selectQuery = "SELECT " + DataBaseSchema.PersonaTable._ID + ", " +
                DataBaseSchema.PersonaTable.COLUMN_NAME_NOMBRE + ", " +
                DataBaseSchema.PersonaTable.COLUMN_NAME_APELLIDO + ", " +
                DataBaseSchema.PersonaTable.COLUMN_NAME_IMAGEN + ", " +
                DataBaseSchema.PersonaTable.COLUMN_NAME_FRASE + ", " +
                DataBaseSchema.PersonaTable.COLUMN_NAME_TIPO +
                " FROM " + DataBaseSchema.PersonaTable.TABLE_NAME + ", " +
                DataBaseSchema.RelacionUsuarioPersona.TABLE_NAME +
                " WHERE " + DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_USUARIO + " = " +
                id + " AND " + DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_RELACION +
                " != \"Amigo\" AND " + DataBaseSchema.PersonaTable._ID + " = " +
                DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_PERSONA;

        Log.d("getallfam", selectQuery);
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()){
                do {
                    persona = new Persona(Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getBlob(3),
                            cursor.getString(4),
                            cursor.getString(5));
                    listaFamiliares.add(persona);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e){
            Log.e("SQLList", e.toString());
        }
        return listaFamiliares;
    }


    public boolean updatePersona(Persona persona){
        boolean result = false;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.PersonaTable.COLUMN_NAME_NOMBRE, persona.getNombre());
            values.put(DataBaseSchema.PersonaTable.COLUMN_NAME_APELLIDO, persona.getApellido());
            values.put(DataBaseSchema.PersonaTable.COLUMN_NAME_IMAGEN, persona.getImagen());
            values.put(DataBaseSchema.PersonaTable.COLUMN_NAME_FRASE, persona.getFrase());
            values.put(DataBaseSchema.PersonaTable.COLUMN_NAME_TIPO, persona.getRelacion());
            db.update(DataBaseSchema.PersonaTable.TABLE_NAME, values,
                    DataBaseSchema.PersonaTable._ID +
                            " = ? ", new String[]{String.valueOf(persona.getIdPersona())});
            result = true;
        } catch (SQLiteException e){
            Log.e("SQLUPDATE", e.toString());
        }
        return result;
    }










    /*
    OPERACIONES PARA EVENTOS
     */

    public long addEvento(Evento evento, long idUsuario, String tipoEvento){
        long newID = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.EventoTable.COLUMN_NAME_TITULO, evento.getTitulo());
            values.put(DataBaseSchema.EventoTable.COLUMN_NAME_FECHA, evento.getFecha());
            values.put(DataBaseSchema.EventoTable.COLUMN_NAME_DESCRIPCION, evento.getDescripcion());
            values.put(DataBaseSchema.EventoTable.COLUMN_NAME_IMAGEN, evento.getImagen());
            newID = db.insert(DataBaseSchema.EventoTable.TABLE_NAME, null, values);
            AgregarRelacionEvento(idUsuario, newID, tipoEvento);
        } catch(SQLiteException e){
            Log.e("SQLADD", e.toString());
        }
        return newID;
    }

    public Persona findEvento(long id){
        String query = "Select * FROM " +
                DataBaseSchema.EventoTable.TABLE_NAME +
                " WHERE " + DataBaseSchema.EventoTable._ID +
                " = \"" + id + "\"";
        try  {
            Cursor cursor = db.rawQuery(query, null);
            evento = null;
            if (cursor.moveToFirst()){
                evento = new Evento(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getBlob(4));
            }
            cursor.close();
        } catch (SQLiteException e){
            Log.e("SQLFIND", e.toString());
        }
        return persona;
    }

    public boolean deleteEvento(long id){
        boolean result = false;
        String query = "Select * FROM " + DataBaseSchema.EventoTable.TABLE_NAME +
                " Where " + DataBaseSchema.EventoTable._ID + " = " + id;
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()){
                int idEvento = Integer.parseInt(cursor.getString(0));
                db.delete(DataBaseSchema.EventoTable.TABLE_NAME,
                        DataBaseSchema.EventoTable._ID + " = ?",
                        new String[]{String.valueOf(idEvento)});
                db.delete(DataBaseSchema.RelacionUsuarioEvento.TABLE_NAME,
                        DataBaseSchema.RelacionUsuarioEvento.COLUMN_NAME_ID_EVENTO + " = ?",
                        new String[]{String.valueOf(idEvento)});
                result = true;
            }
            cursor.close();
        } catch (SQLiteException e){
            Log.e("SQLDELETE", e.toString());
        }
        return result;
    }

    public ArrayList<Evento> getAllEventos(long idUsuario, String tipo) {
        ArrayList<Evento> listaEventos = new ArrayList<>();
        String selectQuery = "SELECT " + DataBaseSchema.EventoTable._ID + ", " +
                DataBaseSchema.EventoTable.COLUMN_NAME_TITULO + ", " +
                DataBaseSchema.EventoTable.COLUMN_NAME_FECHA + ", " +
                DataBaseSchema.EventoTable.COLUMN_NAME_DESCRIPCION + ", " +
                DataBaseSchema.EventoTable.COLUMN_NAME_IMAGEN +
                " FROM " + DataBaseSchema.EventoTable.TABLE_NAME + ", " +
                DataBaseSchema.RelacionUsuarioEvento.TABLE_NAME +
                " WHERE " + DataBaseSchema.RelacionUsuarioEvento.COLUMN_NAME_ID_USUARIO + " = " +
                idUsuario + " AND " + DataBaseSchema.RelacionUsuarioEvento.COLUMN_NAME_TIPO +
                " = \"" + tipo +"\" AND " + DataBaseSchema.EventoTable._ID + " = " +
                DataBaseSchema.RelacionUsuarioEvento.COLUMN_NAME_ID_EVENTO;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()){
                do {
                    evento = new Evento(Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getBlob(4));
                    listaEventos.add(evento);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e){
            Log.e("SQLList", e.toString());
        }
        return listaEventos;
    }




    public boolean updateEvento(Evento evento){
        boolean result = false;
        try {
            ContentValues values = new ContentValues();
            values.put(DataBaseSchema.EventoTable.COLUMN_NAME_TITULO, evento.getTitulo());
            values.put(DataBaseSchema.EventoTable.COLUMN_NAME_FECHA, evento.getFecha());
            values.put(DataBaseSchema.EventoTable.COLUMN_NAME_IMAGEN, evento.getImagen());
            values.put(DataBaseSchema.EventoTable.COLUMN_NAME_DESCRIPCION, evento.getDescripcion());
            db.update(DataBaseSchema.EventoTable.TABLE_NAME, values, DataBaseSchema.EventoTable._ID +
                    " = ? ", new String[]{String.valueOf(evento.getIdEvento())});
            result = true;
        } catch (SQLiteException e){
            Log.e("SQLUPDATE", e.toString());
        }
        return result;
    }



    public void AgregarRelacionPersona(long idUsuario, long idPersona, String relacion){
        ContentValues values = new ContentValues();
        values.put(DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_USUARIO, idUsuario);
        values.put(DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_PERSONA, idPersona);
        values.put(DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_RELACION, relacion);
        db.insert(DataBaseSchema.RelacionUsuarioPersona.TABLE_NAME, null, values);
    }



    public void AgregarRelacionEvento(long idUsuario, long idEvento, String tipo){
        ContentValues values = new ContentValues();
        values.put(DataBaseSchema.RelacionUsuarioEvento.COLUMN_NAME_ID_USUARIO, idUsuario);
        values.put(DataBaseSchema.RelacionUsuarioEvento.COLUMN_NAME_ID_EVENTO, idEvento);
        values.put(DataBaseSchema.RelacionUsuarioEvento.COLUMN_NAME_TIPO, tipo);
        db.insert(DataBaseSchema.RelacionUsuarioEvento.TABLE_NAME, null, values);
    }

}
