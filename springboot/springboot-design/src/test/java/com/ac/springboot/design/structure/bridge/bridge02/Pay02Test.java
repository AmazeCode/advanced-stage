package com.ac.springboot.design.structure.bridge.bridge02;


import com.ac.springboot.design.structure.bridge.bridge02.impl.PayFaceMode;
import com.ac.springboot.design.structure.bridge.bridge02.impl.PayFingerprintMode;
import com.ac.springboot.design.structure.bridge.bridge02.pay.WxPay;
import com.ac.springboot.design.structure.bridge.bridge02.pay.ZfbPay;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


/**
 * 支付抽象
 * @Author: zhangyadong
 * @Date: 2022/12/14 18:10
 */
@SpringBootTest
public class Pay02Test {

    @Test
    public void pay02Test() {

        System.out.println("测试场景1:微信支付，人脸支付");
        WxPay wxPay = new WxPay(new PayFaceMode());
        wxPay.transfer("wx_0001","1000100",new BigDecimal(100));


        System.out.println("测试场景2:支付宝支付，指纹支付");
        ZfbPay zfbPay = new ZfbPay(new PayFingerprintMode());
        zfbPay.transfer("zfb_0002","20002000",new BigDecimal(200));
    }
}
