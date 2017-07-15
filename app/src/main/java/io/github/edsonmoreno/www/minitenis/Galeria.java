package io.github.edsonmoreno.www.minitenis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Galeria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
    }

    public void Salir(View vista){
        finish();
    }
}
