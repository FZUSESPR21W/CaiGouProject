package com.example.caigouapp.ui.order;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private TextView allOrderTextView;
    private TextView recentOrderTextView;
    private Button cancelButton;
    private RecyclerView allOrderRecyclerView;
    private GridView recentOrderGridView;
    private BaseAdapter mAdapter;
    private ArrayList<Icon> mData = null;
    private List<Order> list = null;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_course,container,false);

        //grid_recent_order
        recentOrderGridView = view.findViewById(R.id.recent_order_grid);
        mData = new ArrayList<Icon>();
        mData.add(new Icon(R.drawable.sample,"ダサ"));
        mData.add(new Icon(R.drawable.sample,"ダサ"));
        mData.add(new Icon(R.drawable.sample,"ダサ"));
        mData.add(new Icon(R.drawable.sample,"ダサ"));
        mData.add(new Icon(R.drawable.sample,"ダサ"));

        setHorizontalGridView();

        //all_order_recycler
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.all_order_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//getActivity获得活动（context）
        list = getOrderList();//初始化list<order>
        recyclerView.setAdapter(new OrderPreviewAdapter(getActivity(),list));

        return view;
    }

    private void setHorizontalGridView(){
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
                Icon icon = (Icon)obj;
                holder.setImageResource(R.id.img_grid, icon.getiId());
                holder.setText(R.id.txt_grid, icon.getiName());
            }
        };
        recentOrderGridView.setAdapter(mAdapter);
    }

    public static List<Order> getOrderList(){
        List<Order> list = new ArrayList<>();
        for(int i=1;i<=3;i++){
            Order order = new Order();
            order.setAddress("福大学生公寓");
            order.setOrderNumber("1123 7109 0824 3702 55");
            order.setOrderCreateTime("2021-05-01 11:01:10");
            order.setPhone("17612345678");
            order.setOrderServeTime("2021-05-01 11:30");
            order.setStoreName("sf超市");
            order.setOrder_state(true);
            order.setPiece(2);
            order.setPrice(23.33);
            ArrayList<Icon> icons = new ArrayList<>();
            for(int j=1;j<=i;j++){
                Icon icon = new Icon();
                icon.setiId(R.drawable.sample);
                icon.setiName("图标名字"+i);
                icon.setMethod("做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法做法");
                icon.setPrice(7.21);
                icon.setPiece(2);
                icon.setSourceMenuId(265);
                icons.add(icon);
            }
            order.setIcons(icons);
            list.add(order);
        }
        return list;
    }
}