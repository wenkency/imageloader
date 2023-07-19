package cn.carhouse.imageloader;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.RemoteViews;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * ================================================================
 * <p>
 * <p>
 * 时间: 2019-11-15 09:00
 * <p>
 * 描述：图片加载接口定义
 * ================================================================
 */
public interface IImageLoader {

    /**
     * 根据URL加载图片
     *
     * @param url 图片URL
     */
    <T extends View> void displayImage(T view, String url);

    <T extends View> void displayImage(T view, GlideUrl url);

    /**
     * 加载本地图片
     *
     * @param resId 本地资源
     */
    <T extends View> void displayImage(T view, int resId);

    /**
     * 根据URL加载图片
     *
     * @param url     图片URL
     * @param errorId 加载失败的显示资源ID
     */
    <T extends View> void displayImage(T view, String url, int errorId);

    <T extends View> void displayImage(T view, GlideUrl url, int errorId);

    /**
     * 加载本地图片
     *
     * @param resId   本地资源
     * @param errorId 加载失败的显示资源ID
     */
    <T extends View> void displayImage(T view, int resId, int errorId);


    /**
     * 加载本地图片
     *
     * @param resId 本地资源
     */
    <T extends View> void displayCircleImage(T view, int resId);

    /**
     * 根据URL加载圆形图片
     *
     * @param url 图片URL
     */
    <T extends View> void displayCircleImage(T view, String url);

    <T extends View> void displayCircleImage(T view, GlideUrl url);

    /**
     * 加载本地图片
     *
     * @param resId   本地资源
     * @param errorId 加载失败的显示资源ID
     */
    <T extends View> void displayCircleImage(T view, int resId, int errorId);

    /**
     * 根据URL加载圆形图片
     *
     * @param url     图片URL
     * @param errorId 加载失败的显示资源ID
     */
    <T extends View> void displayCircleImage(T view, String url, int errorId);

    <T extends View> void displayCircleImage(T view, GlideUrl url, int errorId);

    /**
     * 根据URL加载圆角图片
     *
     * @param url 图片URL
     */
    <T extends View> void displayRadiusImage(T view, String url, int radius);

    <T extends View> void displayRadiusImage(T view, GlideUrl url, int radius);

    /**
     * 加载本地图片
     *
     * @param resId 本地资源
     */
    <T extends View> void displayRadiusImage(T view, int resId, int radius);

    /**
     * 根据URL加载圆角图片
     *
     * @param url     图片URL
     * @param errorId 加载失败的显示资源ID
     */
    <T extends View> void displayRadiusImage(T view, String url, int radius, int errorId);

    <T extends View> void displayRadiusImage(T view, GlideUrl url, int radius, int errorId);

    /**
     * 加载本地图片
     *
     * @param resId   本地资源
     * @param errorId 加载失败的显示资源ID
     */
    <T extends View> void displayRadiusImage(T view, int resId, int radius, int errorId);


    <T extends View> void displayBlurImage(T view, String url, int radius, int errorId);

    <T extends View> void displayBlurImage(T view, GlideUrl url, int radius, int errorId);

    <T extends View> void displayBlurImage(T view, int resId, int radius, int errorId);

    <T extends View> void displayBlurImage(T view, String url, int radius);

    <T extends View> void displayBlurImage(T view, GlideUrl url, int radius);

    <T extends View> void displayBlurImage(T view, int resId, int radius);


    /**
     * 加载图片到通知栏
     *
     * @param context
     * @param notification
     * @param rv             远程的View
     * @param viewId
     * @param notificationId
     * @param url
     */
    void displayNotificationImage(Context context, Notification notification, RemoteViews rv,
                                  int viewId, int notificationId, String url);

    /**
     * 为非view加载图片
     */
    void displayTargetImage(Context context, String url, Target<Bitmap> target, RequestListener listener);

    /**
     * 为非view加载图片
     */
    void displayTargetImage(Context context, String url, Target<Bitmap> target);


    void clear(Context context);
}
