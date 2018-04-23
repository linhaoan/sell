package com.linhao.sell.controller;

import com.linhao.sell.Exception.SellException;
import com.linhao.sell.dto.OrderDTO;
import com.linhao.sell.enums.ResultEnum;
import com.linhao.sell.service.OrderService;
import com.linhao.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        //1. 根据订单号查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null ) {
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);

        //2. 发起支付请求
        return new ModelAndView("pay/create");
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        PayResponse payResponse = payService.notifyData(notifyData);

        //返回给微信处理结果

        return new ModelAndView("pay/success");

    }

}
