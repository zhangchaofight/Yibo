package com.example.administrator.myapplication.view;


import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.myapplication.MyReceiver;
import com.example.administrator.myapplication.R;

/**
 *题目：条件（按照项目规范进行）
 1. 根据提供的无序英文字母进行排序（三种算法）
 2. 展示排序之后的字母列表（要求使用fragment展示）
 3. 监听网络变化，如果无网络，增加无网络点击按钮刷新页面，如果有网络自动刷新
 例如 QWERTYUIOPASDFGHJKLZXCVBNM
 4.长按删除某个字母并刷新列表
 5.点击字母提示当前点击字母文字
 6.点击某行，某行字母进行旋转动画

 有哪些部分，之间的联系，做好计划，预期时间和碰到的问题
 首先按时完成任务，基本需求，在此基础上再优化
 扩展性
 复用代码

 activity展示、监听、控制
 */
public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private ShowResultFragment fragment;

    private EmptyFragment efragment;

    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initBroadcast();
        initView();
    }

    private void initView(){
        if(!isNetAvailable()){
            addNoDataFragment();
        }else{
            addDataFragment();
        }
    }

    private void replaceNoDataFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(efragment == null){
            efragment = new EmptyFragment();
        }
        transaction.replace(R.id.main_container, efragment);
        transaction.commitAllowingStateLoss();
    }

    private void replaceDataFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(fragment == null) {
            fragment = new ShowResultFragment();
        }
        transaction.replace(R.id.main_container, fragment);
        transaction.commitAllowingStateLoss();
    }

    private void addNoDataFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(efragment == null){
            efragment = new EmptyFragment();
        }
        transaction.add(R.id.main_container, efragment);
        transaction.commit();
    }

    private void addDataFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(fragment == null) {
            fragment = new ShowResultFragment();
        }
        transaction.add(R.id.main_container, fragment);
        transaction.commit();
    }

    /**
     *
     */
    private void initBroadcast(){
        receiver = new MyReceiver(new MyReceiver.onNetworkChanged() {
            @Override
            public void isOnline() {
                replaceDataFragment();
            }

            @Override
            public void isOffline() {
                replaceNoDataFragment();
            }
        });
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    public boolean isNetAvailable(){
        boolean state = false;
        ConnectivityManager manager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network[] infos = manager.getAllNetworks();
        for(Network info : infos){
            NetworkInfo n = manager.getNetworkInfo(info);
            if(n.isConnected())
                state = true;
        }
        return state;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
