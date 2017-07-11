package io.github.edsonmoreno.www.minitenis;

import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;

/**
 * Created by Diego on 02/07/2017.
 */

public class Raqueta {
    public Raqueta(Game game){
        this.game = game;
        xr=(game.getAncho()/2)-100;
        yr=game.getAlto()-200;
        raqueta = new RectF(xr,yr,xr+200,yr+30);
        x=0;
        tam_raq = 200;
    }

    public RectF getRaqueta(){  return raqueta; }


    public void Actualizar(){
        if(xr + x > 0 && xr + x < game.getAncho()-tam_raq ) xr+=x;
        this.raqueta.set(xr,yr,xr+tam_raq,yr+30);
    }

    public void Mover(MotionEvent event){
        float corx = event.getX();
        int action = MotionEventCompat.getActionMasked(event);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                x=10;
                if(corx < xr){
                    x*=-1;
                }
                if(corx > (xr+200)){
                    x*=1;
                }
            break;
            case MotionEvent.ACTION_UP:
                x=0;
            break;
        }
    }

    public boolean Colision(RectF rectF){
        return raqueta.intersect(rectF);
    }

    private float xr, yr, x, y, tam_raq;
    private RectF raqueta;
    private Game game;
}
