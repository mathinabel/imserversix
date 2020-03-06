package com.quyuanjin.imserver.utils;

import com.google.gson.Gson;
import com.quyuanjin.imserver.pojo.User;
import com.quyuanjin.imserver.pojo.Usert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {

    public static String getCurrentTime(){
        Date day=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(df.format(day));


        return df.format(day);
    }
    public static void main(String[] args) {
      //  getCurrentTime();
        Gson gson = new Gson();
        Usert user = new Usert((long) 6,"565","ada");
        String jsonObject = gson.toJson(user);
        System.out.println(jsonObject);
    }
}
