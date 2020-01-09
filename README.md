# imageloader
[![](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
&emsp;&emsp;[![](https://img.shields.io/badge/version-1.7.0-yellow.svg)](https://bintray.com/lfw/mavenRelease/imageloader/1.7.0)

> 图片加载类，加载正常、圆形、圆角图片、高斯模糊图片等

### 引入

```android
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}}

implementation 'com.github.wenkency:imageloader:2.1.0'

```

### 使用方式
```android
        String url = "https://img.car-house.cn/Upload/activity/20191126/EMBkW2wXZ8MHjXDTR8p6PjmcGTD44xdD.gif";

        IImageLoader imageLoader = ImageLoaderFactory.getInstance();
        // 正常加载图片
        imageLoader.displayImage(iv, url);
        // 加载圆形图片
        imageLoader.displayCircleImage(ivCircle, url);
        // 加载圆角图片
        imageLoader.displayRadiusImage(ivRadius, url, 30);
        imageLoader.displayImage(view, url);

        // 加载本地图片
        imageLoader.displayImage(viewRes, R.mipmap.ic_launcher);
        imageLoader.displayImage(ivRes, R.mipmap.ic_launcher);
        imageLoader.displayCircleImage(viewCircle, R.mipmap.ic_launcher);
        imageLoader.displayRadiusImage(viewRadius, R.mipmap.ic_launcher, 10);
        // 加载高斯模糊图片
        imageLoader.displayBlurImage(ivBlur, url, 50);
```

### 运行结果
![](screenshot/image.jpg "运行结果")