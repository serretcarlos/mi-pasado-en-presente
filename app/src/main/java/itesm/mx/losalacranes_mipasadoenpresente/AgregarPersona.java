package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AgregarPersona extends AppCompatActivity implements View.OnClickListener{

    EditText etNombre;
    EditText etApellido;
    EditText etRelacion;
    EditText etFrase;
    TextView tvCancelar;
    ImageView ivFoto;
    Button btnGuardar;
    Button btnFoto;

    DataBaseOperations dao;

    int REQUEST_CODE = 1;
    int validaFoto = 0;
    byte[] foto = null;
    long idUsuario;
    Usuario usuarioActual;
    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);
        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        usuarioActual = globalUser.getUser();
        idUsuario = usuarioActual.getIdUsuario();

        Intent intent = this.getIntent();
        tipo = intent.getStringExtra("Relacion");

        dao = new DataBaseOperations(this);
        dao.open();
        tvCancelar = (TextView) findViewById(R.id.text_cancelar_persona);
        btnGuardar = (Button) findViewById(R.id.button_guardar_persona);
        btnFoto = (Button) findViewById(R.id.button_foto_persona);
        ivFoto = (ImageView) findViewById(R.id.image_persona);

        etNombre = (EditText) findViewById(R.id.edit_nombre_persona);
        etApellido = (EditText) findViewById(R.id.edit_apellido_persona);
        etRelacion = (EditText) findViewById(R.id.edit_tipo_persona);
        etFrase = (EditText) findViewById(R.id.edit_frase_persona);

        tvCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnFoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Persona persona;
        switch (v.getId()) {
            case R.id.button_guardar_persona:

                if(validaCampos()){
                    persona = agregarPersona();
                    intent = new Intent();
                    intent.putExtra("persona", persona);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.text_cancelar_persona:
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.button_foto_persona:
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
            ivFoto.setImageBitmap(bitmap);
            validaFoto = 1;
        }
    }

    public boolean validaCampos(){
        boolean flag = true;
        if(etNombre.getText().toString().equals("")){
            flag = false;
        }

        if(etApellido.getText().toString().equals("")){
            flag = false;
        }

        if(etRelacion.getText().toString().equals("")){
            flag = false;
        }

        if(etFrase.getText().toString().equals("")){
            flag = false;
        }

        if(validaFoto == 0){
            flag = false;
        }

        return flag;
    }

    public Persona agregarPersona(){
        Persona persona = new Persona();
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String frase = etFrase.getText().toString();
        String relacion = etRelacion.getText().toString();
        persona = new Persona(nombre, apellido, foto, frase, relacion, null);
        long id = dao.addPersona(persona, idUsuario, tipo);
        persona.setIdPersona(id);
        return persona;
    }
}
