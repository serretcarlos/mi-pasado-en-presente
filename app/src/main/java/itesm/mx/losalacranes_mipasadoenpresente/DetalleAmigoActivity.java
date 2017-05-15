package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DetalleAmigoActivity extends AppCompatActivity implements View.OnClickListener{

    private MediaPlayer mediaPlayer;
    String mFileName;
    private SeekBar seekbar;
    private double startTime = 0;
    private double finalTime = 0;
    private ImageButton btn_play;
    private ImageButton btn_pause;
    private Handler myHandler = new Handler();
    Persona persona;

    TextView text_nomb;
    TextView text_apellido;
    TextView text_rel;
    TextView text_frase;
    ImageView image_persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_amigo);

        Intent intent = getIntent();

        text_nomb = (TextView) findViewById(R.id.text_nombre);
        text_apellido = (TextView) findViewById(R.id.text_apellido);
        text_rel = (TextView) findViewById(R.id.text_relacion);
        text_frase = (TextView) findViewById(R.id.text_frase);
        image_persona = (ImageView) findViewById(R.id.image_persona);

        persona = (Persona)intent.getSerializableExtra("persona");


        btn_play = (ImageButton) findViewById(R.id.button_play);
        btn_pause = (ImageButton) findViewById(R.id.button_pause);
        //seekbar = (SeekBar) findViewById(R.id.seekBar3);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //////////////////////////--AUDIO--/////////////////////////

        mFileName = persona.getSonido();

        //////////////////////////--AUDIO--/////////////////////////

        //seekbar = (SeekBar)findViewById(R.id.seekBar3);
        //seekbar.setClickable(false);
        btn_pause.setEnabled(false);
        text_nomb.setText(persona.getNombre());
        text_apellido.setText(persona.getApellido());
        text_rel.setText(persona.getRelacion());
        text_frase.setText(persona.getFrase());


        byte [] image = persona.getImagen();
        if (image != null){
            Bitmap bmImage = BitmapFactory.decodeByteArray(image, 0, image.length);
            image_persona.setImageBitmap(bmImage);
        }
        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_modificar:
                Intent intent = new Intent(this, ModificaPersona.class);
                intent.putExtra("persona", persona);
                startActivityForResult(intent, ActivityConstants.AGREGO_PERSONA);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == ActivityConstants.AGREGO_PERSONA){
                Bundle datos = data.getExtras();
                persona = (Persona)datos.getSerializable("persona");

                text_nomb.setText(persona.getNombre());
                text_apellido.setText(persona.getApellido());
                text_rel.setText(persona.getRelacion());
                text_frase.setText(persona.getFrase());
                byte [] image = persona.getImagen();
                if (image != null){
                    Bitmap bmImage = BitmapFactory.decodeByteArray(image, 0, image.length);
                    image_persona.setImageBitmap(bmImage);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //////////////////////////--AUDIO--/////////////////////////
            case R.id.button_play:

                Toast.makeText(getApplicationContext(), "Reproduciendo", Toast.LENGTH_SHORT).show();

                mediaPlayer = new MediaPlayer();
                try {

                    mediaPlayer.setDataSource(mFileName);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    Log.e("Falla", "prepare() failed");
                }




               // finalTime = mediaPlayer.getDuration();
                //startTime = mediaPlayer.getCurrentPosition();

                //seekbar.setProgress((int) startTime);
               // myHandler.postDelayed(UpdateSongTime, 100);
                btn_pause.setEnabled(true);
                btn_play.setEnabled(false);

                break;

            case R.id.button_pause:
                Toast.makeText(getApplicationContext(), "Pausando",Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                btn_pause.setEnabled(false);
                btn_play.setEnabled(true);

                break;
//////////////////////////--AUDIO--/////////////////////////
        }
    }

    /*private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };*/
}