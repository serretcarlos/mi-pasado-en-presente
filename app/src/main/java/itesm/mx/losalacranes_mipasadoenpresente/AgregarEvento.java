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

public class AgregarEvento extends AppCompatActivity implements View.OnClickListener{

    EditText etTitulo;
    EditText etFecha;
    EditText etDesc;
    TextView tvCancelar;
    Button btnGuardar;
    Button btnFoto;

    DataBaseOperations dao;

    int REQUEST_CODE = 1;
    byte[] foto = null;
    long idUsuario;
    Usuario usuarioActual;
    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_evento);
        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        usuarioActual = globalUser.getUser();
        idUsuario = usuarioActual.getIdUsuario();

        Intent intent = this.getIntent();
        tipo = intent.getStringExtra("Tipo");

        dao = new DataBaseOperations(this);
        dao.open();
        tvCancelar = (TextView) findViewById(R.id.text_cancelar_evento);
        btnGuardar = (Button) findViewById(R.id.button_guardar_evento);
        btnFoto = (Button) findViewById(R.id.button_foto_evento);

        etTitulo = (EditText) findViewById(R.id.edit_titulo_evento);
        etFecha = (EditText) findViewById(R.id.edit_fecha_evento);
        etDesc = (EditText) findViewById(R.id.edit_desc_evento);

        tvCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnFoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Evento evento;
        switch (v.getId()) {
            case R.id.button_guardar_evento:
                evento = agregarEvento();
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

    public Evento agregarEvento(){
        Evento evento = new Evento();
        String titulo = etTitulo.getText().toString();
        String fecha = etFecha.getText().toString();
        String desc = etDesc.getText().toString();
        evento = new Evento(titulo, fecha, desc, foto);
        long id = dao.addEvento(evento, idUsuario, tipo);
        evento.setIdEvento(id);
        return evento;
    }
}
