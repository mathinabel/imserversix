package com.quyuanjin.imserver.handler.detailhandler;

import com.quyuanjin.imserver.constant.ProtoConst;
import io.netty.channel.ChannelHandlerContext;

public class PullUnreadMsg {
    public PullUnreadMsg(ChannelHandlerContext channelHandlerContext) {
        //head          uuid
        //str[0]        str[1]

        String string = "";


        System.out.println(string);
        String pullback = ProtoConst.PULL_UNREAD_MESSAGE_BACK + "|" + string;
        channelHandlerContext.channel().writeAndFlush(pullback);


        //拉取完成之后将未读消息删除

    }
}
