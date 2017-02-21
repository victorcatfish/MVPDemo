package com.victor.mvpdemo.model;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Victor on 2017/2/14.
 */

public class RemoteDataSource implements IDataSource<AndroidData.Field> {

    @Override
    public void loadData(final LoadCallback<AndroidData.Field> callback) {
        String url = "http://gank.io/api/data/Android/10/1";

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                callback.onLoadFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String result = response.body().string();
                AndroidData androidData = gson.fromJson(result, AndroidData.class);
                callback.onLoadSucess(androidData.results);
            }
        });
    }
}
