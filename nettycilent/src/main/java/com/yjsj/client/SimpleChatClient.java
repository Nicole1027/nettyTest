package com.yjsj.client;

import com.yjsj.model.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 简单聊天服务器-客户端
 *
 * @author Nicole
 * @date 2018-6-1
 */
public class SimpleChatClient implements ActionListener {

    public static void main(String[] args) throws Exception{
        new SimpleChatClient("localhost", 8080).run();
    }

    private final String host;
    private final int port;

    private final JTextArea inputText;
    private final JTextArea outputText;
    private Channel channel;

    public SimpleChatClient(String host, int port){
        this.host = host;
        this.port = port;

        JFrame frame = new JFrame();
        inputText = new JTextArea(10, 25);
        outputText = new JTextArea(15, 25);

        JButton button = new JButton("发送");
        button.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(inputText);
        panel.setBackground(Color.lightGray);

        frame.getContentPane().add(BorderLayout.NORTH, outputText);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);


    }

    public void run() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap  = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SimpleChatClientInitializer(this));
            channel = bootstrap.connect(host, port).sync().channel();

            final ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

    public void actionPerformed(ActionEvent e) {
        Message msg = new Message();
        msg.setUser();
        msg.setMessageStr(inputText.getText());

        if (inputText.getText().length() != 0) {
            channel.writeAndFlush(msg);
        }
        inputText.setText("");
    }
}
