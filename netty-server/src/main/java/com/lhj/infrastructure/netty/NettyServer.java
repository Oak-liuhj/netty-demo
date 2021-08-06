package com.lhj.infrastructure.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * netty 服务端
 *
 * @author: liuhj
 * @date: 2021/8/4 16:28
 */
@Slf4j
public class NettyServer {

    private String host;
    private int port;

    public NettyServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(host, port)
                    .childHandler(new ChannelInitializer<Channel>() {

                        //实现抽象类ChannelInitializer的initChannel方法来设置ChannelHandler
                        protected void initChannel(Channel ch) {
                            //解码器将消息从字节码转成Java对象
                            ch.pipeline().addLast(new StringDecoder())
                                    //编码器将Java对象转成字节码
                                    .addLast(new StringEncoder())
                                    //添加一个ChannelHandler，客户端成功连接服务器后就会被执行
                                    .addLast(new NettyServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();

            log.info(NettyServer.class.getName() + " started and listen on " + future.channel().localAddress());

            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyServer("127.0.0.1", 20000).start();
    }

}
