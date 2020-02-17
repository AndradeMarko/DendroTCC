package br.com.ufpr.dendrodata.ui.activity.projeto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.database.DendroDataDatabase;
import br.com.ufpr.dendrodata.database.dao.AmostraDAO;
import br.com.ufpr.dendrodata.model.Amostra;
import br.com.ufpr.dendrodata.model.Projeto;

public class ListaProjetosAdapter extends BaseAdapter {

    private final List<Projeto> projetos = new ArrayList<>();
    private final Context context;


    public ListaProjetosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return projetos.size();
    }

    @Override
    public Projeto getItem(int posicao) {
        return projetos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return projetos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Projeto projetoDevolvido = projetos.get(posicao);
        vincula(viewCriada, projetoDevolvido);
        return viewCriada;
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_projeto, viewGroup, false);
    }

    private void vincula(View view, Projeto projeto) {
        AmostraDAO amostraDAO = DendroDataDatabase.getInstance(context).getRoomAmostraDAO();
        TextView codigo = view.findViewById(R.id.item_projeto_codigo);
        codigo.setText(projeto.getCodigo());
        TextView area = view.findViewById(R.id.item_projeto_area);
        area.setText(projeto.getArea() + " hectares.");
        TextView municipio = view.findViewById(R.id.item_projeto_municipio);
        municipio.setText(projeto.getMunicipio());
        TextView quantidade = view.findViewById(R.id.item_projeto_parcelas);
        quantidade.setText(amostraDAO.todas(projeto.getId()).size() + "/" + projeto.getQuantidade());
    }

    public void remove(Projeto projeto) {
        projetos.remove(projeto);
        notifyDataSetChanged();
    }

    public void atualiza(List<Projeto> projetos) {
        this.projetos.clear();
        this.projetos.addAll(projetos);
        notifyDataSetChanged();
    }
}

