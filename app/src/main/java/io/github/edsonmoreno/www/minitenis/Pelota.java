package io.github.edsonmoreno.www.minitenis;

import android.graphics.RectF;

/**
 * Created by Diego on 02/07/2017.
 */

public class Pelota {
    public Pelota(Game game){
        this.game = game;
        ancho=game.getAncho();
        alto=game.getAlto();
        xp=(ancho/2)-20;
        yp=(alto/2)-20;
        x=y=velJuego=10;
        golpe=0;

        pelota = new RectF(xp,yp,xp+40,yp+40);
    }

    public RectF getPelota(){   return pelota;  }

    public void Actualizar(){
        if(xp+x < 0){
            x=velJuego;
        }
        if(xp+x > game.getAncho()-50){
            x=-velJuego;
        }
        if(yp+y < 0){
            y=velJuego;
        }
        if(yp+y > game.getAlto()-50){
            y=-velJuego;
        }
        xp+=x;
        yp+=y;
        pelota.set(xp, yp, xp+50, yp+50);
    }

    public boolean Colision(RectF rectF) {
        return pelota.intersect(rectF);
    }

    private int xp,yp,x,y, velJuego;
    private int ancho, alto;
    private RectF pelota;
    private Game game;
    private int golpe;
}
