package br.com.ufpr.dendrodata.ui.activity.projeto.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import br.com.ufpr.dendrodata.database.DendroDataDatabase;
import br.com.ufpr.dendrodata.database.csv.CSVWriter;
import br.com.ufpr.dendrodata.database.dao.AmostraDAO;
import br.com.ufpr.dendrodata.database.dao.IndividuoDAO;
import br.com.ufpr.dendrodata.database.dao.ProjetoDAO;
import br.com.ufpr.dendrodata.model.Amostra;
import br.com.ufpr.dendrodata.model.Individuo;
import br.com.ufpr.dendrodata.model.Projeto;
import br.com.ufpr.dendrodata.ui.activity.amostra.ListaAmostrasActivity;
import br.com.ufpr.dendrodata.ui.activity.projeto.FormularioProjetoActivity;
import br.com.ufpr.dendrodata.ui.activity.projeto.adapter.ListaProjetosAdapter;

import static br.com.ufpr.dendrodata.ui.activity.const_n_masks.ConstantesActivities.KEY_PROJETO;

public class ListaProjetosView {

    private final ListaProjetosAdapter adapter;
    private final ProjetoDAO dao;
    private final Context context;


    public ListaProjetosView(Context context) {
        this.context = context;
        this.adapter = new ListaProjetosAdapter(this.context);
        dao = DendroDataDatabase.getInstance(context).getRoomProjetoDAO();
    }

    public void configuraAdapter(ListView listaDeProjetos) {
        listaDeProjetos.setAdapter(adapter);
    }

    public void atualizaProjetos() {
        adapter.atualiza(dao.todos());
    }

    public void confirmaRemocao(int index) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo projeto")
                .setMessage("Tem certeza que deseja remover o projeto?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    Projeto projetoEscolhido = adapter.getItem(index);
                    remove(projetoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void remove(Projeto projeto) {
        dao.remove(projeto);
        adapter.remove(projeto);
    }

    public void carrega(int index) {
        Projeto projetoEscolhido = adapter.getItem(index);
        abreFormularioEditaProjeto(projetoEscolhido);
    }

    private void abreFormularioEditaProjeto(Projeto projeto) {
        Intent vaiParaFormularioActivity = new Intent(context, FormularioProjetoActivity.class);
        vaiParaFormularioActivity.putExtra(KEY_PROJETO, projeto);
        context.startActivity(vaiParaFormularioActivity);
    }

    public void abreListaAmostras(Projeto projeto) {
        Intent vaiParaListaAmostrasActivity = new Intent(context, ListaAmostrasActivity.class);
        vaiParaListaAmostrasActivity.putExtra(KEY_PROJETO, projeto);
        context.startActivity(vaiParaListaAmostrasActivity);
    }

    public void exporta(int index) {
        Projeto projeto = adapter.getItem(index);
        new ExportDatabaseCSVTask(projeto).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public class ExportDatabaseCSVTask extends AsyncTask<String, Void, Boolean> {
        private final ProgressDialog dialog = new ProgressDialog(context);
        Projeto projeto;

        ExportDatabaseCSVTask(Projeto projeto) {
            this.projeto = projeto;
        }


        @Override
        protected Boolean doInBackground(final String... args) {
            String filename = projeto + ".csv";
            String filepath = "Projetos";
            File file = new File(context.getExternalFilesDir(filepath), filename);
            IndividuoDAO individuoDAO = DendroDataDatabase.getInstance(context).getRoomIndividuoDAO();
            AmostraDAO amostraDAO = DendroDataDatabase.getInstance(context).getRoomAmostraDAO();

            try {
                file.createNewFile();
                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                String[] column = {
                        "projetoId",
                        "projetoCodigo",
                        "fazenda",
                        "municipio",
                        "area",
                        "especie",
                        "quantidade",
                        "amostraId",
                        "numeroAmostra",
                        "coordenadaX",
                        "coordenadaY",
                        "espacamento",
                        "observacoes",
                        "individuoId",
                        "placa",
                        "dap",
                        "hcomercial",
                        "htotal",
                        "sanidade",
                        "qualidade",
                        "observacao",
                };
                csvWrite.writeNext(column);
                List<Amostra> amostras = amostraDAO.todas(projeto.getId());
                for (int a = 0; a < amostras.size(); a++) {
                    List<Individuo> individuos = individuoDAO.todos(amostras.get(a).getId());
                    for (int i = 0; i < individuos.size(); i++) {
                        String[] mySecondStringArray = {
                                String.valueOf(individuos.get(i).getProjetoId()),
                                projeto.getCodigo(),
                                projeto.getFazenda(),
                                projeto.getMunicipio(),
                                projeto.getArea(),
                                projeto.getEspecie(),
                                projeto.getQuantidade(),
                                String.valueOf(amostras.get(a).getId()),
                                amostras.get(a).getNumero(),
                                amostras.get(a).getCoordX(),
                                amostras.get(a).getCoordY(),
                                amostras.get(a).getEspacamento(),
                                amostras.get(a).getObservacao(),
                                String.valueOf(individuos.get(i).getId()),
                                individuos.get(i).getPlaca(),
                                individuos.get(i).getDap(),
                                individuos.get(i).getHcomercial(),
                                individuos.get(i).getHtotal(),
                                individuos.get(i).getSanidade(),
                                individuos.get(i).getQualidade(),
                                individuos.get(i).getObservacao()};

                        csvWrite.writeNext(mySecondStringArray);
                    }
                }

                csvWrite.close();
                return true;
            } catch (
                    IOException e) {
                return false;
            }
        }

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();

        }

        protected void onPostExecute(final Boolean success) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (success) {
                Toast.makeText(context, "Export successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Export failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
