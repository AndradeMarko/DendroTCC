package br.com.ufpr.dendrodata.ui.activity.amostra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.model.Amostra;

public class ListaAmostrasAdapter extends BaseAdapter {

    private final List<Amostra> amostras = new ArrayList<>();
    private final Context context;

    public ListaAmostrasAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return amostras.size();
    }

    @Override
    public Amostra getItem(int posicao) {
        return amostras.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return amostras.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Amostra amostraDevolvida = amostras.get(posicao);
        vincula(viewCriada, amostraDevolvida);
        return viewCriada;
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_amostra, viewGroup, false);
    }

    private void vincula(View view, Amostra amostra) {
        TextView numero = view.findViewById(R.id.item_amostra_numero);
        numero.setText("Parcela: F-" + amostra.getNumero());
        TextView data = view.findViewById(R.id.item_amostra_data);
        data.setText(amostra.dataFormatada());
//        TextView individuos =
    }

    public void remove(Amostra amostra) {
        amostras.remove(amostra);
        notifyDataSetChanged();
    }

    public void atualiza(List<Amostra> amostras) {
        this.amostras.clear();
        this.amostras.addAll(amostras);
        notifyDataSetChanged();
    }
}
