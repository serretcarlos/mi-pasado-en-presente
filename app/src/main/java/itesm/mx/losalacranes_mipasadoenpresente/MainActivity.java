package itesm.mx.losalacranes_mipasadoenpresente;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{


    ArrayList<Usuario> listaUsuarios;
    UsuarioAdapter usuarioAdapter;
    Usuario usuarioActual;

    public ArrayList<Usuario> getListaUsuarios(){
        Usuario usuario;
        ArrayList<Usuario> listUsers = new ArrayList<>();
        usuario = new Usuario("Carlos", "Serret", 21, "18/11/1995", "soltero", 0, 0, 3, null);
        listUsers.add(usuario);
        usuario = new Usuario("Daniel", "Serret", 21, "18/11/1995", "soltero", 0, 0, 3, null);
        listUsers.add(usuario);
        usuario = new Usuario("Iram", "Diaz", 21, "29/05/1996", "soltero", 0, 0, 1, null);
        listUsers.add(usuario);
        return  listUsers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GridView gridView = (GridView)findViewById(R.id.gridview_usuarios);
        listaUsuarios = getListaUsuarios();
        usuarioAdapter = new UsuarioAdapter(this, listaUsuarios);
        gridView.setAdapter(usuarioAdapter);
        gridView.setOnItemClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        Usuario user = (Usuario)parent.getItemAtPosition(position);
        globalUser.setUser(user);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}