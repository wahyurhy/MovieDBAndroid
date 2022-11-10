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
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.widget.ANImageView;

import org.json.JSONException;
import org.json.JSONObject;

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
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String title = response.getString("original_title");
                            String overview = response.getString("overview");
                            mPoster.setImageUrl("https://image.tmdb.org/t/p/original" + response.getString("poster_path"));
                            mTitle.setText(title);
                            mOverview.setText(overview);
                            Log.d("TAG", "onResponse: " + response.getString("overview"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d("TAG", "onError errorCode : " + error.getErrorCode());
                            Log.d("TAG", "onError errorBody : " + error.getErrorBody());
                            Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                            // get parsed error object (If ApiError is your class)
                            //ApiError apiError = error.getErrorAsObject(ApiError.class);
                            mTitle.setText(error.getErrorDetail());
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("TAG", "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });
    }

    private void initView() {
        mPoster = findViewById(R.id.poster);
        mTitle = findViewById(R.id.title);
        mOverview = findViewById(R.id.overview);
    }
}