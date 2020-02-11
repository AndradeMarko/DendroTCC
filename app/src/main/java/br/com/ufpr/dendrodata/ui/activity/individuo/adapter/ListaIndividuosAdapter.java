package br.com.ufpr.dendrodata.ui.activity.individuo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.model.Individuo;

public class ListaIndividuosAdapter extends BaseAdapter {

    private final Context context;
    private List<Individuo> individuos = new ArrayList<>();

    public ListaIndividuosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return individuos.size();
    }

    @Override
    public Individuo getItem(int posicao) {
        return individuos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return individuos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Individuo individuoDevolvido = individuos.get(posicao);
        vincula(viewCriada, individuoDevolvido);
        return viewCriada;
    }

    private void vincula(View view, Individuo individuo) {
        TextView placa = view.findViewById(R.id.item_individuo_placa);
        placa.setText("Árv. - " + individuo.getPlaca());
        TextView dap = view.findViewById(R.id.item_individuo_dapnumero);
        dap.setText(individuo.getDap() + " cm");
        TextView hTotal = view.findViewById(R.id.item_individuo_altura_totalnumero);
        hTotal.setText(individuo.getHtotal() + " m");
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_individuo, viewGroup, false);
    }

    public void atualiza(List<Individuo> individuos) {
        this.individuos.clear();
        this.individuos.addAll(individuos);
        notifyDataSetChanged();
    }

    public void remove(Individuo individuo) {
        individuos.remove(individuo);
        notifyDataSetChanged();
    }
}
