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
            //Obtener sharedpreferences por defecto
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            //crear  objeto editable shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //modificamos el archivo
            editor.putInt("puntos",game.puntos());
            //guardamos cambios
            editor.apply();
        }
    }

    private Game game;
}
