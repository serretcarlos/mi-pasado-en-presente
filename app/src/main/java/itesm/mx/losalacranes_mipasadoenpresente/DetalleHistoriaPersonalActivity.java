package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class DetalleHistoriaPersonalActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer;
    private SeekBar seekbar;
    private double startTime = 0;
    private double finalTime = 0;
    private Button btn_play;
    private Button btn_pause;
    private Handler myHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_historia_personal);

        Intent intent = getIntent();

        TextView text_nomb = (TextView) findViewById(R.id.text_evento);
        TextView text_desc = (TextView) findViewById(R.id.text_descripcion);
        TextView text_fecha = (TextView) findViewById(R.id.text_fecha);
        ImageView image_evento = (ImageView) findViewById(R.id.image_detalle_evento);
        Button btn_play = (Button) findViewById(R.id.button_play_evento);
        Button btn_pause = (Button) findViewById(R.id.button_pause_evento);
        seekbar = (SeekBar) findViewById(R.id.seekBar_evento);

        Evento event = (Evento)intent.getSerializableExtra("evento");
        text_nomb.setText(event.getTitulo());
        text_desc.setText(event.getDescripcion());
        text_fecha.setText(event.getFecha());

        //mediaPlayer = MediaPlayer.create(this, R.raw.song);
        //seekbar = (SeekBar)findViewById(R.id.seekBar3);

        seekbar.setClickable(false);
        btn_pause.setEnabled(false);

        byte [] image = event.getImagen();
        if (image != null){
            Bitmap bmImage = BitmapFactory.decodeByteArray(image, 0, image.length);
            image_evento.setImageBitmap(bmImage);
        }

        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_play:

                Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
                btn_pause.setEnabled(true);
                btn_play.setEnabled(false);

                break;
            case R.id.button_pause:
                Toast.makeText(getApplicationContext(), "Pausing sound",Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                btn_pause.setEnabled(false);
                btn_play.setEnabled(true);

                break;

        }
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };

}
