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
 * Created by Carlos on 01/04/2017.
 */

public class UsuarioAdapter extends ArrayAdapter<Usuario> {
    private Context context;
    public UsuarioAdapter(Context context, ArrayList<Usuario> usuarios){
        super(context, 0, usuarios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        ImageView ivUsuario = (ImageView) convertView.findViewById(R.id.picture_usuario);
        TextView tvUsuario = (TextView) convertView.findViewById(R.id.nombre_usuario_main);
        Usuario usuario = getItem(position);
        tvUsuario.setText(usuario.getNombre()+ " " + usuario.getApellido());
        byte [] image = usuario.getImagen();
        if (image != null){
            Bitmap bmImage = BitmapFactory.decodeByteArray(image, 0, image.length);
            ivUsuario.setImageBitmap(bmImage);
        }
        return convertView;
    }
}
