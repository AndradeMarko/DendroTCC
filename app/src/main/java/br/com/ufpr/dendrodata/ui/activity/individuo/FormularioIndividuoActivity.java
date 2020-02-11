package br.com.ufpr.dendrodata.ui.activity.individuo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.database.DendroDataDatabase;
import br.com.ufpr.dendrodata.database.dao.IndividuoDAO;
import br.com.ufpr.dendrodata.model.Individuo;

import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.KEY_INDIVIDUO;
import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.TITLE_APPBAR_EDITAINDIVIDUO;
import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.TITLE_APPBAR_NOVOINDIVIDUO;

public class FormularioIndividuoActivity extends AppCompatActivity {

    private EditText campoPlaca;
    private EditText campoDap;
    private EditText campoHComercial;
    private EditText campoHTotal;
    private EditText campoSanidade;
    private EditText campoQualidade;
    private EditText campoObservacao;

    private IndividuoDAO dao;
    private Individuo individuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_individuo);
        DendroDataDatabase database = DendroDataDatabase.getInstance(this);
        dao = database.getRoomIndividuoDAO();
        inicializacaoCampos();
        carregaIndividuo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formularioindividuo_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formularioindividuo_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaIndividuo() {
        Intent dados = getIntent();

        individuo = (Individuo) dados.getSerializableExtra(KEY_INDIVIDUO);

        assert individuo != null;

        if (individuo.getId() == 0) {
            setTitle(TITLE_APPBAR_NOVOINDIVIDUO);
        } else {
            setTitle(TITLE_APPBAR_EDITAINDIVIDUO);
            preencheCampos();
        }

    }

    private void preencheCampos() {
        campoPlaca.setText(individuo.getPlaca());
        campoDap.setText(individuo.getDap());
        campoHComercial.setText(individuo.getHcomercial());
        campoHTotal.setText(individuo.getHtotal());
        campoSanidade.setText(individuo.getSanidade());
        campoQualidade.setText(individuo.getQualidade());
        campoObservacao.setText(individuo.getObservacao());
    }

    private void finalizaFormulario() {
        preencheIndividuo();
        if (individuo.idValido()) {
            dao.edita(individuo);
        } else {
            dao.salva(individuo);
        }
        finish();
    }

    private void preencheIndividuo() {
        String placa = campoPlaca.getText().toString();
        String dap = campoDap.getText().toString();
        String hComercial = campoHComercial.getText().toString();
        String hTotal = campoHTotal.getText().toString();
        String sanidade = campoSanidade.getText().toString();
        String qualidade = campoQualidade.getText().toString();
        String observacao = campoObservacao.getText().toString();

        individuo.setPlaca(placa);
        individuo.setDap(dap);
        individuo.setHcomercial(hComercial);
        individuo.setHtotal(hTotal);
        individuo.setSanidade(sanidade);
        individuo.setQualidade(qualidade);
        individuo.setObservacao(observacao);
    }

    private void inicializacaoCampos() {
        campoPlaca = findViewById(R.id.activity_formularioindividuo_placa);
        campoDap = findViewById(R.id.activity_formularioindividuo_dap);
        campoHComercial = findViewById(R.id.activity_formularioindividuo_hcomercial);
        campoHTotal = findViewById(R.id.activity_formularioindividuo_htotal);
        campoSanidade = findViewById(R.id.activity_formularioindividuo_sanidade);
        campoQualidade = findViewById(R.id.activity_formularioindividuo_qualidade);
        campoObservacao = findViewById(R.id.activity_formularioindividuo_observacoes);
    }
}
