/*Memorical
        Copyright (C) 2017

        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

package itesm.mx.losalacranes_mipasadoenpresente;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaRecorder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.io.IOException;

public class ModificaPersona extends AppCompatActivity implements View.OnClickListener{

    EditText etNombre;
    EditText etApellido;
    EditText etFrase;
    EditText etRelacion;
    TextView tvCancelar;
    ImageView ivFoto;
    Button btnGuardar;
    Button btnFoto;

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
    int validaFoto = 0;
    int indexAudio;
    int grabarAudio = 0;
    String audioActual;
    byte[] foto = null;
    Persona persona;
    GlobalUserClass globalUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_persona);
        globalUser = (GlobalUserClass) getApplicationContext();
        indexAudio = globalUser.getSoundIndex();

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

        //////////////////////////--AUDIO--/////////////////////////
        RecordButton = (Button) findViewById(R.id.button_grabar);
        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/" + "audio" + globalUser.getUser().getIdUsuario() + indexAudio + ".3gp";
        // mFileName = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + "raw"+ File.separator + "myFile.3gp";
        //mFileName += "/audiorecordtest.3gp";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        RecordButton.setOnClickListener(this);
        //////////////////////////--AUDIO--/////////////////////////

        tvCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnFoto.setOnClickListener(this);

        etNombre.setText(persona.getNombre());
        etApellido.setText(persona.getApellido());
        etFrase.setText(String.valueOf(persona.getFrase()));
        etRelacion.setText(persona.getRelacion());

        audioActual = persona.getSonido();
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
            /////////////////////////--AUDIO--/////////////////////////
            case R.id.button_grabar:
                grabarAudio = 1;

                onRecord(mStartRecording);
                if (mStartRecording) {
                    RecordButton.setText("Parar");
                } else {
                    RecordButton.setText("Grabar Sonido");
                }
                mStartRecording = !mStartRecording;

                break;
            /////////////////////////--AUDIO--/////////////////////////
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

        if(grabarAudio == 1){
            persona.setSonido(mFileName);
            ((GlobalUserClass) this.getApplication()).setSoundIndex(indexAudio+1);
        } else {
            persona.setSonido(audioActual);
        }

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
