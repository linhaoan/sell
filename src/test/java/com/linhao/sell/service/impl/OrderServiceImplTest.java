package com.linhao.sell.service.impl;

import com.linhao.sell.dataobject.OrderDetail;
import com.linhao.sell.dto.OrderDTO;
import com.linhao.sell.enums.OrderStatusEnum;
import com.linhao.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("林浩");
        orderDTO.setBuyerPhone("18860106760");
        orderDTO.setBuyerOpenid("ew3euwhd7sjw9diwkq");
        orderDTO.setBuyerAddress("杭州市西湖区");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail  o1 = new OrderDetail();
        o1.setProductId("123123");
        o1.setProductQuantity(2);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123123123");
        o2.setProductQuantity(1);
        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        orderService.create(orderDTO);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne("1524201173751722050");
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
        Pageable pageable = new PageRequest(0, 20);
        String buyerOpenid = "ew3euwhd7sjw9diwkq";
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid,pageable);
        Assert.assertNotNull(orderDTOPage);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("1524201173751722050");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne("1524201366040814991");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne("1524201366040814991");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }
}