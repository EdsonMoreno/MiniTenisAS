package io.github.edsonmoreno.www.minitenis;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Edson D.  on 02/07/2017.
 */

public class Game extends SurfaceView implements Runnable {
    public Game(Context context) {
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display pantalla = wm.getDefaultDisplay();
        Point point = new Point();
        pantalla.getSize(point);

        holder = getHolder();
        paint = new Paint();
        paint.setColor(Color.WHITE);
        score = getResources().getString(R.string.puntajes);
        level = getResources().getString(R.string.nivel);
        life = getResources().getString(R.string.vidas);
        paint.setTextSize(30);

        ancho = point.x;
        alto = point.y;

        //calcula tres cuartos de pantalla para el texto d enivel
        tres_cuartos = ancho / 4;
        un_cuarto = (ancho / 4) + 45;
        tres_cuartos *= 3;

        nivel = 0;
        puntos = 0;
        vidas = 2;

        raqueta = new Raqueta(this);
        pelota = new Pelota(this);
        pelotas = new Obstaculo[5];
        for (int x = 0; x < pelotas.length; x++) {
            pelotas[x] = new Obstaculo(this);
        }
        pelotas[0].defineColor(Color.GREEN);
        pelotas[1].defineColor(Color.BLUE);
        pelotas[2].defineColor(Color.RED);
        pelotas[3].defineColor(Color.YELLOW);
        pelotas[4].defineColor(Color.GRAY);
    }

    public int getAncho() {
        return ancho;
    }
    public void setActivity(Activity a){    activity = a;   }
    public int getAlto(){  return alto;    }
    public void ganaVida(){ vidas++;    }

    public void pierdeVida() {
        vidas--;
        if (vidas < 0) {
            jugando = false;
            activity.finish();
        }
    }

    public void finDeJuego() {
        nivel = 0;
        puntos = 0;
        vidas = 2;
        jugando = false;
    }

    public void Actualizar() {
        raqueta.Actualizar();
        pelota.Actualizar();
        for (Obstaculo o : pelotas) {
            if (o.isVisible()) o.Actualizar();
        }
    }

    public void Pintar() {
        if (holder.getSurface().isValid()) {
            canvas = holder.lockCanvas();
            canvas.drawColor(getResources().getColor(R.color.Suelo));
            //Barra superior
            paint.setColor(Color.BLACK);
            canvas.drawRect(0, 0, ancho, 100, paint);
            //Resto del campo
            paint.setColor(Color.WHITE);
            canvas.drawRect(raqueta.getRaqueta(), paint);
            canvas.drawOval(pelota.getPelota(), paint);
            for (int x = 0; x < pelotas.length; x++) {
                paint.setColor(pelotas[x].colorPelota());
                if (pelotas[x].isVisible()) {
                    canvas.drawRect(pelotas[x].getPelota(), paint);
                }
            }
            paint.setColor(Color.WHITE);
            canvas.drawText(score + " : " + puntos, 2, 70, paint);
            canvas.drawText(level + " : " + nivel, tres_cuartos, 70, paint);
            canvas.drawText(life + ": " + vidas, un_cuarto, 70, paint);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public boolean estaMuerto() {
        if (vidas >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void Detener() {
        jugando = false;
    }

    public void SumarPuntos() {
        puntos++;
        if (puntos % 5 == 0) {
            pelota.CambiarVelocidad();
            nivel++;

            if (pelotas[3].isVisible()) pelotas[4].mostrarCuadro();
            if (pelotas[2].isVisible()) pelotas[3].mostrarCuadro();
            if (pelotas[1].isVisible()) pelotas[2].mostrarCuadro();
            if (pelotas[0].isVisible()) pelotas[1].mostrarCuadro();
            pelotas[0].mostrarCuadro();
        }
    }

    public void nuevoJuego() {
        if (vidas < 0) this.finDeJuego();
        jugando = true;
        pintor = new Thread(this);
        pintor.start();
    }

    @Override
    public void run() {
        Pintar();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (jugando) {
            Actualizar();
            Pintar();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        raqueta.Mover(event);
        int a = event.getActionMasked();
        switch (a) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return super.onTouchEvent(event);
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
    protected Obstaculo pelotas[];
    public Activity activity;

    public int puntos() {   return  puntos; }
    public int vidas() {    return vidas;   }
}