package com.linhao.sell.service;

import com.linhao.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notifyData(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
