package br.com.ufpr.dendrodata.ui.activity.amostra;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.dao.AmostraDAO;
import br.com.ufpr.dendrodata.model.Amostra;
import br.com.ufpr.dendrodata.ui.activity.amostra.dialog.SalvaAmostraDialog;

public class ListaAmostrasActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR_AMOSTRAS = "Amostras Executadas do projeto: ";
    private AmostraDAO dao = new AmostraDAO();

    private ArrayAdapter<Amostra> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_amostras);
        setTitle(TITLE_APPBAR_AMOSTRAS);
        configuraFABNovo();
        configuraLista();

    }

    private void configuraFABNovo() {
        FloatingActionButton botaoNovaAmostra = findViewById(R.id.activity_listaAmostras_fab_novo);
        botaoNovaAmostra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioInsereAmostra();
            }
        });
    }

    private void abreFormularioInsereAmostra() {
        new SalvaAmostraDialog(this, this::salva).mostra();
    }

    private List<Amostra> salva(Amostra amostraCriada) {
        dao.salva(amostraCriada);
        return dao.todas();
    }

    private void configuraLista() {
        ListView listaAmostras = findViewById(R.id.lista_amostras_recyclerview);
        configuraAdapter(listaAmostras);
        configuraListenerClickItem(listaAmostras);
        registerForContextMenu(listaAmostras);
    }

    private void configuraAdapter(ListView ListaAmostras) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        ListaAmostras.setAdapter(adapter);
    }

    private void configuraListenerClickItem(ListView listaProjetos) {
        listaProjetos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Amostra amostraSelecionada = (Amostra) adapterView.getItemAtPosition(posicao);
                Toast.makeText(ListaAmostrasActivity.this, "Amostra Clicada para Trabalhar: " + amostraSelecionada.getParcela(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAmostras();
    }

    private void atualizaAmostras() {
        adapter.clear();
        adapter.addAll(dao.todas());
    }


}
