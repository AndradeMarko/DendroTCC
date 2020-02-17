package br.com.ufpr.dendrodata.ui.activity.individuo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.model.Amostra;
import br.com.ufpr.dendrodata.model.Individuo;
import br.com.ufpr.dendrodata.ui.activity.individuo.view.ListaIndividuosView;

import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.KEY_AMOSTRA;
import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.KEY_INDIVIDUO;
import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.TITLE_APPBAR_LISTAINDIVIDUOS;

public class ListaIndividuosActivity extends AppCompatActivity {

    private ListaIndividuosView listaIndividuosView;
    private Amostra amostra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_individuos);
        setTitle(TITLE_APPBAR_LISTAINDIVIDUOS);
        listaIndividuosView = new ListaIndividuosView(this);
        configuraFABNovo();
        configuraLista();
    }

    @Override
    protected void onResume() {
        Intent dados = getIntent();
        amostra = (Amostra) dados.getSerializableExtra(KEY_AMOSTRA);
        super.onResume();
        listaIndividuosView.atualizaIndividuo(amostra.getId());
    }

    private void configuraFABNovo() {
        FloatingActionButton botaoNovoIndividuo = findViewById(R.id.activity_listaIndividuos_fab_novo);
        botaoNovoIndividuo.setOnClickListener(view -> abreFormularioInsereAmostra());
    }

    private void abreFormularioInsereAmostra() {
        Intent vaiParaFormIndividuo = new Intent(this, FormularioIndividuoActivity.class);

        Individuo individuo = new Individuo();
        individuo.setAmostraId(amostra.getId());
        individuo.setProjetoId(amostra.getProjetoId());

        vaiParaFormIndividuo.putExtra(KEY_INDIVIDUO, individuo);
        startActivity(vaiParaFormIndividuo);
    }

    private void configuraLista() {
        ListView listaIndividuos = findViewById(R.id.activity_lista_individuos_listview);
        listaIndividuosView.configuraAdapter(listaIndividuos);
        configuraListenerClickItem(listaIndividuos);
        registerForContextMenu(listaIndividuos);
    }

    private void configuraListenerClickItem(ListView listaIndividuos) {
        listaIndividuos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            listaIndividuosView.carrega(posicao);
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().
                inflate(R.menu.activity_listaindividuos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_listaindividuos_menu_remover:
                listaIndividuosView.confirmaRemocao(item);
                break;
            case R.id.activity_listaindividuos_menu_editar:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                listaIndividuosView.carrega(menuInfo.position);
                break;
            default:
                super.onContextItemSelected(item);
        }
        return true;
    }
}
