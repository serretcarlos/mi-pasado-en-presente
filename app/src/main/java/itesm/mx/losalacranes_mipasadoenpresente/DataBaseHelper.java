package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Carlos on 30/03/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RelacionMemoriaDB.db";

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    String CREATE_USUARIO_TABLE = "CREATE TABLE " + DataBaseSchema.UsuarioTable.TABLE_NAME + "(" +
            DataBaseSchema.UsuarioTable._ID + " INTEGER PRIMARY KEY," +
            DataBaseSchema.UsuarioTable.COLUMN_NAME_NOMBRE + " TEXT," +
            DataBaseSchema.UsuarioTable.COLUMN_NAME_APELLIDO + " TEXT," +
            DataBaseSchema.UsuarioTable.COLUMN_NAME_EDAD + " INT," +
            DataBaseSchema.UsuarioTable.COLUMN_NAME_FECHA_NACIMIENTO + " TEXT," +
            DataBaseSchema.UsuarioTable.COLUMN_NAME_ESTADO_CIVIL + " TEXT,"+
            DataBaseSchema.UsuarioTable.COLUMN_NAME_NIETOS + " INT,"+
            DataBaseSchema.UsuarioTable.COLUMN_NAME_HIJOS + " INT," +
            DataBaseSchema.UsuarioTable.COLUMN_NAME_HERMANOS + " INT," +
            DataBaseSchema.UsuarioTable.COLUMN_NAME_IMAGEN + " BLOB " +
            ")";

    String CREATE_PERSONA_TABLE = "CREATE TABLE " + DataBaseSchema.PersonaTable.TABLE_NAME + "(" +
            DataBaseSchema.PersonaTable._ID + " INTEGER PRIMARY KEY," +
            DataBaseSchema.PersonaTable.COLUMN_NAME_NOMBRE + " TEXT," +
            DataBaseSchema.PersonaTable.COLUMN_NAME_APELLIDO + " TEXT," +
            DataBaseSchema.PersonaTable.COLUMN_NAME_IMAGEN + " BLOB," +
            DataBaseSchema.PersonaTable.COLUMN_NAME_FRASE + " TEXT " +
            ")";

    String CREATE_EVENTO_TABLE = "CREATE TABLE " + DataBaseSchema.EventoTable.TABLE_NAME + "(" +
            DataBaseSchema.EventoTable._ID + " INTEGER PRIMARY KEY," +
            DataBaseSchema.EventoTable.COLUMN_NAME_TITULO + " TEXT," +
            DataBaseSchema.EventoTable.COLUMN_NAME_FECHA + " TEXT," +
            DataBaseSchema.EventoTable.COLUMN_NAME_DESCRIPCION + " TEXT," +
            DataBaseSchema.EventoTable.COLUMN_NAME_IMAGEN + " BLOB " +
            ")";

    String CREATE_RELACION_USUARIO_PERSONA_TABLE = "CREATE TABLE " +
            DataBaseSchema.RelacionUsuarioPersona.TABLE_NAME + "(" +
            DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_USUARIO + " INT," +
            DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_ID_PERSONA + " INT," +
            DataBaseSchema.RelacionUsuarioPersona.COLUMN_NAME_RELACION + " TEXT " +
            ")";

    String CREATE_RELACION_USUARIO_EVENTO_TABLE = "CREATE TABLE " +
            DataBaseSchema.RelacionUsuarioEvento.TABLE_NAME + "(" +
            DataBaseSchema.RelacionUsuarioEvento.COLUMN_NAME_ID_USUARIO + " INT," +
            DataBaseSchema.RelacionUsuarioEvento.COLUMN_NAME_ID_EVENTO + " INT," +
            DataBaseSchema.RelacionUsuarioEvento.COLUMN_NAME_TIPO + " TEXT " +
            ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USUARIO_TABLE);
        db.execSQL(CREATE_PERSONA_TABLE);
        db.execSQL(CREATE_EVENTO_TABLE);
        db.execSQL(CREATE_RELACION_USUARIO_PERSONA_TABLE);
        db.execSQL(CREATE_RELACION_USUARIO_EVENTO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseSchema.UsuarioTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseSchema.PersonaTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseSchema.EventoTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseSchema.RelacionUsuarioPersona.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseSchema.RelacionUsuarioEvento.TABLE_NAME);
        onCreate(db);
    }
}
