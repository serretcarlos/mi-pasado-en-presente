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
/*
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class DetalleHistoriaPersonalActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer;
    String mFileName;
    private SeekBar seekbar;
    private double startTime = 0;
    private double finalTime = 0;
    private ImageButton btn_play;
    private ImageButton btn_pause;
    Evento evento;
    TextView text_nomb;
    TextView text_desc;
    TextView text_fecha;
    ImageView image_evento;
    DataBaseOperations dao;

    private Handler myHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_historia_personal);
        dao = new DataBaseOperations(this);
        dao.open();

        Intent intent = getIntent();
        evento = (Evento)intent.getSerializableExtra("evento");

        text_nomb = (TextView) findViewById(R.id.text_evento);
        text_desc = (TextView) findViewById(R.id.text_descripcion);
        text_fecha = (TextView) findViewById(R.id.text_fecha);
        image_evento = (ImageView) findViewById(R.id.image_detalle_evento);
        btn_play = (ImageButton) findViewById(R.id.button_play_evento);
        btn_pause = (ImageButton) findViewById(R.id.button_pause_evento);
        //seekbar = (SeekBar) findViewById(R.id.seekBar_evento);


        text_nomb.setText(evento.getTitulo());
        text_desc.setText(evento.getDescripcion());
        text_fecha.setText(evento.getFecha());

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //////////////////////////--AUDIO--/////////////////////////

        mFileName = evento.getSonido();

        //////////////////////////--AUDIO--/////////////////////////
        btn_pause.setEnabled(false);

        byte [] image = evento.getImagen();
        if (image != null){
            Bitmap bmImage = BitmapFactory.decodeByteArray(image, 0, image.length);
            image_evento.setImageBitmap(bmImage);
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
                Intent intent = new Intent(this, ModificaEvento.class);
                intent.putExtra("evento", evento);
                startActivityForResult(intent, ActivityConstants.AGREGO_EVENTO);
                break;
            case R.id.menu_delete:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Eliminar suceso")
                        .setMessage("¿Está seguro que desea eliminar este suceso?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.deleteEvento(evento.getIdEvento());
                                Toast.makeText(getApplicationContext(), "Este suceso ha sido eliminado.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == ActivityConstants.AGREGO_EVENTO){
                Bundle datos = data.getExtras();
                evento = (Evento)datos.getSerializable("evento");

                text_nomb.setText(evento.getTitulo());
                text_desc.setText(evento.getDescripcion());
                text_fecha.setText(evento.getFecha());
                byte [] image = evento.getImagen();
                if (image != null){
                    Bitmap bmImage = BitmapFactory.decodeByteArray(image, 0, image.length);
                    image_evento.setImageBitmap(bmImage);
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //////////////////////////--AUDIO--/////////////////////////
            case R.id.button_play_evento:

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

            case R.id.button_pause_evento:
                Toast.makeText(getApplicationContext(), "Pausando",Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                btn_pause.setEnabled(false);
                btn_play.setEnabled(true);

                break;
            //////////////////////////--AUDIO--/////////////////////////

        }
    }

    /*
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
    */

}
