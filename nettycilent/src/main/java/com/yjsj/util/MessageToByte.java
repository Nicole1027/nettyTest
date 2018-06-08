package com.yjsj.util;

import com.yjsj.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageToByte extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(final ChannelHandlerContext channelHandlerContext, final Message message, final ByteBuf byteBuf) throws Exception {
        if(null==message){
            throw new Exception("RpcMessage is null");
        }
        if (message.getClass().isAssignableFrom(Message.class)) {
            byte[] user = message.getUser().getBytes("utf-8");
            byte[] messageStr = message.getMessageStr().getBytes("utf-8");

            byteBuf.writeInt(user.length);
            byteBuf.writeInt(messageStr.length);
            byteBuf.writeBytes(user);
            byteBuf.writeBytes(messageStr);
        }
    }
}
