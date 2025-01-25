package com.example.empty_news;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        EditText aiInput = view.findViewById(R.id.ai_input);
        Button btnConfirm = view.findViewById(R.id.btn_confirm);

        // 监听键盘上的 "确认" 按键
        aiInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.ACTION_DOWN ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    performSearch(aiInput);
                    return true;
                }
                return false;
            }
        });

        // 监听按钮点击事件
        btnConfirm.setOnClickListener(v -> performSearch(aiInput));

        // 查找id为set的控件
        View paButton = view.findViewById(R.id.pa);

        // 设置点击事件
        paButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到 SetActivity
                Intent intent = new Intent(getActivity(), com.example.empty_news.pa.pa.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void performSearch(EditText aiInput) {
        String userInput = aiInput.getText().toString().trim();
        if (!userInput.isEmpty()) {
            Intent intent = new Intent(getActivity(), SearchResultActivity.class);
            intent.putExtra("query", userInput);
            startActivity(intent);
        }
    }
}
