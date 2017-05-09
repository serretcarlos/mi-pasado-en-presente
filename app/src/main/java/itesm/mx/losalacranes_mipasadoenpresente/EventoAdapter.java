package itesm.mx.losalacranes_mipasadoenpresente;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pablo on 5/3/2017.
 */

public class EventoAdapter extends ArrayAdapter<Evento> {
    private Context context;
    public EventoAdapter(Context context, ArrayList<Evento> eventos){
        super(context, 0, eventos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        ImageView ivUsuario = (ImageView) convertView.findViewById(R.id.picture_usuario);
        TextView tvUsuario = (TextView) convertView.findViewById(R.id.nombre_usuario_main);
        Evento evento = getItem(position);
        tvUsuario.setText(evento.getTitulo()+ " " + evento.getFecha());
        byte [] image = evento.getImagen();
        if (image != null){
            Bitmap bmImage = BitmapFactory.decodeByteArray(image, 0, image.length);
            ivUsuario.setImageBitmap(bmImage);
        }
        return convertView;
    }
}
