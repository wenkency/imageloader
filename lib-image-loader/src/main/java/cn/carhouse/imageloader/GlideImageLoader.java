package cn.carhouse.imageloader;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import cn.carhouse.imageloader.trnsformation.BlurTransformation;
import cn.carhouse.imageloader.trnsformation.GlideCircleTransform;
import cn.carhouse.imageloader.utils.LoaderUtils;

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
public class GlideImageLoader implements IImageLoader {
    private static int mWidth = 480;
    private static int mHeight = 800;
    public static final int ERROR_ID = -1;
    private static boolean isThumbnail = false;
    // 模糊加载
    public static final float SIZE_MULTIPLIER = 0.1f;
    private static ColorDrawable mErrorDrawable = new ColorDrawable(Color.TRANSPARENT);
    private static ColorDrawable mLoadingDrawable = new ColorDrawable(Color.TRANSPARENT);


    @Override
    public void displayImage(final View view, String url) {
        displayImage(view, url, ERROR_ID);
    }

    @Override
    public void displayImage(final View view, final String url, final int errorId) {
        if (view == null || TextUtils.isEmpty(url)) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, url, errorId);
                LoaderUtils.into(builder, view, target);
            }
        });

    }

    @Override
    public void displayImage(View view, GlideUrl url) {
        displayImage(view, url, ERROR_ID);
    }

    @Override
    public void displayImage(final View view, final GlideUrl url, final int errorId) {
        if (view == null || url == null) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, url, errorId);
                LoaderUtils.into(builder, view, target);
            }
        });

    }

    @Override
    public void displayImage(View view, int resId) {
        displayImage(view, resId, ERROR_ID);
    }

    @Override
    public void displayImage(final View view, final int resId, final int errorId) {
        if (view == null) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, resId, errorId);
                LoaderUtils.into(builder, view, target);
            }
        });

    }


    @Override
    public void displayCircleImage(View view, String url) {
        displayCircleImage(view, url, ERROR_ID);
    }

    @Override
    public void displayCircleImage(final View view, final String url, final int errorId) {
        if (view == null || TextUtils.isEmpty(url)) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, url, errorId).circleCrop();
                LoaderUtils.into(builder, view, target);
            }
        });

    }

    @Override
    public void displayCircleImage(View view, GlideUrl url) {
        displayCircleImage(view, url, ERROR_ID);
    }

    @Override
    public void displayCircleImage(final View view, final GlideUrl url, final int errorId) {
        if (view == null || url == null) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, url, errorId).circleCrop();
                LoaderUtils.into(builder, view, target);
            }
        });

    }

    @Override
    public void displayCircleImage(View view, int resId) {
        displayCircleImage(view, resId, ERROR_ID);
    }

    @Override
    public void displayCircleImage(final View view, final int resId, final int errorId) {
        if (view == null) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, resId, errorId)
                        .circleCrop();
                LoaderUtils.into(builder, view, target);
            }
        });

    }


    @Override
    public void displayRadiusImage(final View view, final String url, final int radius, final int errorId) {
        if (view == null || TextUtils.isEmpty(url)) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, url, errorId)
                        .transform(new GlideCircleTransform(view.getContext(), radius));
                LoaderUtils.into(builder, view, target);
            }
        });

    }

    @Override
    public void displayRadiusImage(View view, String url, int radius) {
        displayRadiusImage(view, url, radius, ERROR_ID);
    }

    @Override
    public void displayRadiusImage(View view, GlideUrl url, int radius) {
        displayRadiusImage(view, url, radius, ERROR_ID);
    }

    @Override
    public void displayRadiusImage(final View view, final GlideUrl url,
                                   final int radius, final int errorId) {
        if (view == null || url == null) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, url, errorId)
                        .transform(new GlideCircleTransform(view.getContext(), radius));
                LoaderUtils.into(builder, view, target);
            }
        });

    }

    @Override
    public void displayRadiusImage(View view, int resId, int radius) {
        displayRadiusImage(view, resId, radius, ERROR_ID);
    }


    @Override
    public void displayRadiusImage(final View view, final int resId, final int radius, final int errorId) {
        if (view == null) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, resId, errorId)
                        .transform(new GlideCircleTransform(view.getContext(), radius));
                LoaderUtils.into(builder, view, target);
            }
        });

    }

    @Override
    public void displayBlurImage(View view, int resId, int radius) {
        displayBlurImage(view, resId, radius, ERROR_ID);
    }

    @Override
    public void displayBlurImage(View view, GlideUrl url, int radius) {
        displayBlurImage(view, url, radius, ERROR_ID);
    }

    @Override
    public void displayBlurImage(View view, String url, int radius) {
        displayBlurImage(view, url, radius, ERROR_ID);
    }

    @Override
    public void displayBlurImage(final View view, final int resId, final int radius, final int errorId) {
        if (view == null) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, resId, errorId)
                        .transform(new BlurTransformation(view.getContext(), radius));
                LoaderUtils.into(builder, view, target);
            }
        });

    }

    @Override
    public void displayBlurImage(final View view, final GlideUrl url, final int radius, final int errorId) {
        if (view == null || url == null) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, url, errorId)
                        .transform(new BlurTransformation(view.getContext(), radius));
                LoaderUtils.into(builder, view, target);
            }
        });

    }

    @Override
    public void displayBlurImage(final View view, final String url, final int radius, final int errorId) {
        if (view == null || TextUtils.isEmpty(url)) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                GlideCustomTarget target = new GlideCustomTarget(view, errorId);
                RequestBuilder<Drawable> builder = getBuilder(view, url, errorId)
                        .transform(new BlurTransformation(view.getContext(), radius));
                LoaderUtils.into(builder, view, target);
            }
        });

    }

    @Override
    public void clear(Context context) {
        Glide.get(context).clearMemory();
    }

    public static class GlideCustomTarget extends CustomTarget<Drawable> {
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
        return getRequestBuilder(view, Glide.with(view).load(url), errorId);
    }

    private RequestBuilder<Drawable> getBuilder(View view, GlideUrl url, int errorId) {
        return getRequestBuilder(view, Glide.with(view).load(url), errorId);
    }

    private RequestBuilder<Drawable> getBuilder(View view, int resId, int errorId) {
        return getRequestBuilder(view, Glide.with(view).load(resId), errorId);
    }


    private RequestBuilder<Drawable> getRequestBuilder(View view, RequestBuilder<Drawable> builder, int errorId) {
        if (errorId == ERROR_ID) {
            builder.error(mErrorDrawable)
                    .placeholder(mLoadingDrawable);
        } else {
            builder.error(errorId)
                    .placeholder(errorId);
        }
        if (isThumbnail) {
            builder.thumbnail(SIZE_MULTIPLIER);
        }
        builder.diskCacheStrategy(DiskCacheStrategy.ALL)
                .format(DecodeFormat.PREFER_ARGB_8888);

        if (view.getMeasuredWidth() > 0 && view.getMeasuredHeight() > 0) {
            builder.override(view.getMeasuredWidth(), view.getMeasuredHeight());
        } else {
            builder.override(mWidth, mHeight);
        }
        return builder;
    }


    /**
     * 为notification加载图
     */
    @Override
    public void displayNotificationImage(Context context, Notification notification, RemoteViews rv,
                                         int viewId, int notificationId, String url) {
        NotificationTarget target = initNotificationTarget(context, rv, viewId, notification, notificationId);
        this.displayTargetImage(context, url, target);
    }

    /**
     * 为非view加载图片
     */
    @Override
    public void displayTargetImage(Context context, String url, Target<Bitmap> target) {
        this.displayTargetImage(context, url, target, null);
    }

    /**
     * 为非view加载图片
     */
    @Override
    public void displayTargetImage(Context context, String url, Target<Bitmap> target, RequestListener listener) {
        RequestBuilder builder = Glide.with(context)
                .asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .listener(listener);
        builder.submit(mWidth, mHeight);
        builder.into(target);
    }


    /*
     * 初始化Notification Target
     */
    private NotificationTarget initNotificationTarget(Context context, RemoteViews rv, int viewId,
                                                      Notification notification, int notificationId) {
        NotificationTarget notificationTarget =
                new NotificationTarget(context, viewId, rv, notification, notificationId);
        return notificationTarget;
    }


    /**
     * 设置默认缓存图片大小
     */
    public static void submit(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    /**
     * 设置要不要模糊加载
     */
    public static void setIsThumbnail(boolean isThumbnail) {
        GlideImageLoader.isThumbnail = isThumbnail;
    }

    /**
     * 加载显示的ColorDrawable
     */
    public static void setLoadingDrawable(ColorDrawable loadingDrawable) {
        mLoadingDrawable = loadingDrawable;
    }

    /**
     * 失败显示的ColorDrawable
     */
    public static void setErrorDrawable(ColorDrawable errorDrawable) {
        mErrorDrawable = errorDrawable;
    }
}
