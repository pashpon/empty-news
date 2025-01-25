package com.example.empty_news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.List;

public class video extends AppCompatActivity {
    private ViewPager2 videoPager;
    private List<String> videoUrls = new ArrayList<>();
    private VideoAdapter videoAdapter;
    private Handler handler;
    private boolean isDataLoaded = false;  // 用来标记数据是否加载成功
    private ProgressBar progressBar;  // 添加 ProgressBar 引用

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        videoPager = findViewById(R.id.videoPager);
        progressBar = findViewById(R.id.progressBar);  // 初始化 ProgressBar

        videoPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL); // ✅ 设置为垂直方向滑动
        videoPager.setOffscreenPageLimit(2);  // ✅ 预加载 2 个视频，减少画面闪烁

        // 获取 ViewPager2 的内部 RecyclerView
        RecyclerView recyclerView = (RecyclerView) videoPager.getChildAt(0);

        // 将 RecyclerView 传递给 VideoAdapter
        videoAdapter = new VideoAdapter(this, videoUrls, recyclerView);
        videoPager.setAdapter(videoAdapter);

        // 监听页面切换事件
        videoPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // 将当前页面的视频音量设置为正常
                videoAdapter.setVolume(position, 1.0f);

                // 将其他页面的视频音量设置为静音
                for (int i = 0; i < videoUrls.size(); i++) {
                    if (i != position) {
                        videoAdapter.setVolume(i, 0.0f);
                    }
                }
            }
        });

        // 创建一个 Handler 用于延时操作
        handler = new Handler();

        fetchVideoList();

        // 启用 Edge-to-Edge 模式，处理状态栏等窗口内边距
        ViewCompat.setOnApplyWindowInsetsListener(getWindow().getDecorView(), (view, insets) -> {
            // 获取系统边距（包括状态栏和导航栏）
            Insets newInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            // 处理边距（可以根据需要进行修改）
            view.setPadding(newInsets.left, newInsets.top, newInsets.right, newInsets.bottom);

            // 返回更新后的 insets
            return insets.consumeSystemWindowInsets();
        });

        // 启动一个定时任务，20秒后检查是否加载成功
        handler.postDelayed(() -> {
            if (!isDataLoaded) {
                openErrorActivity();  // 超时后打开错误页面
            }
        }, 20000);  // 设置为20秒（20000毫秒）
    }

    private void fetchVideoList() {
        String url = "http://192.168.5.28:8080/video/list";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    videoUrls.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            videoUrls.add(response.getString(i));
                        } catch (Exception e) {
                            Log.e("VideoActivity", "JSON解析错误", e);
                        }
                    }
                    videoAdapter.notifyDataSetChanged();
                    isDataLoaded = true;  // 数据加载成功
                    progressBar.setVisibility(ProgressBar.GONE);  // 数据加载完成后隐藏 ProgressBar
                },
                error -> {
                    Log.e("VideoActivity", "请求失败", error);
                    isDataLoaded = false;  // 请求失败
                    progressBar.setVisibility(ProgressBar.GONE);  // 请求失败后隐藏 ProgressBar
                });

        Volley.newRequestQueue(this).add(request);
    }

    // 打开错误页面
    private void openErrorActivity() {
        Intent intent = new Intent(video.this, error.class);  // 你可以替换成你实际的错误页面
        startActivity(intent);
        finish();  // 关闭当前 Activity
    }
}