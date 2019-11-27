package com.bawei.myapplication.presenter;

import com.bawei.myapplication.base.BasePresenter;
import com.bawei.myapplication.contract.IContract;
import com.bawei.myapplication.model.MyModel;

import java.util.Map;

/**
 * author : Eaves
 * desc   : P层
 * date   : 2019/11/27
 */
public class MyPresenter extends BasePresenter {
    @Override
    protected IContract.IModel initModel() {
        return new MyModel();
    }

    @Override
    public void startGetRequest(String httpUrl) {
        mModel.doGet(httpUrl, new IContract.MVPCallBack() {
            @Override
            public void onSuccess(String json) {
                if (getViewInstance() != null) {
                    getViewInstance().onSuccess(json);
                }
            }

            @Override
            public void onError(String error) {
                if (getViewInstance() != null) {
                    getViewInstance().onError(error);
                }
            }
        });
    }

    @Override
    public void startPostRequest(String httpUrl, Map<String, String> map) {
        mModel.doPost(httpUrl, map,new IContract.MVPCallBack() {
            @Override
            public void onSuccess(String json) {
                if (getViewInstance() != null) {
                    getViewInstance().onSuccess(json);
                }
            }

            @Override
            public void onError(String error) {
                if (getViewInstance() != null) {
                    getViewInstance().onError(error);
                }
            }
        });
    }
}
