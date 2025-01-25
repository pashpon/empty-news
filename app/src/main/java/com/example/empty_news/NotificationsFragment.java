package com.example.empty_news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.empty_news.ai.ai_button;
import com.example.empty_news.ai.alice;
import com.example.empty_news.ai.inc;
import com.example.empty_news.ai.nai;

public class NotificationsFragment extends Fragment {

    public NotificationsFragment() {
        // 空构造函数
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // 查找id为set的控件
        View aliceButton = view.findViewById(R.id.alice);

        // 设置点击事件
        aliceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到 SetActivity
                Intent intent = new Intent(getActivity(), com.example.empty_news.ai.alice.class);
                startActivity(intent);
            }
        });

        // 查找id为star的控件
        View naiButton = view.findViewById(R.id.nai);

        // 设置点击事件
        naiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到 StarActivity
                Intent intent = new Intent(getActivity(), com.example.empty_news.ai.nai.class);
                startActivity(intent);
            }
        });

        // 查找id为star的控件
        View incButton = view.findViewById(R.id.inc);

        // 设置点击事件
        incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到 StarActivity
                Intent intent = new Intent(getActivity(), com.example.empty_news.ai.inc.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
