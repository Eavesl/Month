package com.bawei.myapplication.base;

import com.bawei.myapplication.contract.IContract;

import java.lang.ref.WeakReference;

/**
 * author : Eaves
 * desc   : P层基类
 * date   : 2019/11/27
 */
public abstract class BasePresenter<V extends IContract.IView> implements IContract.IPresenter {

    protected WeakReference<V> mWeak;
    protected IContract.IModel mModel;
    public BasePresenter() {
        mModel = initModel();
    }

    protected abstract IContract.IModel initModel();

    protected void onAttach(V v){

        mWeak = new WeakReference<>(v);
    }

    protected V getViewInstance(){
        return mWeak.get();
    }

    protected void onDetach(){
        if (mWeak != null) {
            mWeak.clear();
            mWeak = null;
            mModel = null;
        }
    }
}
