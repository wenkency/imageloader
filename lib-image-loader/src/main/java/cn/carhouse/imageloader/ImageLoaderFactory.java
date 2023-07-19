package cn.carhouse.imageloader;

import androidx.collection.ArrayMap;

import java.util.Map;

/**
 * ================================================================
 * <p>
 * <p>
 * 时间: 2019-11-15 09:02
 * <p>
 * 描述：创建图片加载的工厂
 * ================================================================
 */
public class ImageLoaderFactory {

    private static Map<Class, IImageLoader> loaders = new ArrayMap<>(2);

    public static <I extends IImageLoader> I createImageLoader(Class<I> clz) {
        IImageLoader loader = loaders.get(clz);
        if (loader != null) {
            return (I) loader;
        }
        I imageLoader = null;
        try {
            imageLoader = clz.newInstance();
            loaders.put(clz, imageLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageLoader;
    }

    /**
     * 默认用GlideImageLoader，以后可以扩展
     */
    public static IImageLoader getInstance() {
        return createImageLoader(GlideImageLoader.class);
    }



}  
