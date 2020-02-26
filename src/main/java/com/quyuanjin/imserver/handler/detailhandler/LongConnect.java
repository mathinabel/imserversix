package com.quyuanjin.imserver.handler.detailhandler;

import com.quyuanjin.imserver.constant.ProtoConst;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class LongConnect {


    public LongConnect(String str, HashMap hashMap, ChannelHandlerContext channelHandlerContext) {
        String userid = str;
        //uuid 为key  ctx.channel 为value
        hashMap.put(userid, channelHandlerContext.channel().id());

        channelHandlerContext.channel().writeAndFlush(ProtoConst.LONG_CONNECT_BACK + "\r\n");
        log.info("新连接建立，hashmap。put：key是" + str + "  value是：" + channelHandlerContext.channel());
        log.info("hashmap:" + hashMap.get(str));
        log.info("hashmap当前连接个数为：" + hashMap.size());
    }

}
