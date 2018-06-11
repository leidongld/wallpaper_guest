package com.example.leidong.wallpaper_guest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.leidong.wallpaper_guest.adapter.WallPaperAdapter;
import com.example.leidong.wallpaper_guest.bean.WallPaperBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private static final String URL = "http://192.168.0.103:8080/wallpapers";

    private List<WallPaperBean> wallPaperBeanList;

    @BindView(R.id.container)
    RecyclerView mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initWidgets();

        initActions();
    }

    private void initActions() {

    }

    private void initWidgets() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET,
                        URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, response);

                                Gson gson = new Gson();
                                wallPaperBeanList = gson.fromJson(response, new TypeToken<List<WallPaperBean>>(){}.getType());

                                mContainer.setHasFixedSize(true);
                                mContainer.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                                mContainer.setAdapter(new WallPaperAdapter(MainActivity.this, wallPaperBeanList));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                requestQueue.add(stringRequest);
            }
        }).start();
    }
}
