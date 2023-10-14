package com.example.forcavenda.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.forcavenda.Models.Cliente;
import com.example.forcavenda.R; // Este import refere-se ao arquivo R do seu projeto. Se o nome do seu pacote for diferente, ajuste este import.

import java.util.ArrayList;
import java.util.List;


public class ClienteAdapter extends ArrayAdapter<Cliente> {
    private final Context context;
    private final List<Cliente> clientes;

    public ClienteAdapter(Context context, List<Cliente> clientes) {
        super(context, R.layout.spinner_item, clientes);
        this.context = context;
        this.clientes = clientes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.spinner_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.spinnerItemText);
        textView.setText(clientes.get(position).getNome());
        return rowView;
    }
}

