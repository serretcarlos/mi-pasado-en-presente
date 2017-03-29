package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton iBtnPersona1 =(ImageButton) findViewById(R.id.imageButton_persona1);
        ImageButton iBtnPersona2  =(ImageButton) findViewById(R.id.imageButton_persona2);
        ImageButton iBtnAgregar  = (ImageButton) findViewById(R.id.imageButton_agregar);
        iBtnPersona1.setOnClickListener(this);
        iBtnPersona2.setOnClickListener(this);
        iBtnAgregar.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.imageButton_persona1:
                intent = new Intent(MainActivity.this, MenuSeccionRecuerdos.class);
                startActivity(intent);
                break;
            case R.id.imageButton_persona2:
                intent = new Intent(MainActivity.this, MenuSeccionRecuerdos.class);
                startActivity(intent);
                break;
            case R.id.imageButton_agregar:
                intent = new Intent(MainActivity.this, AgregarUsuario.class);
                startActivity(intent);
                break;
        }
    }
}
