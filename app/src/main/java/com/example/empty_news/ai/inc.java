package com.example.empty_news.ai;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

import com.example.empty_news.R;

public class inc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inc);

        WebView webView = findViewById(R.id.webview);

        // 启用JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        // 设置WebView客户端
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // 防止网页跳转到外部浏览器
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        // 设置WebChromeClient
        webView.setWebChromeClient(new WebChromeClient());

        // 加载网页
        webView.loadUrl("http://43.140.246.235:4000");
    }

    @Override
    public void onBackPressed() {
        WebView webView = findViewById(R.id.webview);
        if (webView.canGoBack()) {
            webView.goBack();  // 如果WebView有历史记录，则返回上一页
        } else {
            super.onBackPressed();
        }
    }
}
