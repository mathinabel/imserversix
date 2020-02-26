package com.quyuanjin.imserver.handler.onlinejudge;

import com.quyuanjin.imserver.pojo.User;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSession;


public class OnLineJudge {
    private HashMap hashMap;


    /**
     *
     * @param hashMap hashmap

     */
    public OnLineJudge(HashMap hashMap) {
        this.hashMap = hashMap;


    }


    public String judegOnLineWithPhoneFromUser(String strings) {

        String hql2 = "from User where phone =" + strings;
        Query query2 = getSession().createQuery(hql2);
        List<User> list2 = query2.list();
        User user1 = list2.get(0);
        String touuid = user1.getToken();
        System.out.println("收到号码查询到的好友uuid为" + touuid);
        if (hashMap.containsKey(touuid)) {
            System.out.println("hashmap含有该uuid");
            return touuid;
        } else {
            return "";
        }


    }
    public String judegOnLineWithUserIdFromUser(String userId) {

        if (hashMap.containsKey(userId)) {
            System.out.println("hashmap含有该userid");
            return userId;
        } else {
            return "";
        }


    }




}
