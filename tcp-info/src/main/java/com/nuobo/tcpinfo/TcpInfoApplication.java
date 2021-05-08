package com.nuobo.tcpinfo;

import com.nuobo.tcpinfo.server.NettyServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.InetSocketAddress;

@SpringBootApplication
public class TcpInfoApplication {

    @Value("${websocket.ip}")
    private String ip;

    @Value("${websocket.port}")
    private int port;

    //启动Netty服务端
    @Bean
    public void serverRun(){
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(new InetSocketAddress(ip, port));
    }

    public static void main(String[] args) {
        SpringApplication.run(TcpInfoApplication.class, args);
    }
}
