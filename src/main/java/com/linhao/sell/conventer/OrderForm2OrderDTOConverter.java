package com.linhao.sell.conventer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linhao.sell.Exception.SellException;
import com.linhao.sell.dataobject.OrderDetail;
import com.linhao.sell.dto.OrderDTO;
import com.linhao.sell.enums.ResultEnum;
import com.linhao.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e) {
            log.error("【对象转换】错误,String = {}",orderForm.getItems());
            throw new SellException(ResultEnum.CONVERTER_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
