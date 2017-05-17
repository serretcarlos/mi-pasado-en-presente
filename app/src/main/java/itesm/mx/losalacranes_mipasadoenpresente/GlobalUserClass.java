
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

import android.app.Application;

/**
 * Created by Carlos on 02/04/2017.
 */

public class GlobalUserClass extends Application {
    private Usuario user;
    private int soundIndex = 0;

    private int sound = 1;


    public int getSound() {
        return sound;
    }

    public void setSound(int i) {
        this.sound = i;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
   public int getSoundIndex(){return soundIndex;}

   public void setSoundIndex(int index){this.soundIndex = index;}


}
