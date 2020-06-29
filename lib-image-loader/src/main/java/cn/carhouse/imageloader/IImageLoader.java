package cn.carhouse.imageloader;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * ================================================================
 * 版权: 爱车小屋所有（C） 2019
 * <p>
 * 作者：刘付文 （61128910@qq.com）
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
    void displayImage(ImageView iv, String url);

    /**
     * 根据URL加载图片
     *
     * @param url     图片URL
     * @param errorId 加载失败的显示资源ID
     */
    void displayImage(ImageView iv, String url, int errorId);

    void displayImage(View view, String url, int errorId, int width, int height);

    void displayImage(View view, String url, int width, int height);

    /**
     * 加载本地图片
     *
     * @param resId 本地资源
     */
    void displayImage(ImageView iv, int resId);

    /**
     * 加载本地图片
     *
     * @param resId   本地资源
     * @param errorId 加载失败的显示资源ID
     */
    void displayImage(ImageView iv, int resId, int errorId);

    /**
     * 根据URL加载图片
     *
     * @param uri 图片Uri
     */
    void displayImage(ImageView iv, Uri uri);

    /**
     * 根据URL加载图片
     *
     * @param uri     图片Uri
     * @param errorId 加载失败的显示资源ID
     */
    void displayImage(ImageView iv, Uri uri, int errorId);

    /**
     * 根据URL加载图片
     *
     * @param url 图片URL
     */
    void displayImage(View view, String url);

    /**
     * 根据URL加载图片
     *
     * @param url     图片URL
     * @param errorId 加载失败的显示资源ID
     */
    void displayImage(View view, String url, int errorId);

    /**
     * 加载本地图片
     *
     * @param resId 本地资源
     */
    void displayImage(View view, int resId);

    /**
     * 加载本地图片
     *
     * @param resId   本地资源
     * @param errorId 加载失败的显示资源ID
     */
    void displayImage(View view, int resId, int errorId);

    /**
     * 根据URL加载圆形图片
     *
     * @param url 图片URL
     */
    void displayCircleImage(ImageView iv, String url);

    /**
     * 根据URL加载圆形图片
     *
     * @param url     图片URL
     * @param errorId 加载失败的显示资源ID
     */
    void displayCircleImage(ImageView iv, String url, int errorId);

    /**
     * 加载本地图片
     *
     * @param resId 本地资源
     */
    void displayCircleImage(View view, int resId);

    /**
     * 加载本地图片
     *
     * @param resId   本地资源
     * @param errorId 加载失败的显示资源ID
     */
    void displayCircleImage(View view, int resId, int errorId);

    /**
     * 根据URL加载圆形图片
     *
     * @param url 图片URL
     */
    void displayCircleImage(View view, String url);

    /**
     * 根据URL加载圆形图片
     *
     * @param url     图片URL
     * @param errorId 加载失败的显示资源ID
     */
    void displayCircleImage(View view, String url, int errorId);

    /**
     * 根据URL加载圆角图片
     *
     * @param url 图片URL
     */
    void displayRadiusImage(ImageView iv, String url, int radius);

    /**
     * 根据URL加载圆角图片
     *
     * @param url     图片URL
     * @param errorId 加载失败的显示资源ID
     */
    void displayRadiusImage(ImageView iv, String url, int radius, int errorId);

    /**
     * 根据URL加载圆角图片
     *
     * @param url 图片URL
     */
    void displayRadiusImage(View view, String url, int radius);

    /**
     * 根据URL加载圆角图片
     *
     * @param url     图片URL
     * @param errorId 加载失败的显示资源ID
     */
    void displayRadiusImage(View view, String url, int radius, int errorId);

    /**
     * 加载本地图片
     *
     * @param resId 本地资源
     */
    void displayRadiusImage(View view, int resId, int radius);

    /**
     * 加载本地图片
     *
     * @param resId   本地资源
     * @param errorId 加载失败的显示资源ID
     */
    void displayRadiusImage(View view, int resId, int radius, int errorId);


    void displayBlurImage(ImageView view, String url, int radius);

    void displayBlurImage(ImageView view, String url, int radius, int errorId);

    void displayBlurImage(View view, String url, int radius, int errorId);

    void displayBlurImage(View view, String url, int radius);


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
