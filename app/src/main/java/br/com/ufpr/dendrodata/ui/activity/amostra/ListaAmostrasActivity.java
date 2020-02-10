package br.com.ufpr.dendrodata.ui.activity.amostra;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.model.Amostra;
import br.com.ufpr.dendrodata.model.Projeto;
import br.com.ufpr.dendrodata.ui.activity.amostra.adapter.ListaAmostrasAdapter;
import br.com.ufpr.dendrodata.ui.activity.amostra.view.ListaAmostrasView;

import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.KEY_AMOSTRA;
import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.KEY_PROJETO;
import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.TITLE_APPBAR_LISTAAMOSTRAS;

public class ListaAmostrasActivity extends AppCompatActivity {

    private ListaAmostrasView listaAmostrasView;
    private ListaAmostrasAdapter adapter;
    private Projeto projeto;

//    public ListaAmostrasActivity() {
//        Intent dados = getIntent();
//        projeto = (Projeto) dados.getSerializableExtra(KEY_PROJETO);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_amostras);
        setTitle(TITLE_APPBAR_LISTAAMOSTRAS);
        listaAmostrasView = new ListaAmostrasView(this);
        configuraFABNovo();
        configuraLista();
    }

    @Override
    protected void onResume() {
        Intent dados = getIntent();
        projeto = (Projeto) dados.getSerializableExtra(KEY_PROJETO);
        super.onResume();
        listaAmostrasView.atualizaAmostra(projeto.getId());
    }

    private void configuraFABNovo() {
        FloatingActionButton botaoNovaAmostra = findViewById(R.id.activity_listaAmostras_fab_novo);
        botaoNovaAmostra.setOnClickListener(view -> abreFormularioInsereAmostra());
    }

    private void abreFormularioInsereAmostra() {
        Intent vaiParaFormAmostra = new Intent(this, FormularioAmostraActivity.class);

        Amostra amostra = new Amostra();
        amostra.setProjetoId(projeto.getId());

        vaiParaFormAmostra.putExtra(KEY_AMOSTRA, amostra);
        startActivity(vaiParaFormAmostra);
    }

    private void configuraLista() {
        ListView listaAmostras = findViewById(R.id.activity_lista_amostras_listview);
        listaAmostrasView.configuraAdapter(listaAmostras);
        configuraListenerClickItem(listaAmostras);
        registerForContextMenu(listaAmostras);
    }

    /* Não sei pra q isso serve mas arrumei pq tava errado. Se tu fez o edit lá dentro da view, usa sempre lá. */
    private void configuraListenerClickItem(ListView listaAmostras) {
        listaAmostras.setOnItemClickListener((adapterView, view, posicao, id) -> {
            listaAmostrasView.carrega(posicao);
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().
                inflate(R.menu.activity_listaamostra_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_listaamostras_menu_remover:
                listaAmostrasView.confirmaRemocao(item);
                break;
            case R.id.activity_listaamostras_menu_editar:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                listaAmostrasView.carrega(menuInfo.position);
                break;
            default:
                super.onContextItemSelected(item);
        }
        return true;
    }

}