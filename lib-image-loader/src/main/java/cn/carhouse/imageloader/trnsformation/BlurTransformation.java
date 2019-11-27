package cn.carhouse.imageloader.trnsformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.renderscript.Allocation;
import androidx.renderscript.Element;
import androidx.renderscript.RenderScript;
import androidx.renderscript.ScriptIntrinsicBlur;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.security.MessageDigest;


/**
 * 还可以参考：https://github.com/wasabeef/glide-transformations
 * 高斯模糊
 */

public class BlurTransformation extends CenterCrop {
    private static final int VERSION = 1;
    private static final String ID =
            "cn.carhouse.imageloader.trnsformation.BlurTransformation." + VERSION;
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
        paint.setFlags(Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG);
        PorterDuffColorFilter filter =
                new PorterDuffColorFilter(mColor, PorterDuff.Mode.SRC_ATOP);
        paint.setColorFilter(filter);
        canvas.drawBitmap(toTransform, 0, 0, paint);
        RenderScript rs = RenderScript.create(mContext);
        Allocation input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        Allocation output = Allocation.createTyped(rs, input.getType());
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        blur.setInput(input);
        blur.setRadius(mRadius);
        blur.forEach(output);
        output.copyTo(bitmap);
        rs.destroy();
        if (needScaled) {
            return bitmap;
        } else {
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, originWidth, originHeight, true);
            bitmap.recycle();
            return scaled;
        }
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
        return (int) (ID.hashCode() + mRadius * 1000 + mSampling * 10);
    }
}
