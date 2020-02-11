package br.com.ufpr.dendrodata.ui.activity.projeto;

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
import br.com.ufpr.dendrodata.model.Projeto;
import br.com.ufpr.dendrodata.ui.activity.projeto.view.ListaProjetosView;

import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.TITLE_APPBAR_LISTAPROJETOS;

public class ListaProjetosActivity extends AppCompatActivity {

    private ListaProjetosView listaProjetosView;
//    private ListaProjetosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_projetos);
        setTitle(TITLE_APPBAR_LISTAPROJETOS);
        listaProjetosView = new ListaProjetosView(this);
        configuraFABNovo();
        configuraLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaProjetosView.atualizaProjetos();
    }

    private void configuraLista() {
        ListView listaProjetos = findViewById(R.id.activity_listaProjetos_listview);
        listaProjetosView.configuraAdapter(listaProjetos);
        configuraListenerClickItem(listaProjetos);
        registerForContextMenu(listaProjetos);
    }

    private void configuraListenerClickItem(ListView listaProjetos) {
        listaProjetos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Projeto projetoEscolhido = (Projeto) adapterView.getItemAtPosition(posicao);
            listaProjetosView.abreListaAmostras(projetoEscolhido);
        });
    }

    private void configuraFABNovo() {
        FloatingActionButton botaoNovoProjeto = findViewById(R.id.activity_listaProjetos_fab_novo);
        botaoNovoProjeto.setOnClickListener(view -> abreFormularioInsereProjeto());
    }

    private void abreFormularioInsereProjeto() {
        startActivity(new Intent(this, FormularioProjetoActivity.class));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().
                inflate(R.menu.activity_listaprojetos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_listaprojetos_menu_remover:
                listaProjetosView.confirmaRemocao(item);
                break;
            case R.id.activity_listaprojetos_menu_editar:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                listaProjetosView.carrega(menuInfo.position);
                break;
            default:
                super.onContextItemSelected(item);
        }
        return true;
    }
}