package br.com.ufpr.dendrodata.ui.activity.amostra.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.ufpr.dendrodata.database.DendroDataDatabase;
import br.com.ufpr.dendrodata.database.dao.AmostraDAO;
import br.com.ufpr.dendrodata.model.Amostra;
import br.com.ufpr.dendrodata.ui.activity.amostra.FormularioAmostraActivity;
import br.com.ufpr.dendrodata.ui.activity.amostra.adapter.ListaAmostrasAdapter;
import br.com.ufpr.dendrodata.ui.activity.individuo.ListaIndividuosActivity;

import static br.com.ufpr.dendrodata.ui.activity.const_n_masks.ConstantesActivities.KEY_AMOSTRA;

public class ListaAmostrasView {

    private final ListaAmostrasAdapter adapter;
    private final AmostraDAO dao;
    private final Context context;

    public ListaAmostrasView(Context context) {
        this.context = context;
        this.adapter = new ListaAmostrasAdapter(this.context);
        dao = DendroDataDatabase.getInstance(context).getRoomAmostraDAO();
    }

    public void configuraAdapter(ListView listaAmostras) {
        listaAmostras.setAdapter(adapter);
    }

    public void atualizaAmostra(int projetoId) {
        adapter.atualiza(dao.todas(projetoId));
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Remover Amostra")
                .setMessage("Tem certeza que deseja remover esta amostra?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Amostra amostraEscolhida = adapter.getItem(menuInfo.position);
                    remove(amostraEscolhida);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void remove(Amostra amostra) {
        dao.remove(amostra);
        adapter.remove(amostra);
    }

    public void carrega(int index) {
        Amostra amostraEscolhida = adapter.getItem(index);
        abreFormularioEditaAmostra(amostraEscolhida);
    }

    private void abreFormularioEditaAmostra(Amostra amostra) {
        Intent vaiParaFormularioActivity = new Intent(context, FormularioAmostraActivity.class);
        vaiParaFormularioActivity.putExtra(KEY_AMOSTRA, amostra);
        context.startActivity(vaiParaFormularioActivity);
    }

    public void abreListaIndividuos(Amostra amostra) {
        Intent vaiParaListaIndividuosActivity = new Intent(context, ListaIndividuosActivity.class);
        vaiParaListaIndividuosActivity.putExtra(KEY_AMOSTRA, amostra);
        context.startActivity(vaiParaListaIndividuosActivity);
    }
}
