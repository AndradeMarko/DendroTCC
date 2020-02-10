package br.com.ufpr.dendrodata.ui.activity.amostra.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputLayout;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.model.Amostra;

public abstract class FormularioAmostraDialog {

    private final String titulo;
    private final String tituloBotaoPositivo;
    private final ConfirmacaoListener listener;
    private final Context context;
    private static final String TITULO_BOTAO_NEGATIVO = "Cancelar";
    private Amostra amostra;

    FormularioAmostraDialog(Context context,
                                      String titulo,
                                      String tituloBotaoPositivo,
                                      ConfirmacaoListener listener) {
        this.titulo = titulo;
        this.tituloBotaoPositivo = tituloBotaoPositivo;
        this.listener = listener;
        this.context = context;
    }

    FormularioAmostraDialog(Context context,
                            String titulo,
                            String tituloBotaoPositivo,
                            ConfirmacaoListener listener,
                            Amostra amostra) {
        this(context, titulo, tituloBotaoPositivo, listener);
        this.amostra = amostra;
    }

    public void mostra() {
        @SuppressLint("InflateParams") View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.formulario_amostra, null);
        tentaPreencherFormulario(viewCriada);
        new AlertDialog.Builder(context)
                .setTitle(titulo)
                .setView(viewCriada)
                .setPositiveButton(tituloBotaoPositivo, (dialog, which) -> {
                    EditText campoParcela = getEditText(viewCriada, R.id.formulario_amostra_parcela);
                    EditText campoCoordX = getEditText(viewCriada, R.id.formulario_amostra_coord_x);
                    EditText campoCoordY = getEditText(viewCriada, R.id.formulario_amostra_coord_y);
                    EditText campoObservacao = getEditText(viewCriada, R.id.formulario_amostra_observacoes);
//                    criaProduto(campoParcela, campoCoordX, campoCoordY, campoObservacao);
                })
                .setNegativeButton(TITULO_BOTAO_NEGATIVO, null)
                .show();
    }

    @SuppressLint("SetTextI18n")
    private void tentaPreencherFormulario(View viewCriada) {
        if (amostra != null) {
            TextView campoId = viewCriada.findViewById(R.id.formulario_amostra_id);
            campoId.setText(String.valueOf(amostra.getId()));
            campoId.setVisibility(View.VISIBLE);
            EditText campoParcela = getEditText(viewCriada, R.id.formulario_amostra_parcela);
            campoParcela.setText(amostra.getNumero());
            EditText campoCoordX = getEditText(viewCriada, R.id.formulario_amostra_coord_x);
            campoCoordX.setText(amostra.getCoordX());
            EditText campoCoordY = getEditText(viewCriada, R.id.formulario_amostra_coord_y);
            campoCoordY.setText(amostra.getCoordY());
            EditText campoObservacao = getEditText(viewCriada, R.id.formulario_amostra_observacoes);
            campoObservacao.setText(amostra.getObservacao());
        }
    }

//    private void criaProduto(EditText campoParcela, EditText campoCoordX, EditText campoCoordY, EditText campoObservacao) {
//        String parcela = campoParcela.getText().toString();
//        String coordX = campoCoordX.getText().toString();
//        String coordY = campoCoordY.getText().toString();
//        String observacao = campoObservacao.getText().toString();
//        long id = preencheId();
//        Amostra amostra = new Amostra(id, parcela, coordX, coordY, observacao);
//        listener.quandoConfirmado(amostra);
//    }

    private EditText getEditText(View viewCriada, int idTextInputLayout) {
        TextInputLayout textInputLayout = viewCriada.findViewById(idTextInputLayout);
        return textInputLayout.getEditText();
    }
    public interface ConfirmacaoListener {
        void quandoConfirmado(Amostra amostra);
    }

    private long preencheId() {
        if (amostra != null) {
            return amostra.getId();
        }
        return 0;
    }

}
