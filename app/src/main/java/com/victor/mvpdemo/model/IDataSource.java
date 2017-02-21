package com.victor.mvpdemo.model;

import java.util.List;

/**
 * Created by Victor on 2017/2/14.
 */

public interface IDataSource<T> {

    interface LoadCallback<T> {
        void onLoadSucess(List<T> data);
        void onLoadFailed();
    }

    void loadData(LoadCallback<T> callback);
}
