package com.example.empty_news.pa;

public class IconItem {
    private final int iconRes;
    private final String text;
    private final String contentDescription; // 新增字段

    // 修复：构造函数需要接收三个参数
    public IconItem(int iconRes, String text, String contentDescription) {
        this.iconRes = iconRes;
        this.text = text;
        this.contentDescription = contentDescription; // 正确初始化
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