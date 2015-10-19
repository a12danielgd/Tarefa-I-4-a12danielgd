package com.example.daniel.tarefa_i_4_a12danielgd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarSpinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_spinner);
        Spinner spnDatos=(Spinner) findViewById(R.id.spnDatos);

        // Enlace do adaptador cos datos
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Tarefa_I_4_a12danielgd.linhas);
        spnDatos.setAdapter(adaptador);

        //Escoitador para o Spinner
        spnDatos.setOnItemSelectedListener(new OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MostrarSpinner.this, "Pos: "+position+" Valor: "+((TextView)view).getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
