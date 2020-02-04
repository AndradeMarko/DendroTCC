package br.com.ufpr.dendrodata.ui.activity.main.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.ui.activity.main.InicioActivity;

public class SplashDendroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_dendro);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarDendroSplash();
            }
        }, 750);
    }

    private void mostrarDendroSplash() {
        Intent intent = new Intent(SplashDendroActivity.this, InicioActivity.class);
        startActivity(intent);
        finish();
    }
}
