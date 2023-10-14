package com.example.forcavenda.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forcavenda.Adapters.ItemPedidoAdapter;
import com.example.forcavenda.Controller.ClienteController;
import com.example.forcavenda.Controller.ItemController;
import com.example.forcavenda.Models.Cliente;
import com.example.forcavenda.Models.Item;
import com.example.forcavenda.Models.ItemPedido;
import com.example.forcavenda.R;

import java.util.ArrayList;

public class PedidoItemActivity extends AppCompatActivity {

    private double valorTotalPedido = 0.0;

    private Spinner spinnerCliente, spinnerItem, spinnerCondicaoPagamento, spinnerEnderecoEntrega;
    private EditText etQuantidade, etQuantidadeParcelas;
    private TextView tvValorUnitario, tvUnidadeMedida, tvTotalItens, tvValorFrete, tvValorTotalComDescontoAcrescimo;
    private Button btnAdicionarItem, btnConcluirPedido;
    private RecyclerView recyclerViewItensPedido, recyclerViewParcelas;
    private ClienteController clienteController;
    private ItemController itemController;
    private ArrayList<ItemPedido> listaItensPedido = new ArrayList<>();

    private ItemPedidoAdapter itemPedidoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_item);

        recyclerViewItensPedido = findViewById(R.id.recyclerViewItensPedido);

        clienteController = new ClienteController(this);
        itemController = new ItemController(this);

        itemPedidoAdapter = new ItemPedidoAdapter(listaItensPedido, itemController);
        recyclerViewItensPedido.setAdapter(itemPedidoAdapter);
        recyclerViewItensPedido.setLayoutManager(new LinearLayoutManager(this));


        // Inicialização dos componentes
        spinnerEnderecoEntrega = findViewById(R.id.spinnerEnderecoEntrega);
        spinnerCliente = findViewById(R.id.spinnerCliente);
        spinnerItem = findViewById(R.id.spinnerItem);
        spinnerCondicaoPagamento = findViewById(R.id.spinnerCondicaoPagamento);
        spinnerEnderecoEntrega = findViewById(R.id.spinnerEnderecoEntrega);
        etQuantidade = findViewById(R.id.etQuantidade);
        etQuantidadeParcelas = findViewById(R.id.etQuantidadeParcelas);
        tvValorUnitario = findViewById(R.id.tvValorUnitario);
        tvUnidadeMedida = findViewById(R.id.tvUnidadeMedida);
        tvValorFrete = findViewById(R.id.tvValorFrete);
        tvValorTotalComDescontoAcrescimo = findViewById(R.id.tvValorTotalComDescontoAcrescimo);
        btnAdicionarItem = findViewById(R.id.btnAdicionarItem);
        btnConcluirPedido = findViewById(R.id.btnConcluirPedido);
        recyclerViewParcelas = findViewById(R.id.recyclerViewParcelas);

        preencherSpinnerClientes();
        preencherSpinnerItens();
        preencherSpinnerEnderecos();


        String[] condicoesPagamento = new String[]{"Selecione", "À vista", "A prazo"};
        ArrayAdapter<String> adapterCondicao = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, condicoesPagamento);
        adapterCondicao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCondicaoPagamento.setAdapter(adapterCondicao);



        spinnerCondicaoPagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String condicao = (String) parent.getItemAtPosition(position);
                if ("A Prazo".equals(condicao)) {
                    etQuantidadeParcelas.setVisibility(View.VISIBLE);
                    recyclerViewParcelas.setVisibility(View.VISIBLE);
                } else {
                    etQuantidadeParcelas.setVisibility(View.GONE);
                    recyclerViewParcelas.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada a fazer aqui
            }
        });

        etQuantidadeParcelas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    int numParcelas = Integer.parseInt(s.toString());
                    double valorTotal = 1000; // Valor fixo para simplificar
                    double valorParcela = valorTotal / numParcelas;
                    Toast.makeText(PedidoItemActivity.this, "Valor de cada parcela: R$" + valorParcela, Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinnerItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nomeItemSelecionado = (String) parent.getItemAtPosition(position);
                atualizarInformacoesItem(nomeItemSelecionado);
            }

            private void atualizarInformacoesItem(String nomeItem) {
                ArrayList<Item> itens = itemController.listarItens();

                for (Item item : itens) {
                    if (item.getDescricao().equals(nomeItem)) {
                        tvValorUnitario.setText("Valor Unitário: " + item.getVlrUnitaro());
                        tvUnidadeMedida.setText("Unidade de Medida: " + item.getUnMedida());
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Listener para a condição de pagamento
        spinnerCondicaoPagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String condicao = (String) parent.getItemAtPosition(position);
                double valorFinal = valorTotalPedido; // Inicialize com o valor total do pedido

                if ("A Prazo".equals(condicao)) {
                    findViewById(R.id.etQuantidadeParcelas).setVisibility(View.VISIBLE);
                    recyclerViewParcelas.setVisibility(View.VISIBLE);

                    // Quando o usuário selecionar "A Prazo", vamos pegar a quantidade de parcelas
                    int quantidadeParcelas = Integer.parseInt(etQuantidadeParcelas.getText().toString());

                    // Calcula o acréscimo baseado na quantidade de parcelas
                    double acrescimo = valorTotalPedido * 0.05 * quantidadeParcelas;

                    valorFinal += acrescimo; // Adiciona o acréscimo ao valor final

                    // Aqui você pode também atualizar o RecyclerView com o valor de cada parcela
                    double valorParcela = valorFinal / quantidadeParcelas;
                    // Atualize o RecyclerView com o valorParcela

                } else {
                    etQuantidadeParcelas.setVisibility(View.GONE);
                    recyclerViewParcelas.setVisibility(View.GONE);
                    valorFinal -= valorTotalPedido * 0.05; // Desconto de 5%
                }

                tvValorTotalComDescontoAcrescimo.setText("Valor Total (com desconto/acréscimo): R$" + valorFinal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada a fazer aqui
            }
        });


        spinnerEnderecoEntrega.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String enderecoSelecionado = (String) parent.getItemAtPosition(position);
                calcularFrete(enderecoSelecionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada a fazer aqui
            }
        });





        // TODO: Adicione lógica para calcular o valor do frete com base no endereço de entrega

        btnAdicionarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeItemSelecionado = spinnerItem.getSelectedItem().toString();
                int quantidade = Integer.parseInt(etQuantidade.getText().toString());

                for (Item item : itemController.listarItens()) {
                    if (item.getDescricao().equals(nomeItemSelecionado)) {
                        ItemPedido itemPedido = new ItemPedido();
                        itemPedido.setItem(item);
                        itemPedido.setQuantidade(quantidade);
                        itemPedido.setValorUnitario(item.getVlrUnitaro());
                        listaItensPedido.add(itemPedido);
                        valorTotalPedido += itemPedido.getValorUnitario() * itemPedido.getQuantidade();
                        break;
                    }
                }

                itemPedidoAdapter.notifyDataSetChanged();

                Toast.makeText(PedidoItemActivity.this, "Item Adicionado com sucesso!", Toast.LENGTH_SHORT).show();

            }
        });

        btnConcluirPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concluirPedido();
            }
        });
    }

    private void calcularFrete(String endereco) {
        double frete = 0.0;
        if (!endereco.contains("Toledo-PR")) {
            frete += 20.0;
        }
        if (!endereco.contains("-PR")) {
            frete += 50.0;
        }
        tvValorFrete.setText("Valor do Frete: R$" + frete);
    }

    private void concluirPedido() {
        // Aqui você pode adicionar a lógica para concluir o pedido, como salvar no banco de dados, etc.
        // Por enquanto, apenas exibiremos uma mensagem de sucesso.
        Toast.makeText(this, "Pedido de venda cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void preencherSpinnerClientes() {
        ArrayList<Cliente> clientes = clienteController.listarClientes();
        ArrayList<String> nomesClientes = new ArrayList<>();

        for (Cliente cliente : clientes) {
            nomesClientes.add(cliente.getNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nomesClientes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCliente.setAdapter(adapter);
    }

    private void preencherSpinnerItens() {
        ArrayList<Item> itens = itemController.listarItens();
        ArrayList<String> nomesItens = new ArrayList<>();

        for (Item item : itens) {
            nomesItens.add(item.getDescricao()); // Supondo que a classe Item tenha um método getNome()
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nomesItens);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItem.setAdapter(adapter);
    }

    private void preencherSpinnerEnderecos() {
        ArrayList<String> enderecos = new ArrayList<>();
        enderecos.add("Endereço 1, Toledo-PR");
        enderecos.add("Endereço 2, Curitiba-PR");
        enderecos.add("Outro endereço");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, enderecos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEnderecoEntrega.setAdapter(adapter);
    }

    // TODO: Adicione métodos adicionais para gerenciar a condição de pagamento, calcular e mostrar as parcelas, gerenciar o endereço de entrega e calcular o frete, calcular o valor total com desconto ou acréscimo, etc.
}
