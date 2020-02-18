package br.com.ufpr.dendrodata.database.csv;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import br.com.ufpr.dendrodata.R;
import br.com.ufpr.dendrodata.database.DendroDataDatabase;
import br.com.ufpr.dendrodata.database.dao.IndividuoDAO;
import br.com.ufpr.dendrodata.model.Individuo;

import static br.com.ufpr.dendrodata.ui.activity.const_n_masks.ConstantesActivities.TITLE_APPBAR_EXPORTAR;

public class ExportarActivity extends AppCompatActivity {


    public static final int STORAGE_PERMISSION_REQUEST_CODE = 974;

    private String filename = "DendroData.csv";
    private String filepath = "MyFileStorage";

    private Button backupBtn;
    private Button shareBtn;
    private IndividuoDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportar);
        setTitle(TITLE_APPBAR_EXPORTAR);
        dao = DendroDataDatabase.getInstance(this).getRoomIndividuoDAO();

        backupBtn = findViewById(R.id.backupBtn);
        backupBtn.setOnClickListener(view -> {
            willAskForPermission();
            new ExportDatabaseCSVTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        });

        shareBtn = findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(view -> {
            willAskForPermission();
            openFile();
        });

    }

    public class ExportDatabaseCSVTask extends AsyncTask<String, Void, Boolean> {
        private final ProgressDialog dialog = new ProgressDialog(ExportarActivity.this);

        @Override
        protected Boolean doInBackground(final String... args) {
            File file = new File(getExternalFilesDir(filepath), filename);
            try {
                file.createNewFile();
                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                String[] column = {"id", "placa", "dap", "hcomercial", "htotal", "sanidade", "qualidade", "observacao", "amostraId", "projetoId"};
                csvWrite.writeNext(column);
                List<Individuo> individuos = dao.todosIndividuos();
                for (int i = 0; i < individuos.size(); i++) {
                    String[] mySecondStringArray = {String.valueOf(individuos.get(i).getId()),
                            individuos.get(i).getPlaca(),
                            individuos.get(i).getDap(),
                            individuos.get(i).getHcomercial(),
                            individuos.get(i).getHtotal(),
                            individuos.get(i).getSanidade(),
                            individuos.get(i).getQualidade(),
                            individuos.get(i).getObservacao(),
                            String.valueOf(individuos.get(i).getAmostraId()),
                            String.valueOf(individuos.get(i).getProjetoId())};

                    csvWrite.writeNext(mySecondStringArray);
                }
                csvWrite.close();
                return true;
            } catch (
                    IOException e) {
                return false;
            }
        }


        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();
        }

        protected void onPostExecute(final Boolean success) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (success) {
                Toast.makeText(ExportarActivity.this, "Export successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ExportarActivity.this, "Export failed", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public boolean willAskForPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        }
        if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return false;
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST_CODE);
        return true;
    }


//    public void compartilhar() {
//        Intent viewIntent = new Intent(Intent.ACTION_VIEW);
//        Intent sendIntent = new Intent(Intent.ACTION_SEND);
//
//        viewIntent.setDataAndType(Uri.fromFile(getExternalFilesDir(filepath))filename);
//    }


    public void openFile() {

//        Intent viewIntent = new Intent(Intent.ACTION_VIEW);
//        Intent sendIntent = new Intent(Intent.ACTION_SEND);
//
//        viewIntent.setDataAndType(FileProvider.getUriForFile(getContext(), "com.ufpr.dendrodata.projetos", target), "application/csv");
//
//        viewIntent.putExtra(Intent.EXTRA_TITLE, report.getSummary());
//        viewIntent.putExtra(Intent.EXTRA_SUBJECT, report.getUrl());
//
//        sendIntent.setType("text/plain");
//        sendIntent.putExtra(Intent.EXTRA_TEXT, report.getUrl());
//        sendIntent.putExtra(Intent.EXTRA_TITLE, report.getSummary());
//        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Echosis PEF");
//
//        Intent chooser = Intent.createChooser(viewIntent, report.getSummary());
//        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{sendIntent});
//
//        getContext().startActivity(chooser);

        Intent viewIntent = new Intent(Intent.ACTION_VIEW);
        viewIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Uri uri = Uri.fromFile(new File(getExternalFilesDir(filepath)+ "/" + filename));
        viewIntent.setDataAndType(uri, "text/plain");
        startActivity(viewIntent);
    }

    }

