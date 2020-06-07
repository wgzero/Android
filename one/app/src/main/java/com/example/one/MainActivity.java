package com.example.one;

import android.support.v7.app.AppCompatActivity;
//import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        initX5WebView();
        webView = findViewById(R.id.web_view);
        //JS调用问题
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setDatabaseEnabled(true);
        //  X5webView的调用JS问题
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);

        //防止跳转到系统浏览器
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loadurlLocalMethod(view, url);
                return false;
            }
        });
//        webView.loadUrl("http://www.baidu.com");
//        http://w.wgzero.com/#/
          webView.loadUrl("http://w.wgzero.com/#/");
//        webView.loadUrl("http://10.6.142.21:8090/");

    }

    public void loadurlLocalMethod(final WebView webView, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(url);
            }
        });
    }

    private void initX5WebView() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                if (arg0) {
                    Log.d("ArticleSystem", "X5 内核加载成功");
                } else {
                    Log.d("ArticleSystem", "X5 内核加载失败");
                }
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }
}
