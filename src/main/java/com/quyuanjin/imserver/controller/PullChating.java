package com.quyuanjin.imserver.controller;

import com.quyuanjin.imserver.pojo.RecementMsg;
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
public class PullChating {

    @RequestMapping(value = "chating", produces = "application/json; charset=UTF-8")
    public List<RecementMsg> chating(HttpServletRequest request) {
        String uid = request.getParameter("userid");
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from RecementMsg rct where rct.myUserId=?1";
        Query q = session.createQuery(hql);
        q.setParameter(1, uid);
        List<RecementMsg> userList = q.list();
        tx.commit();
        //关闭事务
        session.close();
        return userList;
    }
}
