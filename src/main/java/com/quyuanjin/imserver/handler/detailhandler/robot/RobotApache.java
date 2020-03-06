package com.quyuanjin.imserver.handler.detailhandler.robot;

import com.google.gson.Gson;
import com.quyuanjin.imserver.constant.Constant;
import com.quyuanjin.imserver.constant.ProtoConst;
import com.quyuanjin.imserver.constant.State;
import com.quyuanjin.imserver.pojo.Message;
import com.quyuanjin.imserver.test.Sheet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

import java.io.IOException;
import java.util.HashMap;

public class RobotApache {

    public RobotApache(Message message, HashMap hashMap, ChannelHandlerContext channelHandlerContext) throws IOException {
       ;

        Message message1=new Message("1",message.getSendID(),
                Sheet.autoAnswer(message.getMsg()),"","","http://120.79.178.226:8080/File/portrait/upload/666.png", Constant.item_text_send,0,
        "","","","","");
        Gson gson2 = new Gson();
        String backmsg = ProtoConst.SEND_MESSAGE_BACK + "|" + gson2.toJson(message1)
                + "\r\n";

        channelHandlerContext.writeAndFlush(backmsg);
    }
}
