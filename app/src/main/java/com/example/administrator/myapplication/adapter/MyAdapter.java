package com.example.administrator.myapplication.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;

    private LayoutInflater inflater;

    private List list;

    public MyAdapter(Context context, List list){
        if(list != null)
            this.list = list;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewHolder vh;
        if(view == null){
            view = inflater.inflate(R.layout.item_layout, null);
            vh = new MyViewHolder();
            vh.textView = view.findViewById(R.id.tv);
            view.setTag(vh);
        }else{
            vh = (MyViewHolder)view.getTag();
        }
        vh.textView.setText((String)list.get(i));

        //不管是vh.textview还是view的longclick都不执行
        //当这里的view的onclick被注释掉之后gridview的longclick可以执行，否则不执行

//        view.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Log.d("zczczc","oh1");
//                Toast.makeText(context, list.get(i) + "", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, list.get(i) + "", Toast.LENGTH_SHORT).show();
//                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 180f);
//                animator.start();
//            }
//        });
        return view;
    }

    class MyViewHolder{
        private TextView textView;
    }
}
