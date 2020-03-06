package com.quyuanjin.imserver.controller;

import com.quyuanjin.imserver.pojo.Message;
import com.quyuanjin.imserver.pojo.Usert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PullUnReadMsg {
    @RequestMapping(value = "1", produces = "application/json; charset=UTF-8")
    public List<Usert> unreadMsg() {
        List<Usert> usertList = new ArrayList<>();

     /* Message message=new Message();
         message.setMsg("666");
         return  message;*/
        usertList.add(new Usert((long) 6, "666", "999"));
        usertList.add(new Usert((long) 6, "666", "999"));

        usertList.add(new Usert((long) 6, "666", "999"));

        return usertList;
    }
}
