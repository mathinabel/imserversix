package com.quyuanjin.imserver.controller;

import com.quyuanjin.imserver.pojo.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSession;

@RestController
public class LoginController {
    @RequestMapping(value = "login", produces = "application/json; charset=UTF-8")
    public User login(HttpServletRequest request) {
        String userid = request.getParameter("userid");
        String password = request.getParameter("pwd");
        System.out.println("password is:" + password);

        //查询数据库是否有该phone
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from User user where user.id=?1";
        Query q = session.createQuery(hql);
        q.setParameter(1, Long.valueOf(userid));
        User user = (User) q.uniqueResult();
            if (user.getPwd().equals(password)) {
                return user;
            }

        tx.commit();
        session.close();
        return new User();//没找到返回一个都是空的json
    }
}
