package com.linhao.sell.repository;

import com.linhao.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456789");
        orderDetail.setOrderId("123456");
        orderDetail.setProductId("123123");
        orderDetail.setProductName("皮蛋瘦肉粥大份");
        orderDetail.setProductPrice(new BigDecimal(11.9));
        orderDetail.setProductQuantity(2);
        orderDetail.setProductIcon("http://xxxxx.jpg\"");
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("1234567");
        Assert.assertNotNull(orderDetailList);

    }
}