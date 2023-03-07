package com.kj.memoryhelper;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;


@GlideModule
public class GlideConfigModule extends AppGlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
//        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
//        Logger.error("GameCenterGlideConfigModule memory size =" + calculator.getMemoryCacheSize());
//        Logger.error("GameCenterGlideConfigModule bitmap map pool size =" + calculator.getBitmapPoolSize());
//        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
//        builder.setBitmapPool(new LruBitmapPool(calculator.getBitmapPoolSize()));
//        //外部缓存(Android/包名/cache/glide_cache)
//        builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, "game_center_cache", 40 * 1024 * 1024));

//        float cacheScreenCount = 0.2f;
//        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
//                .setBitmapPoolScreens(cacheScreenCount)
//                .setMemoryCacheScreens(cacheScreenCount)
//                .build();
//        Log.d("kbjay_test", "memory："+calculator.getMemoryCacheSize() +"bitmap: "+calculator.getBitmapPoolSize());
//        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
//        builder.setBitmapPool(new LruBitmapPool(calculator.getBitmapPoolSize()));
    }
}
