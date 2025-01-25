package com.example.empty_news;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class otherdown extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置沉浸式模式
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        }

        setContentView(R.layout.otherdown);

        // 获取WebView实例并设置WebView
        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient()); // 防止跳转到默认浏览器
        webView.setWebChromeClient(new WebChromeClient()); // 用于处理JavaScript的支持

        // 启用JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        // 加载指定网页
        webView.loadUrl("https://www.isyd.net/");
    }
}
