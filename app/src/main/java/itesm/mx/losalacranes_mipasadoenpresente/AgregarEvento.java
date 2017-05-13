package itesm.mx.losalacranes_mipasadoenpresente;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaRecorder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AgregarEvento extends AppCompatActivity implements View.OnClickListener{

    EditText etTitulo;
    EditText etFecha;
    EditText etDesc;
    TextView tvCancelar;
    ImageView ivFoto;
    Button btnGuardar;
    Button btnFoto;
    GlobalUserClass globalUser;

    //////////////////////////--AUDIO--/////////////////////////
    boolean mStartRecording = true;
    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String mFileName = null;

    private Button RecordButton;
    private MediaRecorder mRecorder = null;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    //////////////////////////--AUDIO--/////////////////////////

    DataBaseOperations dao;

    int REQUEST_CODE = 1;
    int validaFoto;
    byte[] foto = null;
    long idUsuario;
    Usuario usuarioActual;
    String tipo;
    int indexAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_evento);
        globalUser = (GlobalUserClass) getApplicationContext();
        indexAudio = globalUser.getSoundIndex();
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
        ivFoto = (ImageView) findViewById(R.id.image_evento);

        //////////////////////////--AUDIO--/////////////////////////
        RecordButton = (Button) findViewById(R.id.button_grabar_evento);
        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/" + "audio" + idUsuario + indexAudio + ".3gp";
        // mFileName = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + "raw"+ File.separator + "myFile.3gp";
        //mFileName += "/audiorecordtest.3gp";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        RecordButton.setOnClickListener(this);
        //////////////////////////--AUDIO--/////////////////////////

        tvCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnFoto.setOnClickListener(this);

        validaFoto = 0;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Evento evento;
        switch (v.getId()) {
            case R.id.button_guardar_evento:
                if(validaCampos()){
                    evento = agregarEvento();
                    intent = new Intent();
                    intent.putExtra("evento", evento);
                    dao.close();
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.text_cancelar_evento:
                Toast.makeText(getApplicationContext(), "Operacion Cancelada", Toast.LENGTH_SHORT).show();
                dao.close();
                finish();
                break;
            /////////////////////////--AUDIO--/////////////////////////
            case R.id.button_grabar_evento:

                onRecord(mStartRecording);
                if (mStartRecording) {
                    RecordButton.setText("Parar");
                } else {
                    RecordButton.setText("Grabar Sonido");
                }
                mStartRecording = !mStartRecording;

                break;
            /////////////////////////--AUDIO--/////////////////////////
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
            ivFoto.setImageBitmap(bitmap);
            validaFoto = 1;
        }
    }

    public boolean validaCampos(){
        boolean flag = true;
        if(etTitulo.getText().toString().equals("")){
            flag = false;
        }

        if(etFecha.getText().toString().equals("")){
            flag = false;
        }

        if(etFecha.getText().toString().equals("")){
            flag = false;
        }

        if(etDesc.getText().toString().equals("")){
            flag = false;
        }

        if(validaFoto == 0){
            flag = false;
        }

        return flag;
    }

    public Evento agregarEvento(){
        Evento evento = new Evento();
        String titulo = etTitulo.getText().toString();
        String fecha = etFecha.getText().toString();
        String desc = etDesc.getText().toString();
        ((GlobalUserClass) this.getApplication()).setSoundIndex(indexAudio+1);
        String audio = mFileName;
        evento = new Evento(titulo, fecha, desc, foto, audio);
        long id = dao.addEvento(evento, idUsuario, tipo);
        evento.setIdEvento(id);
        return evento;
    }

    //////////////////////////--AUDIO--/////////////////////////

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;

        }
        if (!permissionToRecordAccepted ) finish();

    }
    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
        Toast.makeText(getApplicationContext(), "Grabando", Toast.LENGTH_SHORT).show();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        Toast.makeText(getApplicationContext(), "Sonido Guardado", Toast.LENGTH_SHORT).show();
        mRecorder = null;
    }



    //////////////////////////--AUDIO--/////////////////////////
}
