package cn.carhouse.imageloader_sample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import cn.carhouse.imageloader.IImageLoader;
import cn.carhouse.imageloader.ImageLoaderFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView iv, ivCircle, ivRadius,ivRes;
    private View view, viewRes, viewCircle, viewRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        ivCircle = findViewById(R.id.iv_circle);
        ivRadius = findViewById(R.id.iv_radius);
        ivRes = findViewById(R.id.iv_res);
        view = findViewById(R.id.view);
        viewRes = findViewById(R.id.view_res);
        viewCircle = findViewById(R.id.view_circle);
        viewRadius = findViewById(R.id.view_radius);

        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573796397226&di=4ebc060b0d6f6a6509cdd08737d24e3a&imgtype=0&src=http%3A%2F%2Fpic26.nipic.com%2F20121225%2F9252150_165232606338_2.jpg";

        IImageLoader imageLoader = ImageLoaderFactory.getInstance();

        imageLoader.displayImage(iv, url);
        imageLoader.displayCircleImage(ivCircle, url);
        imageLoader.displayRadiusImage(ivRadius, url, 30);
        imageLoader.displayImage(view, url);

        imageLoader.displayImage(viewRes,R.mipmap.ic_launcher);
        imageLoader.displayImage(ivRes,R.mipmap.ic_launcher);
        imageLoader.displayCircleImage(viewCircle,R.mipmap.ic_launcher);
        imageLoader.displayRadiusImage(viewRadius,R.mipmap.ic_launcher,10);
    }
}
