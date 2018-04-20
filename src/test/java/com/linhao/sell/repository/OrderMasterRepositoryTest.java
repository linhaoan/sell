package com.linhao.sell.repository;

import com.linhao.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void addTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("林浩");
        orderMaster.setBuyerPhone("13699444842");
        orderMaster.setBuyerAddress("杭州西湖区");
        orderMaster.setBuyerOpenid("123123");
        orderMaster.setOrderAmount(new BigDecimal(20));
        orderMasterRepository.save(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0, 20);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid("123123", pageRequest);
        Assert.assertNotNull(result);
//        orderMasterRepository.findByBuyerOpenid();
    }
}