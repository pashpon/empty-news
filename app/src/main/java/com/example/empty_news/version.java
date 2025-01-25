package com.example.empty_news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class version extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置布局
        setContentView(R.layout.version);

        // 查找id为account的控件
        View versionButton = findViewById(R.id.version);

        // 设置点击事件
        if (versionButton != null) {
            versionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转到 AccountActivity
                    Intent intent = new Intent(version.this,more.class);
                    startActivity(intent);
                }
            });
        } else {
            // 如果没有找到该视图，输出日志
            Log.e("version.java", "versionButton not found in the layout!");
        }

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
