package com.bawei.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.myapplication.adapter.MyAdapter;
import com.bawei.myapplication.app.MyApp;
import com.bawei.myapplication.base.BaseActivity;
import com.bawei.myapplication.base.BasePresenter;
import com.bawei.myapplication.bean.JsonBean;
import com.bawei.myapplication.httpurls.HttpUrls;
import com.bawei.myapplication.net.VolleyUtils;
import com.bawei.myapplication.presenter.MyPresenter;
import com.bawei.myapplication.widget.MyFloatView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private EditText mEditText;
    private TextView mTextView;
    private MyFloatView mMyFloatView;
    private RecyclerView mRecyclerView;
    private ArrayList<String> mTags;
    private MyAdapter mMyAdapter;

    @Override
    protected void initData() {

        mMyAdapter = new MyAdapter(MainActivity.this, new ArrayList<JsonBean.ResultBean>());
        mRecyclerView.setAdapter(mMyAdapter);
        mMyAdapter.setAdapterCallBack(new MyAdapter.AdapterCallBack() {
            @Override
            public void onRecyclerItemClick(int i) {
                //获取ViewHolder
                View childAt = mRecyclerView.getChildAt(i);
                childAt.setTransitionName("water");
                //跳转
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                MainActivity.this.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,childAt,"water").toBundle());

            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {
        mTags = new ArrayList<>();

        mEditText = findViewById(R.id.search_ed);
        mTextView = findViewById(R.id.search_tv);
        mTextView.setOnClickListener(this);
        mMyFloatView = findViewById(R.id.my_float);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2,GridLayoutManager.VERTICAL,false));

    }

    @Override
    protected int provideLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(String json) {

        JsonBean jsonBean = new Gson().fromJson(json, JsonBean.class);
        if (jsonBean.getResult().size() != 0){
            mMyAdapter.setData(jsonBean.getResult());
        }else {
            Toast.makeText(MyApp.mContext, "请求的数据为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String error) {

        Toast.makeText(MyApp.mContext, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_tv:
                //获取edit
                String trim = mEditText.getText().toString().trim();
                if (TextUtils.isEmpty(trim)){
                    //为空不做任何处理
                }else {
                    //判断
                    if (mTags.contains(trim)){
                        //包含不做任何处理
                        if (VolleyUtils.getInstance().hasNet(MainActivity.this)){
                            //有网请求
                            mPresenter.startGetRequest(HttpUrls.getHttpUrl(trim));
                        }else {
                            Toast.makeText(MyApp.mContext, "此时无网", Toast.LENGTH_SHORT).show();
                        }
                    }else {//添加同时发起请求
                        mTags.add(trim);
                        //添加到流式布局中
                        mMyFloatView.addTag(trim);
                        if (VolleyUtils.getInstance().hasNet(MainActivity.this)){
                            //有网请求
                            mPresenter.startGetRequest(HttpUrls.getHttpUrl(trim));
                        }else {
                            Toast.makeText(MyApp.mContext, "此时无网", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                break;
        }
    }
}
