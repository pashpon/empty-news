package com.example.empty_news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;  // 或其他控件类型

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // 空构造函数
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // 查找id为set的控件
        View setButton = view.findViewById(R.id.set);

        // 设置点击事件
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到 SetActivity
                Intent intent = new Intent(getActivity(), set.class);
                startActivity(intent);
            }
        });

        // 查找id为star的控件
        View payButton = view.findViewById(R.id.pay);

        // 设置点击事件
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到 StarActivity
                Intent intent = new Intent(getActivity(), pay.class);
                startActivity(intent);
            }
        });

        // 查找id为star的控件
        View downButton = view.findViewById(R.id.down);

        // 设置点击事件
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到 StarActivity
                Intent intent = new Intent(getActivity(), down.class);
                startActivity(intent);
            }
        });

        // 查找id为star的控件
        View qudaoButton = view.findViewById(R.id.qudao);

        // 设置点击事件
        qudaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到 StarActivity
                Intent intent = new Intent(getActivity(), qudao.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
