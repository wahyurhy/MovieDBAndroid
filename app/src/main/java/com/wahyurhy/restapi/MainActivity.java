package com.wahyurhy.restapi;

import static com.wahyurhy.restapi.utils.Const.API_KEY;
import static com.wahyurhy.restapi.utils.Const.LANGUAGE;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener;
import com.androidnetworking.widget.ANImageView;
import com.wahyurhy.restapi.data.response.detail.DetailMovieByIDResponse;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ANImageView mPoster;
    private TextView mTitle;
    private TextView mOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadData();
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        mTitle.setText(R.string.loading_data);
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/{id}")
                .addQueryParameter(API_KEY)
                .addQueryParameter(LANGUAGE)
                .addPathParameter("id", "663712")
                .setTag(getString(R.string.load_data_tag))
                .setPriority(Priority.HIGH)
                .build()
                .getAsOkHttpResponseAndObject(DetailMovieByIDResponse.class, new OkHttpResponseAndParsedRequestListener<DetailMovieByIDResponse>() {
                    @Override
                    public void onResponse(Response okHttpResponse, DetailMovieByIDResponse response) {
                        mPoster.setImageUrl("https://image.tmdb.org/t/p/original" + response.getPosterPath());
                        mTitle.setText(response.getTitle());
                        mOverview.setText(response.getOverview());
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

    private void initView() {
        mPoster = findViewById(R.id.poster);
        mTitle = findViewById(R.id.title);
        mOverview = findViewById(R.id.overview);
    }
}