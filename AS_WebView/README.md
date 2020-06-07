# Android混合开发



AndroidStuido版本： 3.5.2

## I.Android原生WebView开发

### 1.新建项目wv_demo，等待项目依赖完成

### 2.新建一个webView_main.xml页面，添加WebView组件

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <WebView
        android:id="@+id/web_view"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:paddingLeft="5dp"
        android:paddingRight="5dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### 3.修改MainActivity页面

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_main);
        //  获取控件，组件ID名字
        WebView webView = (WebView)findViewById(R.id.web_view);

//        调用JS，两句不加会出现错误
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

```



### 4.页面出现如下错误，是因为从Android 9.0（API级别28）开始，默认情况下禁用明文支持。因此http的url均无法在webview中加载。

![1591539709631](C:\Users\wugang\AppData\Roaming\Typora\typora-user-images\1591539709631.png)



解决办法：在AndroidManifest.xml页面中配置一句，就行了

```
android:usesCleartextTraffic="true"
```

还要记得在AndroidManifest.xml加上联网许可

```
<uses-permission android:name="android.permission.INTERNET" />
```



### 5.要添加调用JS的语句，不然会出现以下的错误

![1591539656949](C:\Users\wugang\AppData\Roaming\Typora\typora-user-images\1591539656949.png)

### 6.模拟器运行和真机测试