package br.com.ufpr.dendrodata.ui.activity.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.database.DendroDataDatabase;
import br.com.ufpr.dendrodata.database.dao.ProjetoDAO;
import br.com.ufpr.dendrodata.model.Projeto;

import static br.com.ufpr.dendrodata.ui.activity.const_n_masks.ConstantesActivities.KEY_PROJETO;
import static br.com.ufpr.dendrodata.ui.activity.const_n_masks.ConstantesActivities.TITLE_APPBAR_EDITAPROJETO;
import static br.com.ufpr.dendrodata.ui.activity.const_n_masks.ConstantesActivities.TITLE_APPBAR_NOVOPROJETO;

public class FormularioProjetoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText campoCodigo;
    private EditText campoFazenda;
    private EditText campoMunicipio;
    private EditText campoArea;
    private Spinner campoEspecie;
    private EditText campoTamanho;
    private EditText campoQuantidade;
    private EditText campoDescricao;

    private ProjetoDAO dao;
    private Projeto projeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_projeto);
        DendroDataDatabase database = DendroDataDatabase.getInstance(this);
        dao = database.getRoomProjetoDAO();
        inicializacaoCampos();
        carregaProjeto();
        backButton();
    }

    private void backButton() {
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formularioprojeto_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formularioprojeto_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaProjeto() {
        Intent dados = getIntent();
        if (dados.hasExtra(KEY_PROJETO)) {
            setTitle(TITLE_APPBAR_EDITAPROJETO);
            projeto = (Projeto) dados.getSerializableExtra(KEY_PROJETO);
            preencheCampos();
        } else {
            setTitle(TITLE_APPBAR_NOVOPROJETO);
            projeto = new Projeto();
        }
    }

    private void preencheCampos() {
        campoCodigo.setText(projeto.getCodigo());
        campoFazenda.setText(projeto.getFazenda());
        campoMunicipio.setText(projeto.getMunicipio());
//        campoEspecie.setSelected(projeto.getEspecie());
        campoArea.setText(projeto.getArea());
        campoTamanho.setText(projeto.getTamanho());
        campoQuantidade.setText(projeto.getQuantidade());
        campoDescricao.setText(projeto.getDescricao());
    }

    private void finalizaFormulario() {
        preencheProjeto();
        if (projeto.idValido()) {
            dao.edita(projeto);
        } else {
            dao.salva(projeto);
        }
        finish();
    }

    private void inicializacaoCampos() {
        campoCodigo = findViewById(R.id.activity_formularioprojeto_codigo);
        campoFazenda = findViewById(R.id.activity_formularioprojeto_fazenda);
        campoMunicipio = findViewById(R.id.activity_formularioprojeto_municipio);
        campoEspecie = findViewById(R.id.activity_spinner_projeto_especie);
        campoEspecie.setOnItemSelectedListener(this);
        campoArea = findViewById(R.id.activity_formularioprojeto_area);
        campoTamanho = findViewById(R.id.activity_formularioprojeto_tamanho_amostra);
        campoQuantidade = findViewById(R.id.activity_formularioprojeto_quantidade_amostras);
        campoDescricao = findViewById(R.id.activity_formularioprojeto_descricao_amostra);

    }

    private void preencheProjeto() {
        String codigo = campoCodigo.getText().toString();
        String fazenda = campoFazenda.getText().toString();
        String municipio = campoMunicipio.getText().toString();
        String especie = campoEspecie.getSelectedItem().toString();
        String area = campoArea.getText().toString();
        String tamanho = campoTamanho.getText().toString();
        String quantidade = campoQuantidade.getText().toString();
        String descricao = campoDescricao.getText().toString();

        projeto.setCodigo(codigo);
        projeto.setFazenda(fazenda);
        projeto.setMunicipio(municipio);
        projeto.setEspecie(especie);
        projeto.setArea(area);
        projeto.setTamanho(tamanho);
        projeto.setQuantidade(quantidade);
        projeto.setDescricao(descricao);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
