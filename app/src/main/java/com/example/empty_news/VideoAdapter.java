package com.example.empty_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.empty_news.VideoPlayer;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private Context context;
    private List<String> videoUrls;
    private RecyclerView recyclerView;

    public VideoAdapter(Context context, List<String> videoUrls, RecyclerView recyclerView) {
        this.context = context;
        this.videoUrls = videoUrls;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        String videoUrl = videoUrls.get(position);
        holder.videoPlayer.load(videoUrl);

        // 设置双击事件监听器
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            private long lastClickTime = 0;

            @Override
            public void onClick(View v) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastClickTime < 300) { // 双击时间间隔小于300毫秒
                    // 双击事件：重新播放视频
                    if (holder.videoPlayer.isPrepared()) {
                        holder.videoPlayer.seekTo(0); // 跳转到视频开头
                        holder.videoPlayer.start(); // 开始播放
                    }
                }
                lastClickTime = currentTime;
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoUrls.size();
    }

    public void setVolume(int position, float volume) {
        VideoViewHolder holder = (VideoViewHolder) recyclerView.findViewHolderForAdapterPosition(position);
        if (holder != null) {
            holder.videoPlayer.setVolume(volume);
        }
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoPlayer videoPlayer;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoPlayer = itemView.findViewById(R.id.videoPlayer);
        }
    }
}