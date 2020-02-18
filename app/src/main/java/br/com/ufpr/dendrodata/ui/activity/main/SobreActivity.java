package br.com.ufpr.dendrodata.ui.activity.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.ufpr.dendrodata.R;

import static br.com.ufpr.dendrodata.ui.activity.const_n_masks.ConstantesActivities.TITLE_APPBAR_SOBRE;


public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        setTitle(TITLE_APPBAR_SOBRE);
        backButton();
    }

    private void backButton() {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
