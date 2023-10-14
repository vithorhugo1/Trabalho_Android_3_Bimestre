package com.example.forcavenda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.forcavenda.Models.Cliente;
import com.example.forcavenda.DAO.ClienteDao;
import com.example.forcavenda.R;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import com.example.forcavenda.Models.Endereco;
import com.example.forcavenda.DAO.EnderecoDao;
import java.util.List;

public class CadastroClienteActivity extends AppCompatActivity {

    // Declaração dos componentes
    EditText etNome, etCPF, etDtNasc;
    Button btnSalvar, btnNovoEndereco;
    Spinner spinnerEnderecos;
    List<Endereco> listaEnderecos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        // Referenciando os componentes
        etNome = findViewById(R.id.et_nome);
        etCPF = findViewById(R.id.et_cpf);
        etDtNasc = findViewById(R.id.et_dtnasc);
        btnSalvar = findViewById(R.id.btn_salvar);
        spinnerEnderecos = findViewById(R.id.spinnerEnderecos);
        btnNovoEndereco = findViewById(R.id.btnNovoEndereco);

        // Carregar endereços no Spinner
        carregarEnderecos();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCliente();
            }
        });

        btnNovoEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroClienteActivity.this, CadastroEnderecoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarEnderecos();
    }

    private void carregarEnderecos() {
        EnderecoDao enderecoDao = EnderecoDao.getInstance(this);
        listaEnderecos = enderecoDao.getAll();

        ArrayAdapter<Endereco> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaEnderecos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEnderecos.setAdapter(adapter);
    }

    private void salvarCliente() {
        String nome = etNome.getText().toString();
        String cpf = etCPF.getText().toString();
        String dtNasc = etDtNasc.getText().toString();



        Endereco enderecoSelecionado = (Endereco) spinnerEnderecos.getSelectedItem();
        ArrayAdapter<Endereco> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaEnderecos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEnderecos.setAdapter(adapter);


        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setDtNasc(dtNasc);

        if (enderecoSelecionado != null) {
            cliente.setCodEndereco(enderecoSelecionado.getCodigoEnd());
        } else {
            Toast.makeText(this, "Por favor, selecione um endereço.", Toast.LENGTH_SHORT).show();
            return;
        }

        ClienteDao clienteDao = ClienteDao.getInstancia(this);
        long resultado = clienteDao.insert(cliente);

        if (resultado != -1) {
            Toast.makeText(this, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show();
            // Você pode adicionar código aqui para limpar os campos ou redirecionar o usuário para outra tela, se desejar.
        } else {
            Toast.makeText(this, "Erro ao salvar o cliente.", Toast.LENGTH_SHORT).show();
        }

    }
}

