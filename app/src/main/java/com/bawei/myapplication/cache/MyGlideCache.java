package com.bawei.myapplication.cache;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/11/27
 */
public class MyGlideCache implements GlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {

        //缓存大小
        int cacheSize = 1024 * 1024 * 20;
        File images = new File(Environment.getExternalStorageDirectory(), "images");

        String path = images.getPath();
        builder.setDiskCache(new DiskLruCacheFactory(path,cacheSize));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

    }
}
