package br.com.ufpr.dendrodata.ui.activity.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import br.com.ufpr.dendrodata.R;

import static br.com.ufpr.dendrodata.ui.activity.constantes.ConstantesActivities.TITLE_APPBAR_EXPORTAR;

public class ExportarActivity extends AppCompatActivity {

    EditText inputText;
    TextView response;
    Button saveButton,readButton;

    private String filename = "Arquivo Dois.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String myData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportar);
        setTitle(TITLE_APPBAR_EXPORTAR);

        inputText = findViewById(R.id.myInputText);
        response = findViewById(R.id.response);


        saveButton =
                findViewById(R.id.saveExternalStorage);
        saveButton.setOnClickListener(v -> {
            try {
                willAskForPermission();
                FileOutputStream fos = new FileOutputStream(myExternalFile);
                fos.write(inputText.getText().toString().getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputText.setText("");
            response.setText("SampleFile.txt saved to External Storage...");
        });

        readButton = findViewById(R.id.getExternalStorage);
        readButton.setOnClickListener(v -> {
            try {
                willAskForPermission();
                FileInputStream fis = new FileInputStream(myExternalFile);
                DataInputStream in = new DataInputStream(fis);
                BufferedReader br =
                        new BufferedReader(new InputStreamReader(in));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    myData = myData + strLine;
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputText.setText(myData);
            response.setText("SampleFile.txt data retrieved from Internal Storage...");
        });

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            saveButton.setEnabled(false);
        }
        else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }


    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }






    public static final int STORAGE_PERMISSION_REQUEST_CODE = 974;

    public boolean willAskForPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        }
        if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return false;
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST_CODE);
        return true;
    }


}

