package com.example.daniel.tarefa_i_4_a12danielgd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_list_view);
        ListView lvFroitas = (ListView) findViewById(R.id.lvDatos);

        //Fonte de datos

        //Enlace do adaptador coa fonte de datos
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Tarefa_I_4_a12danielgd.linhas);

        //Enlace do adaptador co ListView
        lvFroitas.setAdapter(adaptador);

        //Escoitador para o ListView
        lvFroitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MostrarListView.this, "Pos: " + position + " Valor: " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
