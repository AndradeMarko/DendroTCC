package br.com.ufpr.dendrodata.ui.activity.projeto.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.ufpr.dendrodata.database.DendroDataDatabase;
import br.com.ufpr.dendrodata.database.dao.ProjetoDAO;
import br.com.ufpr.dendrodata.model.Projeto;
import br.com.ufpr.dendrodata.ui.activity.projeto.FormularioProjetoActivity;
import br.com.ufpr.dendrodata.ui.activity.projeto.ListaProjetosActivity;
import br.com.ufpr.dendrodata.ui.activity.projeto.adapter.ListaProjetosAdapter;

import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.KEY_PROJETO;

public class ListaProjetosView extends AppCompatActivity {

    private final ListaProjetosAdapter adapter;
    private final ProjetoDAO dao;
    private final Context context;

    public ListaProjetosView(Context context) {
        this.context = context;
        this.adapter = new ListaProjetosAdapter(this.context);
        dao = DendroDataDatabase.getInstance(context).getRoomProjetoDAO();
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

    public void carrega(MenuItem item) {
        Intent vaiParaFormularioActivity = new Intent(context, FormularioProjetoActivity.class);
        startActivity(vaiParaFormularioActivity);

//        AdapterView.AdapterContextMenuInfo menuInfo =
//                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        Projeto projetoEscolhido = adapter.getItem(menuInfo.position);
//        abreFormularioEditaProjeto(projetoEscolhido);
    }

    private void abreFormularioEditaProjeto(Projeto projeto) {
        Intent vaiParaFormularioActivity = new Intent(context, FormularioProjetoActivity.class);
//        vaiParaFormularioActivity.putExtra(KEY_PROJETO, projeto);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraListenerClickItem(ListView listaProjetos) {
        listaProjetos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Projeto projetoEscolhido = (Projeto) adapterView.getItemAtPosition(posicao);
            abreFormularioEditaProjeto(projetoEscolhido);
        });
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
