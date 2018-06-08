package com.yjsj.client;

import com.yjsj.util.ByteToMessage;
import com.yjsj.util.MessageToByte;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;


/**
 * 客户端 ChannelInitializer
 * 
 * @author waylau.com
 * @date 2015-2-26
 */
public class SimpleChatClientInitializer extends ChannelInitializer<SocketChannel> {

    private final SimpleChatClient client;


    public SimpleChatClientInitializer(SimpleChatClient client) {
        this.client = client;
    }

	@Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        

        pipeline.addLast("decoder", new ByteToMessage());
        pipeline.addLast("encoder", new MessageToByte());

        pipeline.addLast("handler", new SimpleChatClientHandler());
    }
}
