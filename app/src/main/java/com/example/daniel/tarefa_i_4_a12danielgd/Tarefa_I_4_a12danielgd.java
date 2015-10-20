package com.example.daniel.tarefa_i_4_a12danielgd;


import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class Tarefa_I_4_a12danielgd extends FragmentActivity {

    public static DialogoMostrar dialogo;
    boolean sdDisponhible = false;
    boolean sdAccesoEscritura = false;
    File dirFicheiroSD;
    File rutaCompleta;
    public static String nomeFicheiro = "ficheiro_SD.txt";
    public static ArrayList<String> linhas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa__i_4_a12danielgd);
        dialogo = new DialogoMostrar();
        comprobarEstadoSD();
        establecerDirectorioFicheiro();
        linhas = new ArrayList<>();

    }


    public void onClickMostrar(View view) {
        linhas = new ArrayList<>();
        lerFicheiro();
        FragmentManager fm = getSupportFragmentManager();
        dialogo.show(fm, "");
    }

    public void comprobarEstadoSD() {
        String estado = Environment.getExternalStorageState();
        Log.e("SD", estado);

        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            sdDisponhible = true;
            sdAccesoEscritura = true;
        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
            sdDisponhible = true;
    }

    public void establecerDirectorioFicheiro() {

        if (sdDisponhible) {
            // dirFicheiroSD = Environment.getExternalStorageDirectory();
            dirFicheiroSD = getExternalFilesDir(null);
            try {
                rutaCompleta = new File(dirFicheiroSD.getAbsolutePath(), nomeFicheiro);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }
    }

    public void onEscribirEngadirClick(View v) {

        EditText etMarca = (EditText) findViewById(R.id.etMarca);
        RadioButton rdbEngadir = (RadioButton) findViewById(R.id.rdbEngadir);
        RadioButton rdbSobreescribir = (RadioButton) findViewById(R.id.rdbSobreescribir);
        Calendar cal = Calendar.getInstance();
        boolean engadir = true;
        if (rdbEngadir.isChecked()) engadir = true;
        if (rdbSobreescribir.isChecked()) engadir = false;
        if (sdAccesoEscritura) {

            try {
                if (!etMarca.getText().toString().equals("")) {
                    OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(rutaCompleta, engadir));
                    osw.write(etMarca.getText() + " - " + cal.getTime().toString() + "\n");
                    osw.close();
                    Log.i("RUTA COMPLETA", rutaCompleta.getAbsolutePath());
                    Log.i("INTRODUCIDO", etMarca.getText() + " - " + cal.getTime().toString() + "\n");
                    etMarca.setText("");
                } else Log.e("SD", "Campo marca valeiro");
            } catch (Exception ex) {
                Log.e("SD", "Error escribindo no ficheiro");

            }
        } else {
            Toast.makeText(this, "A tarxeta SD non está en modo acceso escritura", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void lerFicheiro() {

        String linha;

        try {
            if (sdDisponhible) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(rutaCompleta)));
                while ((linha = br.readLine()) != null) {
                    linhas.add(linha);
                }
                br.close();
            } else {
                Toast.makeText(this, "A tarxeta SD non está en modo acceso lectura", Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (IOException ex) {
            Log.e("ERRRO","Non existe o ficheiro");
        } catch (Exception ex) {
            Toast.makeText(this, "Problemas lendo o ficheiro", Toast.LENGTH_SHORT).show();
            Log.e("INTERNA", "Erro lendo o ficheiro. ");

        }
    }

}
