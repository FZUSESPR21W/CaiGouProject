package com.example.caigouapp.ui.order;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.caigouapp.R;
import com.example.caigouapp.data.OrderResponse;
import com.example.caigouapp.utils.GsonUtil;
import com.example.caigouapp.utils.SpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderFragment extends Fragment {

    private Button cancelButton;
    private RecyclerView recyclerView;
    private TextView noOrderTextView;
    private GridView recentOrderGridView;
    private BaseAdapter mAdapter;
    private ArrayList<CustomerMenu> mData = null;
    private List<Order> list = new ArrayList<>();
    Call<OrderResponse> call;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_order,container,false);
        noOrderTextView = (TextView)view.findViewById(R.id.txt_order_preview_noOrder);

        //grid_recent_order
        recentOrderGridView = view.findViewById(R.id.recent_order_grid);
        mData = new ArrayList<>();
        mData.add(new CustomerMenu(R.drawable.sample,"番茄炒牛肉"));
        setHorizontalGridView();

        recyclerView = (RecyclerView) view.findViewById(R.id.all_order_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//getActivity获得活动（context）
        //String str = GsonUtil.getOrderJson(getActivity());
        //System.out.println(GsonUtil.ParseOrderGson(str));

        int id = SpUtil.getInstance().getInt("id",1);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("user_id", id);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://106.53.148.37:8082/")
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
        OrderRequest request = retrofit.create(OrderRequest.class);
        call = request.getPostCall(requestBody);
        //System.out.println(call.request().headers());
        //System.out.println(call.request());
        //System.out.println(call.request().url());
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                String str = new Gson().toJson(response.body());
                //GsonUtil.e("123",str);
                try{
                    JSONObject jsonObject = new JSONObject(str);
                    JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("info");
                    String data = jsonArray.toString();
                    GsonUtil.e("123",data);
                    list = new Gson().fromJson(data, new TypeToken<List<Order>>(){}.getType());
                    Collections.reverse(list);
                }catch(Exception e){
                    e.printStackTrace();
                }

                if(isAdded()){//必须要这个判断，否则快速切换导航栏时程序会闪退。
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(list.size()==0) noOrderTextView.setText("暂无订单");
                            else {
                                noOrderTextView.setVisibility(View.GONE);
                                recyclerView.setAdapter(new OrderPreviewAdapter(getActivity(),list));
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        //System.out.println(GsonUtil.ParseOrderGson(GsonUtil.getOrderJson(getActivity())));

        return view;
    }

    private void setHorizontalGridView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int size = mData.size();
                int length = 100;
                DisplayMetrics dm = getResources().getDisplayMetrics();
                float density = dm.density;
                int gridviewWidth = (int) (size * (length + 4) * density);
                int itemWidth = (int) (length * density);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
                recentOrderGridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
                recentOrderGridView.setColumnWidth(itemWidth); // 设置列表项宽
                recentOrderGridView.setHorizontalSpacing(5); // 设置列表项水平间距
                recentOrderGridView.setStretchMode(GridView.NO_STRETCH);
                recentOrderGridView.setNumColumns(size); // 设置列数量=列表集合数

                mAdapter = new GridAdapter(mData, R.layout.item_grid_order) {
                    @Override
                    public void bindView(ViewHolder holder, Object obj) {
                        CustomerMenu cm = (CustomerMenu)obj;
                        holder.setImageResource(R.id.img_grid, BitmapFactory.decodeResource(getResources(),R.drawable.sample));
                        holder.setText(R.id.txt_grid, cm.getiName());
                    }
                };
                recentOrderGridView.setAdapter(mAdapter);
            }
        },100);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (call != null && call.isExecuted())
            call.cancel();
    }
}