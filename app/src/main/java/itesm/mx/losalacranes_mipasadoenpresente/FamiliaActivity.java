
/*
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import static itesm.mx.losalacranes_mipasadoenpresente.MainActivity.tutorialVisible;
import static itesm.mx.losalacranes_mipasadoenpresente.R.id.tutorialFamilia;
import static itesm.mx.losalacranes_mipasadoenpresente.R.id.tutorialView;

public class FamiliaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    ArrayList<Persona> listaPersonas;
    PersonaAdapter personaAdapter;
    Usuario usuarioActual;
    DataBaseOperations dao;
    long idUsuario;
    RelativeLayout tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familia);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

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

        tutorial = (RelativeLayout) findViewById(tutorialFamilia);
        if(MainActivity.tutorialVisible<=7)
            tutorial.setVisibility(RelativeLayout.VISIBLE);
        else
            tutorial.setVisibility(RelativeLayout.GONE);
        tutorial.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaPersonas = dao.getAllFamiliares(idUsuario);
        GridView gridView = (GridView)findViewById(R.id.gridview_usuarios);
        personaAdapter = new PersonaAdapter(this, listaPersonas);
        gridView.setAdapter(personaAdapter);
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
        if(v.getId()==R.id.tutorialFamilia){
            tutorial.setVisibility(RelativeLayout.GONE);
            MainActivity.tutorialVisible++;
        }
        else {
            Intent intent = new Intent(this, AgregarPersona.class);
            String tipo = "Familiar";
            intent.putExtra("Relacion", tipo);
            //tutorialVisible=false;
            startActivityForResult(intent, ActivityConstants.AGREGO_PERSONA);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        Persona persona = (Persona)parent.getItemAtPosition(position);
        Intent intent = new Intent(this, DetallePersonaActivity.class);
        intent.putExtra("persona", persona);
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
