package com.quyuanjin.imserver.handler.detailhandler;

import com.quyuanjin.imserver.constant.ProtoConst;
import com.quyuanjin.imserver.pojo.UnReadAddFriendRequest;
import io.netty.channel.ChannelHandlerContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSession;


public class PullUnreadAddFriend {
    public PullUnreadAddFriend(String[] str, ChannelHandlerContext channelHandlerContext) {
        //head          uuid
        //str[0]        str[1]

        String selfuuId = str[1];
        String addFriendPacket = ProtoConst.PULL_UNREAD_ADDFRIEND_BACK + "|";
        if (!("".equals(selfuuId))) {
            //从数据库获取未读加好友信息
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from UnReadAddFriendRequest unReadAddFriendRequest");
            if (q.list().size() > 0) {
                List<UnReadAddFriendRequest> unreadList = q.list();
                for (int i = 0; i < unreadList.size(); i++) {
                    //如果客户端发送来的uuid与数据库接收者uuid相同，则将信息组包发送
                    if (unreadList.get(i).getReceiverId().equals(selfuuId)) {
                        //组包  将意向加好友的id统统发给客户端
                        addFriendPacket += (unreadList.get(i).getSenderId()  + "|");


                    }

                }
                String backmsgpack = addFriendPacket.substring(0, addFriendPacket.length() - 1);
                backmsgpack += "\r\n";
                System.out.println(backmsgpack);
                channelHandlerContext.channel().writeAndFlush(backmsgpack);
            }
            tx.commit();
            session.close();

        }




        //拉取完成之后将未读消息删除
    }
}
