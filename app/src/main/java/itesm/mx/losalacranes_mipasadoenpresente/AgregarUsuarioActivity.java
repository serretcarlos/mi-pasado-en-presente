package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AgregarUsuarioActivity extends AppCompatActivity implements View.OnClickListener{


    EditText etNombre;
    EditText etApellido;
    EditText etEdad;
    EditText etLugarNacimiento;
    EditText etEstadoCivil;
    EditText etNumHijos;
    EditText etNumNietos;
    EditText etFecha;
    TextView tvCancelar;
    Button btnGuardar;
    Button btnFoto;

    DataBaseOperations dao;
    int REQUEST_CODE = 1;
    byte[] foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        dao = new DataBaseOperations(this);
        dao.open();
        tvCancelar = (TextView) findViewById(R.id.text_cancelar_usuario);
        btnGuardar = (Button) findViewById(R.id.button_guardar_usuario);
        btnFoto = (Button) findViewById(R.id.button_foto_usuario);

        etNombre = (EditText) findViewById(R.id.edit_nombre_usuario);
        etApellido = (EditText) findViewById(R.id.edit_apellido_usuario);
        etEdad = (EditText) findViewById(R.id.edit_edad_usuario);

        etFecha = (EditText) findViewById(R.id.edit_fecha_usuario);
        etLugarNacimiento = (EditText) findViewById(R.id.edit_lugar_nacimiento_usuario);
        etEstadoCivil = (EditText) findViewById(R.id.edit_estado_civil_usuario);
        etNumHijos = (EditText) findViewById(R.id.edit_hijos_usuario);
        etNumNietos = (EditText) findViewById(R.id.edit_nietos_usuario);
        tvCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnFoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Usuario usuario;
        switch (v.getId()) {
            case R.id.button_guardar_usuario:
                usuario = agregarUsuario();
                intent = new Intent();
                intent.putExtra("usuario", usuario);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.text_cancelar_usuario:
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.button_foto_usuario:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, REQUEST_CODE);
                }
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
        }
    }



    public Usuario agregarUsuario(){
        Usuario usuario = new Usuario();
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        int edad = Integer.parseInt(etEdad.getText().toString());
        String fecha = etFecha.getText().toString();
        String lugar = etLugarNacimiento.getText().toString();
        String estado = etEstadoCivil.getText().toString();
        int nietos = Integer.parseInt(etNumNietos.getText().toString());
        int hijos = Integer.parseInt(etNumHijos.getText().toString());
        usuario = new Usuario(nombre, apellido, edad, fecha, estado, nietos, hijos, foto);
        long id = dao.addUsuario(usuario);
        usuario.setIdUsuario(id);
        return usuario;
    }
}
