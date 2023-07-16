package cn.carhouse.imageloader_sample.okhttp3;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;

import java.net.URL;

/**
 * 自定义解析
 */
public class OkGlideUrl extends GlideUrl {
    public OkGlideUrl(URL url) {
        super(url);
    }

    public OkGlideUrl(String url) {
        super(url);
    }

    public OkGlideUrl(URL url, Headers headers) {
        super(url, headers);
    }

    public OkGlideUrl(String url, Headers headers) {
        super(url, headers);
    }
}
