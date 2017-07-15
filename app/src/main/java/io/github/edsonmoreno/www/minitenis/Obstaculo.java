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

    public void Actualizar(){
        if(yp+y <= 100){
            y=velocidad;
        }
        if(yp+y > game.getAlto()-50){
            if(game.estaMuerto()) {
                game.raqueta.ReduceRaqueta();
                reubicarBola();
            }
            else {
                y = 0;
            }
        }
        if(RectF.intersects(getPelota(),game.raqueta.getRaqueta())){
            y=-velocidad;
            game.SumarPuntos();
        }
        yp+=y;
        pelota.set(xp, yp, xp+50, yp+50);
    }
}
