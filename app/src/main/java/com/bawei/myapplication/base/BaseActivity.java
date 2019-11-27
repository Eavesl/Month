package com.bawei.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bawei.myapplication.contract.IContract;
import com.bawei.myapplication.immersive.ImmersiveUtils;
import com.bawei.myapplication.transition.TransitionUtils;

/**
 * author : Eaves
 * desc   : Activity基类
 * date   : 2019/11/27
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IContract.IView, View.OnClickListener {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (provideLayoutID() != 0){
            //转场动画
            TransitionUtils.explodeTransition(getWindow());
            //沉浸
            ImmersiveUtils.immersiveScreen(this);

            setContentView(provideLayoutID());
            initView();
            mPresenter = initPresenter();
            if (mPresenter != null) {
                mPresenter.onAttach(this);
            }
            initData();

        }
    }

    protected abstract void initData();

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract int provideLayoutID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
            mPresenter = null;
        }
    }
}
