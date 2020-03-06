package com.quyuanjin.imserver.controller;

import com.quyuanjin.imserver.constant.State;
import com.quyuanjin.imserver.pojo.RecementMsg;
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
public class PullUnReadAddFriendReq {
    @RequestMapping(value = "PullUnReadAddFriendReq", produces = "application/json; charset=UTF-8")
    public List<UnReadAddFriendRequest> PullUnReadAddFriendReq(HttpServletRequest request) {
        System.out.println("客户端请求获得未发送好友");

        String uid = request.getParameter("userid");
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from UnReadAddFriendRequest rct where rct.userid=?1";
        Query q = session.createQuery(hql);
        q.setParameter(1, uid);
        List<UnReadAddFriendRequest> userList = q.list();
        //过滤 没发送过的才发送 ，todo发完之后还要更改发送状态
    /*   for (UnReadAddFriendRequest unReadAddFriendRequest :userList){
         if (State.SEND.equals(unReadAddFriendRequest.getSendType())){
               userList.remove(unReadAddFriendRequest);
          }
      } */

            tx.commit();
        //关闭事务
        session.close();
    return userList;
    }
}
