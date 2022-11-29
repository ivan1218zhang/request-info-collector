package com.zyf.request.info.collector.core.utils;

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
    public static void sortDesc(ArrayList<String> keys, ArrayList<Integer> values){
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
                int right=index*2+2;
                int indexTmp=index;
                boolean flag=true;
                if (left<i&&values.get(indexTmp)>values.get(left)){
                    swap(keys,values,indexTmp,left);
                    index=left;
                    flag=false;
                }
                if (right<i&&values.get(indexTmp)>values.get(right)) {
                    swap(keys,values,indexTmp,right);
                    if (flag){
                        index=right;
                        flag=false;
                    }
                }
                if (flag){
                    break;
                }
            }
        }
    }
    public static void sortAsc(ArrayList<String> keys, ArrayList<Integer> values){
        int len=keys.size();
        //构建大根堆
        for (int i=0;i<len;i++){
            int index=i;
            while (index>0){
                int pre=(index+1)/2-1;
                if (values.get(index)>values.get(pre)){
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
                int right=index*2+2;
                int indexTmp=index;
                boolean flag=true;
                if (left<i&&values.get(indexTmp)<values.get(left)){
                    swap(keys,values,indexTmp,left);
                    index=left;
                    flag=false;
                }
                if (right<i&&values.get(indexTmp)<values.get(right)) {
                    swap(keys,values,indexTmp,right);
                    if (flag){
                        index=right;
                        flag=false;
                    }
                }
                if (flag){
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
}
