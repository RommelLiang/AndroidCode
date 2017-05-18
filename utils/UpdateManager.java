package com.product.jiamiao.healthbooks.utils;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.product.jiamiao.healthbooks.R;
import com.product.jiamiao.healthbooks.bean.AppUpdateBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * @author coolszy
 * @date 2012-4-26
 * @blog http://blog.92coding.com
 */

public class UpdateManager {
    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    /* 保存解析的XML信息 */
    HashMap<String, String> mHashMap;
    /* 下载保存路径 */
    private String mSavePath;
    /* 记录进度条数量 */
    private int progress;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;

    private Context mContext;
    /* 更新进度条 */
    private ProgressBar mProgress;
    private Dialog mDownloadDialog;

    private AppUpdateBean.DataBean data;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    mProgress.setProgress(progress);
                    tv.setText("已下载: " + progress + "%");

                    break;
                case DOWNLOAD_FINISH:
                    // 安装文件
                    installApk();
                    break;
                default:
                    break;
            }
        }

        ;
    };
    private Dialog noticeDialog;
    private TextView tv;
    private TextView tvLeft;
    private TextView tvCancel;
    private LinearLayout ll2;

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    /**
     * 检测软件更新
     *
     * @param data
     */
    public void checkUpdate(AppUpdateBean.DataBean data) {
        this.data = data;
        // 显示提示对话框
        showNoticeDialog();
    }


    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = context.getPackageManager().getPackageInfo("com.szy.update", 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialog() {
        // 构造对话框
        Builder builder =  new Builder(mContext);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.softupdate_progress, null);
        builder.setView(v);
        RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.rl);
        LinearLayout ll2 = (LinearLayout) v.findViewById(R.id.ll2);
        ((TextView) v.findViewById(R.id.tv2)).setText("检测到新版本,是否更新");
        tvLeft = (TextView) v.findViewById(R.id.tv_left);
        TextView tvRight =  (TextView) v.findViewById(R.id.tv_right);
        if (data.isIsEnabled()){
            tvLeft.setOnClickListener(onClickListener);
            tvRight.setOnClickListener(onClickListener);
        }else {
            tvLeft.setText("取消");
            tvRight.setText("更新");
        }

        rl.setVisibility(View.GONE);
        ll2.setVisibility(View.GONE);
        // 更新
//        builder.setPositiveButton(R.string.soft_update_updatebtn, new OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                // 显示下载对话框
//                showDownloadDialog();
//            }
//        });
//        // 稍后更新
//        builder.setNegativeButton(R.string.soft_update_later, new OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
        noticeDialog = builder.create();
        noticeDialog.show();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_left:
                    noticeDialog.dismiss();
                    break;
                case R.id.tv_right:
                    showDownloadDialog();
                    noticeDialog.dismiss();
                    break;
                case R.id.tv_cancel:
                    if (tvLeft.getText().toString().equals("取消")){
                        ll2.setVisibility(View.GONE);
                }
                    mDownloadDialog.dismiss();
                    break;
            }
        }
    };
    /**
     * 显示软件下载对话框
     */
    private void showDownloadDialog() {
        // 构造软件下载对话框
        Builder builder = new Builder(mContext);
//        builder.setTitle(R.string.soft_updating);
        // 给下载对话框增加进度条
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.softupdate_progress, null);
        builder.setView(v);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.rl);
        ll2 = (LinearLayout) v.findViewById(R.id.ll2);
        LinearLayout ll3 = (LinearLayout) v.findViewById(R.id.ll3);
        TextView tvLeft = (TextView) v.findViewById(R.id.tv_left);
        TextView tvRight = (TextView) v.findViewById(R.id.tv_right);
        ((TextView) v.findViewById(R.id.tv2)).setText("正在下载");
        tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
        tv = (TextView) v.findViewById(R.id.tv);
        tvLeft.setOnClickListener(onClickListener);
        tvRight.setOnClickListener(onClickListener);
        rl.setVisibility(View.VISIBLE);
        ll2.setVisibility(View.VISIBLE);
        ll3.setVisibility(View.GONE);
        tvCancel.setOnClickListener(onClickListener);
//        builder.setNegativeButton(R.string.soft_update_cancel, new OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                // 设置取消状态
//                cancelUpdate = true;
//            }
//        });
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
        // 现在文件
        downloadApk();
    }

    /**
     * 下载apk文件
     */
    private void downloadApk() {
        // 启动新线程下载软件
        new downloadApkThread().start();
    }


    /**
     * 下载文件线程
     *
     * @author coolszy
     * @date 2012-4-26
     * @blog http://blog.92coding.com
     */
    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    // 获得存储卡的路径
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "download";
                    URL url = new URL(data.getUrl());
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, "加秒健康账本");
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0) {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 取消下载对话框显示
            mDownloadDialog.dismiss();
        }
    }

    ;

    /**
     * 安装APK文件
     */
    private void installApk() {
        File apkfile = new File(mSavePath, "加秒健康账本");
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        mContext.startActivity(i);
    }
}
