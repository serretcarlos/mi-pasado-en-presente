package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AmigosActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos);

        btnAgregar = (ImageButton)findViewById(R.id.button_agregar);
        btnAgregar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(this, AgregarPersona.class);
        startActivity(intent);
    }
}
