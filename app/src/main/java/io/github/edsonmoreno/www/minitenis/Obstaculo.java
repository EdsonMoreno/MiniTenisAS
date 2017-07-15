package io.github.edsonmoreno.www.minitenis;

import android.graphics.RectF;

/**
 * Created by Diego on 15/07/2017.
 */

public class Obstaculo extends Pelota {
    public Obstaculo(Game game) {
        super(game);
        xp=(int) (Math.random()*game.getAncho())-50;
    }

    @Override
    public void Actualizar(){
        if(yp+y <= 100){
            y=velocidad;
        }
        if(yp+y > game.getAlto()-50){
            game.raqueta.ReduceRaqueta();
            game.pierdeVida();
            game.quitarCuadro();
            reubicarCuadro();
        }
        if(RectF.intersects(getPelota(),game.raqueta.getRaqueta())){
            y=-velocidad;
        }
        yp+=y;
        pelota.set(xp, yp, xp+50, yp+50);
    }

    public void reubicarCuadro(){
        xp=(int) (Math.random()*game.getAncho())-50;
        yp=-20;
    }

}
