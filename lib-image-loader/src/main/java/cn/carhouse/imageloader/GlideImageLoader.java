package cn.carhouse.imageloader;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * ================================================================
 * 版权: 爱车小屋所有（C） 2019
 * <p>
 * 作者：刘付文 （61128910@qq.com）
 * <p>
 * 时间: 2019-11-15 09:05
 * <p>
 * 描述：
 * ================================================================
 */
@GlideModule
public class GlideImageLoader extends AppGlideModule implements IImageLoader {

    private int mWidth = 480;
    private int mHeight = 800;

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();//16588800
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();//33177600
        int customMemoryCacheSize = (int) (0.8 * defaultMemoryCacheSize);//19906560
        int customBitmapPoolSize = (int) (0.8 * defaultBitmapPoolSize);//39813120
        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
    }

    // 模糊加载
    public static final float SIZE_MULTIPLIER = 0.1f;
    private ColorDrawable mErrorDrawable = new ColorDrawable(Color.TRANSPARENT);
    private ColorDrawable mLoadingDrawable = new ColorDrawable(Color.parseColor("#f2f2f2"));
    private ColorDrawable mFallbackDrawable = new ColorDrawable(Color.parseColor("#f2f2f2"));

    @Override
    public void displayImage(ImageView iv, String url) {
        if (iv == null || TextUtils.isEmpty(url)) {
            return;
        }
        getDrawableRequestBuilder(iv, url).into(iv);
    }

    @Override
    public void displayImage(ImageView iv, int resId) {
        if (iv == null) {
            return;
        }
        getRequestBuilder(Glide.with(iv).load(resId)).into(iv);
    }

    @Override
    public void displayImage(ImageView iv, int resId, int errorId) {
        if (iv == null) {
            return;
        }
        getRequestBuilder(Glide.with(iv).load(resId), errorId).into(iv);
    }

    @Override
    public void displayImage(ImageView iv, String url, int errorId) {
        if (iv == null || TextUtils.isEmpty(url)) {
            return;
        }
        getDrawableRequestBuilder(iv, url, errorId).into(iv);
    }

    public void displayImage(ImageView iv, Uri url) {
        if (iv == null) {
            return;
        }
        getDrawableRequestBuilder(iv, url).into(iv);
    }

    public void displayImage(ImageView iv, Uri url, int errorId) {
        if (iv == null) {
            return;
        }
        getDrawableRequestBuilder(iv, url, errorId).into(iv);
    }

    @Override
    public void displayCircleImage(ImageView iv, String url) {
        if (iv == null || TextUtils.isEmpty(url)) {
            return;
        }
        getDrawableRequestBuilder(iv, url)
                .circleCrop()
                .into(iv);
    }

    @Override
    public void displayCircleImage(ImageView iv, String url, int errorId) {
        if (iv == null || TextUtils.isEmpty(url)) {
            return;
        }
        getDrawableRequestBuilder(iv, url, errorId)
                .circleCrop()
                .into(iv);
    }


    @Override
    public void displayRadiusImage(ImageView iv, String url, int radius) {
        if (iv == null || TextUtils.isEmpty(url)) {
            return;
        }
        getDrawableRequestBuilder(iv, url)
                .transform(new GlideCircleTransform(iv.getContext(), radius))
                .into(iv);
    }

    @Override
    public void displayRadiusImage(ImageView iv, String url, int radius, int errorId) {
        if (iv == null || TextUtils.isEmpty(url)) {
            return;
        }
        getDrawableRequestBuilder(iv, url, errorId)
                .transform(new GlideCircleTransform(iv.getContext(), radius))
                .into(iv);
    }

    @Override
    public void displayImage(final View view, String url) {
        if (view == null || TextUtils.isEmpty(url)) {
            return;
        }
        getDrawableRequestBuilder(view, url)
                .into(new GlideCustomTarget(view));
    }

    @Override
    public void displayImage(final View view, String url, final int errorId) {
        if (view == null || TextUtils.isEmpty(url)) {
            return;
        }
        getDrawableRequestBuilder(view, url)
                .into(new GlideCustomTarget(view, errorId));
    }

    @Override
    public void displayCircleImage(View view, String url) {
        getDrawableRequestBuilder(view, url)
                .circleCrop()
                .into(new GlideCustomTarget(view));
    }

    @Override
    public void displayCircleImage(View view, String url, int errorId) {
        getDrawableRequestBuilder(view, url)
                .circleCrop()
                .into(new GlideCustomTarget(view, errorId));
    }

    @Override
    public void displayRadiusImage(View view, String url, int radius) {
        getDrawableRequestBuilder(view, url)
                .transform(new GlideCircleTransform(view.getContext(), radius))
                .into(new GlideCustomTarget(view));
    }

    @Override
    public void displayRadiusImage(View view, String url, int radius, int errorId) {
        getDrawableRequestBuilder(view, url)
                .transform(new GlideCircleTransform(view.getContext(), radius))
                .into(new GlideCustomTarget(view, errorId));
    }


    private static class GlideCustomTarget extends CustomTarget<Drawable> {
        private View view;
        private int errorId = -1;

        public GlideCustomTarget(View view, int errorId) {
            this.view = view;
            this.errorId = errorId;
        }

        public GlideCustomTarget(View view) {
            this.view = view;
        }

        @Override
        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
            if (view == null) {
                return;
            }
            view.setBackgroundDrawable(resource);
        }

        @Override
        public void onLoadCleared(@Nullable Drawable placeholder) {
            if (view == null) {
                return;
            }
            view.setBackgroundDrawable(placeholder);
        }

        @Override
        public void onLoadFailed(@Nullable Drawable errorDrawable) {
            if (errorId != -1) {
                view.setBackgroundResource(errorId);
            }
        }
    }

    private RequestBuilder<Drawable> getDrawableRequestBuilder(View view, String url) {
        return getRequestBuilder(Glide.with(view).load(url));

    }

    private RequestBuilder<Drawable> getDrawableRequestBuilder(View view, Uri uri) {
        return getRequestBuilder(Glide.with(view).load(uri));
    }


    private RequestBuilder<Drawable> getDrawableRequestBuilder(View view, String url, int errorId) {
        return getRequestBuilder(Glide.with(view).load(url), errorId);
    }

    private RequestBuilder<Drawable> getDrawableRequestBuilder(View view, Uri url, int errorId) {
        return getRequestBuilder(Glide.with(view).load(url), errorId);
    }

    private RequestBuilder<Drawable> getRequestBuilder(RequestBuilder<Drawable> builder, int errorId) {
        builder.placeholder(mLoadingDrawable)
                .error(errorId)
                .fallback(errorId)
                .thumbnail(SIZE_MULTIPLIER)
                .override(mWidth, mHeight)
                .format(DecodeFormat.PREFER_ARGB_8888);
        return builder;
    }

    private RequestBuilder<Drawable> getRequestBuilder(RequestBuilder<Drawable> builder) {
        builder.placeholder(mLoadingDrawable)
                .error(mErrorDrawable)
                .fallback(mFallbackDrawable)
                .thumbnail(SIZE_MULTIPLIER)
                .override(mWidth, mHeight)
                .format(DecodeFormat.PREFER_ARGB_8888);
        return builder;
    }
}
