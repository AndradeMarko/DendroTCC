package br.com.ufpr.dendrodata.ui.activity.amostra.dialog;

import android.content.Context;

public class SalvaAmostraDialog extends FormularioAmostraDialog {

    private static final String TITULO = "Salvando amostra";
    private static final String TITULO_BOTAO_POSITIVO = "Salvar";

    public SalvaAmostraDialog(Context context,
                              ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener);
    }
}
