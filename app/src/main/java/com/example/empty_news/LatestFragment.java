package com.example.empty_news;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class LatestFragment extends Fragment {

    private WebView webView;
    private EditText urlEditText;
    private Button loadButton;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "UserPreferences";
    private static final String KEY_URL = "saved_url";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_latest, container, false);

        // 初始化控件
        webView = rootView.findViewById(R.id.webview);
        urlEditText = rootView.findViewById(R.id.urlEditText);
        loadButton = rootView.findViewById(R.id.loadButton);

        // 初始化 SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // 设置 WebView
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setDomStorageEnabled(true); // 启用 localStorage

        // 从 SharedPreferences 获取已保存的 URL
        String savedUrl = sharedPreferences.getString(KEY_URL, null);
        if (savedUrl != null) {
            // 如果存在已保存的 URL，直接加载它
            webView.loadUrl(savedUrl);
        }

        // 加载用户输入的网址
        loadButton.setOnClickListener(v -> {
            String url = urlEditText.getText().toString().trim();
            if (!url.isEmpty()) {
                showConfirmationDialog(url);
            }
        });

        return rootView;
    }

    private void showConfirmationDialog(String url) {
        new AlertDialog.Builder(getContext())
                .setTitle("确认更换链接")
                .setMessage("您确定要更换链接吗？")
                .setPositiveButton("确定", (dialog, which) -> {
                    // 保存用户输入的网址
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_URL, url);
                    editor.apply();

                    // 加载输入的网址
                    webView.loadUrl(url);
                })
                .setNegativeButton("取消", null)
                .show();
    }
}
