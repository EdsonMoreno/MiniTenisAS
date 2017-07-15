package io.github.edsonmoreno.www.minitenis;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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

    }

    public void  newGame(View vista){

    }

}
