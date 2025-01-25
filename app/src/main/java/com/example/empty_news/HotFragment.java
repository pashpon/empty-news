package com.example.empty_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HotFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot, container, false);

        // 获取 WebView 实例并进行设置
        WebView webView = view.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient()); // 防止跳转到默认浏览器
        webView.setWebChromeClient(new WebChromeClient()); // 用于处理 JavaScript 的支持

        // 启用 JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        // 加载指定网页
        webView.loadUrl("http://43.140.246.235:5000/hot_search");

        return view;
    }
}
