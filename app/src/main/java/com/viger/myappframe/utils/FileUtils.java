package com.viger.myappframe.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    private void writeFileToSdCard(Context context) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Download";
        File file = new File(path + File.separator + "mm.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("message".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(context, "写入文件成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
