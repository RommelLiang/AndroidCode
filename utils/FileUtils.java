package com.product.jiamiao.healthbooks.utils;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 文件类
 * Created by Reinhard Tristan Eugen Heydrich on 2016/10/28.
 */
public class FileUtils {
    /**
     * 从assets目录中复制整个文件夹内容
     *
     * @param context Context 使用CopyFiles类的Activity
     * @param oldPath String  原文件路径  如：/aa
     * @param newPath String  复制后路径  如：xx:/bb/cc
     */
    public static void copyFiles2Assets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {
                //如果是目录,创建目录
                File file = new File(newPath);
                file.mkdirs();
                //如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFiles2Assets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {//如果是文件
                File file = new File(newPath);
                //如果文件不存在
                if (!file.exists()) {
                    InputStream is = context.getAssets().open(oldPath);
                    FileOutputStream fos = new FileOutputStream(new File(newPath));
                    byte[] buffer = new byte[1024];
                    int byteCount = 0;
                    while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                        fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                    }
                    fos.flush();//刷新缓冲区
                    is.close();
                    fos.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.v(e.getLocalizedMessage());
            //如果捕捉到错误则通知UI线程
            //MainActivity.handler.sendEmptyMessage(COPY_FALSE);
        }
    }

    /**
     * 获取文件夹数量
     *
     * @param filePath
     * @return
     */
    public static List<File> getFolderList(String filePath) {
        List<File> fileNames = new ArrayList<>();
        try {
            File file = new File(filePath);
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory())
                    fileNames.add(f);
            }
            return fileNames;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.v(e.getLocalizedMessage());
            return fileNames;
        }
    }

    /**
     * 获取文件数量
     *
     * @param filePath
     * @return
     */
    public static ArrayList<File> getFileList(String filePath) {
        ArrayList<File> fileNames = new ArrayList<>();
        try {
            File file = new File(filePath);
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile())
                    fileNames.add(f);
            }
            return fileNames;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.v(e.getLocalizedMessage());
            return fileNames;
        }
    }

    /**
     * 将字节数组写入手机内部存储
     *
     * @param context
     * @param fileName 只要文件名，不要别的
     * @param mode
     * @param data
     * @return 如果成功，返回被存储文件的file，如果失败，返回null
     */
    public static File saveBytesToFile(Context context, String fileName, int mode, byte[] data) {
        boolean flag = false;
        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(fileName, mode);
            outputStream.write(data);
            flag = true;
            System.out.println("文件存储" + fileName + "成功！");
            return context.getFileStreamPath(fileName);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("写入文件" + fileName + "失败！！！！！！");
            return null;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e2) {
                    // TODO: handle exception

                }
            }
        }

    }

    /**
     * 将文件写入SD卡，可以规定父目录，如: /storage/emulated/0/imgs/haha2.txt
     *
     * @param parentFolder 形如“imgs”
     * @param fileName     形如photo.jpg
     * @param data
     * @return
     */
    public static File saveBytesToFileSD(String parentFolder, String fileName, byte[] data) {
        File file = null;
        if (parentFolder == null || parentFolder.length() == 0) {
            file = new File(Environment.getExternalStorageDirectory().toString() + "/" + fileName);

        } else {
            file = new File(Environment.getExternalStorageDirectory().toString() + "/" + parentFolder + "/" + fileName);
        }
        return saveBytesToFileSD(file, data);

    }

    /**
     * 把文件写入sd卡的缓存目录下，如/storage/emulated/0/Android/data/com.example.situ_android_publish/cache
     *
     * @param context
     * @param parentFolder 父目录形如“imgs”
     * @param fileName     形如photo.jpg
     * @param data
     * @return
     */
    public static File saveBytesToFileSDCache(Context context, String parentFolder, String fileName, byte[] data) {
        File file = null;
        if (parentFolder == null || parentFolder.length() == 0) {
            file = new File(context.getExternalCacheDir().toString() + "/" + fileName);

        } else {
            file = new File(context.getExternalCacheDir().toString() + "/" + parentFolder + "/" + fileName);
        }
        return saveBytesToFileSD(file, data);
    }

    /**
     * 把文件写进机身内存的缓存目录中
     *
     * @param context
     * @param parentFolder 父目录
     * @param fileName
     * @param data
     * @return
     */
    public static File saveBytesToFileCache(Context context, String parentFolder, String fileName, byte[] data) {
        File file = null;
        if (parentFolder == null || parentFolder.length() == 0) {
            file = new File(context.getCacheDir().toString() + "/" + fileName);

        } else {
            file = new File(context.getCacheDir().toString() + "/" + parentFolder + "/" + fileName);
        }
        return saveBytesToFile(file, data);
    }


    /**
     * 将字节数组写入sd卡
     *
     * @param file
     * @param data
     * @return
     */
    public static File saveBytesToFileSD(File file, byte[] data) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            System.out.println("未安装SD卡或没有权限");
            return null;
        }
        //判断存储空间是否足够
