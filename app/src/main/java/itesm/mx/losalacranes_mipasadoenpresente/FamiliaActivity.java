package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FamiliaActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btnAgregar;
    private ImageButton btnFamilia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familia);

        btnAgregar = (ImageButton)findViewById(R.id.button_agregar);
        btnAgregar.setOnClickListener(this);
        btnFamilia = (ImageButton) findViewById(R.id.button_familiar);
        btnFamilia.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()) {
            case R.id.button_agregar:
                intent = new Intent(this, AgregarPersona.class);
                startActivity(intent);
                break;
            case R.id.button_familiar:
                intent = new Intent(this, DetalleFamilia.class);
                startActivity(intent);
                break;
        }
    }
}
