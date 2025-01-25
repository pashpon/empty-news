package com.example.empty_news;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.BuildConfig;

public class book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book);

        WebView webView = findViewById(R.id.webView);

        // 启用JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);  // 启用 JavaScript
        webSettings.setDomStorageEnabled(true);  // 启用 localStorage 支持

        // 确保链接在WebView内打开
        webView.setWebViewClient(new WebViewClient());

        // 加载本地HTML文件
        webView.loadUrl("file:///android_asset/book/book.html");

        // 启用 WebView 调试功能（仅在调试时使用）
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        // 确保触摸事件传递给 WebView
        webView.setOnTouchListener((v, event) -> false);  // 让 WebView 处理触摸事件
    }

    // 处理返回键，允许网页回退
    @Override
    public void onBackPressed() {
        WebView webView = findViewById(R.id.webView);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
