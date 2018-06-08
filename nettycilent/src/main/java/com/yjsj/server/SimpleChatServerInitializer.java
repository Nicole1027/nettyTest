package com.yjsj.server;


import com.yjsj.util.ByteToMessage;
import com.yjsj.util.MessageToByte;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;

/**
 * 服务端 ChannelInitializer
 * 
 * @author waylau.com
 * @date 2015-2-26
 */
public class SimpleChatServerInitializer extends
        ChannelInitializer<SocketChannel> {

	@Override
    public void initChannel(SocketChannel ch) throws Exception {
		 ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("decoder", new ByteToMessage());
        pipeline.addLast("encoder", new MessageToByte());
        pipeline.addLast("handler", new SimpleChatServerHandler());
 
		System.out.println("SimpleChatClient:"+ch.remoteAddress() +"连接上");
    }

}
