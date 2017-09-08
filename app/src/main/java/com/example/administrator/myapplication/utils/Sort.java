package com.example.administrator.myapplication.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/9/7.
 */

public class Sort {

    /**
     * 冒泡算法
     * 先判断参数合法性
     * 将String对象变为char数组，对数组里的元素直接比较大小，进行冒泡排序
     * @param target
     * @return
     */
    public static String sort0(String target){
        if(target == null || target.length() == 0)
            return null;
        int length = target.length();
        char[] before = target.toCharArray();
        char temp;
        for(int i = 0; i < length - 1; i++){
            for(int j = i + 1; j < length; j++){
                if(before[i] > before[j]){
                    temp = before[i];
                    before[i] = before[j];
                    before[j] = temp;
                }
            }
        }
        Log.d("zzzzzzzzz", new String(before));
        return new String(before);
    }

    /**
     *先判断入参有效性
     * 接着统计每个字母出现的个数
     * 最后从A打印到Z，出现几次打印几次
     * @param target
     * @return
     */
    public static String sort1(String target){
        if(target == null || target.length() == 0)
            return null;
        char[] temp = target.toCharArray();
        int[] cout = new int[26];
        for(int i = 0; i < target.length(); i++){
            if(temp[i] >= 'A' && temp[i] <= 'Z'){
                cout[temp[i] - 'A']++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++){
            if(cout[i] != 0){
                for(int j = 0; j < cout[i]; j++){
                    sb.append((char)(i + 'A'));
                }
            }
        }
        return sb.toString();
    }
}
