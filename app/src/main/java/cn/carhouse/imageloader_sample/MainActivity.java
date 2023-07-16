package cn.carhouse.imageloader_sample;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import cn.carhouse.imageloader.IImageLoader;
import cn.carhouse.imageloader.ImageLoaderFactory;
import cn.carhouse.imageloader_sample.okhttp3.OkGlideUrl;
import cn.carhouse.imageloader_sample.okhttp3.OkHttpUrlLoader;

public class MainActivity extends AppCompatActivity {

    private ImageView iv, ivCircle, ivRadius, ivRes, ivBlur;
    private View view, viewRes, viewCircle, viewRadius;

    private static final String DATA_URI = "data:image/jpeg;base64,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%";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        ivCircle = findViewById(R.id.iv_circle);
        ivRadius = findViewById(R.id.iv_radius);
        ivBlur = findViewById(R.id.iv_blur);
        ivRes = findViewById(R.id.iv_res);
        view = findViewById(R.id.view);
        viewRes = findViewById(R.id.view_res);
        viewCircle = findViewById(R.id.view_circle);
        viewRadius = findViewById(R.id.view_radius);


        loadImage();
    }

    private void loadImage(){
        String url = "https://buydo.oss-accelerate.aliyuncs.com/buydo/6574697f1f1b4576835cc837697835b4.jpeg";

        // 加载Base64图片
        String base64 =
"https://h5.vd.vsxqrhvt.com/uploadPath/2023/07/06/%E6%8A%BD%E5%A5%96%E5%BC%B9%E7%AA%97banner1_20230706160801A007_ae.%7B%7D?auth_key=1689524958-0-0-4306553fd0c8a1b4f48c485e47ba825b";

        Log.e("TAGS","Main");
        //Glide.with(iv).load(new OkGlideUrl(base64)).into(iv);
        IImageLoader imageLoader = ImageLoaderFactory.getInstance();

        imageLoader.displayRadiusImage(iv,new OkGlideUrl(base64),50);

        imageLoader.displayImage(view, new OkGlideUrl(url));
        imageLoader.displayBlurImage(ivBlur, url, 10);
        imageLoader.displayCircleImage(ivCircle, url);
        imageLoader.displayRadiusImage(ivRadius, url, 50);

        imageLoader.displayImage(viewRes, R.mipmap.aaa);
        imageLoader.displayImage(ivRes, R.mipmap.aaa);
        imageLoader.displayCircleImage(viewCircle, R.mipmap.aaa);
        imageLoader.displayRadiusImage(viewRadius, R.mipmap.aaa, 50);


        // 去加载图片，要自己在onResourceReady处理
        imageLoader.displayTargetImage(this, url, new CustomTarget<Bitmap>() {


            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                // 成功要做什么
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                // 失败要做什么
            }
        });
    }
}
