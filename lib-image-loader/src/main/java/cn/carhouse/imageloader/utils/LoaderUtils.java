package cn.carhouse.imageloader.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;

import cn.carhouse.imageloader.GlideImageLoader;

/**
 * 加载的一个帮助类
 */
public class LoaderUtils {
    public static void into(final RequestBuilder<Drawable> builder, final View view, final GlideImageLoader.GlideCustomTarget target) {
        if (view == null) {
            return;
        }
        doInfo(builder, view, target);
    }

    public static void into(final RequestBuilder<Drawable> builder, final View view) {
        into(builder, view, null);
    }

    private static void doInfo(RequestBuilder<Drawable> builder, View view, GlideImageLoader.GlideCustomTarget target) {
        try {
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            if (width > 1 && height > 1) {
                builder.override(width, height);
            }
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
