package com.hf.menu.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @title 版本工具类
 * @author mark
 * @date 2022.10.21
 */
public class VersionUtil {

    public static final String defVersion = "1.0.0";

    public static String getVersion(String version) {
        AtomicInteger counts = new AtomicInteger();
        String[] vs = version.split("[.]");
        if (Integer.parseInt(vs[2]) < 999) {
            counts.getAndIncrement();
            vs[2] = String.valueOf(Integer.valueOf(vs[2])+counts.get());
        }
        if(Integer.parseInt(vs[2]) == 998){
            vs[2] = String.valueOf(Integer.valueOf(vs[2])+1);
        }
        if(Integer.parseInt(vs[2]) == 999){
            counts.getAndIncrement();
            vs[1] = String.valueOf(Integer.valueOf(vs[1])+counts.get());
            vs[2] = "0";
        }
        if (Integer.parseInt(vs[2]) > 999) {
            counts.getAndIncrement();
            vs[1] = String.valueOf(Integer.valueOf(vs[1])+counts.get());
            vs[2] = "0";
        }
        if(Integer.parseInt(vs[1]) > 9){
            counts.getAndIncrement();
            vs[0] = String.valueOf(Integer.valueOf(vs[0])+1);
            vs[1]="0";
        }
        String result = String.join(".", vs[0], vs[1], vs[2]);
        return result;
    }

    public static String getVersionTwo(String version) {
        AtomicInteger counts = new AtomicInteger();
        String[] vs = version.split("[.]");
        if (Integer.parseInt(vs[2]) < 999) {
            counts.getAndIncrement();
            vs[2] = String.valueOf(Integer.valueOf(vs[2])+counts.get()+1);
        }
        if(Integer.parseInt(vs[2]) == 998){
            vs[2] = String.valueOf(Integer.valueOf(vs[2])+2);
        }
        if(Integer.parseInt(vs[2]) == 999){
            counts.getAndIncrement();
            vs[1] = String.valueOf(Integer.valueOf(vs[1])+counts.get());
            vs[2] = "0";
        }
        if (Integer.parseInt(vs[2]) > 999) {
            counts.getAndIncrement();
            vs[1] = String.valueOf(Integer.valueOf(vs[1])+counts.get());
            vs[2] = "0";
        }
        if(Integer.parseInt(vs[1]) > 9){
            counts.getAndIncrement();
            vs[0] = String.valueOf(Integer.valueOf(vs[0])+1);
            vs[1]="0";
        }
        String result = String.join(".", vs[0], vs[1], vs[2]);
        return result;
    }


    public static void main(String[] args) {
        String[] vs = defVersion.split("[.]");
        System.out.println(vs[2]);
        String str = String.join("^", "You", "are", "Awesome");
        System.out.println(str);
        System.out.println(getVersionTwo("4.8.55"));
    }
}
