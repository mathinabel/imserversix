package com.quyuanjin.imserver.handler.detailhandler;

import com.google.gson.Gson;
import com.quyuanjin.imserver.constant.ProtoConst;
import com.quyuanjin.imserver.constant.State;
import com.quyuanjin.imserver.handler.onlinejudge.OnLineJudge;
import com.quyuanjin.imserver.handler.send.SendOrStore;
import com.quyuanjin.imserver.pojo.UnReadAddFriendRequest;
import com.quyuanjin.imserver.pojo.User;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSession;

@Slf4j
public class AddFriendOrGroup {
    public AddFriendOrGroup(String str, HashMap hashMap, ChannelGroup channelGroup) {
//加好友，解析json
        Gson gson = new Gson();
        UnReadAddFriendRequest unReadAddFriendRequest = gson.fromJson(str, UnReadAddFriendRequest.class);
        //判断接收者在线与否
        OnLineJudge onLineJudge1 = new OnLineJudge(hashMap);
        String userid = onLineJudge1.judegOnLineWithUserIdFromUser(unReadAddFriendRequest.getReceiverId());

        SendOrStore sendOrStore1 = new SendOrStore(hashMap, unReadAddFriendRequest, channelGroup);
        if (!userid.equals("")) {
            //不为空表示该用户在线
            //在线则给其发过去并存储进mysql，同意状态未读,接收状态为已发送
            //数据库里面加一个字段。用来标注是是否是 主动添加方还是被动添加方，
            //添加是同时插入两条，一条是 selfuid +targetuid 同时标明发送者还是被添加者，另一条相反

            sendOrStore1.send(userid);
            unReadAddFriendRequest.setSendType(State.SEND);
            sendOrStore1.storeURADAddFriendNew(unReadAddFriendRequest);
        } else {
            //该用户不在线，存储进mysql，接收状态为未发送
            unReadAddFriendRequest.setSendType(State.UN_SEND);

            sendOrStore1.storeURADAddFriendNew(unReadAddFriendRequest);
        }
    }
}

