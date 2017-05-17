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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class MenuActivity extends AppCompatActivity implements  View.OnClickListener{
    Usuario usuarioActual;
    String nombre;
    RelativeLayout tutorial;
    Boolean tutorialVisible=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        usuarioActual = globalUser.getUser();
        String nombre = usuarioActual.getNombre();
        setTitle("Hola, "+ nombre+".");

        CardView Card1 = (CardView) findViewById(R.id.card_view); //familia
        CardView Card2 = (CardView) findViewById(R.id.card_view2); //amigos
        CardView Card3 = (CardView) findViewById(R.id.card_view3); //eventos relevantes
        CardView Card4 = (CardView) findViewById(R.id.card_view4); //sucesos historicos
        CardView Card5 = (CardView) findViewById(R.id.card_view5); //historia personal
        CardView Card6 = (CardView) findViewById(R.id.card_view6); //seccion de preguntas

        tutorial = (RelativeLayout) findViewById(R.id.tutorialMenu);
        if(MainActivity.tutorialVisible<=2)
        {
            tutorial.setVisibility(RelativeLayout.VISIBLE);
        }
        else
            tutorial.setVisibility(RelativeLayout.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MenuActivity.this, ModificarUsuario.class);
                startActivityForResult(intent, 1);
            }
        });

        Card1.setOnClickListener(this);
        Card2.setOnClickListener(this);
        Card3.setOnClickListener(this);
        Card4.setOnClickListener(this);
        Card5.setOnClickListener(this);
        Card6.setOnClickListener(this);
        tutorial.setOnClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        nombre = usuarioActual.getNombre();
        setTitle("Hola, "+ nombre+".");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.card_view:
                intent = new Intent(this, FamiliaActivity.class);
                startActivity(intent);
                break;
            case R.id.card_view2:
                intent = new Intent(this, AmigosActivity.class);
                startActivity(intent);
                break;
            case R.id.card_view3:
                intent = new Intent(this, EventosActivity.class);
                startActivity(intent);
                break;
            case R.id.card_view4:
                intent = new Intent(this, SucesoActivity.class);
                startActivity(intent);
                break;
            case R.id.card_view5:
                intent = new Intent(this, PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.card_view6:
                intent = new Intent(this, PreguntasActivity.class);
                startActivity(intent);
                break;
            case R.id.tutorialMenu:
                tutorial.setVisibility(RelativeLayout.GONE);
                //tutorialVisible=false;
                MainActivity.tutorialVisible++;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK){
            finish();
        }
    }
}

