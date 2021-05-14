package com.example.caigouapp.ui.mine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.caigouapp.R;
import com.example.caigouapp.data.CommonResponse;
import com.example.caigouapp.data.UserTagResponse;
import com.example.caigouapp.data.UserTagResponse.*;
import com.example.caigouapp.http.Constant;
import com.example.caigouapp.http.UserServices;
import com.example.caigouapp.utils.SpUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TagChooseActivity extends AppCompatActivity {

    private List<TagsBean> data = new ArrayList<>();
    MineTagChooseAdapter mineAdapter;
    String tags = "快手菜,下饭菜,素食,肉食,汤,家常菜,早餐,午餐,下午茶,晚餐,夜宵,川菜,粤菜,鲁菜,苏菜,闽菜,浙菜,徽菜";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_choose);
        initData();
        initView();
    }

    private void initData() {
        String tagChosen = getIntent().getStringExtra("tagChosen");
        String[] arr = tags.split(",");
        for (int i = 0;i < arr.length;i++) {
            data.add(new TagsBean(i+1, arr[i]));
            if (tagChosen.contains(arr[i])){
                data.get(i).setStatus(1);
            }
        }
    }

    private void initView(){
        ImageView imageView = (ImageView)findViewById(R.id.btn_arrow_left);
        imageView.setOnClickListener(v -> finish());

        ImageView imageView1 = (ImageView) findViewById(R.id.btn_selected);
        imageView1.setOnClickListener(v -> {
            updateUserTags();
        });

        RadioButton radioButton = findViewById(R.id.rb_tag_choose);
        radioButton.setOnClickListener(v -> {
            if (radioButton.isSelected()){
                for (int i = 0;i < 18;i++){
                    data.get(i).setStatus(0);
                    mineAdapter.notifyDataSetChanged();
                }
            }else{
                for (int i = 0;i < 18;i++){
                    data.get(i).setStatus(1);
                    mineAdapter.notifyDataSetChanged();
                }
            }
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.cb_tag_choose);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        mineAdapter = new MineTagChooseAdapter(data, this);
        recyclerView.setAdapter(mineAdapter);
    }

    private void updateUserTags(){
        String account = SpUtil.getInstance().getString("account","");
        String token = SpUtil.getInstance().getString("token","");
        HashMap<String , Object> map = new HashMap<>();
        map.put("account",account);
        List<String> tags = new ArrayList<>();
        for (int i = 0;i < 18;i++){
            if (data.get(i).getStatus() == 1)
                tags.add(data.get(i).getTag_id()+"");
        }
        map.put("tags",tags);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserServices userServices = retrofit.create(UserServices.class);
        Call<CommonResponse> call = userServices.addUserTags(token, requestBody);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if (response.isSuccessful()){
                    runOnUiThread(() -> {
                        finish();
                    });
                }
            }
            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Log.d("MineFragment",t.getMessage());
            }
        });
    }
}