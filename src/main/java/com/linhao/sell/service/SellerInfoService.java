package com.linhao.sell.service;

import com.linhao.sell.dataobject.SellerInfo;

public interface SellerInfoService {

    SellerInfo findSellInfoByOpenid(String openid);
}
