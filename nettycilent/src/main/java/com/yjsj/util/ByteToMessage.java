package com.yjsj.util;

import com.yjsj.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ByteToMessage extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes() < (Integer.SIZE / Byte.SIZE) * 4){
            return;
        }
        byteBuf.markReaderIndex();
        final int userLen = byteBuf.readInt();
        final int messageStrLen = byteBuf.readInt();

        if(byteBuf.readableBytes() < (userLen + messageStrLen)){
            byteBuf.resetReaderIndex();
            return;
        }
        final byte[] user = new byte[userLen];
        byteBuf.readBytes(user);
        String userStr = new String(user,"utf-8");

        final byte[] message = new byte[messageStrLen];
        byteBuf.readBytes(message);
        String messageStr = new String(message,"utf-8");

        Message messageObj = new Message();
        messageObj.setUser(userStr).setMessageStr(messageStr);
        list.add(messageObj);
    }

}
