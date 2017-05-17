
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
