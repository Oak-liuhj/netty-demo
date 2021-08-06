package com.lhj.domain.listener;

import com.lhj.infrastructure.netty.NettyServer;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @author: liuhj
 * @date: 2021/8/6 17:50
 */
@Component
public class NettyServerApplicationListener implements ApplicationListener, ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        try {
            NettyServer nettyServer = new NettyServer("127.0.0.1", 8083);
            if (applicationEvent instanceof ApplicationReadyEvent) {
                nettyServer.start();
            }

            if (applicationEvent instanceof ContextClosedEvent) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
