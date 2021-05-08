package com.nuobo.tcpinfo.server;

import com.nuobo.tcpinfo.config.HL7ParseUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: tcp-info
 * @description: netty服务处理器
 * @author: 袁彪
 * @create: 2021-05-06 13:46
 **/
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 功能描述: 客户端连接成功触发
     * @param: [ctx]
     * @return: void
     * @auther: 袁彪
     * @date: 2021/5/6 16:31
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端已连接，IP为{}",ctx.channel().remoteAddress());
    }

    /**
     * 功能描述: 获取客户端内容
     * @param: [ctx, msg]
     * @return: void
     * @auther: 袁彪
     * @date: 2021/5/6 16:29
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        log.info("服务器收到消息: {}", msg.toString());
        /**
         * 根据客户端内容处理业务
         */
        HL7ParseUtil.receiveAndPushData(msg.toString());
        /**
         * 回传结果给客户端
         */
        ctx.writeAndFlush("你好，客户端！！！");
    }

    /**
     * 功能描述: 异常判断
     * @param: [ctx, cause]
     * @return: void
     * @auther: 袁彪
     * @date: 2021/5/6 16:31
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.error("服务器异常：{}",cause.toString());
        ctx.close();
    }
}
