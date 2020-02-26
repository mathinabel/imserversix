package com.quyuanjin.imserver.handler.send;

import com.google.gson.Gson;
import com.quyuanjin.imserver.constant.Constant;
import com.quyuanjin.imserver.constant.ProtoConst;
import com.quyuanjin.imserver.constant.State;
import com.quyuanjin.imserver.handler.session.HibernateUtil;
import com.quyuanjin.imserver.pojo.*;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSession;
import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSessionAndCommit;


public class SendOrStore {
    private HashMap hashMap;
    private UnReadAddFriendRequest unReadAddFriendRequest;
    private ChannelGroup channelGroup;

    public SendOrStore(HashMap hashMap, UnReadAddFriendRequest unReadAddFriendRequest, ChannelGroup channelGroup) {
        this.hashMap = hashMap;
        this.unReadAddFriendRequest = unReadAddFriendRequest;
        this.channelGroup = channelGroup;
    }

    public void send(String userid) {
        ChannelId channelid = (ChannelId) hashMap.get(userid);
        //   Gson gson = new Gson();
        //  String backmsg = ProtoConst.ADD_FRIEND_BACK + "|" + gson.toJson(unReadAddFriendRequest) + "\r\n";
        String backmsg = ProtoConst.ADD_FRIEND_BACK + "|" + "\r\n";

        channelGroup.find(channelid).writeAndFlush(backmsg);
        System.out.println("addfriends回送内容为" + backmsg);
    }

    public void sendAddFriendAgree(String userid) {
        ChannelId channelid = (ChannelId) hashMap.get(userid);
        Gson gson = new Gson();
        String backmsg = ProtoConst.ADD_FRIEND_AGREE_BACK + "|" + gson.toJson(unReadAddFriendRequest) + "\r\n";
        channelGroup.find(channelid).writeAndFlush(backmsg);
        System.out.println("sendAddFriendAgree" + backmsg);
    }

    public void sendAddFriendAgree2(String userid2, String userid3) {
        ChannelId channelid2 = (ChannelId) hashMap.get(userid2);
        ChannelId channelid3 = (ChannelId) hashMap.get(userid3);

        String backmsg = ProtoConst.ADD_FRIEND_AGREE_BACK + "|" + "\r\n";
        channelGroup.find(channelid2).writeAndFlush(backmsg);
        channelGroup.find(channelid3).writeAndFlush(backmsg);
        System.out.println("sendAddFriendAgree" + backmsg);
    }

    /**
     * 存储加好友请求信息
     */
    public void storeURAD(UnReadAddFriendRequest unReadAddFriendRequest) {

        //进行数据库保存加好友请求
        // getSessionAndCommit(unReadAddFriendRequest);

        System.out.println("进行数据库保存加好友请求");
    }

    /**
     * 新存储加好友请求信息
     */
    public void storeURADAddFriendNew(UnReadAddFriendRequest unReadAddFriendRequest) {
        //selfuid +targetuid 同时标明发送者还是被添加者 自身在获取的时候不会乱
        unReadAddFriendRequest.setReadType(State.addfriend_send_but_wait_to_answer);
        HibernateUtil.add(unReadAddFriendRequest);
        // getSessionAndCommit(unReadAddFriendRequest);
        System.out.println("进行数据库保存加好友请求：加好友一");

        //targetuid +selfuid 同时标明发送者还是被添加者 被添加方在获取的时候不会乱
        UnReadAddFriendRequest unReadAddFriendRequest1 = new UnReadAddFriendRequest();
        String a = unReadAddFriendRequest.getUserid();
        String b = unReadAddFriendRequest.getReceiverId();
        unReadAddFriendRequest1.setReadType(State.be_asked_to_addfriend);
        unReadAddFriendRequest1.setUserid(b);
        unReadAddFriendRequest1.setReceiverId(a);
        unReadAddFriendRequest1.setNameTextView(unReadAddFriendRequest.getNameTextView());

        HibernateUtil.add(unReadAddFriendRequest1);
        //getSessionAndCommit(unReadAddFriendRequest1);
        System.out.println("进行数据库保存加好友请求：加好友er");

        System.out.println("进行数据库保存加好友请求");
    }

