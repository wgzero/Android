package com.example.as_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_main);
        //  获取控件
        WebView webView = (WebView)findViewById(R.id.web_view);

//        调用JS
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);

//        访问网页
//        webView.loadUrl("http://www.baidu.com");
        webView.loadUrl("http://w.wgzero.com");

//        系统默认会通过手机浏览器打开网页 为了能够直接通过WebView显示网页 必须设置
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
//                使用WebView加载显示url
                view.loadUrl(url);
                return true;
            }
        });
    }
}
