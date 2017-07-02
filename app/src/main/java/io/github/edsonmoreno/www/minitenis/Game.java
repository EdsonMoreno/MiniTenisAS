package io.github.edsonmoreno.www.minitenis;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * Created by Edson D.  on 02/07/2017.
 */

public class Game extends SurfaceView {
    public Game (Context context){
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display pantalla = wm.getDefaultDisplay();
        Point point = new Point();
        pantalla.getSize(point);

        ancho = point.x;
        alto = point.y;
    }

    public void Actualizar(){

    }

    public void Pintar(){

    }

    private int ancho, alto;
}