//      if(StorageUtil.getAvailableExternal_SDMemorySize()<data.length){
//          System.out.println("SD卡空间不足，写入失败");
//          return null;
//      }
        FileOutputStream outputStream = null;
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.out.println("创建文件" + file + "失败！！！！！！");
            return null;
        }
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(data);
            System.out.println("文件存储" + file + "成功！");
            return file;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("写入文件" + file + "失败！！！！！！");
            return null;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e2) {
                    // TODO: handle exception

                }
            }
        }
    }

    /**
     * 根据父目录和文件名读取sd卡中的数据
     *
     * @param parentFolder
     * @param fileName
     * @return
     */
    public byte[] readBytesFromFileSD(String parentFolder, String fileName) {
        File file = null;
        if (parentFolder == null || parentFolder.length() == 0) {
            file = new File(Environment.getExternalStorageDirectory().toString() + "/" + fileName);
        } else {
            file = new File(Environment.getExternalStorageDirectory().toString() + "/" + parentFolder + "/" + fileName);
        }
        return readBytesFromFileSD(file);
    }

    public byte[] readBytesFromFileSDCache(Context context, String parentFolder, String fileName) {
        File file = null;
        if (parentFolder == null || parentFolder.length() == 0) {
            file = new File(context.getExternalCacheDir().toString() + "/" + fileName);

        } else {
            file = new File(context.getExternalCacheDir().toString() + "/" + parentFolder + "/" + fileName);
        }
        return readBytesFromFileSD(file);
    }


    /**
     * 根据file对象读取sd卡中的数据
     *
     * @param file
     * @return
     */
    @SuppressWarnings("resource")
    public static byte[] readBytesFromFileSD(File file) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            System.out.println("未安装SD卡或没有权限");
            return null;
        }
        System.out.println("正在读取" + file);
        FileInputStream fileInputStream = null;
        //输出流用于生产字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (!file.exists()) {
            System.out.println("要读取的文件不存在" + file);
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            int len = 0;
            byte[] data = new byte[1024];
            while ((len = fileInputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);

            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("读取文件" + file + "失败");
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 通过文件父目录和文件名来获取手机内存里的文件
     *
     * @param context
     * @param parentFolder
     * @param fileName
     * @return
     */
    public static byte[] readBytesFromFile(Context context, String parentFolder, String fileName) {
        File file = null;
        if (parentFolder == null || parentFolder.length() == 0) {
            file = new File(context.getFilesDir().toString() + "/" + fileName);
        } else {
            file = new File(context.getFilesDir().toString() + "/" + parentFolder + "/" + fileName);
        }
        return readBytesFromFile(file);
    }

    /**
     * 获取手机内存中本app的缓存文件
     *
     * @param context
     * @param parentFolder
     * @param fileName
     * @return
     */
    public static byte[] readBytesFromFileCache(Context context, String parentFolder, String fileName) {
        File file = null;
        if (parentFolder == null || parentFolder.length() == 0) {
            file = new File(context.getCacheDir().toString() + "/" + fileName);
        } else {
            file = new File(context.getCacheDir().toString() + "/" + parentFolder + "/" + fileName);
        }
        return readBytesFromFile(file);
    }

    public static byte[] readBytesFromFile(File file) {
        System.out.println("正在读取" + file);
        FileInputStream fileInputStream = null;
        //输出流用于生产字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (!file.exists()) {
            System.out.println("要读取的文件不存在" + file);
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            int len = 0;
            byte[] data = new byte[1024];
            while ((len = fileInputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);

            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("读取文件" + file + "失败");
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据父目录和文件名写入手机内部存储
     *
     * @param context
     * @param parentFolder
     * @param fileName
     * @param data
     * @return
     */
    public static File saveBytesToFile(Context context, String parentFolder, String fileName, byte[] data) {
        File file = null;
        if (parentFolder == null || parentFolder.length() == 0) {
            file = new File(context.getFilesDir().toString() + "/" + fileName);
        } else {
            file = new File(context.getFilesDir().toString() + "/" + parentFolder + "/" + fileName);
        }
        return saveBytesToFile(file, data);
    }

    /**
     * 通过file对象写手机的内部存储
     *
     * @param file
     * @param data
     * @return
     */
    public static File saveBytesToFile(File file, byte[] data) {
        FileOutputStream outputStream = null;
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.out.println("创建文件" + file + "失败！！！！！！");
            return null;
        }
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(data);
            System.out.println("文件存储" + file + "成功！");
            return file;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("写入文件" + file + "失败！！！！！！");
            return null;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e2) {
                    // TODO: handle exception

                }
            }
        }
    }

    /**
     * 写新的字符串或者覆盖原来的文件
     *
     * @param context
     * @param fileName 只要文件名，不要别的
     * @param myData
     * @return
     */
    public static File saveTextToNewFile(Context context, String fileName, String myData) {
        byte[] data = myData.getBytes();
        return saveBytesToFile(context, fileName, context.MODE_PRIVATE, data);
    }


    /**
     * 追加文件，在系统内部存储
     *
     * @param context
     * @param fileName 只要文件名，不要别的
     * @param data
     * @return
     */
    public static File appendTextToFile(Context context, String fileName, String data) {
        return saveBytesToFile(context, fileName, context.MODE_APPEND, data.getBytes());
    }

    public static File appendBytesToFile(Context context, String fileName, byte[] data) {
        return saveBytesToFile(context, fileName, context.MODE_APPEND, data);
    }

    /**
     * 从内部存储文件中读取字符数组
     *
     * @param context
     * @param fileName
     * @return
     */
    public static byte[] readBytesFromFile(Context context, String fileName) {
        System.out.println("正在读取" + context.getFileStreamPath(fileName));
        FileInputStream fileInputStream = null;
        //输出流用于生产字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            fileInputStream = context.openFileInput(fileName);
            int len = 0;
            byte[] data = new byte[1024];
            while ((len = fileInputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);

            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("读取文件" + fileName + "失败");
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取本app机身内存根目录下的文件里的字符串
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readTextFromFile(Context context, String fileName) {
        byte[] bytes = readBytesFromFile(context, fileName);
        if (bytes == null) {
            System.out.println("文件不存在" + fileName);
            return null;
        }
        return new String(bytes);
    }

    /**
     * @return Json数据（String）
     * @description 通过assets文件获取json数据。
     */
    public static String getStrFromAssets(Context context, String name) {
        ByteArrayOutputStream bos = null;
        try {
            InputStream input = context.getAssets().open(name);
            byte buf[] = new byte[1024];
            bos = new ByteArrayOutputStream();
            int length = -1;
            while ((length = input.read(buf)) != -1) {
                bos.write(buf, 0, length);
            }
            bos.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toString();
    }

    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
    public static File getDiskCacheDirs(Context context) {
        File cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir();
        } else {
            cachePath = context.getCacheDir();
        }
        return cachePath;
    }

    public static String getDiskFileDir(Context context) {
        String filePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            filePath = context.getExternalFilesDir("figure").getPath();
        } else {
            filePath = context.getFilesDir().getPath() + "/figure";
            File file = new File(filePath);
            if (!file.exists())
                file.mkdirs();
        }
        return filePath;
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
     */
    private static void deleteFilesByDirectory(String dir) {
        File directory = new File(dir);
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    public static void deleteCache(Context context) {
        deleteFilesByDirectory(getDiskCacheDir(context));
    }


    public static String getCacheSize(Context context) throws Exception {
        return getFormatSize(getFolderSize(getDiskCacheDirs(context)));
    }




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
    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
}
