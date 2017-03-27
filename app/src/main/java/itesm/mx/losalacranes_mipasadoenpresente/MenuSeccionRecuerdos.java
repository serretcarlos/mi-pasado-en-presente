package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuSeccionRecuerdos extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_seccion_recuerdos);
        ImageButton iBtnFamilia = (ImageButton) findViewById(R.id.imageButton_familia);
        ImageButton iBtnAmigos = (ImageButton) findViewById(R.id.imageButton_amigos);
        ImageButton iBtnEventos = (ImageButton) findViewById(R.id.imageButton_eventos);
        ImageButton iBtnSucesos = (ImageButton) findViewById(R.id.imageButton_sucesos);
        ImageButton iBtnHistoria = (ImageButton) findViewById(R.id.imageButton_historia);
        ImageButton iBtnPreguntas = (ImageButton) findViewById(R.id.imageButton_preguntas);
        iBtnFamilia.setOnClickListener(this);
        iBtnAmigos.setOnClickListener(this);
        iBtnEventos.setOnClickListener(this);
        iBtnSucesos.setOnClickListener(this);
        iBtnHistoria.setOnClickListener(this);
        iBtnPreguntas.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.imageButton_familia:
                intent = new Intent(MenuSeccionRecuerdos.this, FamiliaActivity.class);
                startActivity(intent);
                break;
            case R.id.imageButton_amigos:
                intent = new Intent(MenuSeccionRecuerdos.this, AmigosActivity.class);
                startActivity(intent);
                break;
            case R.id.imageButton_eventos:
                intent = new Intent(MenuSeccionRecuerdos.this, EventosActivity.class);
                startActivity(intent);
                break;
            case R.id.imageButton_sucesos:
                intent = new Intent(MenuSeccionRecuerdos.this, SucesoActivity.class);
                startActivity(intent);
                break;
            case R.id.imageButton_historia:
                intent = new Intent(MenuSeccionRecuerdos.this, PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.imageButton_preguntas:
                intent = new Intent(MenuSeccionRecuerdos.this, PreguntasActivity.class);
                startActivity(intent);
                break;
        }

    }
}


