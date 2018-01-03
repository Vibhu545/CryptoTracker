package com.one.cryptotracker.cryptotracker.Presenter;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anupamchugh on 05/01/17.
 */

class APIClient {

    private static Retrofit retrofit = null;
    private static String BaseUrl = "https://api.coindesk.com/";
    static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();



        return retrofit;
    }


    public static <S> S createServiceWithAuth(Class<S> serviceClass, final String token) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                // concatenate username and password with colon for authentication
               /* String credentials = "gskstaging7" + ":" + "Health4All";
                // create Base64 encodet string
                final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);*/
                final String basic ="Bearer"+token;
                final Request request = chain.request().newBuilder().addHeader("Authorization", basic)
                        .build();
                return chain.proceed(request);
            }
        };
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://stagingiis7.mylupuslog.com/api/service/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

}
