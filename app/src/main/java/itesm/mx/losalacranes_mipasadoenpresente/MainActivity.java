package itesm.mx.losalacranes_mipasadoenpresente;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{



    ArrayList<Usuario> listaUsuarios;
    UsuarioAdapter usuarioAdapter;
    Usuario usuarioActual;
    DataBaseOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        dao = new DataBaseOperations(this);
        dao.open();
        listaUsuarios = dao.getAllUsers();
        GridView gridView = (GridView)findViewById(R.id.gridview_usuarios);
        usuarioAdapter = new UsuarioAdapter(this, listaUsuarios);
        gridView.setAdapter(usuarioAdapter);
        gridView.setOnItemClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        ViewCompat.setNestedScrollingEnabled(gridView,true);
    }

    @Override
    protected void onResume(){
        super.onResume();
        dao = new DataBaseOperations(this);
        dao.open();
        listaUsuarios = dao.getAllUsers();
        GridView gridView = (GridView)findViewById(R.id.gridview_usuarios);
        usuarioAdapter = new UsuarioAdapter(this, listaUsuarios);
        gridView.setAdapter(usuarioAdapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AgregarUsuarioActivity.class);
        startActivityForResult(intent, ActivityConstants.AGREGO_USUARIO);
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

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}