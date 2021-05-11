package com.example.caigouapp.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.caigouapp.R;

import java.util.ArrayList;
import java.util.List;

public class MineFragment extends Fragment {

    private List<MineBean> data = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        for (int i = 0; i < 1000; i++) {
            MineBean mineBean = new MineBean();
            mineBean.setName("标签" + i);
            data.add(mineBean);
        }

        TextView textView = root.findViewById(R.id.tag_choose_tv);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TagChooseActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.mine_tag_rv);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        MineAdapter mineAdapter = new MineAdapter(data, getActivity());
        Log.d("567","```"+data.size());
        recyclerView.setAdapter(mineAdapter);
        return root;
    }


}