package com.bawei.myapplication.model;

import com.bawei.myapplication.contract.IContract;
import com.bawei.myapplication.net.VolleyUtils;

import java.util.Map;

/**
 * author : Eaves
 * desc   : M层
 * date   : 2019/11/27
 */

public class MyModel implements IContract.IModel {

    @Override
    public void doGet(String httpUrl, final IContract.MVPCallBack mvpCallBack) {
        VolleyUtils.getInstance().doGet(httpUrl, new VolleyUtils.VolleyCallBack() {
            @Override
            public void onSuccess(String json) {
                mvpCallBack.onSuccess(json);
            }

            @Override
            public void onError(String error) {
                mvpCallBack.onError(error);
            }
        });
    }

    @Override
    public void doPost(String httpUrl, Map<String, String> map, final IContract.MVPCallBack mvpCallBack) {
        VolleyUtils.getInstance().doPost(httpUrl, map, new VolleyUtils.VolleyCallBack() {
            @Override
            public void onSuccess(String json) {
                mvpCallBack.onSuccess(json);
            }

            @Override
            public void onError(String error) {
                mvpCallBack.onError(error);
            }
        });
    }
}
