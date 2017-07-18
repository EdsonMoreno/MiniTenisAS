package io.github.edsonmoreno.www.minitenis;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Galeria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        TextView texto = (TextView) findViewById(R.id.p1);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int aux = sharedPreferences.getInt("puntos",0);
        texto.setText(""+aux+"\n"+aux);
    }

    public void Salir(View vista){
        finish();
    }
    public void ordenarQuicksort(int[] vector, int primero, int ultimo){
        int i=primero, j=ultimo;
        int pivote=vector[(primero + ultimo) / 2];
        int auxiliar;
        do{
            while(vector[i]<pivote) i++;
            while(vector[j]>pivote) j--;
            if (i<=j){
                auxiliar=vector[j];
                vector[j]=vector[i];
                vector[i]=auxiliar;
                i++;
                j--;
            }
        } while (i<=j);
        if(primero<j) ordenarQuicksort(vector,primero, j);
        if(ultimo>i) ordenarQuicksort(vector,i, ultimo);
    }
}
