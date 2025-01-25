package com.example.empty_news.pa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.empty_news.R;
import java.util.ArrayList;
import java.util.List;

public class pa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pa);

        // 启用 Edge-to-Edge 模式，处理状态栏等窗口内边距
        ViewCompat.setOnApplyWindowInsetsListener(getWindow().getDecorView(), (view, insets) -> {
            // 获取系统边距（包括状态栏和导航栏）
            Insets newInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            // 处理边距（可以根据需要进行修改）
            view.setPadding(newInsets.left, newInsets.top, newInsets.right, newInsets.bottom);

            // 返回更新后的 insets
            return insets.consumeSystemWindowInsets();
        });

        // 初始化 RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4)); // 每行4个图标

        // 准备15个图标的数据
        List<IconItem> iconList = new ArrayList<>();
        iconList.add(new IconItem(R.drawable.ic_icon1, getString(R.string.text1), getString(R.string.icon1_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon2, getString(R.string.text2), getString(R.string.icon2_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon3, getString(R.string.text3), getString(R.string.icon3_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon4, getString(R.string.text4), getString(R.string.icon4_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon5, getString(R.string.text5), getString(R.string.icon5_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon6, getString(R.string.text6), getString(R.string.icon6_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon7, getString(R.string.text7), getString(R.string.icon7_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon8, getString(R.string.text8), getString(R.string.icon8_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon9, getString(R.string.text9), getString(R.string.icon9_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon10, getString(R.string.text10), getString(R.string.icon10_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon11, getString(R.string.text11), getString(R.string.icon11_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon12, getString(R.string.text12), getString(R.string.icon12_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon13, getString(R.string.text13), getString(R.string.icon13_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon14, getString(R.string.text14), getString(R.string.icon14_desc)));
        iconList.add(new IconItem(R.drawable.ic_icon15, getString(R.string.text15), getString(R.string.icon15_desc)));

        // 设置适配器
        IconAdapter adapter = new IconAdapter(iconList);
        recyclerView.setAdapter(adapter);
    }

    // 图标数据模型类（内部类）
    public static class IconItem {
        private final int iconRes;
        private final String text;
        private final String contentDescription;

        public IconItem(int iconRes, String text, String contentDescription) {
            this.iconRes = iconRes;
            this.text = text;
            this.contentDescription = contentDescription;
        }

        public int getIconRes() {
            return iconRes;
        }

        public String getText() {
            return text;
        }

        public String getContentDescription() {
            return contentDescription;
        }
    }

    // RecyclerView 适配器（内部类）
    public static class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {
        private final List<IconItem> iconList;

        public IconAdapter(List<IconItem> iconList) {
            this.iconList = iconList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_icon, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            IconItem item = iconList.get(position);
            holder.imageView.setImageResource(item.getIconRes());
            holder.textView.setText(item.getText());
            holder.imageView.setContentDescription(item.getContentDescription());
        }

        @Override
        public int getItemCount() {
            return iconList.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;

            ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_view);
                textView = itemView.findViewById(R.id.text_view);
            }
        }
    }
}