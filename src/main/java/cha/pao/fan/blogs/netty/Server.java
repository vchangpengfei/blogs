package cha.pao.fan.blogs.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

public class Server {
    public static void main(String args[])
    {

        EventLoopGroup bossgroup=new NioEventLoopGroup(1);
        EventLoopGroup workergroup=new NioEventLoopGroup();

        try{
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossgroup,workergroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childAttr(AttributeKey.newInstance("childAttr"),"test")
                    .handler(new ServerHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //
                        }
                    });

            ChannelFuture f=b.bind(8080).sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
