package com.bawei.myapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.myapplication.base.BaseActivity;
import com.bawei.myapplication.base.BasePresenter;

public class DetailActivity extends BaseActivity {


    private EditText mEditText;
    private Button mButton;
    private WebView mWebView;
    private String url = "file:///android_asset/info.html";
    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        mEditText = findViewById(R.id.detail_ed);
        mButton = findViewById(R.id.detail_btn);
        mButton.setOnClickListener(this);
        mWebView = findViewById(R.id.web_view);

        initWebView();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {

        mWebView.loadUrl(url);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                mWebView.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);

        mWebView.addJavascriptInterface(new MyWebView(),"android");
    }

    @Override
    protected int provideLayoutID() {
        return R.layout.activity_detail;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.detail_btn:

                String trim = mEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(trim)){
                    mWebView.loadUrl("javascript:changeNum('"+trim+"')");
                }else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    class MyWebView{
        @JavascriptInterface
        public void buyNow(int id){
            Log.e("TAG", "BuyNow: ————>"+id);
            Toast.makeText(DetailActivity.this, ""+id, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(String json) {

    }

    @Override
    public void onError(String error) {

    }
}
