package cha.pao.fan.blogs.netty.sse;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import io.netty.util.ResourceLeakDetector;

public class TimeServer {
	public void bind(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
//		OioEventLoopGroup bossGroup = new OioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);
//        OioEventLoopGroup workerGroup = new OioEventLoopGroup();
        try {
            // 配置服务器的NIO线程租
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
//                    .channel(OioServerSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_BACKLOG, 1024)
//                    .handler(handler)//服务端handler
                    .childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(32*1024*1024, 64*1024*1024))
//                    .option(ChannelOption.WRITE_BUFFER_WATER_MARK,  WriteBufferWaterMark.DEFAULT)
            .childHandler(new ChildChannelHandler());

            // 绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
            
            f.channel().closeFuture().addListener(new ChannelFutureListener() {
				
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					bossGroup.shutdownGracefully();
		            workerGroup.shutdownGracefully();
				}
			});
        } finally {
            // 优雅退出，释放线程池资源
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel arg0) throws Exception {
            System.out.println("server initChannel..");
            arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
            arg0.pipeline().addLast(new StringDecoder());
            arg0.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws Exception {
    	
//    	ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
    	
        int port = 9000;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }

        new TimeServer().bind(port);
    }
}