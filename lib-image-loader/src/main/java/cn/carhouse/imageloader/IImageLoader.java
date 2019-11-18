package cn.carhouse.imageloader;

import android.view.View;
import android.widget.ImageView;

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
    void displayImage(ImageView iv, String url);


    void displayImage(ImageView iv, String url, int errorId);

    void displayCircleImage(ImageView iv, String url);

    void displayCircleImage(ImageView iv, String url, int errorId);

    void displayRadiusImage(ImageView iv, String url, int radius);

    void displayRadiusImage(ImageView iv, String url, int radius, int errorId);

    /**
     * 加载本地图片
     */
    void displayImage(ImageView iv, int resId);


    void displayImage(View view, String url);

}
