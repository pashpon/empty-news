package com.example.empty_news.club;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.empty_news.R;

public class club extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club);

        // 启用 Edge-to-Edge 模式，处理状态栏等窗口内边距
        ViewCompat.setOnApplyWindowInsetsListener(getWindow().getDecorView(), (view, insets) -> {
            // 获取系统边距（包括状态栏和导航栏）
            Insets newInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            // 处理边距（可以根据需要进行修改）
            view.setPadding(newInsets.left, newInsets.top, newInsets.right, newInsets.bottom);

            // 返回更新后的 insets
            return insets.consumeSystemWindowInsets();
        });
    }
}