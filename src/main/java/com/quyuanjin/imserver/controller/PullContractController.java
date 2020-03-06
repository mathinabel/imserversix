package com.quyuanjin.imserver.controller;

import com.google.gson.Gson;
import com.quyuanjin.imserver.pojo.PojoContract;
import com.quyuanjin.imserver.pojo.RecementMsg;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSession;

@RestController
public class PullContractController {
    @RequestMapping(value = "contract", produces = "application/json; charset=UTF-8")
    public List<PojoContract> contract(HttpServletRequest request) {
        String uid = request.getParameter("userid");
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from PojoContract cont where cont.myUserId=?1";
        Query q = session.createQuery(hql);
        q.setParameter(1, uid);
        List<PojoContract> userList = q.list();
        tx.commit();
        //关闭事务
        session.close();
        Gson gson=new Gson();
       System.out.println("回送的联系人列表信息为："+gson.toJson(userList));
        return userList;
    }
}
