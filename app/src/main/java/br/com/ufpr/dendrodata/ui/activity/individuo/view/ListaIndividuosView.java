package br.com.ufpr.dendrodata.ui.activity.individuo.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.ufpr.dendrodata.database.DendroDataDatabase;
import br.com.ufpr.dendrodata.database.dao.IndividuoDAO;
import br.com.ufpr.dendrodata.model.Individuo;
import br.com.ufpr.dendrodata.ui.activity.individuo.FormularioIndividuoActivity;
import br.com.ufpr.dendrodata.ui.activity.individuo.adapter.ListaIndividuosAdapter;

import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.KEY_INDIVIDUO;

public class ListaIndividuosView {


    private ListaIndividuosAdapter adapter;
    private IndividuoDAO dao;
    private Context context;

    public ListaIndividuosView(Context context) {
        this.context = context;
        this.adapter = new ListaIndividuosAdapter(this.context);
        dao = DendroDataDatabase.getInstance(context).getRoomIndividuoDAO();
    }

    public void configuraAdapter(ListView listaIndividuos) {
        listaIndividuos.setAdapter(adapter);
    }

    public void atualizaIndividuo(int amostraId) {
        adapter.atualiza(dao.todos(amostraId));

    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Remover Indivíduo")
                .setMessage("Tem certeza que deseja remover este indivíduo?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Individuo individuoEscolhido = adapter.getItem(menuInfo.position);
                    remove(individuoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void remove(Individuo individuo) {
        dao.remove(individuo);
        adapter.remove(individuo);
    }

    public void carrega(int index) {
        Individuo individuoEscolhido = adapter.getItem(index);
        abreFormularioEditaIndividuo(individuoEscolhido);
    }

    private void abreFormularioEditaIndividuo(Individuo individuo) {
        Intent vaiParaFormularioActivity = new Intent(context, FormularioIndividuoActivity.class);
        vaiParaFormularioActivity.putExtra(KEY_INDIVIDUO, individuo);
        context.startActivity(vaiParaFormularioActivity);
    }
}
