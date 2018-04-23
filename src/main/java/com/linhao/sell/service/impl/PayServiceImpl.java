package com.linhao.sell.service.impl;

import com.linhao.sell.Exception.SellException;
import com.linhao.sell.Utils.JsonUtil;
import com.linhao.sell.Utils.MathUtil;
import com.linhao.sell.dto.OrderDTO;
import com.linhao.sell.enums.ResultEnum;
import com.linhao.sell.service.OrderService;
import com.linhao.sell.service.PayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderName("微信点餐订单");
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("支付结果={}",JsonUtil.toJson(payResponse));
        return  payResponse;
    }

    @Override
    public PayResponse notifyData(String notifyData) {

        // 1.验证签名

        // 2.支付状态

        // 3.支付金额

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信返回】异步通知 payResponse={}",JsonUtil.toJson(payResponse));

        //支付成功修改订单支付状态
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());

        if (orderDTO == null) {
            log.error("【微信支付】异步通知, 订单不存在, orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断金额是否一致(0.10   0.1)
        if (!MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())) {
            log.error("【微信支付】异步通知, 订单金额不一致, orderId={}, 微信通知金额={}, 系统金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //判断订单是否存在


        orderService.paid(orderDTO);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}", JsonUtil.toJson(refundRequest));

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】response={}", JsonUtil.toJson(refundResponse));

        return refundResponse;
    }
}
