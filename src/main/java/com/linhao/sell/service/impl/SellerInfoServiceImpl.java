package com.linhao.sell.service.impl;

import com.linhao.sell.dataobject.SellerInfo;
import com.linhao.sell.repository.SellerInfoRepository;
import com.linhao.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellInfoByOpenid(String openid) {

        return sellerInfoRepository.findByOpenid(openid);
    }
}
