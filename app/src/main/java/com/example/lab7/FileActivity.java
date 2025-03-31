package com.example.lab7;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FileActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_PERMISSIONS = 402;
    private TextView tvFileData;
    private final String FILE_PATH = Environment.getExternalStorageDirectory() + "/lab7_test.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        tvFileData = findViewById(R.id.tv_file_data);
        Button btnWriteFile = findViewById(R.id.btn_write_file);
        Button btnReadFile = findViewById(R.id.btn_read_file);

        btnWriteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile("Hello: " + new Date().toString());
            }
        });

        btnReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(FILE_PATH);
                if (file.exists()) {
                    String data = readFile();
                    tvFileData.setText(data);
                } else {
                    Toast.makeText(FileActivity.this, "File does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        requestPermissions();
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSIONS);
        } else {
            Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "READ/WRITE permissions granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permissions Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void writeFile(String data) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(FILE_PATH);
            fos.write(data.getBytes());
            fos.flush();
            Toast.makeText(this, "File Written: " + FILE_PATH, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error writing file", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private String readFile() {
        FileInputStream fis = null;
        String result;
        try {
            fis = new FileInputStream(FILE_PATH);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int ch;
            while ((ch = fis.read()) != -1) {
                bos.write(ch);
            }
            result = bos.toString();
        } catch (Exception e) {
            e.printStackTrace();
            result = "Error reading file";
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
        return result;
    }
}
