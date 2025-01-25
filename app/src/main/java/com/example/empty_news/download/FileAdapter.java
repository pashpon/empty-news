package com.example.empty_news.download;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {
    private List<File> files;
    private Context context;

    public FileAdapter(Context context, List<File> files) {
        this.context = context;
        this.files = files;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        File file = files.get(position);
        holder.textView.setText(file.getName());

        // 设置点击事件，点击后尝试打开文件
        holder.itemView.setOnClickListener(v -> openFile(file));
    }

    @Override
    public int getItemCount() {
        return files != null ? files.size() : 0;
    }

    static class FileViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        FileViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }

    // 打开文件的方法
    private void openFile(File file) {
        Uri uri = FileProvider.getUriForFile(context, "com.example.empty_news.fileprovider", file);
        Intent intent = new Intent(Intent.ACTION_VIEW);

        // 根据文件类型设置 MIME 类型
        String mimeType = context.getContentResolver().getType(uri);
        intent.setDataAndType(uri, mimeType);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
