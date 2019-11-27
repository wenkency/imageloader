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

import cn.carhouse.imageloader.trnsformation.BlurTransformation;
import cn.carhouse.imageloader.trnsformation.GlideCircleTransform;

/**
 * ================================================================
 * 版权: 爱车小屋所有（C） 2019
 * <p>
 * 作者：刘付文 （61128910@qq.com）
 * <p>
 * 时间: 2019ERROR_ID1ERROR_ID5 09:05
 * <p>
 * 描述：
 * ================================================================
 */
@GlideModule
public class GlideImageLoader extends AppGlideModule implements IImageLoader {
    private static final int mWidth = 480;
    private static final int mHeight = 800;
    public static final int ERROR_ID = -1;
    private static boolean isThumbnail = false;
    // 模糊加载
    public static final float SIZE_MULTIPLIER = 0.1f;
    private ColorDrawable mErrorDrawable = new ColorDrawable(Color.TRANSPARENT);
    private ColorDrawable mLoadingDrawable = new ColorDrawable(Color.parseColor("#f2f2f2"));
    private ColorDrawable mFallbackDrawable = new ColorDrawable(Color.parseColor("#f2f2f2"));

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

    @Override
    public void displayImage(ImageView iv, String url) {
        displayImage(iv, url, ERROR_ID);
    }

    @Override
    public void displayImage(ImageView iv, String url, int errorId) {
        if (iv == null || TextUtils.isEmpty(url)) {
            return;
        }
        RequestBuilder<Drawable> builder = getBuilder(iv, url, errorId);
        iv.post(new Task(builder, iv));
    }

    @Override
    public void displayImage(ImageView iv, int resId) {
        displayImage(iv, resId, ERROR_ID);
    }

    @Override
    public void displayImage(ImageView iv, int resId, int errorId) {
        if (iv == null) {
            return;
        }
        RequestBuilder<Drawable> builder = getBuilder(iv, resId, errorId);
        iv.post(new Task(builder, iv));
    }


    public void displayImage(ImageView iv, Uri url) {
        displayImage(iv, url, ERROR_ID);
    }

    public void displayImage(ImageView iv, Uri url, int errorId) {
        if (iv == null || url == null) {
            return;
        }
        RequestBuilder<Drawable> builder = getBuilder(iv, url, errorId);
        iv.post(new Task(builder, iv));
    }

    @Override
    public void displayCircleImage(ImageView iv, String url) {
        displayCircleImage(iv, url, ERROR_ID);
    }

    @Override
    public void displayCircleImage(ImageView iv, String url, int errorId) {
        if (iv == null || TextUtils.isEmpty(url)) {
            return;
        }
        RequestBuilder<Drawable> builder = getBuilder(iv, url, errorId).circleCrop();
        iv.post(new Task(builder, iv));
    }

    @Override
    public void displayCircleImage(View view, int resId) {
        displayCircleImage(view, resId, ERROR_ID);
    }

    @Override
    public void displayCircleImage(View view, int resId, int errorId) {
        if (view == null) {
            return;
        }
        GlideCustomTarget target = new GlideCustomTarget(view, errorId);
        RequestBuilder<Drawable> builder = getBuilder(view, resId, errorId)
                .circleCrop();
        view.post(new Task(builder, view, target));
    }


    @Override
    public void displayRadiusImage(ImageView iv, String url, int radius) {
        displayRadiusImage(iv, url, radius, ERROR_ID);
    }

    @Override
    public void displayRadiusImage(ImageView iv, String url, int radius, int errorId) {
        if (iv == null || TextUtils.isEmpty(url)) {
            return;
        }
        RequestBuilder<Drawable> builder = getBuilder(iv, url, errorId)
                .transform(new GlideCircleTransform(iv.getContext(), radius));
        iv.post(new Task(builder, iv));
    }

    @Override
    public void displayImage(final View view, String url) {
        displayImage(view, url, ERROR_ID);
    }

    @Override
    public void displayImage(final View view, String url, final int errorId) {
        if (view == null || TextUtils.isEmpty(url)) {
            return;
        }
        GlideCustomTarget target = new GlideCustomTarget(view, errorId);
        RequestBuilder<Drawable> builder = getBuilder(view, url, errorId);
        view.post(new Task(builder, view, target));
    }

    @Override
    public void displayImage(View view, int resId) {
        displayImage(view, resId, ERROR_ID);
    }

    @Override
    public void displayImage(View view, int resId, int errorId) {
        if (view == null) {
            return;
        }
        GlideCustomTarget target = new GlideCustomTarget(view, errorId);
        RequestBuilder<Drawable> builder = getBuilder(view, resId, errorId);
        view.post(new Task(builder, view, target));
    }

