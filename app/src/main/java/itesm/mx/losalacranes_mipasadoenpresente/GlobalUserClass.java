package itesm.mx.losalacranes_mipasadoenpresente;

import android.app.Application;

/**
 * Created by Carlos on 02/04/2017.
 */

public class GlobalUserClass extends Application {
    private Usuario user;

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



}
