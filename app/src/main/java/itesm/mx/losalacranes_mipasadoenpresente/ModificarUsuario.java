package itesm.mx.losalacranes_mipasadoenpresente;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ModificarUsuario extends AppCompatActivity implements View.OnClickListener{

    EditText etNombre;
    EditText etApellido;
    EditText etEdad;
    EditText etLugarNacimiento;
    EditText etEstadoCivil;
    EditText etNumHijos;
    EditText etNumNietos;
    EditText etFecha;
    TextView tvCancelar;
    ImageView ivFoto;
    Button btnGuardar;
    Button btnFoto;
    Button btnEliminar;

    DataBaseOperations dao;
    Usuario usuarioActual;

    int REQUEST_CODE = 1;
    int validaFoto = 0;
    int eliminarUsuario = 0;
    byte[] foto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        usuarioActual = globalUser.getUser();

        dao = new DataBaseOperations(this);
        dao.open();
        tvCancelar = (TextView) findViewById(R.id.text_cancelar_usuario);
        btnGuardar = (Button) findViewById(R.id.button_guardar_usuario);
        btnFoto = (Button) findViewById(R.id.button_foto_usuario);
        btnEliminar = (Button) findViewById(R.id.button_eliminar);

        etNombre = (EditText) findViewById(R.id.edit_nombre_usuario);
        etApellido = (EditText) findViewById(R.id.edit_apellido_usuario);
        etEdad = (EditText) findViewById(R.id.edit_edad_usuario);
        etFecha = (EditText) findViewById(R.id.edit_fecha_usuario);
        etLugarNacimiento = (EditText) findViewById(R.id.edit_lugar_nacimiento_usuario);
        etEstadoCivil = (EditText) findViewById(R.id.edit_estado_civil_usuario);
        etNumHijos = (EditText) findViewById(R.id.edit_hijos_usuario);
        etNumNietos = (EditText) findViewById(R.id.edit_nietos_usuario);
        ivFoto = (ImageView) findViewById(R.id.image_usuario);

        tvCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnFoto.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

        etNombre.setText(usuarioActual.getNombre());
        etApellido.setText(usuarioActual.getApellido());
        etEdad.setText(String.valueOf(usuarioActual.getEdad()));
        etFecha.setText(usuarioActual.getFechaNacimiento());
        etLugarNacimiento.setText(usuarioActual.getLugarNacimiento());
        etEstadoCivil.setText(usuarioActual.getEstadoCivil());
        etNumHijos.setText(String.valueOf(usuarioActual.getHijos()));
        etNumNietos.setText(String.valueOf(usuarioActual.getNietos()));

        foto = usuarioActual.getImagen();
        Bitmap bmImage = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        ivFoto.setImageBitmap(bmImage);
    }

    @Override
    public void onResume(){
        super.onResume();

        if(eliminarUsuario == 1){
            dao.deleteUsuario(usuarioActual.getIdUsuario());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_elimina, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_eliminar:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Eliminar persona")
                        .setMessage("¿Está seguro que desea eliminar esta persona de sus relaciones? Los cambios serán permanentes.")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.deleteUsuario(usuarioActual.getIdUsuario());
                                Toast.makeText(getApplicationContext(), "Este usuario ha sido eliminado.", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
        }
        if(item.getItemId() == android.R.id.home){
            finish();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_guardar_usuario:
                modificaUsuario();
                Toast.makeText(getApplicationContext(), "El usuario se ha modificado existosamente", Toast.LENGTH_SHORT).show();
                intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.text_cancelar_usuario:
                Toast.makeText(getApplicationContext(), "Operación cancelada", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.button_foto_usuario:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
            case R.id.button_eliminar:
                eliminaPopup();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            foto = stream.toByteArray();
            ivFoto.setImageBitmap(bitmap);
            validaFoto = 1;
        }
    }

    public void modificaUsuario(){
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        int edad = Integer.parseInt(etEdad.getText().toString());
        String fecha = etFecha.getText().toString();
        String lugar = etLugarNacimiento.getText().toString();
        String estado = etEstadoCivil.getText().toString();
        int nietos = Integer.parseInt(etNumNietos.getText().toString());
        int hijos = Integer.parseInt(etNumHijos.getText().toString());

        usuarioActual.setNombre(nombre);
        usuarioActual.setApellido(apellido);
        usuarioActual.setEdad(edad);
        usuarioActual.setFechaNacimiento(fecha);
        usuarioActual.setLugarNacimiento(lugar);
        usuarioActual.setEstadoCivil(estado);
        usuarioActual.setNietos(nietos);
        usuarioActual.setHijos(hijos);
        usuarioActual.setImagen(foto);
        dao.updateUsuario(usuarioActual);

    }

    public void eliminaPopup(){
        btnEliminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                AlertDialog.Builder builder = new AlertDialog.Builder(ModificarUsuario.this);
                builder.setTitle("Eliminar?");
                builder.setMessage("¿Está seguro que desea eliminar este usuario?");
                builder.setCancelable(false);

                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dao.deleteUsuario(usuarioActual.getIdUsuario());
                        eliminarUsuario = 1;
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Toast.makeText(getApplicationContext(), "Operación cancelada", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

}
