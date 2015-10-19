package com.example.daniel.tarefa_i_4_a12danielgd;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by daniel on 19/10/15.
 */



public class DialogoMostrar extends DialogFragment {

    AlertDialog.Builder venta;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String infSevice = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(infSevice);
        View inflador = li.inflate(R.layout.dialogo_layout,null);
        final Button btnSpinner=(Button) inflador.findViewById(R.id.btnSpinner);
        final Button btnListView=(Button) inflador.findViewById(R.id.btnListView);

        venta = new AlertDialog.Builder(getActivity());
        venta.setTitle("Modo de mostrar");
        venta.setView(inflador);

        btnSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarSpinner = new Intent(getActivity(), MostrarSpinner.class);
                startActivity(mostrarSpinner);
                Tarefa_I_4_a12danielgd.dialogo.dismiss();
            }
        });

        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarListView = new Intent(getActivity(), MostrarListView.class);
                startActivity(mostrarListView);
                Tarefa_I_4_a12danielgd.dialogo.dismiss();
            }
        });

        return venta.create();
    }


}