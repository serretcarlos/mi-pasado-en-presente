package itesm.mx.losalacranes_mipasadoenpresente;

import android.app.Application;

/**
 * Created by Carlos on 02/04/2017.
 */

public class GlobalUserClass extends Application {
    private Usuario user;

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

}
