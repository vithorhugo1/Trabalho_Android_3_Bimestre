package com.example.forcavenda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcavenda.DAO.ItemDao;
import com.example.forcavenda.Models.Item;
import com.example.forcavenda.R;

public class CadastroItemActivity extends AppCompatActivity {

    private EditText etDescricao, etValor, etUnidadeMedida;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item); // Ajuste para o nome correto do seu layout

        // Inicializar os componentes
        etDescricao = findViewById(R.id.et_descricao);
        etValor = findViewById(R.id.et_valor);
        etUnidadeMedida = findViewById(R.id.et_unidade_medida);
        btnSalvar = findViewById(R.id.btn_salvar_item);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarItem();
            }
        });

    }

    private void salvarItem() {
        String descricao = etDescricao.getText().toString();
        double valor = Double.parseDouble(etValor.getText().toString()); // Certifique-se de tratar possíveis exceções aqui
        String unidadeMedida = etUnidadeMedida.getText().toString();

        Item item = new Item();
        item.setDescricao(descricao);
        item.setVlrUnitaro(valor);
        item.setUnMedida(unidadeMedida);

        ItemDao itemDao = ItemDao.getInstancia(this); // Ajuste para o método correto de obtenção da instância
        long resultado = itemDao.insert(item);

        if (resultado != -1) {
            Toast.makeText(this, "Item salvo com sucesso!", Toast.LENGTH_SHORT).show();
            // Você pode querer limpar os campos ou navegar para outra tela aqui
        } else {
            Toast.makeText(this, "Erro ao salvar o item.", Toast.LENGTH_SHORT).show();
        }
    }

}
