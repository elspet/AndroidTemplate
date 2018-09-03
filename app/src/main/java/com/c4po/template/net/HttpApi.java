package com.c4po.template.net;

import android.content.Context;
import android.util.Log;

import com.c4po.template.global.AppConfigs;
import com.c4po.template.utils.MyLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 接口对象
 * @author Lisa
 * @date 18/08/28.
 */
public class HttpApi {

    private final static String REQUEST_TYPE_POST = "POST";

    private static ApiService baseApi;
    public static Gson gson = new GsonBuilder()
            .serializeNulls()
            .registerTypeAdapter(Date.class, new DateTypeAdapter())
            // 过滤字段
            .excludeFieldsWithModifiers(Modifier.PROTECTED)
            .create();

    /**
     * 获取配置文件的api，由于只调用一次，所以不进行静态化了
     *
     * @return
     */
    public static ApiService settingApi() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfigs.DEFAULT_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ApiService.class);
    }



    /**
     * 其他接口的api对象
     *
     * @param context
     * @return
     */
    public static ApiService api(final Context context) {
        if (null == baseApi) {
            //okhttp
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new OkHttpInterceptor(context))
                    .addInterceptor(httpLoggingInterceptor).build();
            //retrofit
            String baseUrl = AppConfigs.DEFAULT_URL;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            baseApi = retrofit.create(ApiService.class);
        }
        return baseApi;
    }


    /**
     * 日志拦截器，打印请求日志
     */
    public static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            MyLogger.showLargeLog(message,"RetrofitLog");
        }
    });

    /**
     * 拦截器实现
     */
    private static class OkHttpInterceptor  implements Interceptor {
        private Context context;

        public OkHttpInterceptor(Context context) {
            this.context = context;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {

            StringBuilder urlSb = new StringBuilder();
            Request original = chain.request();
            RequestBody requestBody = original.body();
            urlSb.append(original.url());
            urlSb.append("?");

            if (REQUEST_TYPE_POST.equals(original.method())) {
                TreeMap<String, String> sortMap = new TreeMap<>();
                FormBody.Builder builder = new FormBody.Builder();
                if (requestBody instanceof FormBody) {
                    FormBody formBody = (FormBody) requestBody;
                    for (int i = 0; i < formBody.size(); i++) {
                        builder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                        sortMap.put(formBody.encodedName(i), formBody.encodedValue(i));
                        if (i > 0) {
                            urlSb.append("&");
                        }
                        urlSb.append(formBody.encodedName(i));
                        urlSb.append("=");
                        urlSb.append(formBody.encodedValue(i));
                    }
                }
                //添加时间戳
                String timestamp = String.valueOf((System.currentTimeMillis() / 1000));
                builder.addEncoded("timestamp", timestamp);
                sortMap.put("timestamp", timestamp);
                //添加签名
                StringBuilder stringBuilder = new StringBuilder();
                for (Map.Entry<String, String> entry : sortMap.entrySet()) {
                    stringBuilder.append(entry.getKey());
                    stringBuilder.append(entry.getValue());
                }
//                String signature = MD5Utils.md5(MD5Utils.md5("intelligent_door") + stringBuilder.toString());
//                builder.addEncoded("signature", signature);
                //生成body内容
                requestBody = builder.build();

            }

            Log.i("net", "url:" + urlSb.toString());


            //拼接请求，添加头
            Request request = original.newBuilder()
                    .addHeader("Authorization", "")
//                    .addHeader("Authorization", authToken)
                    .post(requestBody).build();
            Response response = chain.proceed(request);
            return response;
        }
    }


}