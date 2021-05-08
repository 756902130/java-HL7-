package com.nuobo.tcpinfo.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: tcp-info
 * @description:  客户端处理器
 * @author: 袁彪
 * @create: 2021-05-06 14:13
 **/
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接服务器成功");
        String myHL7string="MSH|^~\\&|HIS|MediInfo|MediII|MediInfo|20150119115703||SRM^S01^SRM_S01|dfb2af425dfb4f3ab146a4508c229db1|P|2.4\n" +
                "ARQ|1000152209||||20005^全麻|1|Surgery^手术|2^择期手术|||20150120084444^20150119115022|0|0||292|||10107|292||10035\n" +
                "NTE|||~膀胱肿瘤等离子电切\n" +
                "PID||661508|661508~661508~~~|0|ZhouShouSheng^周寿生||19410630000000|M|||新安江长运有限公司^新安江长运有限公司<01190>||^^^^^^13858161775|^^^^^^13858161775||O^其他||330182D156000005001AA6B6AB2CC382|330126194106303119|||||||||||||0\n" +
                "PV1|1|I|A31100^^311040^10107&泌尿外科||||293^^孙树文||||||||||44^^吴建民|01|10030754||XJ01||||20150119111732||||0|||||||||||||10107||20150119111148||||||1|V|123\n" +
                "OBX|1|NM|OB01^BODYHEIGHT||70||||||F\n" +
                "OBX|2|NM|OB02^BODYWEIGHT||170||||||FDG1|1|132572||膀胱肿瘤||F\n" +
                "RGS|1\n" +
                "AIS|0||4845^经尿道膀胱癌电切术\n" +
                "AIP|1||292|主刀医生\n" +
                "AIP|2|||洗手护士\n" +
                "AIP|3|||洗手护士\n" +
                "AIP|4|||巡回护士\n" +
                "AIP|5|||巡回护士\n" +
                "AIP|6|||巡回护士\n" +
                "AIP|7|||助理医生\n" +
                "AIP|8|||助理医生\n" +
                "AIP|9|||助理医生\n" +
                "AIP|10|||体外循环师\n" +
                "AIP|11|||麻醉师\n" +
                "AIP|12|||麻醉师\n" +
                "AIP|13|||麻醉师";
        ctx.write(myHL7string);
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("客户端收到内容：{}",msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.error("客户端异常：{}",cause.toString());
        ctx.close();
    }
}
