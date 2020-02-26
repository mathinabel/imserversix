package com.quyuanjin.imserver.handler.detailhandler;

import com.google.gson.Gson;
import com.quyuanjin.imserver.constant.ProtoConst;
import com.quyuanjin.imserver.constant.State;
import com.quyuanjin.imserver.handler.detailhandler.robot.RobotApache;
import com.quyuanjin.imserver.handler.session.HibernateUtil;
import com.quyuanjin.imserver.pojo.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSessionAndCommit;

@Slf4j
public class SendMsg {
    public SendMsg(String str, HashMap hashMap, ChannelGroup channelGroup, ChannelHandlerContext channelHandlerContext) throws IOException {

        //hashmap存储着<各个user的 uid，各个channel的id>
        //从保存在hashmap里的长连接uuid找到所要发送目标的长连接的uuid
        //通过该uuid（key）找到保存在hashmap里的channel
        //通过该目标uuid的channel将数据构建发送

        //构建发送数据
        Gson gson = new Gson();
        Message message = gson.fromJson(str, Message.class);

        if (message.getReceiveId().equals("1")) {
            //这是和机器人在聊天
            new RobotApache(message, hashMap, channelHandlerContext);
        } else {


            //如果hashmap存有接收者id,给发过去
            if (hashMap.containsKey(message.getReceiveId())) {

                ChannelId channelid = (ChannelId) hashMap.get(message.getReceiveId());
                Gson gson2 = new Gson();

                String backmsg = ProtoConst.SEND_MESSAGE_BACK + "|" + gson2.toJson(message)
                        + "\r\n";


                log.info("发送消息内容为：" + backmsg);
                channelGroup.find(channelid).writeAndFlush(backmsg);

                //该用户在线，进行数据库保存操作,状态为未读，但是已经发送
//这里只管有没有发送，读不读无所谓。客户端来获取时，获取的是 未发送的消息

                message.setReadState(State.UN_READ);
                message.setSendState(State.SEND);
                Date date2 = new Date();
                message.setReceiverTime(date2.toString());
                //getSessionAndCommit(message);
                HibernateUtil.add(message);
            } else {

                //该用户不在线，进行数据库保存操作,状态为未读,状态为未发送


                message.setReadState(State.UN_READ);
                message.setSendState(State.UN_SEND);
                Date date3 = new Date();
                message.setReceiverTime(date3.toString());
                //getSessionAndCommit(message);
                HibernateUtil.add(message);
                log.info("hashmap中没有该key!");
                for (int i = 0; i < hashMap.size(); i++) {
                    log.info("hashmap当前有key：" + hashMap.get(i));
                }
                log.info("在数据库中保存未读消息");
            }
        }
    }
}
