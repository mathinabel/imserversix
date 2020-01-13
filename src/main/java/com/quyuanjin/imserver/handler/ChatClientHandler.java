package com.quyuanjin.imserver.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if (!msg.contains("HTTP/1.1") && !msg.contains("Host:") && !msg.contains("Proxy-Connection: keep-alive") && !msg.contains("User-Agent:")){
            System.out.println(msg);
        }
    }
}