    public void storeURAD2(UnReadAddFriendRequest unReadAddFriendRequest) {

        //进行数据库保存加好友请求
        HibernateUtil.add(unReadAddFriendRequest);

        //同时更改对方获取的item的消息
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from UnReadAddFriendRequest uad where uad.userid=?1 and uad.receiverId=?2";
        Query q = session.createQuery(hql);
        q.setParameter(1, unReadAddFriendRequest.getReceiverId());
        q.setParameter(2, unReadAddFriendRequest.getUserid());
        UnReadAddFriendRequest unRead = (UnReadAddFriendRequest) q.uniqueResult();
        unRead.setReadType(State.addFriend_answer_agree);
        session.update(unRead);
        tx.commit();
        session.close();

        System.out.println("进行数据库保存加好友fuifu请求");
      //  HibernateUtil.add(unRead);

    }

    public void storeUserRelationShip(String selfuuid, String frienduuid) {
        UserRelationShip userRelationShip = new UserRelationShip();
        userRelationShip.setUserFriends(frienduuid);
        userRelationShip.setUserId(selfuuid);
        HibernateUtil.add(userRelationShip);
        // getSessionAndCommit(userRelationShip);
        UserRelationShip userRelationShip2 = new UserRelationShip();
        userRelationShip2.setUserFriends(selfuuid);
        userRelationShip2.setUserId(frienduuid);
        HibernateUtil.add(userRelationShip2);
        //  getSessionAndCommit(userRelationShip2);
        System.out.println("好友关系表关系建成");
    }

    public void storeMsg(String selfuuid, String frienduuid) {

        Message message = new Message(selfuuid, frienduuid, "hello你大爷", "", "", "", Constant.item_text_send, 0, "", "", "", "", "");
        HibernateUtil.add(message);
        // getSessionAndCommit(message);
        Message message2 = new Message(frienduuid, selfuuid, "hello你大爷", "", "", "", Constant.item_text_send, 0, "", "", "", "", "");
        HibernateUtil.add(message2);
        //  getSessionAndCommit(message2);
    }


    public void storeContract(String selfuuid, String frienduuid) {
        PojoContract pojoContract = new PojoContract(selfuuid, frienduuid, unReadAddFriendRequest.getPortraitImageViewnetPath(),
                unReadAddFriendRequest.getContentTextView(),"" ,
                unReadAddFriendRequest.getSex(), unReadAddFriendRequest.getReceiverId(), State.TAG_ITEM, "");
        // getSessionAndCommit(pojoContract);
        HibernateUtil.add(pojoContract);
        PojoContract pojoContract2 = new PojoContract(frienduuid, selfuuid, unReadAddFriendRequest.getPortraitImageViewnetPath(),
                unReadAddFriendRequest.getNameTextView()  ,"" ,
                unReadAddFriendRequest.getSex(), unReadAddFriendRequest.getReceiverId(), State.TAG_ITEM, "");
        // getSessionAndCommit(pojoContract2);
        HibernateUtil.add(pojoContract2);
    }

    public void storeUserRecementMsg(String selfuuid, String frienduuid) {
        RecementMsg recementMsg = new RecementMsg(selfuuid, frienduuid, unReadAddFriendRequest.getPortraitImageViewnetPath(),
                "", unReadAddFriendRequest.getNameTextView(), unReadAddFriendRequest.getReceiverTime(), "", "", "", "");

        //  getSessionAndCommit(recementMsg);
        HibernateUtil.add(recementMsg);


        RecementMsg recementMsg2 = new RecementMsg(frienduuid, selfuuid, unReadAddFriendRequest.getPortraitImageViewnetPath(),
                "", unReadAddFriendRequest.getNameTextView(), unReadAddFriendRequest.getReceiverTime(), "", "", "", "");
        HibernateUtil.add(recementMsg2);
        // getSessionAndCommit(recementMsg2);
    }
}
