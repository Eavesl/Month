package com.bawei.myapplication.immersive;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/11/27
 */
public class ImmersiveUtils {

    public static void immersiveScreen(Context context){

        ((Activity) context).getWindow().setStatusBarColor(Color.TRANSPARENT);
        ((Activity) context).getWindow().setNavigationBarColor(Color.TRANSPARENT);


        ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }
}
