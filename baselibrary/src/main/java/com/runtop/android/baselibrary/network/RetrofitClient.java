package com.runtop.android.baselibrary.network;

import android.util.Log;

import com.runtop.android.baselibrary.BuildConfig;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    private RetrofitClient() {
        init();
    }

    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BaseUrl)
                .client(getOkHttpClientByHttps())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//                .addConverterFactory(FastJsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitClient instance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    public <T> T creatApi(final Class<T> service) {
        return retrofit.create(service);
    }


    public static OkHttpClient getOkHttpClientByHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    //添加HttpLoggingInterceptor拦截器方便调试接口
                    @Override
                    public void log(String message) {
                        Log.d("HttpLoggingInterceptor", message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public static OkHttpClient getOkHttpClientByHttps() {

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };

        SSLSocketFactory sslSocketFactory = null;
        try {
            // Install the all-trusting trust manager
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }).addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    //添加HttpLoggingInterceptor拦截器方便调试接口
                    @Override
                    public void log(String message) {
                        Log.d("HttpLoggingInterceptor", message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }


}
