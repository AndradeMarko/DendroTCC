package br.com.ufpr.dendrodata.ui.activity.projeto.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.ufpr.dendrodata.dao.ProjetoDAO;
import br.com.ufpr.dendrodata.model.Projeto;
import br.com.ufpr.dendrodata.ui.activity.projeto.adapter.ListaProjetosAdapter;

public class ListaProjetosView {

    private final ListaProjetosAdapter adapter;
    private final ProjetoDAO dao;
    private final Context context;

    public ListaProjetosView(Context context) {
        this.context = context;
        this.adapter = new ListaProjetosAdapter(this.context);
        this.dao = new ProjetoDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo projeto")
                .setMessage("Tem certeza que deseja remover o projeto?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Projeto projetoEscolhido = adapter.getItem(menuInfo.position);
                    remove(projetoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }


    private void remove(Projeto projeto) {
        dao.remove(projeto);
        adapter.remove(projeto);
    }

    public void configuraAdapter(ListView listaDeProjetos) {
        listaDeProjetos.setAdapter(adapter);
    }

    public void atualizaProjetos() {
        adapter.atualiza(dao.todos());
    }

}
