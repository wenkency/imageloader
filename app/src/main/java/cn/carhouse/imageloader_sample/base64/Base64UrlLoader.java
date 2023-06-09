package cn.carhouse.imageloader_sample.base64;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.signature.ObjectKey;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * 加载Base64图片
 */
public class Base64UrlLoader implements ModelLoader<Base64Url, InputStream> {
    private final OkHttpClient client;

    public Base64UrlLoader(OkHttpClient client) {
        this.client = client;
    }

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(@NonNull Base64Url model, int width, int height, @NonNull Options options) {
        return new LoadData<>(new ObjectKey(model), new Base64StreamFetcher(client, model));
    }

    @Override
    public boolean handles(@NonNull Base64Url base64Url) {
        return true;
    }


    // ------------================
    public static class Factory implements ModelLoaderFactory<Base64Url, InputStream> {

        private final OkHttpClient client;


        public Factory() {
            this.client = new OkHttpClient();
        }

        public Factory(OkHttpClient client) {
            this.client = client;
        }

        @NonNull
        @Override
        public ModelLoader<Base64Url, InputStream> build(@NonNull MultiModelLoaderFactory multiFactory) {
            return new Base64UrlLoader(client);
        }

        @Override
        public void teardown() {

        }
    }

}
