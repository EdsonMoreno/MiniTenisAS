package io.github.edsonmoreno.www.minitenis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.TextView;

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
        score =getResources().getString(R.string.puntajes);
        paint.setTextSize(30);

        ancho = point.x;
        alto = point.y;
        vdjuego=10;

        raqueta = new Raqueta(this);
        pelota = new Pelota(this);

        puntos = 0;
        jugando=true;
        pintor = new Thread(this);
        pintor.start();
    }

    public void Actualizar(){
        raqueta.Actualizar();
        pelota.Actualizar();
    }

    public void Pintar(){
        if(holder.getSurface().isValid()){
            canvas = holder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            canvas.drawRect(raqueta.getRaqueta(), paint);
            canvas.drawOval(pelota.getPelota(), paint);
            canvas.drawText(score+" : "+puntos,2,70,paint);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public int getAncho(){  return ancho;   }
    public int getAlto(){   return alto;    }
    public int getVdjuego(){    return vdjuego; }
    public boolean getJugando(){    return jugando;  }

    public void cambiarVelocidad (int nueva_velocidad){ vdjuego+=nueva_velocidad;   }

    public void setJugando(boolean jugando){ this.jugando = jugando;  }
    public void setScore(int score){    this.puntos+=score;  }

    @Override
    public void run() {
        while(jugando){
            Actualizar();
            Pintar();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        raqueta.Mover(event);
        int a = event.getActionMasked();
        switch (a){
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return super.onTouchEvent(event);
    }

    private int ancho, alto, puntos, vdjuego;
    private String score;
    private boolean jugando;
    private Thread pintor;
    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    Raqueta raqueta;
    Pelota pelota;
}
