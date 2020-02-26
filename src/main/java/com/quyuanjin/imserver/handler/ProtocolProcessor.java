package com.quyuanjin.imserver.handler;

import com.quyuanjin.imserver.constant.ProtoConst;
import com.quyuanjin.imserver.handler.detailhandler.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

import java.io.IOException;
import java.util.HashMap;

public class ProtocolProcessor {
    private ChannelHandlerContext channelHandlerContext;
    private String s;
    private ChannelGroup channelGroup;
    private HashMap hashMap;


    public ProtocolProcessor(ChannelHandlerContext channelHandlerContext, String s, ChannelGroup channelGroup, HashMap hashMap) {
        this.channelHandlerContext = channelHandlerContext;
        this.s = s;
        this.channelGroup = channelGroup;
        this.hashMap = hashMap;
    }

    /*
     消息协议：str0：协议   str1：selfuuid    str2：touuid     str3：contentType    str4：sendTime

     str5：receivedTime      str6：netPath       str7：recorderTime
     */
    public void startProcess() throws IOException {
        String[] str = splitSWithI(s);

        switch (str[0]) {

            case ProtoConst.LONG_CONNECT:
                new LongConnect(str[1], hashMap, channelHandlerContext);
                break;
            case ProtoConst.SEND_MESSAGE:
                new SendMsg(str[1], hashMap, channelGroup, channelHandlerContext);
                break;
            case ProtoConst.ADD_FRIEND_OR_GROUP:
                new AddFriendOrGroup(str[1], hashMap, channelGroup);
                break;
            case ProtoConst.ADD_FRIEND_AGREE:

                new AddFriendAgree(str[1], hashMap, channelGroup);
                break;

            default:
                break;
        }
    }

    private String[] splitSWithI(String s) {
        return s.split("\\|");
    }


}
