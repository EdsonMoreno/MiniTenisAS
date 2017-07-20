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
        //Obtenemos los puntajes con persistencia de datos
        //si el puntaje no esta colocamos un cero
        obtenerPuntajes();

        //Obtenemos las casillas de texto
        TextView uno = (TextView) findViewById(R.id.p1);
        TextView dos = (TextView) findViewById(R.id.p2);
        TextView tres = (TextView) findViewById(R.id.p3);
        TextView cuatro = (TextView) findViewById(R.id.p4);
        TextView cinco = (TextView) findViewById(R.id.p5);

        //rescatamos el puntaje de la ultima partida
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int aux = sharedPreferences.getInt("puntos",0);
        int aux_ultimo = sharedPreferences.getInt("ultimo_puntaje",0);
        if(aux != aux_ultimo){
            //ubicamos el puntaje nuevo en la cg
            ubicarPuntaje(aux);
            SharedPreferences.Editor spe = sharedPreferences.edit();
            spe.putInt("ultimo_puntaje",aux);
            spe.apply();
        }

        //muestro la lista actualzada
        uno.setText(""+ganadores[0]);
        dos.setText(""+ganadores[1]);
        tres.setText(""+ganadores[2]);
        cuatro.setText(""+ganadores[3]);
        cinco.setText(""+ganadores[4]);
    }

    public void Salir(View vista){
        //ordeno los puntajes
      //  insercionDirecta(ganadores);
        //guardo los puntajes ordenados
        salvarPuntajes();
        //cierro la galeria
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

    private void ubicarPuntaje(int puntaje){
        int aux=-1, x;
        for(x=0; x<ganadores.length; x++){
            if(puntaje > ganadores[x] ){
                aux = x;
                break;
            }
        }

        //si aux es -1 es porque el puntaje ingresad es menor a cualquier puntaje de los que estan
        // ya en la lista
        if(aux == -1) return;

        x = (ganadores.length-1);
        while(x < aux){
            ganadores[x] = ganadores[x-1];
            x--;
        }
        ganadores[aux] = puntaje;
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
