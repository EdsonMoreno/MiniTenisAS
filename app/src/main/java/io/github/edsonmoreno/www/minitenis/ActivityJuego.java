package io.github.edsonmoreno.www.minitenis;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Diego on 15/07/2017.
 */

public class ActivityJuego extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        game = new Game(this);
        game.setActivity(this);
        setContentView(game);
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.nuevoJuego();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(game.estaMuerto()){
            game.Detener();
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"has Perdido!",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            ganadores = new int[5];
            obtenerPuntajes();
            ubicarPuntaje(game.puntos());
            salvarPuntajes();
        }
    }

    private void salvarPuntajes(){
        //salvamos los cinco puntahes en orden
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editable = sharedPreferences.edit();
        editable.putInt("uno",ganadores[0]);
        editable.putInt("dos",ganadores[1]);
        editable.putInt("tres",ganadores[2]);
        editable.putInt("cuatro",ganadores[3]);
        editable.putInt("cinco",ganadores[4]);
        editable.apply();
    }

    private void  obtenerPuntajes(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        ganadores[0] = sharedPreferences.getInt("uno",0);
        ganadores[1] = sharedPreferences.getInt("dos",0);
        ganadores[2] = sharedPreferences.getInt("tres",0);
        ganadores[3] = sharedPreferences.getInt("cuatro",0);
        ganadores[4] = sharedPreferences.getInt("cinco",0);
    }

    private void ubicarPuntaje(int puntaje){
        int aux=-1, x;
        for(x=0; x<ganadores.length; x++){
            if(puntaje > ganadores[x] ){
                aux = x;
                break;
            }
        }

        //si aux es -1 es porque el puntaje ingresad es menor a cualquier puntaje de los que estan
        // ya en la lista
        if(aux == -1) return;

        x = (ganadores.length-1);
        while(x < aux){
            ganadores[x] = ganadores[x-1];
            x--;
        }
        ganadores[aux] = puntaje;
    }

    private int ganadores[];
    private Game game;
}
