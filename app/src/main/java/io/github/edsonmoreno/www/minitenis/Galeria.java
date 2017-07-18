package io.github.edsonmoreno.www.minitenis;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Galeria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        TextView texto = (TextView) findViewById(R.id.puntaje_galeria);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int aux = sharedPreferences.getInt("puntos",0);
        texto.setText(""+aux);
    }

    public void Salir(View vista){
        finish();
    }
}
