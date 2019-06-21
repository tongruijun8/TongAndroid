package com.trj.tlib.uils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 作者：小童
 * 创建时间：2019/4/8 17:16
 */
public class BitmapUtils {


    public static Bitmap urlToBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }


    public static Uri saveBitmapToFile(Context context, Bitmap bitmap, String imgname) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File dir = new File(Environment.getExternalStorageDirectory()+"/Images");
            if (!dir.exists())
                dir.mkdirs();
            File file = new File(dir, imgname);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
                return Uri.fromFile(file);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Toast.makeText(context, "请确认已经插入SD卡", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

}
