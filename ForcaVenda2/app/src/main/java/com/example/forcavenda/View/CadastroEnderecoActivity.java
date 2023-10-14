package com.example.forcavenda.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.forcavenda.Models.Endereco;
import com.example.forcavenda.DAO.EnderecoDao;
import com.example.forcavenda.R;

public class CadastroEnderecoActivity extends AppCompatActivity {

    EditText etLogradouro, etNumero, etBairro, etCidade, etUf;
    Button btnSalvarEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        etLogradouro = findViewById(R.id.et_logradouro);
        etNumero = findViewById(R.id.et_numero);
        etBairro = findViewById(R.id.et_bairro);
        etCidade = findViewById(R.id.et_cidade);
        etUf = findViewById(R.id.et_uf);
        btnSalvarEndereco = findViewById(R.id.btn_salvar_endereco);

        btnSalvarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarEndereco();
            }
        });
    }

    private void salvarEndereco() {
        String logradouro = etLogradouro.getText().toString();
        int numero = Integer.parseInt(etNumero.getText().toString());
        String bairro = etBairro.getText().toString();
        String cidade = etCidade.getText().toString();
        String uf = etUf.getText().toString();

        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(uf);

        EnderecoDao enderecoDao = EnderecoDao.getInstance(this);
        long resultado = enderecoDao.insert(endereco);

        if (resultado != -1) {
            Toast.makeText(this, "Endereço salvo com sucesso!", Toast.LENGTH_SHORT).show();
            finish(); // Fecha a atividade após salvar
        } else {
            Toast.makeText(this, "Erro ao salvar endereço.", Toast.LENGTH_SHORT).show();
        }
    }
}
