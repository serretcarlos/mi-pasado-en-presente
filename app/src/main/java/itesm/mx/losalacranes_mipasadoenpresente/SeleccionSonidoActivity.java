package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class SeleccionSonidoActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    GlobalUserClass globalUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_sonido);

        globalUser = (GlobalUserClass) getApplicationContext();

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton_s1:
                if (checked){
                    mediaPlayer = MediaPlayer.create(this, R.raw.acierto);
                    globalUser.setSound(1);
                    Toast.makeText(getApplicationContext(), "Sonido 1 seleccionado", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }
                    break;
            case R.id.radioButton_s2:
                if (checked){
                    mediaPlayer = MediaPlayer.create(this, R.raw.acierto2);
                    globalUser.setSound(2);
                    Toast.makeText(getApplicationContext(), "Sonido 2 seleccionado", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }

                    break;
            case R.id.radioButton_s3:
                if (checked){
                    mediaPlayer = MediaPlayer.create(this, R.raw.acierto3);
                    globalUser.setSound(3);
                    Toast.makeText(getApplicationContext(), "Sonido 3 seleccionado", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }
                    break;
        }
    }

}
