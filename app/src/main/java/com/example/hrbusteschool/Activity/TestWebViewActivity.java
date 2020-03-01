package com.example.hrbusteschool.Activity;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hrbusteschool.R;


public class TestWebViewActivity extends AppCompatActivity {


    WebView testwebv, webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web_view);
        webView.findViewById(R.id.testwebview);
        //webView.loadUrl("file:///android_assets/20191012.html");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//支持js交互，可以点击网页中按钮链接
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持js可以打开新的页面
        settings.setSupportZoom(true);//是否可以缩放，默认true
        settings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        settings.setAppCacheEnabled(false);//是否使用缓存
        settings.setDomStorageEnabled(true);//DOM Storaget
        //testwebView.loadUrl("http://m.baidu.com");
        webView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法

            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, String url) {
                try {
                    if (url.startsWith("http:") || url.startsWith("https:")) {
                        view.loadUrl(url);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                    return true;
                } catch (Exception e){
                    return false;
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//解决webview 加载https 出现没内容
            }
        });

        webView.loadUrl("http://www.baidu.com");
        //webView.loadUrl("http://www.baidu.com");
        //webView = findViewById(R.id.common_webview);
        /*WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//支持js交互，可以点击网页中按钮链接
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持js可以打开新的页面
        settings.setSupportZoom(true);//是否可以缩放，默认true
        settings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        settings.setAppCacheEnabled(false);//是否使用缓存
        settings.setDomStorageEnabled(true);//DOM Storage
        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        webView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //默认是打开系统自带的浏览器，重写这个方法之后就不会打开系统的浏览器了
                //view.loadUrl("http://www.baidu.com");
                view.loadUrl("file:///android_assets/20191012.html");
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//解决webview 加载https 出现没内容
            }
        });

        /*testwebv = findViewById(R.id.testwebview);
        testwebv.getSettings().setJavaScriptEnabled(true);
        testwebv.setWebViewClient(new WebViewClient());
        testwebv.loadUrl("http://www.baidu.com");

        testwebv.setWebViewClient(new WebViewClient())
        {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                view.loadUrl(url);
                //返回true
                return true;
            }


        }*/

    }
}
