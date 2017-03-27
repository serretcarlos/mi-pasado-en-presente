package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btnAgregar;
    private ImageButton btnPersnal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        btnPersnal = (ImageButton)findViewById(R.id.button_personal);
        btnAgregar = (ImageButton)findViewById(R.id.button_agregar);

        btnAgregar.setOnClickListener(this);
        btnPersnal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()) {
            case R.id.button_agregar:
                intent = new Intent(this, AgregarPersona.class);
                startActivity(intent);
                break;
            case R.id.button_personal:
                intent = new Intent(this, DetalleSucesoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
