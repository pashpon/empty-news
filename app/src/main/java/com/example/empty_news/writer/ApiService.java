package com.example.empty_news.writer;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiService {
    @GET("/files")  // 你在服务器上创建的接口
    Call<List<FileInfo>> getFiles();
}
