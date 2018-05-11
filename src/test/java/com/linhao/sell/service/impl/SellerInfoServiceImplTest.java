package com.linhao.sell.service.impl;

import com.linhao.sell.dataobject.SellerInfo;
import com.linhao.sell.service.SellerInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Test
    public void findSellInfoByOpenid() {
        SellerInfo sellerInfo = sellerInfoService.findSellInfoByOpenid("abc");
    }
}