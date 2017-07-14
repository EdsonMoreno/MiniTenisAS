package io.github.edsonmoreno.www.minitenis;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bloquea la actividad en vertical
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        game = new Game(this);
        setContentView(game);
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.Detener();
    }


    @Override
    protected void onResume() {
        super.onResume();
        game.nuevoJuego();
    }

    private Game game;
}
