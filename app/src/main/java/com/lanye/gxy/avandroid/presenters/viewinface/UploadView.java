package com.lanye.gxy.avandroid.presenters.viewinface;

/**
 * 图片上传页
 */
public interface UploadView {
    void onUploadProcess(int percent);
    void onUploadResult(int code, String url);
}
