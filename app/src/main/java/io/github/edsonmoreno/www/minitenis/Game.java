package io.github.edsonmoreno.www.minitenis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
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
        paint.setColor(Color.WHITE);
        score =getResources().getString(R.string.puntajes);
        level = getResources().getString(R.string.nivel);
        life = getResources().getString(R.string.vidas);
        paint.setTextSize(30);

        ancho = point.x;
        alto = point.y;

        //calcula tres cuartos de pantalla para el texto d enivel
        tres_cuartos = ancho/4;
        un_cuarto = ancho/4;
        tres_cuartos*=3;

        nivel = 0;
        puntos = 0;
        vidas = 3;

        raqueta = new Raqueta(this);
        pelota = new Pelota(this);
    }

    public void Actualizar(){
        raqueta.Actualizar();
        pelota.Actualizar();
    }

    public void Pintar(){
        if(holder.getSurface().isValid()){
            canvas = holder.lockCanvas();
            canvas.drawColor(getResources().getColor(R.color.Suelo));
            canvas.drawRect(raqueta.getRaqueta(), paint);
            canvas.drawOval(pelota.getPelota(), paint);
            canvas.drawText(score+" : "+puntos,2,70,paint);
            canvas.drawText(level+" : "+nivel,tres_cuartos,70,paint);
            canvas.drawText(life+": "+vidas, un_cuarto, 2, paint);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public int getAncho(){  return ancho;   }
    public int getAlto(){   return alto;    }

    @Override
    public void run() {
        Pintar();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(jugando){
            long startFrameTime = System.currentTimeMillis();
            Actualizar();
            Pintar();
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
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

    public void Detener(){
        jugando=false;
        try {
            pintor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void SumarPuntos(){
        puntos++;
        if(puntos % 5 == 0){
            pelota.CambiarVelocidad();
            nivel++;
        }
    }

    public void nuevoJuego(){
        jugando=true;
        pintor = new Thread(this);
        pintor.start();
    }

    private int ancho, alto, puntos, nivel, vidas, tres_cuartos, un_cuarto;
    private String score, level, life;
    private boolean jugando;
    private Thread pintor;
    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    protected Raqueta raqueta;
    protected Pelota pelota;
    private long timeThisFrame, fps;
}
