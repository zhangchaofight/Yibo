package com.example.administrator.myapplication.view;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.MyAdapter;
import com.example.administrator.myapplication.utils.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 先数据再展示
 * MainActivity只展示
 * Created by Administrator on 2017/9/7.
 */

/**
 * onCreate -> onCreateView -> onStart -> onResume
 * onCreate执行数据初始化
 * onCreateView初始化布局设置listener
 * onResume展示数据
 */

public class ShowResultFragment extends Fragment{

    private String target = "QWERTYUIOPASDFGHJKLZXCVBNM";

    private GridView gridView;

    private List<String> list;

    private MyAdapter adapter;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("lastlast", "oncreateview");
        view = inflater.inflate(R.layout.show_result_layout, container, false);
        gridView = view.findViewById(R.id.grid_view);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 180f);
                animator.start();
                Toast.makeText(getContext(), list.get(i), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lastlast", "oncreate");
        setData(Sort.sort0(target));
        adapter = new MyAdapter(getContext(), list);
    }

    /**
     * 注意生命周期
     */
    @Override
    public void onResume() {
        super.onResume();
        gridView.setAdapter(adapter);
        Log.d("lastlast", "onresume");
    }

    public void setData(String data){
        if(data == null || data.length() == 0)
            return;
        list = new ArrayList<>();
        char[] c = data.toCharArray();
        for(int i = 0; i < data.length(); i++){
            list.add(c[i] + "");
        }
    }


}
