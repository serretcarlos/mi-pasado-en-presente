package itesm.mx.losalacranes_mipasadoenpresente;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import static itesm.mx.losalacranes_mipasadoenpresente.R.id.tutorialView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{


    ArrayList<Usuario> listaUsuarios;
    UsuarioAdapter usuarioAdapter;
    Usuario usuarioActual;
    DataBaseOperations dao;
    RelativeLayout tutorial;
    public static int tutorialVisible=0;

    public ArrayList<Usuario> getListaUsuarios(){
        Usuario usuario;
        ArrayList<Usuario> listUsers = new ArrayList<>();
        usuario = new Usuario("Carlos", "Serret", 21, "18/11/1995", "soltero", 0, 0, null);
        listUsers.add(usuario);
        usuario = new Usuario("Daniel", "Serret", 21, "18/11/1995", "soltero", 0, 0, null);
        listUsers.add(usuario);
        usuario = new Usuario("Iram", "Diaz", 21, "29/05/1996", "soltero", 0, 0, null);
        listUsers.add(usuario);
        return  listUsers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dao = new DataBaseOperations(this);
        dao.open();
        listaUsuarios = dao.getAllUsers();
        GridView gridView = (GridView)findViewById(R.id.gridview_usuarios);
        //listaUsuarios = getListaUsuarios();
        usuarioAdapter = new UsuarioAdapter(this, listaUsuarios);
        gridView.setAdapter(usuarioAdapter);
        gridView.setOnItemClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        tutorial = (RelativeLayout) findViewById(tutorialView);
        if(listaUsuarios.isEmpty())
            tutorial.setVisibility(RelativeLayout.VISIBLE);
        else if(tutorialVisible>0 || listaUsuarios.isEmpty()==false)
            tutorial.setVisibility(RelativeLayout.GONE);
        tutorial.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tutorialView)
        {
            tutorial.setVisibility(RelativeLayout.GONE);
            tutorialVisible++;
        }
        else {
            Intent intent = new Intent(this, AgregarUsuarioActivity.class);
            startActivityForResult(intent, ActivityConstants.AGREGO_USUARIO);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        Usuario user = (Usuario)parent.getItemAtPosition(position);
        globalUser.setUser(user);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == ActivityConstants.AGREGO_USUARIO){
                Bundle datos = data.getExtras();
                Usuario usuario = (Usuario)datos.getSerializable("usuario");
                usuarioAdapter.add(usuario);
                usuarioAdapter.notifyDataSetChanged();
            }
        }
    }
}