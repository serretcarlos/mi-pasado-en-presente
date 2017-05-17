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
