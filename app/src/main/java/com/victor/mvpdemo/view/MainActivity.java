package com.victor.mvpdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.victor.mvpdemo.R;
import com.victor.mvpdemo.model.AndroidData;
import com.victor.mvpdemo.presenter.Presenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IView<Presenter> {

    private Button mBtnGetData;
    private RecyclerView mRvData;
    private TextView mTvFailed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnGetData = (Button) findViewById(R.id.btn_get_data);
        mRvData = (RecyclerView) findViewById(R.id.rv);
        mTvFailed = (TextView) findViewById(R.id.tv_failed);
        mTvFailed.setVisibility(View.INVISIBLE);

        final Presenter presenter = new Presenter(this);

        mBtnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getData();
            }
        });


    }

    public void showList(final List<AndroidData.Field> data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRvData.setVisibility(View.VISIBLE);
                mTvFailed.setVisibility(View.INVISIBLE);
                MyAdapter adapter = new MyAdapter(data);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                mRvData.setLayoutManager(manager);
                mRvData.setAdapter(adapter);
            }
        });
    }

    public void showFaildView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRvData.setVisibility(View.GONE);
                mTvFailed.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void setPresenter(Presenter presenter) {

    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<AndroidData.Field> mDatas;

        public MyAdapter(List<AndroidData.Field> datas) {
            mDatas = datas;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //View view = View.inflate(getApplicationContext(), R.layout.item, null);
            // 如果采用上面的方式，RecyclerView中的item无法居中
            View view = getLayoutInflater().inflate(R.layout.item, mRvData, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            if (mDatas.get(position).images != null) {
                String imgUrl = mDatas.get(position).images.get(0) + "?imageView2/0/w/200 ";
                Picasso.with(getBaseContext()).load(imgUrl).into(holder.mImageView);
            }
            holder.mTvTitle.setText(mDatas.get(position).desc);
        }

        @Override
        public int getItemCount() {
            if (mDatas != null && mDatas.size() > 0) {
                return mDatas.size();
            }
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTvTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_img);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);

        }
    }
}
