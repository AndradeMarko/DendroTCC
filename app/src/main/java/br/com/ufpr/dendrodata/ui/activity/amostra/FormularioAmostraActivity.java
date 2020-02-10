package br.com.ufpr.dendrodata.ui.activity.amostra;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.database.DendroDataDatabase;
import br.com.ufpr.dendrodata.database.dao.AmostraDAO;
import br.com.ufpr.dendrodata.model.Amostra;
import br.com.ufpr.dendrodata.model.Projeto;

import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.KEY_AMOSTRA;
import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.KEY_PROJETO;
import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.TITLE_APPBAR_EDITAAMOSTRA;
import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.TITLE_APPBAR_NOVAAMOSTRA;

public class FormularioAmostraActivity extends AppCompatActivity {


    private EditText campoNumero;
    private EditText campoCoordX;
    private EditText campoCoordY;
    private EditText campoEspacamento;
    private EditText campoObservacao;
    private EditText campoProjetoId;

    private AmostraDAO dao;
    private Amostra amostra;
    private Projeto projeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_amostra);
        DendroDataDatabase database = DendroDataDatabase.getInstance(this);
        dao = database.getRoomAmostraDAO();
        inicializacaoCampos();
        carregaAmostra();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formularioamostra_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formularioamostra_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAmostra() {
        Intent dados = getIntent();
        if (dados.hasExtra(KEY_AMOSTRA)) {
            setTitle(TITLE_APPBAR_EDITAAMOSTRA);
            amostra = (Amostra) dados.getSerializableExtra(KEY_AMOSTRA);
            preencheCampos();
        } else if (dados.hasExtra(KEY_PROJETO)) {
            setTitle(TITLE_APPBAR_NOVAAMOSTRA);
            amostra = new Amostra();
            projeto = (Projeto) dados.getSerializableExtra(KEY_PROJETO);
        }
    }

    private void preencheCampos() {
        campoProjetoId.setText(amostra.getProjetoId());
        campoNumero.setText(amostra.getNumero());
        campoCoordX.setText(amostra.getCoordX());
        campoCoordY.setText(amostra.getCoordY());
        campoEspacamento.setText(amostra.getEspacamento());
        campoObservacao.setText(amostra.getObservacao());
    }

    private void preencheAmostra() {

        String numero = campoNumero.getText().toString();
        String coordX = campoCoordX.getText().toString();
        String coordY = campoCoordY.getText().toString();
        String espacamento = campoEspacamento.getText().toString();
        String observacao = campoObservacao.getText().toString();

        amostra.setProjetoId(projeto.getId());
        amostra.setNumero(numero);
        amostra.setCoordX(coordX);
        amostra.setCoordY(coordY);
        amostra.setEspacamento(espacamento);
        amostra.setObservacao(observacao);
    }

    private void finalizaFormulario() {
        preencheAmostra();
        if (amostra.idValido()) {
            dao.edita(amostra);
        } else {
            dao.salva(amostra);
        }
        finish();
    }

    private void inicializacaoCampos() {
        campoNumero = findViewById(R.id.activity_formularioamostra_numero);
        campoCoordX = findViewById(R.id.activity_formularioamostra_coordx);
        campoCoordY = findViewById(R.id.activity_formularioamostra_coordy);
        campoEspacamento = findViewById(R.id.activity_formularioamostra_espacamento);
        campoObservacao = findViewById(R.id.activity_formularioamostra_observacoes);
    }
}
