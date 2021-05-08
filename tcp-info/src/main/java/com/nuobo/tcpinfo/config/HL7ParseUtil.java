package com.nuobo.tcpinfo.config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.List;


/**
 * HL7消息解析工具
 */
public class HL7ParseUtil {

    public static Document receiveAndPushData(String hl7Message) {
        //采用MLLP
        hl7Message = hl7Message.replaceAll("\\u000B", "");
        hl7Message = hl7Message.replaceAll("\\u001C", "");
        hl7Message = hl7Message.trim().replace("\r", "\r\n");
        //1.加载xml
        Document document = HL7ToXmlConverter.ConvertToXmlObject(hl7Message);
        String type = document.selectSingleNode("HL7Message/MSH/MSH.9/MSH.9.1")
                .getText();  //消息类型  SRM手术排班
        String iLX = document.selectSingleNode("HL7Message/MSH/MSH.9/MSH.9.2")
                .getText();  //区分增删改  S01新增
        String text = document.selectSingleNode("HL7Message/ARQ/ARQ.1").getText();  //申请ID
        String text1 = document.selectSingleNode("HL7Message/ARQ/ARQ.5/ARQ.5.1").getText(); //麻醉方式代码
        String text2 = document.selectSingleNode("HL7Message/ARQ/ARQ.5/ARQ.5.2").getText();//麻醉方式名称
        return document;
    }


    public static void main(String[] args) {
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
        Document document = receiveAndPushData(myHL7string);
        String type = document.selectSingleNode("HL7Message/MSH/MSH.9/MSH.9.1")
                .getText();  //消息类型  SRM手术排班
        String iLX = document.selectSingleNode("HL7Message/MSH/MSH.9/MSH.9.2")
                .getText();  //区分增删改  S01新增
        String text = document.selectSingleNode("HL7Message/ARQ/ARQ.1").getText();  //申请ID
        String text1 = document.selectSingleNode("HL7Message/ARQ/ARQ.5/ARQ.5.1").getText(); //麻醉方式代码
        String text2 = document.selectSingleNode("HL7Message/ARQ/ARQ.5/ARQ.5.2").getText();//麻醉方式名称
    }
}