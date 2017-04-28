package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;

public class FamiliaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    ArrayList<Persona> listaPersonas;
    PersonaAdapter personaAdapter;
    Usuario usuarioActual;
    DataBaseOperations dao;
    long idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familia);

        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        usuarioActual = globalUser.getUser();
        idUsuario = usuarioActual.getIdUsuario();

        dao = new DataBaseOperations(this);
        dao.open();

        listaPersonas = dao.getAllFamiliares(idUsuario);
        GridView gridView = (GridView)findViewById(R.id.gridview_usuarios);
        personaAdapter = new PersonaAdapter(this, listaPersonas);
        gridView.setAdapter(personaAdapter);
        gridView.setOnItemClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AgregarPersona.class);
        String tipo = "Familiar";
        intent.putExtra("Relacion", tipo);
        startActivityForResult(intent, ActivityConstants.AGREGO_PERSONA);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        Persona persona = (Persona)parent.getItemAtPosition(position);
        Intent intent = new Intent(this, DetalleFamilia.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == ActivityConstants.AGREGO_PERSONA){
                Bundle datos = data.getExtras();
                Persona persona = (Persona) datos.getSerializable("persona");
                personaAdapter.add(persona);
                personaAdapter.notifyDataSetChanged();
            }
        }
    }

}
