package io.github.edsonmoreno.www.minitenis;

import android.graphics.RectF;
import android.view.MotionEvent;

/**
 * Created by Diego on 02/07/2017.
 */

public class Raqueta {
    public Raqueta(Game game){
        this.game = game;
        xr=(game.getAncho()/2)-50;
        yr=game.getAlto()-70;
        raqueta = new RectF(xr,yr,xr+100,yr+30);
        x=10;
    }

    public RectF getRaqueta(){  return raqueta; }

    private int dir=1;
    public void Actualizar(){
        if(x+xr > 0 && xr+x <= game.getAncho()-100)
            xr+=x;
        if(xr < game.getAncho()+100 && dir==1){
            xr=10;
        }
        else if(xr > game.getAncho()+100 && dir==1){
            dir = 0;
        }
        if(xr > 0 && dir ==0){
            xr=10;
        }
        else if(xr <= 0){
            dir = 1;
        }
    }

    public void Mover(MotionEvent event){
        float corx = event.getX();
        if(corx < xr && x == 10){
            x=-10;
        }
        if(corx > (xr+100) && x == -10){
            x=10;
        }
    }

    private float xr, yr, x, y;
    private RectF raqueta;
    private Game game;
}
