package com.example.empty_news;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

public class VideoPlayer extends SurfaceView implements SurfaceHolder.Callback {
    private MediaPlayer mediaPlayer;
    private String videoUrl;
    private boolean isPrepared = false;
    private boolean surfaceAvailable = false; // 新增：标记 Surface 是否可用
    private int lastPosition = 0; // 新增：记录播放位置

    public VideoPlayer(Context context) {
        super(context);
        init();
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        getHolder().addCallback(this);
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    // 新增：检查 Surface 有效性
    private boolean isSurfaceValid() {
        return surfaceAvailable && getHolder().getSurface().isValid();
    }

    public void load(String videoUrl) {
        this.videoUrl = videoUrl;
        if (isSurfaceValid()) {
            initializeMediaPlayer();
        }
        // Surface 未准备好时，等待 surfaceCreated 回调触发初始化
    }

    private void initializeMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(videoUrl);
            mediaPlayer.setDisplay(getHolder());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(mp -> {
                isPrepared = true;
                mp.seekTo(lastPosition); // 恢复播放位置
                mp.start();
            });
            mediaPlayer.setOnErrorListener((mp, what, extra) -> {
                Log.e("VideoPlayer", "Error: what=" + what + ", extra=" + extra);
                release();
                return true; // 错误已处理
            });
        } catch (IOException e) {
            Log.e("VideoPlayer", "DataSource error: " + e.getMessage());
            release();
        }
    }

    public void start() {
        if (mediaPlayer != null && isPrepared && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            lastPosition = mediaPlayer.getCurrentPosition(); // 记录暂停位置
        }
    }

    public void seekTo(int position) {
        if (mediaPlayer != null && isPrepared) {
            mediaPlayer.seekTo(position);
        }
    }

    public void setVolume(float volume) {
        if (mediaPlayer != null && isPrepared) {
            mediaPlayer.setVolume(volume, volume);
        }
    }

    public void release() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
            isPrepared = false;
            lastPosition = 0;
        }
    }

    //--- SurfaceHolder.Callback 改进 ---
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceAvailable = true;
        if (videoUrl != null && (mediaPlayer == null || !isPrepared)) {
            initializeMediaPlayer(); // Surface 重建时重新初始化
        } else if (mediaPlayer != null) {
            mediaPlayer.setDisplay(holder); // 绑定到新 Surface
            if (isPrepared) {
                mediaPlayer.seekTo(lastPosition);
                mediaPlayer.start();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // 无需处理
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceAvailable = false;
        pause(); // 改为暂停而非释放，保留 MediaPlayer 状态
    }
}