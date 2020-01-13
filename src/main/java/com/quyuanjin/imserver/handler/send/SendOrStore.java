package com.quyuanjin.imserver.handler.send;

import com.quyuanjin.imserver.constant.ProtoConst;
import com.quyuanjin.imserver.pojo.UnReadAddFriendRequest;
import com.quyuanjin.imserver.pojo.UnReadAddGroupRequest;
import com.quyuanjin.imserver.pojo.UserRelationShip;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;

import java.util.HashMap;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSessionAndCommit;


public class SendOrStore {
    private HashMap hashMap;
    private String[] string;
    private ChannelGroup channelGroup;

    public SendOrStore(HashMap hashMap, String[] string, ChannelGroup channelGroup) {
        this.hashMap = hashMap;
        this.string = string;
        this.channelGroup = channelGroup;
    }

    public void send(String uuid) {
        ChannelId channelid = (ChannelId) hashMap.get(uuid);
        String backmsg = ProtoConst.ADD_FRIEND_BACK + "|" + string[1] + "|" + uuid + "\r\n";
        channelGroup.find(channelid).writeAndFlush(backmsg);
        System.out.println("addfriends回送内容为" + backmsg);
    }

    /**
     * 存储未加的好友请求信息
     *
     * @param uuid
     */
    public void storeURAD(String uuid,int state,int sendState) {

        //进行数据库保存加好友请求
        UnReadAddFriendRequest unReadAddFriendRequest = new UnReadAddFriendRequest();
        unReadAddFriendRequest.setReceiverId(uuid);
        unReadAddFriendRequest.setSenderId(string[1]);
        unReadAddFriendRequest.setState(state);
        unReadAddFriendRequest.setSendState(sendState);
        getSessionAndCommit(unReadAddFriendRequest);

        System.out.println("该用户不在线，进行数据库保存加好友请求");
    }

    /**
     * 存储未加的好友请求信息
     *
     * @param uuid
     */
    public void storeURAG(String uuid) {
        UnReadAddGroupRequest unReadAddGroupRequest = new UnReadAddGroupRequest();
        unReadAddGroupRequest.setReceiverId(uuid);
        unReadAddGroupRequest.setSenderId(string[1]);
        getSessionAndCommit(unReadAddGroupRequest);

        System.out.println("进行数据库保存加群请求");
    }

    public void storeUserRelationShip(String selfuuid,String frienduuid) {
        UserRelationShip userRelationShip=new UserRelationShip();
        userRelationShip.setUserFriends(frienduuid);
        userRelationShip.setUserId(selfuuid);
        getSessionAndCommit(userRelationShip);

        System.out.println("好友关系表关系建成");
    }

}
