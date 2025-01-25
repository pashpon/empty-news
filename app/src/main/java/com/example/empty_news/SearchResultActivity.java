package com.example.empty_news;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class SearchResultActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        // 获取传递过来的搜索内容
        String query = getIntent().getStringExtra("query");

        // 初始化 WebView
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);  // 启用 JavaScript
        webSettings.setDomStorageEnabled(true); // 启用 DOM 存储

        // 在 WebView 内部打开网页，不跳转到浏览器
        webView.setWebViewClient(new WebViewClient());

        if (query != null && !query.isEmpty()) {
            // 加载百度搜索结果
            String url = "https://www.baidu.com/s?wd=" + query;
            webView.loadUrl(url);
        }
    }
}
