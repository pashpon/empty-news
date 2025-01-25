package com.example.empty_news;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.empty_news.writer.ApiService;
import com.example.empty_news.writer.FileAdapter;
import com.example.empty_news.writer.FileInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class fast extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FileAdapter fileAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fast);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // 初始化 Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.5.28:8080/")  // 替换为你的服务器地址
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<FileInfo>> call = apiService.getFiles();

        // 发送请求并获取响应
        call.enqueue(new Callback<List<FileInfo>>() {
            @Override
            public void onResponse(Call<List<FileInfo>> call, Response<List<FileInfo>> response) {
                Log.d("Retrofit", "Response URL: " + call.request().url());  // 打印请求 URL
                if (response.isSuccessful()) {
                    List<FileInfo> fileInfos = response.body();
                    fileAdapter = new FileAdapter(fileInfos, fast.this, fileInfo -> {
                        if ("folder".equals(fileInfo.getType())) {
                            // 这里可以处理文件夹点击，展开或加载子文件夹内容
                            loadFolderContents(fileInfo);
                        } else {
                            // 处理文件点击的逻辑（例如打开文件）
                            openFile(fileInfo);
                        }
                    });
                    recyclerView.setAdapter(fileAdapter);
                } else {
                    Toast.makeText(fast.this, "Failed to load files", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FileInfo>> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
                Toast.makeText(fast.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 加载文件夹内容
    private void loadFolderContents(FileInfo folder) {
        // 请求该文件夹下的内容，可以通过再次调用 API 或者加载本地数据
        Log.d("Folder Clicked", "Loading contents of folder: " + folder.getName());
        // 这里你可以重新加载数据，或者展开文件夹内部的文件
    }

    // 打开文件
    private void openFile(FileInfo fileInfo) {
        // 处理点击文件后的操作
        Log.d("File Clicked", "Opening file: " + fileInfo.getName());
        // 你可以根据文件类型打开不同的文件
    }
}
