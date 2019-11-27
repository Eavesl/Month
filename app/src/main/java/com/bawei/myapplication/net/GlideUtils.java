package com.bawei.myapplication.net;

import android.widget.ImageView;

import com.bawei.myapplication.R;
import com.bawei.myapplication.app.MyApp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;


/**
 * author : Eaves
 * desc   : Glide工具类
 * date   : 2019/11/27
 */
public class GlideUtils {
    public static void loadImg(String uri, ImageView imageView){

        Glide.with(MyApp.mContext)
                .load(uri)
                .placeholder(R.mipmap.ic_launcher_round)//圆形小机器人占位图
                .error(R.mipmap.ic_launcher)//方形错位图
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }
}
