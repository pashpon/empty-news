package com.example.empty_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DataGenerator {

    public static int[] mTabRes = {R.drawable.tab_home, R.drawable.tab_search, R.drawable.tab_notifications, R.drawable.tab_profile};
    public static int[] mTabResPressed = {R.drawable.tab_home_selected, R.drawable.tab_search_selected, R.drawable.tab_notifications_selected, R.drawable.tab_profile_selected};

    public static Fragment[] getFragments(String tag) {
        // 创建并返回 fragment 数组
        return new Fragment[]{
            new HomeFragment(),
            new SearchFragment(),
            new NotificationsFragment(),
            new ProfileFragment()
        };
    }

    public static View getTabView(Context context, int position) {
        // 你可以使用一个布局文件来创建每个 tab 的自定义视图
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        ImageView icon = view.findViewById(R.id.tab_content_image);
        TextView text = view.findViewById(R.id.tab_content_text);
        
        // 设置图标和文本
        icon.setImageResource(mTabRes[position]);
        text.setText(getTabTitle(position));

        return view;
    }

    private static String getTabTitle(int position) {
        switch (position) {
            case 0:
                return "首页";
            case 1:
                return "下载";
            case 2:
                return "关注";
            case 3:
                return "我的";
            default:
                return "";
        }
    }
}
