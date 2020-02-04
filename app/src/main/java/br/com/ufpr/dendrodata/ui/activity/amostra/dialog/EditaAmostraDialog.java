package br.com.ufpr.dendrodata.ui.activity.amostra.dialog;

import android.content.Context;

import br.com.ufpr.dendrodata.model.Amostra;

public class EditaAmostraDialog extends FormularioAmostraDialog {

    private static final String TITULO = "Editando amostra";
    private static final String TITULO_BOTAO_POSITIVO = "Editar";

    public EditaAmostraDialog(Context context,
                              Amostra amostra,
                              ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener, amostra);
    }
}
