package com.lhj.infrastructure.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: liuhj
 * @date: 2021/8/6 15:14
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 捕获服务器的异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Server received: " + msg);
        ctx.writeAndFlush(msg);
    }
}
