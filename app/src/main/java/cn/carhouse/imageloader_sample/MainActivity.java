package cn.carhouse.imageloader_sample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import cn.carhouse.imageloader.IImageLoader;
import cn.carhouse.imageloader.ImageLoaderFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView iv, ivCircle, ivRadius, ivRes, ivBlur;
    private View view, viewRes, viewCircle, viewRadius;

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

        String url = "https://img.car-house.cn/Upload/activity/20191126/EMBkW2wXZ8MHjXDTR8p6PjmcGTD44xdD.gif";

        IImageLoader imageLoader = ImageLoaderFactory.getInstance();

        imageLoader.displayImage(iv, url);
        imageLoader.displayCircleImage(ivCircle, url);
        imageLoader.displayRadiusImage(ivRadius, url, 30);
        imageLoader.displayImage(view, url);

        imageLoader.displayImage(viewRes, R.mipmap.ic_launcher);
        imageLoader.displayImage(ivRes, R.mipmap.ic_launcher);
        imageLoader.displayCircleImage(viewCircle, R.mipmap.ic_launcher);
        imageLoader.displayRadiusImage(viewRadius, R.mipmap.ic_launcher, 10);

        imageLoader.displayBlurImage(ivBlur, url, 50);
    }
}
