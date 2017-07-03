package io.github.edsonmoreno.www.minitenis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * Created by Edson D.  on 02/07/2017.
 */

public class Game extends SurfaceView implements Runnable {
    public Game (Context context){
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display pantalla = wm.getDefaultDisplay();
        Point point = new Point();
        pantalla.getSize(point);

        holder = getHolder();
        paint = new Paint();
        paint.setColor(Color.BLACK);

        ancho = point.x;
        alto = point.y;

        raqueta = new Raqueta(this);

        jugando=true;
        pintor = new Thread(this);
        pintor.start();
    }

    public void Actualizar(){
        raqueta.Actualizar();
    }

    public void Pintar(){
        if(holder.getSurface().isValid()){
            canvas = holder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            canvas.drawRect(raqueta.getRaqueta(), paint);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public int getAncho(){  return ancho;   }
    public int getAlto(){   return alto;    }

    @Override
    public void run() {
        while(jugando){
            Actualizar();
            Pintar();
        }
    }

    private int ancho, alto;
    private boolean jugando;
    private Thread pintor;
    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    private Raqueta raqueta;
}
