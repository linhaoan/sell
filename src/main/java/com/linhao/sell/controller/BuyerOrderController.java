package com.linhao.sell.controller;

import com.linhao.sell.Exception.SellException;
import com.linhao.sell.Utils.ResultVOUtil;
import com.linhao.sell.VO.ResultVO;
import com.linhao.sell.conventer.OrderForm2OrderDTOConverter;
import com.linhao.sell.dto.OrderDTO;
import com.linhao.sell.enums.ResultEnum;
import com.linhao.sell.form.OrderForm;
import com.linhao.sell.service.BuyerService;
import com.linhao.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/buyer/order")
@Slf4j
public class BuyerOrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;
    //创建订单
    @RequestMapping(value = "/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PRARM_ERROR,
                    bindingResult.getFieldError().getDefaultMessage());
        }


        OrderDTO orderDto = OrderForm2OrderDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping(value = "/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String buyerOpenid,
                               @RequestParam(value = "page",defaultValue = "0") Integer page,
                               @RequestParam(value = "size",defaultValue = "10") Integer size) {
        if(StringUtils.isEmpty(buyerOpenid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.OPENID_NULL);
        }

        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid, request);

        return ResultVOUtil.success(orderDTOPage.getContent());

    }


    //订单详情
    @GetMapping(value = "/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String buyerOpenid,
                                   @RequestParam("orderId") String orderId) {

        if(StringUtils.isEmpty(buyerOpenid)) {
            log.error("【查询订单详情】openid为空");
            throw new SellException(ResultEnum.OPENID_NULL);
        }

        if(StringUtils.isEmpty(orderId)) {
            log.error("【查询订单详情】orderId为空");
            throw new SellException(ResultEnum.PRARM_ERROR);
        }

        OrderDTO orderDTO = buyerService.findOrderOne(buyerOpenid,orderId);
        return ResultVOUtil.success(orderDTO);
    }


    //取消订单
    @PostMapping(value = "/cancel")
    public ResultVO<OrderDTO> cancel(@RequestParam("openid") String buyerOpenid,
                                     @RequestParam("orderId") String orderId) {

        if(StringUtils.isEmpty(buyerOpenid)) {
            log.error("【取消订单】openid为空");
            throw new SellException(ResultEnum.OPENID_NULL);
        }

        if(StringUtils.isEmpty(orderId)) {
            log.error("【取消订单】orderId为空");
            throw new SellException(ResultEnum.PRARM_ERROR);
        }

        buyerService.cancelOrder(buyerOpenid,orderId);
        return ResultVOUtil.success();
    }

}
