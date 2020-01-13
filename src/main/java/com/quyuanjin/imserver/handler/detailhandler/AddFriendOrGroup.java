package com.quyuanjin.imserver.handler.detailhandler;

import com.quyuanjin.imserver.constant.State;
import com.quyuanjin.imserver.handler.onlinejudge.OnLineJudge;
import com.quyuanjin.imserver.handler.send.SendOrStore;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Query;

import java.util.HashMap;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSession;

@Slf4j
public class AddFriendOrGroup {
    public AddFriendOrGroup(String[] str, HashMap hashMap, ChannelGroup channelGroup) {
        log.info("收到号码为" + str[2]);

//head      fromuuid        phonenumber     加群还是加好友             msg(申请信息)
//                                         （0：好友，1：qun）
//str[0]    str[1]          str[2]            str[3]                    str[4]
        String qunOrhaoyou = str[3];
        if (qunOrhaoyou.equals("0")) {//加好友
            String s = str[2];
            //  String hql2 = "from User where phone =" + str[2];
            String hql2 = "from User where phone =" + s;
            Query query2 = getSession().createQuery(hql2);

            OnLineJudge onLineJudge = new OnLineJudge(hashMap, str[2]);

            String uuid = onLineJudge.judegOnLineWithPhoneFromUser();

            SendOrStore sendOrStore = null;
            if (!uuid.equals("")) {
                //不为空表示uuid存在于hashmap中，即该用户在线
                //在线则给其发回去并存储进mysql，同意状态未读,接收状态为已发送


                sendOrStore = new SendOrStore(hashMap, str, channelGroup);
                sendOrStore.send(uuid);
                sendOrStore.storeURAD(str[1], State.UN_READ, State.SEND);
            } else {
                //该用户不在线，存储进mysql，接收状态为未发送
                sendOrStore.storeURAD(str[1], State.UN_READ,State.UN_SEND);
            }

            /* List<> list2 = query2.list();
            User user1 = list2.get(0);
            String touuid = user1.getToken();
            System.out.println("收到号码查询到的uuid为" + touuid);
            OnLineJudge onLineJudge = new OnLineJudge(channelGroup, hashMap, str);
            onLineJudge.judegOnLine();
        } else if (qunOrhaoyou.equals("1")) {//加群
            //查询群*/

            //判断在线与否 选择发送
        }
    }
}
