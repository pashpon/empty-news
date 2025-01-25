package com.example.empty_news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.empty_news.ai.ai_button;

public class qudao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置布局
        setContentView(R.layout.qudao);

        // 查找id为account的控件
        View ai_buttonButton = findViewById(R.id.ai_button);

        // 设置点击事件
        if (ai_buttonButton != null) {
            ai_buttonButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转到 AccountActivity
                    Intent intent = new Intent(qudao.this, ai_button.class);
                    startActivity(intent);
                }
            });
        } else {
            // 如果没有找到该视图，输出日志
            Log.e("setActivity", "accountButton not found in the layout!");
        }

    }
}
