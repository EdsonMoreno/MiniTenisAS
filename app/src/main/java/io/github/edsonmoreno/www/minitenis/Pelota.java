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
        yp=-20;
        y=1;
        x=0;
        velocidad=10;
        pelota = new RectF(xp,yp,xp+40,yp+40);
    }

    public RectF getPelota(){   return pelota;  }

    public void Actualizar(){
        if (xp + x <= 0) {
            x = velocidad;
        }
        if (xp + x > game.getAncho() - 50) {
            x = -velocidad;
        }
        if(yp+y <= 100){
            y=velocidad;
        }
        if(yp+y > game.getAlto()-50){
            if(game.estaMuerto()) {
                game.pierdeVida();
                reubicarBola();
            }
            else {
                y = 0;
                x = 0;
            }
        }
        if(RectF.intersects(getPelota(),game.raqueta.getRaqueta())){
            y=-velocidad;
            game.SumarPuntos();
            if(game.raqueta.getRaqueta().centerX() <= xp) x=-velocidad;
            if(game.raqueta.getRaqueta().centerX() > xp) x=velocidad;
        }
        xp+=x;
        yp+=y;
        pelota.set(xp, yp, xp+50, yp+50);
    }

    public void CambiarVelocidad(){
        velocidad+=1;
    }
    public int getVelocidad(){ return velocidad;   }
    public void reubicarBola(){
        xp=(ancho/2)-20;
        yp=-20;
    }

    private int xp,yp,x,y;
    private int ancho, alto, velocidad;
    private RectF pelota;
    private Game game;
}