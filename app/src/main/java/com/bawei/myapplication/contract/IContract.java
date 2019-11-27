package com.bawei.myapplication.contract;

import java.util.Map;

/**
 * author : Eaves
 * desc   : 契约类
 * date   : 2019/11/27
 */
public interface IContract {
    interface MVPCallBack{
        void onSuccess(String json);
        void onError(String error);
    }

    interface IModel{
        void doGet(String httpUrl,MVPCallBack mvpCallBack);
        void doPost(String httpUrl, Map<String,String> map, MVPCallBack mvpCallBack);
    }

    interface IPresenter{
        void startGetRequest(String httpUrl);
        void startPostRequest(String httpUrl,Map<String,String> map);
    }

    interface IView{
        void onSuccess(String json);
        void onError(String error);
    }
}
