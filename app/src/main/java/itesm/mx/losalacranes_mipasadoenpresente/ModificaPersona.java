package itesm.mx.losalacranes_mipasadoenpresente;

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

public class ModificaPersona extends AppCompatActivity implements View.OnClickListener{

    EditText etNombre;
    EditText etApellido;
    EditText etFrase;
    EditText etRelacion;
    TextView tvCancelar;
    ImageView ivFoto;
    Button btnGuardar;
    Button btnFoto;

    DataBaseOperations dao;

    int REQUEST_CODE = 1;
    int validaFoto = 0;
    byte[] foto = null;
    Persona persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_persona);

        Intent intent = getIntent();
        persona = (Persona) intent.getSerializableExtra("persona");
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        dao = new DataBaseOperations(this);
        dao.open();
        tvCancelar = (TextView) findViewById(R.id.text_cancelar_persona);
        btnGuardar = (Button) findViewById(R.id.button_guardar_persona);
        btnFoto = (Button) findViewById(R.id.button_foto_persona);

        etNombre = (EditText) findViewById(R.id.edit_nombre_persona);
        etApellido = (EditText) findViewById(R.id.edit_apellido_persona);
        etFrase = (EditText) findViewById(R.id.edit_frase_persona);
        etRelacion = (EditText) findViewById(R.id.edit_tipo_persona);
        ivFoto = (ImageView) findViewById(R.id.image_persona);

        tvCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnFoto.setOnClickListener(this);

        etNombre.setText(persona.getNombre());
        etApellido.setText(persona.getApellido());
        etFrase.setText(String.valueOf(persona.getFrase()));
        etRelacion.setText(persona.getRelacion());

        foto = persona.getImagen();
        Bitmap bmImage = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        ivFoto.setImageBitmap(bmImage);
    }



    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_guardar_persona:
                modificaPersona();
                Toast.makeText(getApplicationContext(), "La persona se ha modificado existosamente", Toast.LENGTH_SHORT).show();
                intent = new Intent();
                intent.putExtra("persona", persona);
                setResult(RESULT_OK, intent);
                finish();
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
            case R.id.menu_eliminar:
                dao.deletePersona(persona.getIdPersona());
                Toast.makeText(getApplicationContext(), "La persona se ha eliminado existosamente", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
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

    public void modificaPersona(){
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String frase = etFrase.getText().toString();
        String relacion = etRelacion.getText().toString();

        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setFrase(frase);
        persona.setRelacion(relacion);
        persona.setImagen(foto);
        dao.updatePersona(persona);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            dao.close();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
