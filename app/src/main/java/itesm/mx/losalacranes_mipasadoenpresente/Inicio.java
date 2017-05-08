package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by eduardocastro on 07/05/17.
 */


public class Inicio extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        View someView = findViewById(R.id.activity_inicio);
        View root = someView.getRootView();
        root.setBackgroundColor(Color.rgb(26, 65, 109));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Inicio.this, MainActivity.class);
                startActivity(intent);
            }
        },10000);
    }
}
