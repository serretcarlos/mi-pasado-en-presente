package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);

        Intent intent = getIntent();

        TextView text_nomb = (TextView) findViewById(R.id.text_evento);
        TextView text_desc = (TextView) findViewById(R.id.text_descripcion);
        TextView text_fecha = (TextView) findViewById(R.id.text_fecha);
        ImageView image_evento = (ImageView) findViewById(R.id.image_persona);

        Evento event = (Evento)intent.getSerializableExtra("evento");
        text_nomb.setText(event.getTitulo());
        text_desc.setText(event.getDescripcion());
        text_fecha.setText(event.getFecha());

        byte [] image = event.getImagen();
        if (image != null){
            Bitmap bmImage = BitmapFactory.decodeByteArray(image, 0, image.length);
            image_evento.setImageBitmap(bmImage);
        }

    }
}
