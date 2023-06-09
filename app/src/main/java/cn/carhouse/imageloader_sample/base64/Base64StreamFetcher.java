package cn.carhouse.imageloader_sample.base64;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.util.ContentLengthInputStream;
import com.bumptech.glide.util.Preconditions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 网络请求图片
 */
public class Base64StreamFetcher implements DataFetcher<InputStream>, Callback {
    private final OkHttpClient client;
    private final Base64Url url;

    private DataCallback<? super InputStream> callback;
    private Call call;
    private InputStream stream;
    private ResponseBody body;

    public Base64StreamFetcher(OkHttpClient client, Base64Url url) {
        this.client = client;
        this.url = url;
    }


    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super InputStream> callback) {
        this.callback = callback;
        // 请求图片
        Request.Builder builder = new Request.Builder().url(url.toStringUrl());
        for (Map.Entry<String, String> headerEntry : url.getHeaders().entrySet()) {
            String key = headerEntry.getKey();
            builder.addHeader(key, headerEntry.getValue());
        }
        Request request = builder.build();
        call = client.newCall(request);
        call.enqueue(this);
    }

    // 失败回调
    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        callback.onLoadFailed(e);
        Log.e("TAG", "onFailure " + e.getMessage());
    }

    // 成功回调
    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        body = response.body();
        if (response.isSuccessful() && body != null) {
            Log.e("TAG", "onResponse isSuccessful ");
            if (!TextUtils.isEmpty(url.toStringUrl())) {
                // Base64图片单独解析
                byte[] data = Base64.decode(body.string(), Base64.DEFAULT);
                InputStream inputStream = new ByteArrayInputStream(data);
                callback.onDataReady(inputStream);
            } else {
                // 参考okhttp默认解析
                long contentLength = Preconditions.checkNotNull(body).contentLength();
                stream = ContentLengthInputStream.obtain(body.byteStream(), contentLength);
                callback.onDataReady(stream);
            }
        } else {
            callback.onLoadFailed(new HttpException(response.message(), response.code()));
            Log.e("TAG", "onFailure response " + response.message());
        }
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {

            }
        }
        if (body != null) {
            body.close();
        }
        callback = null;
    }

    @Override
    public void cancel() {
        Call local = call;
        if (local != null) {
            local.cancel();
        }
    }

    @NonNull
    @Override
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.REMOTE;
    }


}
