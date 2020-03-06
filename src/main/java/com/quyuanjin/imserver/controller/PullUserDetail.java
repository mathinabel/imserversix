package com.quyuanjin.imserver.controller;

import com.quyuanjin.imserver.pojo.UnReadAddFriendRequest;
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
public class PullUserDetail {
    @RequestMapping(value = "PullUserDetail", produces = "application/json; charset=UTF-8")
    public User PullUserDetail(HttpServletRequest request) {
        String uid = request.getParameter("userid");
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from User rct where rct.id=?1";
        Query q = session.createQuery(hql);
        q.setParameter(1, Long.valueOf(uid));
        User user = (User) q.uniqueResult();
        tx.commit();
        session.close();
        return user;
    }
}
