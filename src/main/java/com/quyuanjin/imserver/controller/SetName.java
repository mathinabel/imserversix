package com.quyuanjin.imserver.controller;

import com.quyuanjin.imserver.pojo.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSession;

@RestController

public class SetName {

    @RequestMapping(value = "setName", produces = "application/json; charset=UTF-8")
    public User setName(HttpServletRequest request) {
        String userid = request.getParameter("userid");
        String name = request.getParameter("name");

        //查询数据库是否有该phone
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from User user where user.id=?1";
        Query q = session.createQuery(hql);
        q.setParameter(1, Long.valueOf(userid));
        User user = (User) q.uniqueResult();
          if (user!=null){
              user.setName(name);
            return user;
        }

        tx.commit();
        session.close();
        return new User();//没找到返回一个都是空的json
    }

}
