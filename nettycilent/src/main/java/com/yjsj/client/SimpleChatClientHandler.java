package com.yjsj.client;

import com.yjsj.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 客户端 channel
 * 
 * @author waylau.com
 * @date 2015-2-26
 */
public class SimpleChatClientHandler extends SimpleChannelInboundHandler<Message> {
	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, Message s) throws Exception {
		System.out.println(s.getUser()+s.getMessageStr());
	}
}
