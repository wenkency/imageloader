package cn.carhouse.imageloader_sample.base64;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;

/**
 * Bsae64图片地址
 */
public class Base64Url extends GlideUrl {
    private String url;


    public Base64Url(String url) {
        super(url);
    }

    public Base64Url(String url, Headers headers) {
        super(url, headers);
    }
}
