package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PreguntasActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Persona> listaFamilia;
    ArrayList<Persona> listaAmigos;
    ArrayList<Evento> listaEventos;
    ArrayList<Evento> listaSucesos;
    ArrayList<Evento> listaPersonal;

    Usuario usuarioActual;
    long idUsuario;
    DataBaseOperations dao;

    private MediaPlayer mediaPlayer;

    TextView tvOpcionA;
    TextView tvOpcionB;
    TextView tvOpcionC;
    TextView tvPregunta;

    ImageView ivOpcion_a;
    ImageView ivOpcion_b;
    ImageView ivOpcion_c;

    ImageView ivFoto;

    int categoria = 0;
    int respuesta = 0;
    int isUsedFamilia = 0;
    int isUsedAmigos = 0;
    int isUsedEventos = 0;
    int isUsedSucesos = 0;
    int isUsedPersonal = 0;
    int opciones[] = new int[3];

    GlobalUserClass globalUser;
    int sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        GlobalUserClass globalUser = (GlobalUserClass) getApplicationContext();
        usuarioActual = globalUser.getUser();
        idUsuario = usuarioActual.getIdUsuario();

        ivFoto = (ImageView) findViewById(R.id.image_foto);
        tvOpcionA = (TextView) findViewById(R.id.text_opcion_A);
        tvOpcionB = (TextView) findViewById(R.id.text_opcion_B);
        tvOpcionC = (TextView) findViewById(R.id.text_opcion_C);
        tvPregunta = (TextView) findViewById(R.id.text_pregunta);
        ivOpcion_a = (ImageView) findViewById(R.id.image_opcion_a);
        ivOpcion_b = (ImageView) findViewById(R.id.image_opcion_b);
        ivOpcion_c = (ImageView) findViewById(R.id.image_opcion_c);


        dao = new DataBaseOperations(this);
        dao.open();

        //celebración
        globalUser = (GlobalUserClass) getApplicationContext();
        sonido = globalUser.getSound();

        if (sonido == 2){
            mediaPlayer = MediaPlayer.create(this, R.raw.acierto2);
            Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
        } else if (sonido == 3){
            mediaPlayer = MediaPlayer.create(this, R.raw.acierto3);
            Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.acierto);
            Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
        }


        listaFamilia = dao.getAllFamiliares(idUsuario);
        listaAmigos = dao.getAllAmigos(idUsuario);
        listaEventos = dao.getAllEventos(idUsuario, "Evento");
        listaSucesos = dao.getAllEventos(idUsuario, "Suceso");
        listaPersonal = dao.getAllEventos(idUsuario, "Personal");

        if(listaFamilia.size() >= 3){
            isUsedFamilia = 1;
        }

        if(listaAmigos.size() >= 3){
            isUsedAmigos = 1;
        }

        if(listaEventos.size() >= 3){
            isUsedEventos = 1;
        }

        if(listaSucesos.size() >= 3){
            isUsedSucesos = 1;
        }

        if(listaPersonal.size() >= 3){
            isUsedPersonal = 1;
        }

        if(isUsedFamilia == 1 | isUsedAmigos == 1 | isUsedEventos == 1 | isUsedSucesos == 1 |
                isUsedPersonal == 1){

            tvOpcionA.setOnClickListener(this);
            tvOpcionB.setOnClickListener(this);
            tvOpcionC.setOnClickListener(this);
            ivOpcion_a.setOnClickListener(this);
            ivOpcion_b.setOnClickListener(this);
            ivOpcion_c.setOnClickListener(this);
            update();
        }

    }

    @Override
    protected void onResume() {
        globalUser = (GlobalUserClass) getApplicationContext();
        sonido = globalUser.getSound();

        super.onResume();
        if (sonido == 2){
            mediaPlayer = MediaPlayer.create(this, R.raw.acierto2);
        } else if (sonido == 3){
            mediaPlayer = MediaPlayer.create(this, R.raw.acierto3);
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.acierto);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sonidos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_selecciona_sonido:
                Intent intent = new Intent(this, SeleccionSonidoActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    public void update() {

        randomCategoria();
        int respuesta = randomPregunta();
        generaPregunta(respuesta);
        generaOpciones(respuesta);
    }

    public void generaPregunta(int res){

        byte[] foto;

        if(categoria == 1 & isUsedFamilia == 1 | categoria == 2 & isUsedAmigos == 1){

            String pregunta = "Cómo me llamo?";
            if(categoria == 1 & isUsedFamilia == 1){
                foto = listaFamilia.get(res).getImagen();
                Bitmap bmImage = BitmapFactory.decodeByteArray(foto, 0, foto.length);
                ivFoto.setImageBitmap(bmImage);
            }else {
                foto = listaAmigos.get(res).getImagen();
                Bitmap bmImage = BitmapFactory.decodeByteArray(foto, 0, foto.length);
                ivFoto.setImageBitmap(bmImage);

            }
            tvPregunta.setText(pregunta);
        } else if(isUsedEventos == 1 | isUsedSucesos == 1 | isUsedPersonal == 1){

            String pregunta = "Qué evento es este?";
            if(categoria == 3 & isUsedEventos == 1){
                foto = listaEventos.get(res).getImagen();
                Bitmap bmImage = BitmapFactory.decodeByteArray(foto, 0, foto.length);
                ivFoto.setImageBitmap(bmImage);

            }else if(categoria == 4 & isUsedSucesos == 1){
                foto = listaSucesos.get(res).getImagen();
                Bitmap bmImage = BitmapFactory.decodeByteArray(foto, 0, foto.length);
                ivFoto.setImageBitmap(bmImage);

            } else {
                foto = listaPersonal.get(res).getImagen();
                Bitmap bmImage = BitmapFactory.decodeByteArray(foto, 0, foto.length);
                ivFoto.setImageBitmap(bmImage);

            }
            tvPregunta.setText(pregunta);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.text_opcion_A:
                if (opciones[0]== respuesta){
                    Toast.makeText(getApplicationContext(), "Bien Hecho", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Vamos! sigue intentado", Toast.LENGTH_SHORT).show();
                }
                update();
                break;
            case R.id.image_opcion_a:
                if (opciones[0]== respuesta){
                    Toast.makeText(getApplicationContext(), "Bien Hecho", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Vamos! sigue intentado", Toast.LENGTH_SHORT).show();
                }
                update();
                break;
            case R.id.text_opcion_B:
                if (opciones[1]== respuesta){
                    Toast.makeText(getApplicationContext(), "Bien Hecho", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Vamos! sigue intentado", Toast.LENGTH_SHORT).show();
                }
                update();
                break;
            case R.id.image_opcion_b:
                if (opciones[1]== respuesta){
                    Toast.makeText(getApplicationContext(), "Bien Hecho", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Vamos! sigue intentado", Toast.LENGTH_SHORT).show();
                }
                update();
                break;
            case R.id.text_opcion_C:
                if (opciones[2]== respuesta){
                    Toast.makeText(getApplicationContext(), "Bien Hecho", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Vamos! sigue intentado", Toast.LENGTH_SHORT).show();
                }
                update();
                break;
            case R.id.image_opcion_c:
                if (opciones[2]== respuesta){
                    Toast.makeText(getApplicationContext(), "Bien Hecho", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Vamos! sigue intentado", Toast.LENGTH_SHORT).show();
                }
                update();
                break;
        }
    }

    public boolean repetidos(final int[] array, int value){
        boolean flag = false;

        if(array[0] == value){
            flag = true;
        }
        if(array[1] == value){
            flag = true;
        }
        if(array[2] == value){
            flag = true;
        }

        return  flag;
    }

    public void generaOpciones(int res){

        Random rand = new Random();
        int numOpciones = 0;
        int opcion = 0;
        int pos = 0;
        opciones[0] = 0;
        opciones[1] = 0;
        opciones[2] = 0;

        pos = rand.nextInt(3);
        opciones[pos] = res;

        while(numOpciones < 2){
            if(categoria == 1){
                opcion = rand.nextInt(listaFamilia.size());
                if(opcion != res){
                    pos = rand.nextInt(3);
                    if(opciones[pos] == 0) {
                        //if(!repetidos(opciones, opcion)){
                        opciones[pos] = opcion;
                        numOpciones++;
                        //}
                    }
                }
                if(numOpciones == 2){
                    tvOpcionA.setText(listaFamilia.get(opciones[0]).getNombre());
                    tvOpcionB.setText(listaFamilia.get(opciones[1]).getNombre());
                    tvOpcionC.setText(listaFamilia.get(opciones[2]).getNombre());
                }
            }
            else if(categoria == 2){
                tvOpcionA.setText(listaAmigos.get(res).getNombre());
                opcion = rand.nextInt(listaAmigos.size());
                if(opcion != res){
                    if(opcion == 0){
                        tvOpcionB.setText(listaAmigos.get(opcion).getNombre());
                    } else{
                        tvOpcionC.setText(listaAmigos.get(opcion).getNombre());
                    }
                    numOpciones++;
                }
            }
            else if(categoria == 3){
                opcion = rand.nextInt(listaEventos.size());
                if(opcion != res){
                    pos = rand.nextInt(3);
                    if(opciones[pos] == 0) {
                        //if(!repetidos(opciones, opcion)){
                        opciones[pos] = opcion;
                        numOpciones++;
                        //}
                    }
                }
                if(numOpciones == 2){
                    tvOpcionA.setText(listaEventos.get(opciones[0]).getTitulo());
                    tvOpcionB.setText(listaEventos.get(opciones[1]).getTitulo());
                    tvOpcionC.setText(listaEventos.get(opciones[2]).getTitulo());
                }
            }
            else if(categoria == 4){
                tvOpcionA.setText(listaSucesos.get(res).getTitulo());
                opcion = rand.nextInt(listaSucesos.size());
                if(opcion != res){
                    if(opcion == 0){
                        tvOpcionB.setText(listaSucesos.get(opcion).getTitulo());
                    } else{
                        tvOpcionC.setText(listaSucesos.get(opcion).getTitulo());
                    }
                    numOpciones++;
                }
            }
            else if(categoria == 5){
                tvOpcionA.setText(listaPersonal.get(res).getTitulo());
                opcion = rand.nextInt(listaPersonal.size());
                if(opcion != res){
                    if(opcion == 0){
                        tvOpcionB.setText(listaPersonal.get(opcion).getTitulo());
                    } else{
                        tvOpcionC.setText(listaPersonal.get(opcion).getTitulo());
                    }
                    numOpciones++;
                }
            }
        }
    }

    public void randomCategoria(){

        Random rand = new Random();
        categoria = rand.nextInt(6-1) + 1;
    }

    public int randomPregunta() {

        int found = 0;
        int pregunta = -1;
        Random rand = new Random();

        while (found != 1) {

            if (categoria == 1 & listaFamilia.size() >= 3) {
                if (listaFamilia.size() > 0) {
                    int num = listaFamilia.size();
                    pregunta = rand.nextInt(num);
                    found = 1;
                }
            } else if (categoria == 2 & listaAmigos.size() >= 3) {
                if (listaAmigos.size() > 0) {
                    int num = listaAmigos.size();
                    pregunta = rand.nextInt(num);
                    found = 1;
                }

            } else if (categoria == 3 & listaEventos.size() >= 3) {
                if (listaEventos.size() > 0) {
                    int num = listaEventos.size();
                    pregunta = rand.nextInt(num);
                    found = 1;
                }

            } else if (categoria == 4 & listaSucesos.size() >= 3) {
                if (listaSucesos.size() > 0) {
                    int num = listaSucesos.size();
                    pregunta = rand.nextInt(num);
                    found = 1;
                }

            } else if (categoria == 5 & listaPersonal.size() >= 3) {
                if (listaPersonal.size() > 0) {
                    int num = listaPersonal.size();
                    pregunta = rand.nextInt(num);
                    found = 1;
                }
            } else{
                randomCategoria();
            }
        }
        respuesta = pregunta;
        return pregunta;
    }
}