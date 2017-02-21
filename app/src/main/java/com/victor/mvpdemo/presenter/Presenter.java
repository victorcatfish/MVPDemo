package com.victor.mvpdemo.presenter;

import com.victor.mvpdemo.model.AndroidData;
import com.victor.mvpdemo.model.IDataSource;
import com.victor.mvpdemo.model.RemoteDataSource;
import com.victor.mvpdemo.view.MainActivity;

import java.util.List;

/**
 * Created by Victor on 2017/2/14.
 */

public class Presenter {

    private MainActivity mView;
    private RemoteDataSource mDataSource;

    public Presenter(MainActivity view) {
        mView = view;
        mDataSource = new RemoteDataSource();
        mView.setPresenter(this);
    }

    public void getData() {

        mDataSource.loadData(new IDataSource.LoadCallback<AndroidData.Field>() {
            @Override
            public void onLoadSucess(List<AndroidData.Field> data) {
                mView.showList(data);
            }

            @Override
            public void onLoadFailed() {
                mView.showFaildView();
            }
        });
    }
}
