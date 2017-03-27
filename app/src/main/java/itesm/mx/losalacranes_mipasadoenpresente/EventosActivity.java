package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class EventosActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btnAgregar;
    private ImageButton btnEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        btnAgregar = (ImageButton)findViewById(R.id.button_agregar);
        btnAgregar.setOnClickListener(this);
        btnEvento = (ImageButton) findViewById(R.id.button_evento);
        btnEvento.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()) {
            case R.id.button_agregar:
            intent = new Intent(this, AgregarEvento.class);
            startActivity(intent);
                break;
            case R.id.button_evento:
                intent = new Intent(this, DetalleEventoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
