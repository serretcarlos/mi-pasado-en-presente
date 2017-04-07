package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AgregarUsuarioActivity extends AppCompatActivity implements View.OnClickListener{


    EditText etNombre;
    EditText etApellido;
    EditText etEdad;
    EditText etLugarNacimiento;
    EditText etEstadoCivil;
    EditText etNumHijos;
    EditText etNumNietos;
    EditText etFecha;
    DataBaseOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        dao = new DataBaseOperations(this);
        dao.open();
        TextView tvCancelar = (TextView) findViewById(R.id.text_cancelar_usuario);
        Button btnGuardar = (Button) findViewById(R.id.button_guardar_usuario);
        //Button btnFoto = (Button) findViewById(R.id.button_foto_usuario);

        etNombre = (EditText) findViewById(R.id.edit_nombre_usuario);
        etApellido = (EditText) findViewById(R.id.edit_apellido_usuario);
        etEdad = (EditText) findViewById(R.id.edit_edad_usuario);

        etFecha = (EditText) findViewById(R.id.edit_fecha_usuario);
        etLugarNacimiento = (EditText) findViewById(R.id.edit_lugar_nacimiento_usuario);
        etEstadoCivil = (EditText) findViewById(R.id.edit_estado_civil_usuario);
        etNumHijos = (EditText) findViewById(R.id.edit_hijos_usuario);
        etNumNietos = (EditText) findViewById(R.id.edit_nietos_usuario);
        tvCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        //btnFoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Usuario usuario;
        switch (v.getId()) {
            case R.id.button_guardar_usuario:
                usuario = agregarUsuario();
                intent = new Intent();
                intent.putExtra("usuario", usuario);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.text_cancelar_usuario:
                break;

        }
    }



    public Usuario agregarUsuario(){
        Usuario usuario = new Usuario();
        byte [] imagen = null;
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        int edad = Integer.parseInt(etEdad.getText().toString());
        String fecha = etFecha.getText().toString();
        String lugar = etLugarNacimiento.getText().toString();
        String estado = etEstadoCivil.getText().toString();
        int nietos = Integer.parseInt(etNumNietos.getText().toString());
        int hijos = Integer.parseInt(etNumHijos.getText().toString());
        usuario = new Usuario(nombre, apellido, edad, fecha, estado, nietos, hijos, imagen);
        long id = dao.addUsuario(usuario);
        usuario.setIdUsuario(id);
        return usuario;
    }
}
