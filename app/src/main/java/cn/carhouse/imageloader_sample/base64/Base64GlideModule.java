package cn.carhouse.imageloader_sample.base64;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.LibraryGlideModule;

import java.io.InputStream;
import java.net.Proxy;

import okhttp3.OkHttpClient;

/**
 * 自动注册
 */
@GlideModule
public final class Base64GlideModule extends AppGlideModule {
    @Override
    public void registerComponents(
            @NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        // 预设置
        registry.prepend(Base64Url.class, InputStream.class, new Base64UrlLoader.Factory(getClient()));
    }

    private OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .proxy(Proxy.NO_PROXY)
                .build();
        return client;
    }

}
