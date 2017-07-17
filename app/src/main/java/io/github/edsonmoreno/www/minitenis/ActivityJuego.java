package io.github.edsonmoreno.www.minitenis;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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
        Toast.makeText(getApplicationContext(),"onCreate",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"onResume",Toast.LENGTH_SHORT).show();
        game.nuevoJuego();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(game.estaMuerto()){
            game.Detener();
        }else{
            System.out.println("-----------------ONPAUSE ELSE --------------");
          //  Toast.makeText(getApplicationContext(),"onPause",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private Game game;
}
