package com.bawei.myapplication.transition;

import android.transition.Explode;
import android.view.Window;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/11/27
 */
public class TransitionUtils {

    public static void explodeTransition(Window window){

        Explode explode = new Explode();
        explode.setDuration(500);
        window.setEnterTransition(explode);
        window.setReenterTransition(explode);
        window.setExitTransition(explode);

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    }
}
