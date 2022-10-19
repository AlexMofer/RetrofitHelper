/*
 * Copyright (C) 2022 AlexMofer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.am.retrofit.helper.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 下载
 * Created by Alex on 2022/3/25.
 */
public class DownloadUtils {

    private DownloadUtils() {
        //no instance
    }

    /**
     * 下载
     *
     * @param url      链接
     * @param target1  目标文件1
     * @param target2  目标文件2
     * @param md       信息摘要文件
     * @param callback 回调
     * @return 下载成功时返回true
     */
    public static boolean download(String url, File target1, File target2,
                                   MessageDigest md, Callback callback) {
        if (callback != null && callback.isCanceled()) {
            return false;
        }
        if (callback != null) {
            callback.onProgressChanged(0);
        }
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("Connection", "close")
                .build();
        try (final Response response = new OkHttpClient().newCall(request).execute()) {
            if (callback != null && callback.isCanceled()) {
                return false;
            }
            final ResponseBody body = response.body();
            if (body != null) {
                final InputStream input = body.byteStream();
                final long length = body.contentLength();
                try {
                    final boolean result = write(input, target1, target2, md, callback, length);
                    if (result) {
                        if (callback != null) {
                            callback.onProgressChanged(1);
                        }
                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean write(InputStream source, File target1, File target2,
                                 MessageDigest md, Callback callback,
                                 long length) throws Exception {
        try (final FileOutputStream output1 = new FileOutputStream(target1);
             final FileOutputStream output2 = target2 == null ? null : new FileOutputStream(target2);
             final DigestOutputStream dos = md == null ? null : new DigestOutputStream(output1, md)) {
            final byte[] buffer = new byte[1024];
            int count;
            float total = 0;
            while ((count = source.read(buffer)) != -1) {
                if (callback != null && callback.isCanceled()) {
                    return false;
                }
                if (count == 0) {
                    count = source.read();
                    if (count < 0)
                        break;
                    if (dos != null) {
                        dos.write(count);
                    } else {
                        output1.write(count);
                    }
                    if (output2 != null) {
                        output2.write(count);
                    }
                    total += count;
                    if (callback != null && total > 0 && total < length) {
                        callback.onProgressChanged(total / length);
                    }
                    continue;
                }
                if (dos != null) {
                    dos.write(buffer, 0, count);
                    dos.flush();
                } else {
                    output1.write(buffer, 0, count);
                    output1.flush();
                }
                if (output2 != null) {
                    output2.write(buffer, 0, count);
                    output2.flush();
                }
                total += count;
                if (callback != null && total > 0 && total < length) {
                    callback.onProgressChanged(total / length);
                }
            }
        }
        return true;
    }

    /**
     * 下载回调
     */
    public interface Callback {

        /**
         * 下载进度变化
         *
         * @param progress 进度（0-1）
         */
        void onProgressChanged(float progress);

        /**
         * 判断下载是否已经取消
         *
         * @return 下载已经取消时返回true
         */
        boolean isCanceled();
    }
}