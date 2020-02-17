package br.com.ufpr.dendrodata.ui.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.List;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.database.DendroDataDatabase;
import br.com.ufpr.dendrodata.database.dao.IndividuoDAO;
import br.com.ufpr.dendrodata.model.Individuo;

import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.TITLE_APPBAR_SOBRE;



public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        setTitle(TITLE_APPBAR_SOBRE);


        }
    }
