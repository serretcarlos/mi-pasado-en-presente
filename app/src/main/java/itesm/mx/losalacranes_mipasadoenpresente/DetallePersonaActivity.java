package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallePersonaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);

        Intent intent = getIntent();

        TextView text_nomb = (TextView) findViewById(R.id.text_nombre);
        TextView text_apellido = (TextView) findViewById(R.id.text_apellido);
        TextView text_rel = (TextView) findViewById(R.id.text_relacion);
        TextView text_frase = (TextView) findViewById(R.id.text_frase);
        ImageView image_persona = (ImageView) findViewById(R.id.image_persona);

        Persona person = (Persona)intent.getSerializableExtra("persona");
        text_nomb.setText(person.getNombre());
        text_apellido.setText(person.getApellido());
        text_rel.setText(person.getRelacion());
        text_frase.setText(person.getFrase());

        byte [] image = person.getImagen();
        if (image != null){
            Bitmap bmImage = BitmapFactory.decodeByteArray(image, 0, image.length);
            image_persona.setImageBitmap(bmImage);
        }

    }


}
