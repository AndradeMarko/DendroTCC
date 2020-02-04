package br.com.ufpr.dendrodata.dao;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.ufpr.dendrodata.model.Amostra;

public class AmostraDAO {

    private final static List<Amostra> amostras = new ArrayList<>();

    private static int contadorIds = 1;


    public void salva(Amostra amostra) {
        amostra.setId(contadorIds);
        amostras.add(amostra);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorIds++;
    }

    public void edita(Amostra amostra) {
        Amostra amostraEncontrada = buscaAmostraPorId(amostra);
        if (amostraEncontrada != null) {
            int posicaoAmostra = amostras.indexOf(amostraEncontrada);
            amostras.set(posicaoAmostra, amostra);
        }
    }

    @Nullable
    private Amostra buscaAmostraPorId(Amostra amostra) {
        for (Amostra a :
                amostras) {
            if (a.getId() == amostra.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Amostra> todas() {
        return new ArrayList<>(amostras);
    }

    public void remove(Amostra amostra) {
        Amostra amostraDevolvida = buscaAmostraPorId(amostra);
        if (amostraDevolvida != null) {
            amostras.remove(amostra);
        }
    }
}
