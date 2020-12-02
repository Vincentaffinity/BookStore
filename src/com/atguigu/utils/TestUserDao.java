package com.atguigu.utils;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class TestUserDao {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> con = new ArrayList<>();
        String str;

        while((str = br.readLine()) != null){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) != ' '){
                    sb.append(str.charAt(i));
                    if(i == str.length()-1) con.add(sb.toString());
                }else{
                    con.add(sb.toString());
                    sb = new StringBuilder();
                }
            }

            for(int i = con.size()-1; i >= 0 ; i--){
                System.out.print(con.get(i));
                if(i == 0) System.out.print(" ");
            }
        }
    }

    //生成素数
    public static void suNum(int num){
        for(int i = 2; i < num; i++){
            boolean flag = false;
            for(int j = 2; j <= Math.sqrt(i); j++){
                if(i % j == 0) flag = true;
            }
            if(!flag) System.out.println(i);
        }
    }

    public static void index(int num){
        num = 4;
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                System.out.println(i);
                num /= i;
                i--;
            }
        }
        if(num != 1) System.out.println(num);
    }

    public static String reverse(int num){
        String strNum = num + "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strNum.length(); i++){
            sb.insert(0, strNum.charAt(i));
        }
        return sb.toString();
    }

    public static int jud(int num){
        int count = 1;
        while(Math.abs(num) > 0){
            if((num & 1) == 0){
                count++;
                num >>= 1;
            }else{
                return count;
            }
        }
        return count;
    }

    public static int countLen(String str){
        int l = str.length()-1;
        int count = 0;
        while(l >= 0){
            if(str.charAt(l) != ' '){
                l--;
                count++;
            }else{
                break;
            }
        }
        return count;
    }

    public static void quickSort(int nums[], int start, int end){
        if(start >= end) return;
        int l = start;
        int h = end;
        int base = nums[l];

        while(l != h){
            while(nums[h] >= base && l < h){
                h--;
            }
            while(nums[l] <= base && l < h){
                l++;
            }
            if(l < h){
                int temp = nums[h];
                nums[h] = nums[l];
                nums[l] = temp;
            }
        }
        nums[start] = nums[h];
        nums[h] = base;

        quickSort(nums, start, h-1);
        quickSort(nums, h+1, end);

    }

}
