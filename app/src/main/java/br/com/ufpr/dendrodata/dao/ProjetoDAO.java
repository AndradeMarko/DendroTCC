package br.com.ufpr.dendrodata.dao;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.ufpr.dendrodata.model.Projeto;

public class ProjetoDAO {

    private final static List<Projeto> projetos = new ArrayList<>();
    private static int contadorIds = 1;

    public void salva(Projeto projeto) {
        projeto.setId(contadorIds);
        projetos.add(projeto);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorIds++;
    }

    public void edita(Projeto projeto) {
        Projeto projetoEncontrado = buscarProjetoPorId(projeto);
        if (projetoEncontrado != null) {
            int posicaoProjeto = projetos.indexOf(projetoEncontrado);
            projetos.set(posicaoProjeto, projeto);
        }
    }

    @Nullable
    private Projeto buscarProjetoPorId(Projeto projeto) {
        for (Projeto p :
                projetos) {
            if (p.getId() == projeto.getId()) {
                return p;
            }
        }
        return null;
    }

    public List<Projeto> todos() {
        return new ArrayList<>(projetos);
    }

    public void remove(Projeto projeto) {
        Projeto projetoDevolvido = buscarProjetoPorId(projeto);
        if (projetoDevolvido != null) {
            projetos.remove(projeto);
        }
    }
}
