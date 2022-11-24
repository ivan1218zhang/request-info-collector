package com.zyf.request.info.collector.core.utils;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;

/**
 * @author zhangyf45
 * @date 2022/11/24 23:20
 */
public class ReportUtils {
    /**
     * 对hashmap的keys和values对应地排序
     * @param keys
     * @param values
     */
    public static void sort(ArrayList<String> keys, ArrayList<Integer> values){
        int len=keys.size();
        //构建小根堆
        for (int i=0;i<len;i++){
            int index=i;
            while (index>0){
                int pre=(index+1)/2-1;
                if (values.get(index)<values.get(pre)){
                    swap(keys,values,index,pre);
                }else {
                    break;
                }
                index=pre;
            }
        }
        //堆排序
        for (int i=len-1;i>0;i--){
            //交换头尾
            swap(keys,values,0,i);
            int index=0;
            while (true){
                int left=index*2+1;
                int right=index*2+1;
                if (left<i&&values.get(index)>values.get(left)){
                    swap(keys,values,index,left);
                    index=left;
                } else if (right<i&&values.get(index)>values.get(right)) {
                    swap(keys,values,index,right);
                    index=right;
                }else {
                    break;
                }
            }
        }
    }
    static void swap(ArrayList<String> keys,ArrayList<Integer> values,int index1,int index2){
        Integer tmp=values.get(index1);
        values.set(index1,values.get(index2));
        values.set(index2,tmp);
        String tmpKey=keys.get(index1);
        keys.set(index1,keys.get(index2));
        keys.set(index2,tmpKey);
    }

    public static void main(String[] args) {
        ArrayList<String> keys=new ArrayList<>();
        ArrayList<Integer> values=new ArrayList<>();
        keys.add("a");
        keys.add("b");
        keys.add("c");
        values.add(1);
        values.add(2);
        values.add(3);
        sort(keys,values);
        System.out.println(1123);
    }
}
