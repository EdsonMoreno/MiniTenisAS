package io.github.edsonmoreno.www.minitenis;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Diego on 15/07/2017.
 */

public class ActivityJuego extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game(this);
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
        game.Detener();
    }

    private Game game;
}
