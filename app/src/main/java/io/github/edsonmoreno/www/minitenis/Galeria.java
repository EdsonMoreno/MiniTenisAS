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

        ganadores = new int[5];
        for(Integer g:ganadores){
            g = 0;
        }
        TextView texto = (TextView) findViewById(R.id.p1);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int aux = sharedPreferences.getInt("puntos",0);
        texto.setText(""+aux+"\n"+aux);
    }

    public void Salir(View vista){
        insercionDirecta(ganadores);
        salvarPuntajes();
        finish();
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

    public static void insercionDirecta(int A[]){
        int p, j;
        int aux;
        for (p = 1; p < A.length; p++){ // desde el segundo elemento hasta
            aux = A[p]; // el final, guardamos el elemento y
            j = p - 1; // empezamos a comprobar con el anterior
            while ((j >= 0) && (aux < A[j])){ // mientras queden posiciones y el
                // valor de aux sea menor que los
                A[j + 1] = A[j];       // de la izquierda, se desplaza a
                j--;                   // la derecha
            }
            A[j + 1] = aux; // colocamos aux en su sitio
        }
    }

    private int ganadores[];
}
