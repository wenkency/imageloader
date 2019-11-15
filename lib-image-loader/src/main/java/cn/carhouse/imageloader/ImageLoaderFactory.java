package cn.carhouse.imageloader;

/**
 * ================================================================
 * 版权: 爱车小屋所有（C） 2019
 * <p>
 * 作者：刘付文 （61128910@qq.com）
 * <p>
 * 时间: 2019-11-15 09:02
 * <p>
 * 描述：创建图片加载的工厂
 * ================================================================
 */
public class ImageLoaderFactory {

    public static <I extends IImageLoader> I createImageLoader(Class<I> clz) {
        I imageLoader = null;
        try {
            imageLoader = clz.newInstance();
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
