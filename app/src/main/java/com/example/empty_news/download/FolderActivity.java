package com.example.empty_news.download;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.empty_news.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FolderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FileAdapter fileAdapter;
    private List<File> fileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 目标存储路径
        File targetDir = new File(getFilesDir(), "download");

        try {
            FileUtils.copyAssetFolder(getAssets(), "download", targetDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (targetDir.exists()) {
            fileList = Arrays.asList(targetDir.listFiles());
        }

        fileAdapter = new FileAdapter(this, fileList);
        recyclerView.setAdapter(fileAdapter);
    }
}
