package itesm.mx.losalacranes_mipasadoenpresente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarUsuario extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        Button btnCancelar = (Button) findViewById(R.id.button_cancelar_usuario);
        Button btnGuardar = (Button) findViewById(R.id.button_guardar_usuario);
        Button btnFoto = (Button) findViewById(R.id.button_foto_usuario);

        EditText etNombre = (EditText) findViewById(R.id.edit_nombre_usuario);
        EditText etApellid = (EditText) findViewById(R.id.edit_apellido_usuario);
        EditText etEdad = (EditText) findViewById(R.id.edit_edad_usuario);
        EditText etLugarNacimiento = (EditText) findViewById(R.id.edit_lugar_nacimiento_usuario);
        EditText etEstadoCivil = (EditText) findViewById(R.id.edit_estado_civil_usuario);
        EditText etNumHijos = (EditText) findViewById(R.id.edit_hijos_usuario);
        EditText etNumNietos = (EditText) findViewById(R.id.edit_nietos_usuario);

        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
