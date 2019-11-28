package cn.carhouse.imageloader.trnsformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.security.MessageDigest;

import cn.carhouse.imageloader.utils.FastBlurUtils;


/**
 * 还可以参考：https://github.com/wasabeef/glide-transformations
 * 高斯模糊
 */

public class BlurTransformation extends CenterCrop {
    private static final int VERSION = 1;
    private static final String ID = "cn.carhouse.imageloader.trnsformation.BlurTransformation." + VERSION;
    public static final float DEFAULT_RADIUS = 25.0f;
    public static final float MAX_RADIUS = 25.0f;
    private static final float DEFAULT_SAMPLING = 1.0f;

    private Context mContext;
    private float mSampling = DEFAULT_SAMPLING;
    private float mRadius;
    private int mColor;

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((ID + mRadius + mSampling).getBytes(CHARSET));
    }

    /**
     * @param context Context
     * @param radius  The blur's radius.
     * @param color   The color filter for blurring.
     */
    public BlurTransformation(Context context, @FloatRange(from = 0.0f) float radius, int color) {
        super();
        mContext = context;
        if (radius > MAX_RADIUS) {
            mSampling = radius / 25.0f;
            mRadius = MAX_RADIUS;
        } else {
            mRadius = radius;
        }
        mColor = color;
    }

    /**
     * @param context Context
     */
    public BlurTransformation(Context context, @FloatRange(from = 0.0f) float radius) {
        this(context, radius, Color.TRANSPARENT);
    }

    public BlurTransformation(Context context) {
        this(context, DEFAULT_RADIUS);
    }


    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        boolean needScaled = mSampling == DEFAULT_SAMPLING;
        int originWidth = toTransform.getWidth();
        int originHeight = toTransform.getHeight();
        int width, height;
        if (needScaled) {
            width = originWidth;
            height = originHeight;
        } else {
            width = (int) (originWidth / mSampling);
            height = (int) (originHeight / mSampling);
        }
        Bitmap bitmap = pool.get(width, height, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        if (mSampling != DEFAULT_SAMPLING) {
            canvas.scale(1 / mSampling, 1 / mSampling);
        }
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(toTransform, 0, 0, paint);
        return FastBlurUtils.blur(bitmap, (int) mRadius, true);
    }

    @Override
    public String toString() {
        return "BlurTransformation(radius=" + mRadius + ", sampling=" + mSampling + ")";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BlurTransformation &&
                ((BlurTransformation) o).mRadius == mRadius &&
                ((BlurTransformation) o).mSampling == mSampling;
    }

    @Override
    public int hashCode() {
        return (ID.hashCode() + (int) mRadius * 1000 + (int) mSampling * 10);
    }
}
