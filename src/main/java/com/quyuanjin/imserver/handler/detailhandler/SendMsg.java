package com.quyuanjin.imserver.handler.detailhandler;

import com.quyuanjin.imserver.constant.ProtoConst;
import com.quyuanjin.imserver.constant.State;
import com.quyuanjin.imserver.pojo.UnReadMessage;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSessionAndCommit;

@Slf4j
public class SendMsg {
    public SendMsg(String[] str, HashMap hashMap, ChannelGroup channelGroup) {
/*
    消息协议：str0：协议   str1：selfuuid    str2：touuid     str3：contentType    str4：sendTime

     str5：receivedTime      str6：content    str7：netPath   str8:recorderTime
 */
        //hashmap存储着<各个user的selfuuid，各个channel的id>

        //从保存在hashmap里的长连接uuid找到所要发送目标的长连接的uuid
        //通过该uuid（key）找到保存在hashmap里的channel
        //通过该目标uuid的channel将数据构建发送
        //构建发送数据

        String fromuuid = str[1];
        String touuid = str[2];
        String contentType = str[3];
        Date date = new Date();
        String sendTime = date.toString();
        String receivedTime = str[5];
        String content = str[6];
        String netPath = str[7];
        String recorderTime = str[8];
        System.out.println(fromuuid);
        //如果hashmap存有接收者id，即str2
        if (hashMap.containsKey(touuid)) {

            ChannelId channelid = (ChannelId) hashMap.get(touuid);

            String backmsg = ProtoConst.SEND_MESSAGE_BACK + "|" +
                    fromuuid + "|" +
                    touuid + "|" +
                    contentType + "|" +
                    sendTime + "|" +
                    receivedTime + "|" +
                    content + "|" +
                    netPath + "|" +
                    recorderTime + "|" +
                    "\r\n";


            log.info("发送消息内容为：" + backmsg);
            channelGroup.find(channelid).writeAndFlush(backmsg);

            //该用户在线，进行数据库保存操作,状态为未读，但是已经发送
            UnReadMessage unReadMessage = new UnReadMessage();
            unReadMessage.setSenderId(fromuuid);
            unReadMessage.setReceiverId(touuid);
            unReadMessage.setMsg(content);
            unReadMessage.setContentType(contentType);
            unReadMessage.setRecorderTime(Integer.valueOf(recorderTime));
            unReadMessage.setRecorderTime(Integer.valueOf(recorderTime));
            unReadMessage.setReadState(State.UN_READ);
            unReadMessage.setSendState(State.SEND);
            Date date2 = new Date();
            unReadMessage.setSendTime(date2.toString());
            getSessionAndCommit(unReadMessage);

        } else {

            //该用户不在线，进行数据库保存操作,状态为未读,状态为未发送
            UnReadMessage unReadMessage = new UnReadMessage();
            unReadMessage.setSenderId(fromuuid);
            unReadMessage.setReceiverId(touuid);
            unReadMessage.setMsg(content);
            unReadMessage.setContentType(contentType);
            unReadMessage.setRecorderTime(Float.valueOf(recorderTime));
            unReadMessage.setReadState(State.UN_READ);
            unReadMessage.setSendState(State.UN_SEND);
            Date date3 = new Date();
            unReadMessage.setSendTime(date3.toString());
            getSessionAndCommit(unReadMessage);

            log.info("hashmap中没有该key!");
            for (int i = 0; i < hashMap.size(); i++) {
                log.info("hashmap当前有key：" + hashMap.get(i));
            }
            log.info("在数据库中保存未读消息");
        }

    }
}
