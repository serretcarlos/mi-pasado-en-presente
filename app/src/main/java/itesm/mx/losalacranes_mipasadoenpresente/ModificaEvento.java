package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ModificaEvento extends AppCompatActivity implements View.OnClickListener{

    EditText etTitulo;
    EditText etFecha;
    EditText etDescripcion;
    TextView tvCancelar;
    ImageView ivFoto;
    Button btnGuardar;
    Button btnFoto;

    DataBaseOperations dao;

    int REQUEST_CODE = 1;
    int validaFoto = 0;
    byte[] foto = null;
    Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_evento);

        Intent intent = getIntent();
        evento = (Evento) intent.getSerializableExtra("evento");

        dao = new DataBaseOperations(this);
        dao.open();

        tvCancelar = (TextView) findViewById(R.id.text_cancelar_evento);
        btnGuardar = (Button) findViewById(R.id.button_guardar_evento);
        btnFoto = (Button) findViewById(R.id.button_foto_evento);

        etTitulo = (EditText) findViewById(R.id.edit_titulo_evento);
        etFecha = (EditText) findViewById(R.id.edit_fecha_evento);
        etDescripcion = (EditText) findViewById(R.id.edit_desc_evento);
        ivFoto = (ImageView) findViewById(R.id.image_evento);

        tvCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnFoto.setOnClickListener(this);

        etTitulo.setText(evento.getTitulo());
        etFecha.setText(evento.getFecha());
        etDescripcion.setText(evento.getDescripcion());

        foto = evento.getImagen();
        Bitmap bmImage = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        ivFoto.setImageBitmap(bmImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_elimina, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_guardar_evento:
                modificaEvento();
                Toast.makeText(getApplicationContext(), "EL evento se ha modificado existosamente", Toast.LENGTH_SHORT).show();
                intent = new Intent();
                intent.putExtra("evento", evento);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.text_cancelar_evento:
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.button_foto_evento:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
            case R.id.menu_eliminar:
                dao.deleteEvento(evento.getIdEvento());
                Toast.makeText(getApplicationContext(), "El evento se ha eliminado existosamente", Toast.LENGTH_SHORT).show();
                finish();
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

    public void modificaEvento(){
        String titulo = etTitulo.getText().toString();
        String fecha = etFecha.getText().toString();
        String descirpcion = etDescripcion.getText().toString();

        evento.setTitulo(titulo);
        evento.setFecha(fecha);
        evento.setDescripcion(descirpcion);
        evento.setImagen(foto);
        dao.updateEvento(evento);

    }
}