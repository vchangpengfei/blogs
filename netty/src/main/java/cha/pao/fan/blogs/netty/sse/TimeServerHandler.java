package cha.pao.fan.blogs.netty.sse;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.AbstractNioChannel;

public class TimeServerHandler extends SimpleChannelInboundHandler {

	private static int count = 0;

	private String sql;

	public TimeServerHandler() {

	}

	private boolean isWritable(ChannelHandlerContext ctx) {
		while (true) {
			if (ctx.channel().isWritable()) {
				return true;
			} else {
				System.out.println("-------------刷新前" + ctx.channel().isWritable());
				((AbstractNioChannel) ctx.channel()).unsafe().forceFlush();
				System.out.println("-------------刷新后" + ctx.channel().isWritable());
			}
//			try {
//				Thread.sleep(5);
//			} catch (InterruptedException e) {
//			}
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		byte[] reqs = ("end" + System.getProperty("line.separator")).getBytes();
		ByteBuf firstMessage = Unpooled.buffer(reqs.length);
		firstMessage.writeBytes(reqs);
		ctx.writeAndFlush(firstMessage);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("server exceptionCaught..");
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 与服务端建立连接后
		System.out.println("client channelActive..");
		sql = (String) msg;
		System.out.println("sql.." + sql);

		int c = 1000;
		ByteBuf firstMessage = null;
		int index = 0;
		for (int i = 0; i < 20000000; i++) {
			index++;
			byte[] req = ("QUERY TIME ORDER" + i + System.getProperty("line.separator")).getBytes();
			if (firstMessage == null) {
				firstMessage = Unpooled.buffer(req.length);
			}
			firstMessage.writeBytes(req);

			if (index % c == 0) {
				if (isWritable(ctx)) {
					ctx.writeAndFlush(firstMessage);
				}

				index = 0;
				firstMessage = null;
			}
			System.out.println("=============================server:" + count++);
		}

		if (firstMessage != null) {
//			if (isWritable(ctx))
				ctx.writeAndFlush(firstMessage);
		}

		byte[] reqs = ("end" + System.getProperty("line.separator")).getBytes();
		firstMessage = Unpooled.buffer(reqs.length);
		firstMessage.writeBytes(reqs);
//		if (isWritable(ctx))
			ctx.writeAndFlush(firstMessage);
	}

	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 与服务端建立连接后
		System.out.println("client channelActive..");
		sql = (String) msg;
		System.out.println("sql.." + sql);

		int c = 1000;
		ByteBuf firstMessage = null;
		int index = 0;
		for (int i = 0; i < 20000000; i++) {
			index++;
			byte[] req = ("QUERY TIME ORDER" + i + System.getProperty("line.separator")).getBytes();
			if (firstMessage == null) {
				firstMessage = Unpooled.buffer(req.length);
			}
			firstMessage.writeBytes(req);

			if (index % c == 0) {
//				if (isWritable(ctx)) {
					ctx.writeAndFlush(firstMessage);
//				}

				index = 0;
				firstMessage = null;
			}
			System.out.println("=============================server:" + count++);
		}

		if (firstMessage != null) {
//			if (isWritable(ctx))
				ctx.writeAndFlush(firstMessage);
		}

		byte[] reqs = ("end" + System.getProperty("line.separator")).getBytes();
		firstMessage = Unpooled.buffer(reqs.length);
		firstMessage.writeBytes(reqs);
//		if (isWritable(ctx))
			ctx.writeAndFlush(firstMessage);
	}
	
}