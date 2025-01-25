package com.example.empty_news.writer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.empty_news.R;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private List<FileInfo> fileList;
    private Context context;
    private OnFileClickListener fileClickListener;

    // 新增构造器，带回调
    public FileAdapter(List<FileInfo> fileList, Context context, OnFileClickListener fileClickListener) {
        this.fileList = fileList;
        this.context = context;
        this.fileClickListener = fileClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FileInfo fileInfo = fileList.get(position);

        // 设置文件名
        holder.fileName.setText(fileInfo.getName());

        if ("folder".equals(fileInfo.getType())) {
            holder.imageView.setImageResource(R.drawable.folder); // 文件夹图标
            holder.itemView.setOnClickListener(v -> {
                if (fileClickListener != null) {
                    fileClickListener.onFileClick(fileInfo); // 调用回调
                }
            });
        } else if ("image".equals(fileInfo.getType())) {
            Glide.with(context)
                    .load("http://192.168.5.28:8080/files" + fileInfo.getName())
                    .into(holder.imageView);
        } else if ("video".equals(fileInfo.getType())) {
            holder.imageView.setImageResource(R.drawable.video); // 视频图标
        } else if ("text".equals(fileInfo.getType())) {
            holder.imageView.setImageResource(R.drawable.text); // 文本图标
        }
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    // 添加接口回调，用于处理文件点击
    public interface OnFileClickListener {
        void onFileClick(FileInfo fileInfo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fileName;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.file_name);
            imageView = itemView.findViewById(R.id.file_icon);
        }
    }
}