    @Override
    public void displayCircleImage(View view, String url) {
        displayCircleImage(view, url, ERROR_ID);
    }

    @Override
    public void displayCircleImage(View view, String url, int errorId) {
        if (view == null || TextUtils.isEmpty(url)) {
            return;
        }
        GlideCustomTarget target = new GlideCustomTarget(view, errorId);
        RequestBuilder<Drawable> builder = getBuilder(view, url, errorId)
                .circleCrop();
        view.post(new Task(builder, view, target));
    }

    @Override
    public void displayRadiusImage(View view, String url, int radius) {
        displayRadiusImage(view, url, radius, ERROR_ID);
    }

    @Override
    public void displayRadiusImage(View view, String url, int radius, int errorId) {
        if (view == null || TextUtils.isEmpty(url)) {
            return;
        }
        GlideCustomTarget target = new GlideCustomTarget(view, errorId);
        RequestBuilder<Drawable> builder = getBuilder(view, url, errorId)
                .transform(new GlideCircleTransform(view.getContext(), radius));
        view.post(new Task(builder, view, target));
    }

    @Override
    public void displayRadiusImage(View view, int resId, int radius) {
        displayRadiusImage(view, resId, radius, ERROR_ID);
    }

    @Override
    public void displayRadiusImage(View view, int resId, int radius, int errorId) {
        if (view == null) {
            return;
        }
        GlideCustomTarget target = new GlideCustomTarget(view, errorId);
        RequestBuilder<Drawable> builder = getBuilder(view, resId, errorId)
                .transform(new GlideCircleTransform(view.getContext(), radius));
        view.post(new Task(builder, view, target));
    }

    @Override
    public void displayBlurImage(ImageView view, String url, int radius) {
        if (view == null || TextUtils.isEmpty(url)) {
            return;
        }
        RequestBuilder<Drawable> builder = getBuilder(view, url, ERROR_ID)
                .transform(new BlurTransformation(view.getContext(), radius));
        view.post(new Task(builder, view));
    }

    @Override
    public void clear(Context context) {
        Glide.get(context).clearMemory();
    }

    private static class GlideCustomTarget extends CustomTarget<Drawable> {
        private View view;
        private int errorId;

        public GlideCustomTarget(View view, int errorId) {
            this.view = view;
            this.errorId = errorId;
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
            if (errorId != ERROR_ID) {
                view.setBackgroundResource(errorId);
            }
        }
    }


    private RequestBuilder<Drawable> getBuilder(View view, String url, int errorId) {
        return getRequestBuilder(Glide.with(view).load(url), errorId);
    }

    private RequestBuilder<Drawable> getBuilder(View view, Uri url, int errorId) {
        return getRequestBuilder(Glide.with(view).load(url), errorId);
    }

    private RequestBuilder<Drawable> getBuilder(View view, int resId, int errorId) {
        return getRequestBuilder(Glide.with(view).load(resId), errorId);
    }

    private RequestBuilder<Drawable> getRequestBuilder(RequestBuilder<Drawable> builder, int errorId) {
        if (errorId == ERROR_ID) {
            builder.placeholder(mLoadingDrawable)
                    .error(mErrorDrawable)
                    .fallback(mFallbackDrawable)
                    .dontTransform()
                    .dontAnimate()
                    .format(DecodeFormat.PREFER_ARGB_8888);

        } else {
            builder.placeholder(mLoadingDrawable)
                    .error(errorId)
                    .fallback(errorId)
                    .dontTransform()
                    .dontAnimate()
                    .format(DecodeFormat.PREFER_ARGB_8888);
        }
        builder.submit(mWidth, mHeight);
        if (isThumbnail) {
            builder.thumbnail(SIZE_MULTIPLIER);
        }
        return builder;
    }


    public static void setIsThumbnail(boolean isThumbnail) {
        GlideImageLoader.isThumbnail = isThumbnail;
    }


    private static class Task implements Runnable {
        RequestBuilder<Drawable> builder;
        View view;
        GlideCustomTarget target;

        public Task(RequestBuilder<Drawable> builder, View view) {
            this.builder = builder;
            this.view = view;
        }

        public Task(RequestBuilder<Drawable> builder, View view, GlideCustomTarget target) {
            this.builder = builder;
            this.view = view;
            this.target = target;
        }

        @Override
        public void run() {
            try {
                if (view instanceof ImageView) {
                    builder.into((ImageView) view);
                } else if (target != null) {
                    builder.into(target);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
