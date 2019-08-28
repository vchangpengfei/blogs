package cha.pao.fan.blogs.netty.sse;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class TimeClientHandler extends ChannelInboundHandlerAdapter{

    
    private int count;
    
    public TimeClientHandler() {}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //与服务端建立连接后
    	byte[] sql=("select * from hotel"+System.getProperty("line.separator")).getBytes();
    	ByteBuf firstMessage = Unpooled.buffer(sql.length);
        firstMessage.writeBytes(sql);
        ctx.writeAndFlush(firstMessage);
    }

    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
//        System.out.println("client channelRead..");
    	try{
    		String body = (String)msg;
            System.out.println("The time server receive order:" + body+" 	"+count++);
            
            if("end".equals(msg)){
            	ctx.close();
            }
    	}finally {
			ReferenceCountUtil.release(msg);
		}
        
    }
    
    

    @Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("client exceptionCaught..");
        // 释放资源
        ctx.close();
    }
	
	
	

}
