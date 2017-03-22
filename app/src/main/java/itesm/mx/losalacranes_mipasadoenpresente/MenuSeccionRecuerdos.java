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

        public void onClick (View v){
            switch (v.getId()) {
                case R.id.imageButton_familia:
                    Intent intent = new Intent(MenuSeccionRecuerdos.this, Familia.class);
                    startActivity(intent);
                    break;
                case R.id.imageButton_amigos:
                    Intent intent = new Intent(MenuSeccionRecuerdos.this, Amigos.class);
                    startActivity(intent);
                    break;
                case R.id.imageButton_eventos:
                    Intent intent = new Intent(MenuSeccionRecuerdos.this, Eventos.class);
                    startActivity(intent);
                    break;
                case R.id.imageButton_sucesos:
                    Intent intent = new Intent(MenuSeccionRecuerdos.this, Suceos.class);
                    startActivity(intent);
                    break;
                case R.id.imageButton_historia:
                    Intent intent = new Intent(MenuSeccionRecuerdos.this, Historia.class);
                    startActivity(intent);
                    break;
                case R.id.imageButton_preguntas:
                    Intent intent = new Intent(MenuSeccionRecuerdos.this, Preguntas.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}


