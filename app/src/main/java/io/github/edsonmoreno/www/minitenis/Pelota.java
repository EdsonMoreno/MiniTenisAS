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
        yp=-alto*40;
        y=1;
        x=0;
        pelota = new RectF(xp,yp,xp+40,yp+40);
    }

    public RectF getPelota(){   return pelota;  }

    public void Actualizar(){
        if (xp + x <= 0) {
            x = 10;
        }
        if (xp + x > game.getAncho() - 50) {
            x = -10;
        }
        if(yp+y < 0){
            y=10;
        }
        if(yp+y > game.getAlto()-50){
            y=0;
            x=0;
        }
        if(RectF.intersects(getPelota(),game.raqueta.getRaqueta())){
            y=-10;
            int num =(int) (Math.random()*2)+1;
            switch (num){
                case 0:
                    x=0;
                    break;
                case 1:
                    x=10;
                    break;
                case 2:
                    x=-10;
                    break;
            }

        }
        xp+=x;
        yp+=y;
        pelota.set(xp, yp, xp+50, yp+50);
    }

    private int xp,yp,x,y;
    private int ancho, alto;
    private RectF pelota;
    private Game game;
}