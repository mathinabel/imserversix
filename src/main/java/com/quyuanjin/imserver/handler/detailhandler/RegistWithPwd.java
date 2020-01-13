package com.quyuanjin.imserver.handler.detailhandler;

import com.quyuanjin.imserver.constant.ProtoConst;
import com.quyuanjin.imserver.pojo.User;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.UUID;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSessionAndCommit;


public class RegistWithPwd {
    public RegistWithPwd(String[] str, HashMap hashMap, ChannelHandlerContext channelHandlerContext) {
        String uuid = UUID.randomUUID().toString();
        //TODO 执行保存数据库操作
        User user = new User();
        user.setName(str[1]);
        user.setPwd(str[2]);
        user.setPhone(str[3]);
        user.setToken(uuid);
        getSessionAndCommit(user);


        hashMap.put(uuid, channelHandlerContext.channel().id());
        channelHandlerContext.channel().writeAndFlush(ProtoConst.REGISTER_WITH_PWD_BACK + "|" + uuid + "\r\n");
    }
}
