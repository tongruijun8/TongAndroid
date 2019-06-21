package com.trj.tlib.uils;

import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class FileUtils {

    /**
     * 文件转base64字符串
     *
     * @param file
     * @return
     */
    public static String fileToBase64(File file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            int length = in.read(bytes);
            base64 = Base64.encodeToString(bytes, 0, length, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return base64;
    }

    /**
     * base64字符串转文件
     *
     * @param base64
     * @return
     */
    public static File base64ToFile(String base64) {
        File file = null;
        String fileName = "/Petssions/record/testFile.amr";
        FileOutputStream out = null;
        try {
            // 解码，然后将字节转换为文件
            file = new File(Environment.getExternalStorageDirectory(), fileName);
            if (!file.exists())
                file.createNewFile();
            byte[] bytes = Base64.decode(base64, Base64.DEFAULT);// 将字符串转换为byte数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            out = new FileOutputStream(file);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); // 文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 获取文件的扩展名
     *
     * @param filename 文件路径
     * @return
     */
    public static String fileNamePostfix(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return ".png";
    }

    /*
     * Java文件操作 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }



    /**
     * 内部存储
     * @return
     */
    // 获取 Android 数据目录。即data的目录(/data)
    public static String storageDataPath(){
        return storageDataFile().getPath();
    }
    public static File storageDataFile(){
        return Environment.getDataDirectory();
    }
    // 获取 Android 下载/缓存内容目录。即(/cache)
    public static String storageCachePath(){
        return storageCacheFile().getPath();
    }
    public static File storageCacheFile(){
        return Environment.getDownloadCacheDirectory();
    }
    // 获取 Android 的根目录 即系统主目录(/system)
    public static String storageRootPath(){
        return storageRootFile().getPath();
    }
    public static File storageRootFile(){
        return Environment.getRootDirectory();
    }

    /**
     * 获取外部存储目录即 SDCard  (/storage/sdcard)
     * @return
     */
    public static String storageExternalPath(){
        return storageExternalFile().getPath();
    }
    public static File storageExternalFile(){
        return Environment.getExternalStorageDirectory();
    }

    /**
     * 获取一个公用的外部存储器目录
     * 获取路径方式是Environment.getExternalStorageDirectory() /storage/sdcard0
     * @param DIRECTORY
     *
     * type值                                            说明
     * @see Environment.DIRECTORY_MUSIC                 音乐存放  
     * @see Environment.DIRECTORY_PODCASTS              系统广播
     * @see Environment.DIRECTORY_RINGTONES             系统铃声
     * @see Environment.DIRECTORY_ALARMS                系统提醒铃声
     * @see Environment.DIRECTORY_NOTIFICATIONS         系统通知铃声
     * @see Environment.DIRECTORY_PICTURES              图片存放
     * @see Environment.DIRECTORY_MOVIES                电影存放
     * @see Environment.DIRECTORY_DOWNLOADS             下载
     * @see Environment.DIRECTORY_DCIM                  相机拍摄照片和视频
     *
     * @return  返回 File 文件的路径
     *
     */
    @SuppressWarnings("JavadocReference")
    public static String storageExternalPublicPath(String DIRECTORY){
        return storageExternalPublicFile(DIRECTORY).getPath();
    }
    public static File storageExternalPublicFile(String DIRECTORY){
        return Environment.getExternalStoragePublicDirectory(DIRECTORY);
    }

    /**
     * 格式化文件的大小（用于显示）
     * @param fileSize 文件的大小
     * @return
     */
    public static String toFileSizeString(long fileSize) {
        DecimalFormat df = new DecimalFormat("0.00");
        String fileSizeString;
        if (fileSize < 1024L) {
            fileSizeString = fileSize + "B";
        } else if (fileSize < 1048576L) {
            fileSizeString = df.format((double)fileSize / 1024.0D) + "K";
        } else if (fileSize < 1073741824L) {
            fileSizeString = df.format((double)fileSize / 1048576.0D) + "M";
        } else {
            fileSizeString = df.format((double)fileSize / 1.073741824E9D) + "G";
        }

        return fileSizeString;
    }


    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

}
