package br.com.ufpr.dendrodata.ui.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.ui.activity.projeto.ListaProjetosActivity;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        configuraBotaoProjetos();
        configuraBotaoExportar();
        configuraBotaoSobre();
    }


    private void configuraBotaoProjetos() {
        Button botaoProjetos = findViewById(R.id.inicio_botao_projetos);
        botaoProjetos.setOnClickListener(v -> vaiParaListaProjetos());
    }

    private void vaiParaListaProjetos() {
        Intent intent = new Intent(InicioActivity.this, ListaProjetosActivity.class);
        startActivity(intent);
    }

    private void configuraBotaoExportar() {
        Button botaoExportar = findViewById(R.id.inicio_botao_exportar);
        botaoExportar.setOnClickListener(v -> vaiParaExportar());
    }

    private void vaiParaExportar() {
        Intent intent = new Intent(InicioActivity.this, ExportarActivity.class);
        startActivity(intent);
    }

    private void configuraBotaoSobre() {
        Button botaoSobre = findViewById(R.id.inicio_botao_sobre);
        botaoSobre.setOnClickListener(v -> vaiParaSobre());
    }

    private void vaiParaSobre() {
        Intent intent = new Intent(InicioActivity.this, SobreActivity.class);
        startActivity(intent);
    }





}
