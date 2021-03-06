package io.github.edsonmoreno.www.minitenis;

import android.graphics.Color;
import android.graphics.RectF;

/**
 * Created by Diego on 15/07/2017.
 */

public class Obstaculo extends Pelota {
    public Obstaculo(Game game) {
        super(game);
        xp=(int) (Math.random()*game.getAncho())-50;
        visible = false;
    }

    @Override
    public void Actualizar(){
        if(yp+y <= 100){
            y=velocidad;
        }
        if(yp+y > game.getAlto()-50){
            game.raqueta.ReduceRaqueta();
            if(game.estaMuerto())   game.pierdeVida();
            visible = false;
            reubicarCuadro();
        }
        if(RectF.intersects(getPelota(),game.raqueta.getRaqueta())){
            y=-velocidad;
            golpe++;
        }
        yp+=y;
        pelota.set(xp, yp, xp+50, yp+50);
        ganarVida();
    }

    private void reubicarCuadro(){
        xp=(int) (Math.random()*game.getAncho())-50;
        yp=-20;
        golpe = 0;
    }

    private void ganarVida(){
        if(golpe == 10){
            game.ganaVida();
            golpe = 0;
        }
    }

    public void mostrarCuadro(){    visible = true; }
    public boolean isVisible(){ return visible; }
    public int colorPelota(){ return color;   }
    public void defineColor(int c){
        color = c;
    }

    private boolean visible;
    private int color, golpe;
}
