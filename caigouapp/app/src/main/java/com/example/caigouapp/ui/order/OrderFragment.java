package com.example.caigouapp.ui.order;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.caigouapp.R;
import com.example.caigouapp.data.OrderResponse;
import com.example.caigouapp.utils.GraphicUtil;
import com.example.caigouapp.utils.GsonUtil;
import com.example.caigouapp.utils.SpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

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

        recentOrderGridView = view.findViewById(R.id.recent_order_grid);

        recyclerView = (RecyclerView) view.findViewById(R.id.all_order_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//getActivity获得活动（context）

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
                        if(isAdded()){
                            Glide.with(getActivity())
                                    .load(cm.getPhotoUrl())
                                    //.placeholder() 这是等待时的图标
                                    .asBitmap()
                                    .fitCenter()
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                            holder.setImageResource(R.id.img_grid,resource);
                                        }
                                    });
                        }
                        holder.setText(R.id.txt_grid, cm.getiName());
                    }
                };
                recentOrderGridView.setAdapter(mAdapter);
            }
        },100);
    }

    public void getRecyclerViewData(){
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
        call.enqueue(new Callback<OrderResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
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
                    //Collections.reverse(list);
                    mData = getRecentData(list);
                    setHorizontalGridView();
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
                                recyclerView.setVisibility(View.VISIBLE);
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
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<CustomerMenu> getRecentData(List<Order> orders){
        int orderSize = Math.min(orders.size(),10);
        Map<String,CustomerMenu> cmm = new LinkedHashMap<>();
        ArrayList<CustomerMenu> result = new ArrayList<>();

        for(int i=0;i<orderSize;i++){
            ArrayList<CustomerMenu> cms = orders.get(i).getCms();//获得order
            System.out.println(cms.size());
            for(int j=0;j<cms.size();j++){
                CustomerMenu cm = cms.get(j);
                System.out.println(cm.getiName());
                if(cmm.containsKey(cm.getiName())){
                    //已存在该map，直接复用已有的对象计数器要加一
                    CustomerMenu cmTemp = cmm.get(cm.getiName());
                    cmTemp.setCount(cmTemp.getCount()+1);//问题在于相同名称的CM也会拥有不同对象，count并不共通，所以要直接复用
                }else{
                    cm.setCount(1);
                    cmm.put(cm.getiName(),cm);
                }
            }
        }

        List<Map.Entry<String,CustomerMenu>> list = new ArrayList<>(cmm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, CustomerMenu>>() {
            public int compare(Map.Entry<String, CustomerMenu> o1, Map.Entry<String, CustomerMenu> o2) {
                return  o2.getValue().getCount().compareTo(o1.getValue().getCount());
            }
        });

        for(int i=0;i<Math.min(list.size(),3);i++){
            result.add(list.get(i).getValue());
        }

        return result;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (call != null && call.isExecuted())
            call.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        noOrderTextView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        getRecyclerViewData();
    }
}