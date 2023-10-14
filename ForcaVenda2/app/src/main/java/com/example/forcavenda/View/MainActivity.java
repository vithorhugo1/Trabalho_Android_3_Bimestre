package com.example.forcavenda.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.forcavenda.R;

public class MainActivity extends AppCompatActivity {

    Button btnCadastrarCliente, btnCadastrarEndereco, btnCadastrarItem, btnRealizarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCadastrarCliente = findViewById(R.id.btnCadastrarCliente);
        btnCadastrarEndereco = findViewById(R.id.btnCadastrarEndereco);
        btnCadastrarItem = findViewById(R.id.btnCadastrarItem);
        btnRealizarPedido = findViewById(R.id.btnRealizarPedido);

        btnCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroClienteActivity.class);
                startActivity(intent);
            }
        });

        btnCadastrarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroEnderecoActivity.class);
                startActivity(intent);
            }
        });

        btnCadastrarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroItemActivity.class);
                startActivity(intent);
            }
        });

        btnRealizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PedidoItemActivity.class);
                startActivity(intent);
            }
        });
    }
}
