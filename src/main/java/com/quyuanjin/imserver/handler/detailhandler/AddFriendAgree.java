package com.quyuanjin.imserver.handler.detailhandler;

import com.google.gson.Gson;
import com.quyuanjin.imserver.constant.ProtoConst;
import com.quyuanjin.imserver.constant.State;
import com.quyuanjin.imserver.handler.onlinejudge.OnLineJudge;
import com.quyuanjin.imserver.handler.send.SendOrStore;
import com.quyuanjin.imserver.pojo.UnReadAddFriendRequest;
import io.netty.channel.group.ChannelGroup;

import java.util.HashMap;

public class AddFriendAgree {
    public AddFriendAgree(String str, HashMap hashMap, ChannelGroup channelGroup) {

        //解析json
        Gson gson = new Gson();
        UnReadAddFriendRequest unReadAddFriendRequest = gson.fromJson(str, UnReadAddFriendRequest.class);
        //判断接收者在线与否
        OnLineJudge onLineJudge1 = new OnLineJudge(hashMap);
        String userid = onLineJudge1.judegOnLineWithUserIdFromUser(unReadAddFriendRequest.getReceiverId());

        SendOrStore sendOrStore1 = new SendOrStore(hashMap, unReadAddFriendRequest, channelGroup);



        if (!userid.equals("")) {
            //不为空表示该用户在线
            //在线则给其发过去并存储进mysql，同意状态tongyi,接收状态为已发送
            unReadAddFriendRequest.setReadType(State.addFriend_answer_agree);


            System.out.println("加好友fuifu请求，该用户在线，是"+userid);
            //这里不应该是直接insert一条新的，而是更改相对应的条目的信息
            sendOrStore1.storeURAD2(unReadAddFriendRequest);


            //还需要进行用户关系表存储  消息表存储 联系人表 和 最近联系人表存储
            System.out.println("加                        是"+unReadAddFriendRequest.getUserid()+unReadAddFriendRequest.getReceiverId());
            sendOrStore1.storeUserRelationShip(unReadAddFriendRequest.getUserid(), unReadAddFriendRequest.getReceiverId());
            sendOrStore1.storeUserRecementMsg(unReadAddFriendRequest.getUserid(), unReadAddFriendRequest.getReceiverId());
            sendOrStore1.storeMsg(unReadAddFriendRequest.getUserid(), unReadAddFriendRequest.getReceiverId());
            sendOrStore1.storeContract(unReadAddFriendRequest.getUserid(),unReadAddFriendRequest.getReceiverId());

            //双方都通知去数据库来拿
            sendOrStore1.sendAddFriendAgree2(userid,unReadAddFriendRequest.getUserid());
            unReadAddFriendRequest.setSendType(State.SEND);


        } else {
            //该用户不在线，存储进mysql，接收状态为未发送,但是同意了
            unReadAddFriendRequest.setSendType(State.UN_SEND);
            unReadAddFriendRequest.setReadType(State.addFriend_answer_agree);
            System.out.println("加好友fuifu请求，该用户不在线");

            sendOrStore1.storeURAD2(unReadAddFriendRequest);
        }
    }
}
