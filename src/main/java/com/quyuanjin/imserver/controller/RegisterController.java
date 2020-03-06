package com.quyuanjin.imserver.controller;

import com.quyuanjin.imserver.constant.Constant;
import com.quyuanjin.imserver.constant.State;
import com.quyuanjin.imserver.handler.session.HibernateUtil;
import com.quyuanjin.imserver.pojo.PojoContract;
import com.quyuanjin.imserver.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSession;
import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSessionAndCommit;
import static com.quyuanjin.imserver.utils.RandomName.randomName;

@RestController
public class RegisterController {

    @RequestMapping(value = "register",produces = "application/json; charset=UTF-8")
    public User register(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        String chineseName = randomName(false, 3);

        User user = new User();
        user.setName(chineseName);
        user.setToken(uuid);
        //getSessionAndCommit(user);
        HibernateUtil.add(user);

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql ="from User user where user.token=?1";
        Query q = session.createQuery(hql);
        q.setParameter(1,uuid);
        List<User> userList = q.list();
        if (userList.size() == 1) {

            //添加机器人

            HibernateUtil.add(new PojoContract(userList.get(0).getId().toString(),"1",
                    Constant.URL+"File/portrait/upload/666.png",
                    "妮妮","您的机器人助手",
                    Constant.sex_female,"", State.TAG_ITEM,""));
            return userList.get(0);
        }
        tx.commit();
        session.close();


        return  new User();
    }
}
