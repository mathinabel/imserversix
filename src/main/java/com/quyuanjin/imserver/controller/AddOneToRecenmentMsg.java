package com.quyuanjin.imserver.controller;

import com.google.gson.Gson;
import com.quyuanjin.imserver.handler.session.HibernateUtil;
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
public class AddOneToRecenmentMsg {
    @RequestMapping(value = "addOneToRecenmentMsg", produces = "application/json; charset=UTF-8")
    public String addOneToRecenmentMsg(HttpServletRequest request) {
        String uid = request.getParameter("userid");
        Gson gson = new Gson();
        RecementMsg recementMsg = gson.fromJson(uid, RecementMsg.class);
//查找并剔除重复
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from RecementMsg rct where rct.myUserId=?1 and rct.yourUserId=?2";
        Query q = session.createQuery(hql);
        q.setParameter(1, recementMsg.getMyUserId());
        q.setParameter(2, recementMsg.getYourUserId());
        List<RecementMsg> userList = q.list();
        tx.commit();
        //关闭事务
        session.close();
        if (userList.size() == 1) {
            // 只有一条消息记录，更改状态
            RecementMsg recementMsg1=userList.get(0);

            recementMsg1.setContentText(recementMsg.getContentText());

            HibernateUtil.add(recementMsg1);
            return "succeed";
        }else if (userList.size()==0){
            //没有记录，新增一条
            HibernateUtil.add(new RecementMsg(recementMsg.getMyUserId(),recementMsg.getYourUserId()
            ,recementMsg.getPortrait(),recementMsg.getUnreadCount(),recementMsg.getName(),
                    recementMsg.getTime(),"",recementMsg.getStatus(),
                    recementMsg.getContentText(),recementMsg.getSlient()));
            return "succeed";
        }
        return "failed";
    }
}
