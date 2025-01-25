package com.example.empty_news.download;

import android.content.res.AssetManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
    public static void copyAssetFolder(AssetManager assetManager, String srcPath, File destDir) throws IOException {
        String[] files = assetManager.list(srcPath);
        if (files == null) return;

        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        for (String file : files) {
            String assetFilePath = srcPath + "/" + file;
            File outFile = new File(destDir, file);

            if (assetManager.list(assetFilePath).length > 0) {
                // 递归复制文件夹
                copyAssetFolder(assetManager, assetFilePath, outFile);
            } else {
                copyAssetFile(assetManager, assetFilePath, outFile);
            }
        }
    }

    private static void copyAssetFile(AssetManager assetManager, String assetFilePath, File outFile) throws IOException {
        InputStream in = assetManager.open(assetFilePath);
        OutputStream out = new FileOutputStream(outFile);

        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        out.close();
    }
}
