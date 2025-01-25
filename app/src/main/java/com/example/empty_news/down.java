package com.example.empty_news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class down extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置布局
        setContentView(R.layout.down);

        // 正确获取控件并设置点击事件
        View checkitButton = findViewById(R.id.checkit);

        // 确保视图已经正确加载
        if (checkitButton != null) {
            // 设置点击事件
            checkitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转到 otherdown Activity
                    Intent intent = new Intent(down.this, checkit.class);
                    startActivity(intent);
                }
            });
        } else {
            // 如果没有找到对应的视图，可以进行错误日志输出
            // Log.e("downActivity", "otherdownButton not found!");
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
