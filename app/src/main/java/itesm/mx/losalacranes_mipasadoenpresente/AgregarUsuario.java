package itesm.mx.losalacranes_mipasadoenpresente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AgregarUsuario extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);
        Button btnCancelar = (Button) findViewById(R.id.button_cancelar);
        Button btnGuardar = (Button) findViewById(R.id.button_guardar);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
