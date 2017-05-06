package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MenuActivity extends AppCompatActivity implements  View.OnClickListener{
    Usuario usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Card1.setOnClickListener(this);
        Card2.setOnClickListener(this);
        Card3.setOnClickListener(this);
        Card4.setOnClickListener(this);
        Card5.setOnClickListener(this);
        Card6.setOnClickListener(this);
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
        }

    }

}

