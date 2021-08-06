package com.lhj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * netty 服务端
 *
 * @author: liuhj
 * @date: 2021/8/4 14:29
 */
@SpringBootApplication
public class NettyServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class,args);
    }
}
