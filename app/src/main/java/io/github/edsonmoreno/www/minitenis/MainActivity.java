package io.github.edsonmoreno.www.minitenis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bloquea la actividad en vertical
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
    }

    public void dameInfo(View vista){
        Intent intencion = new Intent(this, Informacion.class);
        startActivity(intencion);
    }

    public void highScore(View vista){
        Intent galeria = new Intent(this, Galeria.class);
        startActivity(galeria);
    }

    public void  newGame(View vista){
        Intent intencion_juego = new Intent(this, ActivityJuego.class);
        startActivity(intencion_juego);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Si no encuentra el valor guardado reinicia la variable
        puntos = sharedPreferences.getInt("puntos",0);
    }

    private int puntos;
}
