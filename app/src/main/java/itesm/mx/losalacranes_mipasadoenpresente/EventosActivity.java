package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import static itesm.mx.losalacranes_mipasadoenpresente.R.id.tutorialAmigo;
import static itesm.mx.losalacranes_mipasadoenpresente.R.id.tutorialEventos;

public class EventosActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    ArrayList<Evento> listaEventos;
    EventoAdapter eventoAdapter;
    Usuario usuarioActual;
    DataBaseOperations dao;
    String tipo = "Evento";
    long idUsuario;
    RelativeLayout tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        usuarioActual = globalUser.getUser();
        idUsuario = usuarioActual.getIdUsuario();

        dao = new DataBaseOperations(this);
        dao.open();

        listaEventos = dao.getAllEventos(idUsuario, tipo);
        GridView gridView = (GridView)findViewById(R.id.gridview_usuarios);
        eventoAdapter = new EventoAdapter(this, listaEventos);
        gridView.setAdapter(eventoAdapter);
        gridView.setOnItemClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        tutorial = (RelativeLayout) findViewById(tutorialEventos);
        if(MainActivity.tutorialVisible<=7)
            tutorial.setVisibility(RelativeLayout.VISIBLE);
        else
            tutorial.setVisibility(RelativeLayout.GONE);
        tutorial.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaEventos = dao.getAllEventos(idUsuario, tipo);
        GridView gridView = (GridView)findViewById(R.id.gridview_usuarios);
        eventoAdapter = new EventoAdapter(this, listaEventos);
        gridView.setAdapter(eventoAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            dao.close();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tutorialEventos){
            tutorial.setVisibility(RelativeLayout.GONE);
            MainActivity.tutorialVisible++;
        }
        else {
            Intent intent = new Intent(this, AgregarEvento.class);
            intent.putExtra("Tipo", tipo);
            startActivityForResult(intent, ActivityConstants.AGREGO_EVENTO);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        Evento evento = (Evento)parent.getItemAtPosition(position);
        Intent intent = new Intent(this, DetalleEventoActivity.class);
        intent.putExtra("evento", evento);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == ActivityConstants.AGREGO_EVENTO){
                Bundle datos = data.getExtras();
                Evento evento = (Evento) datos.getSerializable("evento");
                eventoAdapter.add(evento);
                eventoAdapter.notifyDataSetChanged();
            }
        }
    }

}