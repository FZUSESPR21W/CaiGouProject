package com.example.caigouapp.ui.order;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caigouapp.R;
import com.example.caigouapp.data.CancelOrderResponse;
import com.example.caigouapp.utils.GraphicUtil;
import com.example.caigouapp.utils.GsonUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

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

public class OrderDetailActivity extends AppCompatActivity {

    List<Order> list;
    Order mOrder;
    ArrayList<CustomerMenu> mCms;
    RecyclerView recyclerView;
    TextView stateTextView;
    TextView numberTextView;
    TextView copyButton;
    TextView createTextView;
    TextView serveTextView;
    TextView addressTextView;
    TextView phoneTextView;
    TextView remarkTextView;
    TextView cancelOrderButton;
    LinearLayout callSellerButton;
    String data;//传到该活动的订单的json字符串

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        data = getIntent().getStringExtra("data");
        mOrder  = GsonUtil.getOrder(data);

        //list = GsonUtil.getDataList(OrderDetailActivity.this);
        mCms = mOrder.getCms();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_order_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        recyclerView.setAdapter(new OrderDetailAdapter(OrderDetailActivity.this,mCms));

        stateTextView = (TextView) findViewById(R.id.txt_order_detail_state);
        numberTextView = (TextView) findViewById(R.id.txt_order_detail_number);
        copyButton = (TextView) findViewById(R.id.btn_order_detail_copy_number);
        createTextView = (TextView) findViewById(R.id.txt_order_detail_create_time);
        serveTextView = (TextView) findViewById(R.id.txt_order_detail_serve_time);
        addressTextView = (TextView) findViewById(R.id.txt_order_detail_address);
        phoneTextView = (TextView) findViewById(R.id.txt_order_detail_phone);
        remarkTextView = (TextView) findViewById(R.id.txt_order_detail_remark);
        ImageView back = findViewById(R.id.btn_arrow_left);
        callSellerButton = (LinearLayout) findViewById(R.id.call_seller);
        cancelOrderButton = (TextView)findViewById(R.id.btn_order_detail_cancel);

        stateTextView.setText(mOrder.getOrderStateString());
        numberTextView.setText(mOrder.getOrderNumber());
        createTextView.setText(mOrder.getOrderCreateTime());
        serveTextView.setText(mOrder.getOrderServeTime());
        addressTextView.setText(mOrder.getAddress());
        phoneTextView.setText(mOrder.getPhone());
        remarkTextView.setText(mOrder.getRemark());

        back.setOnClickListener(v -> {
            finish();
        });

        copyButton.setOnClickListener(v -> {
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData mClipData = ClipData.newPlainText("Label", mOrder.getOrderNumber());
            cm.setPrimaryClip(mClipData);
            Toast.makeText(OrderDetailActivity.this,"已复制到剪贴板",Toast.LENGTH_SHORT).show();
        });

        callSellerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        if(mOrder.getOrderState() == 1 || mOrder.getOrderState() == 2){
            cancelOrderButton.setText("再来一单");
        }else{
            cancelOrderButton.setText("取消订单");
            cancelOrderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, Integer> map = new HashMap<String, Integer>();
                    map.put("orderId", Integer.valueOf(mOrder.getOrderNumber()));
                    GsonUtil.e("1333",mOrder.getOrderNumber());
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://106.53.148.37:8082/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    OrderRequest request = retrofit.create(OrderRequest.class);
                    Call<CancelOrderResponse> call = request.cancelOrder(requestBody);
                    GsonUtil.e("1333",call.request().url().toString());
                    call.enqueue(new Callback<CancelOrderResponse>() {
                        @Override
                        public void onResponse(Call<CancelOrderResponse> call, Response<CancelOrderResponse> response) {
                            String str = new Gson().toJson(response.body());
                            GsonUtil.e("1333",response.body().toString());
                            try{
                                JSONObject jsonObject = new JSONObject(str);
                                int code = jsonObject.getInt("code");
                                GsonUtil.e("1333",code+"");
                                if(code == 200) {
                                    Toast.makeText(OrderDetailActivity.this,"取消订单成功！",Toast.LENGTH_SHORT).show();
                                    stateTextView.setText("已取消");
                                    cancelOrderButton.setText("再来一单");
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<CancelOrderResponse> call, Throwable t) {
                            System.out.println(t.getMessage());
                        }
                    });
                }
            });
        }


    }
}